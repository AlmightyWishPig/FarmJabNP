//Assessment 4 START
package com.dicycat.kroy.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dicycat.kroy.Kroy;
import java.util.Random;


public class Powerups extends Entity{

    private String type;
    private Boolean exists;
    private float timer;
    private int respawnTimer;
    public Powerups(Vector2 pos){
        super(pos,  new Texture("speed.png"), new Vector2(20,20), 1000000, 25);
        this.setType();
        this.setRespawnTimer();
        this.exists = true;
    }

    //Checks if has collided with the player, if so calls the powerup method for the player
    private Boolean collision () {
        if (playerInRadius()) {
            Kroy.mainGameScreen.getPlayer().powerup(type);
            this.exists = false;
            this.setTexture(new Texture ("blank.png"));
            this.timer = 0;
            return true;
        } else {
            return false;
        }
    }


    //Sets the type of power up
    private void setType() {
        Random r = new Random();
        int typeNumber = r.nextInt(4);
        switch(typeNumber){
            case 0:
                this.type = "speed";
                this.setTexture(new Texture("speed.png"));
                break;
            case 1:
                this.type = "damage";
                this.setTexture(new Texture("damage.png"));
                break;
            case 2:
                this.type = "shield";
                this.setTexture(new Texture("shield.png"));
                break;
            case 3:
                this.type = "refill";
                this.setTexture(new Texture("refill.png"));
                break;
        }
    }

    //Checks if the entity needs respawning
    private void checkspawn(float delta) {
        if (!this.exists){
            timer += delta;
            if (timer >= respawnTimer){
                this.respawn();
            }
        } else {
            timer = 0;
        }
    }

    //Sets the amount of time between collection and respawning
    private void setRespawnTimer(){
        Random r = new Random();
        this.respawnTimer = r.nextInt(10) + 15;
    }


    //Respawns the power up
    private void respawn() {
        this.setType();
        this.setRespawnTimer();
        this.exists = true;
    }

    //Call this function
    public void update(float delta) {
        this.checkspawn(delta);
        if (this.exists) {
            this.collision();
        }
    }

    public boolean getExists(){
        return this.exists;
    }
}
//Assessment 4 END