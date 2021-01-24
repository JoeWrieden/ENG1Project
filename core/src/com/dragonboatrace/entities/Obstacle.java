package com.dragonboatrace.entities;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.tools.PowerupEffect;
import com.dragonboatrace.tools.VectorFactory;

/**
 * Represents an Obstacle.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class Obstacle extends Entity {

    /**
     * The speed of the obstacle.
     */
    private final float speed;
    /**
     * The damage the obstacle will deal when colliding with a player.
     */
    private final PowerupEffect effect;

    private final ObstacleType type;

    /**
     * Creates a new Obstacle of a specific type and bounds in which it can be created.
     *
     * @param type   The type of obstacle.
     * @param laneLeftBound The starting x value the obstacle can be created in.
     * @param laneWidth  How far from startX the obstacle can be created.
     */
    public Obstacle(ObstacleType type, float laneLeftBound, int laneWidth) {
        /* Entity creation */
        /* Form of Entity(Vector2 pos, Vector2 vel, EntityType type, String texture) */
        super(VectorFactory.randomPosition(EntityType.OBSTACLE, laneLeftBound, laneWidth),
                new Vector2(),
                EntityType.OBSTACLE,
                type.getTexture());

        this.speed = type.getSpeed();
        this.effect = type.getEffect();
        this.type = type;

    }

    /**
     * Update the obstacle's position relative to the time passed since last frame and the velocity of the boat in that lane.
     *
     * @param deltaTime The time since last frame.
     * @param velY      The y-velocity of the boat in the same lane as the obstacle.
     */
    public void update(float deltaTime, float velY) {
        this.position.add(0, -1 * (velY + this.speed) * deltaTime);
        this.hitbox.move(this.position.x, this.position.y);
    }

    /**
     * Get the obstacles speed attribute, not the velocity it is moving at currently.
     *
     * @return A float of the obstacles speed attribute.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Invoke the effect of the collidable on the boat.
     */
    public void takeEffect(Boat boat) {
        effect.invoke(boat);
    }

    /**
     * Is the object a powerup or an obstacle
     */
    public boolean isPowerup(){
        switch (type){
            case INVULN:
            case SPEEDUP:
            case LESSDAMAGE:
            case LESSTIME:
            case HEAL:
                return false;
            default:
                return true;
        }
    }
    /**
     * The position of the obstacle.
     *
     * @return A Vector2 of the position of the obstacle.
     */
    public Vector2 getPos() {
        return this.position;
    }

}
