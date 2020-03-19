package com.dicycat.kroy.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dicycat.kroy.Kroy;


public class Powerups extends Entity{

    public Powerups(Vector2 Pos, Texture img){
        super(Pos, img, new Vector2(8,8), 1, 10);

    }
}
