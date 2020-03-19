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
    public Powerups(Vector2 Pos, Texture img){
        super(Pos, img, new Vector2(8,8), 1, 8);
        this.setType();
        this.setRespawnTimer();
        this.exists = true;
    }

    //Checks if has collided with the player, if so calls the powerup method for the player
    private Boolean collision () {
        if (playerInRadius()) {
            this.exists = false;
            this.timer = 0;
            return true;
        } else {
            return false;
        }
    }


    //Sets the type of power up
    private void setType() {
        Random r = new Random();
        int typeNumber = r.nextInt(3);
        switch(typeNumber){
            case 0:
                this.type = "speed";
            case 1:
                this.type = "damage";
            case 2:
                this.type = "shield";
            case 3:
                this.type = "refill";
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
        this.respawnTimer = r.nextInt(10) + 27;
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
        if (this.collision()) {
            Kroy.mainGameScreen.getPlayer().powerup(type);
        }
    }
}
