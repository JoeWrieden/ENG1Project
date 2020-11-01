package com.dragonboatrace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.*;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.PlayerBoat;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.ScrollingBackground;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DragonBoatRace extends ApplicationAdapter {
	
	SpriteBatch batch;
	//Hitbox screen;
	Boat boat;
	ScrollingBackground background;
	BitmapFont font;
	Race race;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//screen = new Hitbox(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
		boat = new PlayerBoat(new Vector2(Gdx.graphics.getWidth()/4, 20), BoatType.FAST, "circle.png", new Lane(new Vector2(), Gdx.graphics.getWidth() / 2));
		font = new BitmapFont(Gdx.files.internal("default.fnt"),false);
		race = new Race(boat);
		font.setColor(Color.RED);
		font.getData().setScale(3);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float dt = Gdx.graphics.getDeltaTime();

		batch.begin();
		boat.update(dt);
		boat.render(batch);
		font.draw(batch, "Health: "+Integer.toString((int)boat.getHealth()), 0, Gdx.graphics.getHeight());
		race.checkWinner(batch);
		batch.end();
		if (boat.getHealth() <= 0){
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);

	}
	
	@Override
	public void dispose () {
		font.dispose();
		batch.dispose();
	}

}
