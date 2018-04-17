package com.lpoortal.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ButtonActor extends Actor {
    Sprite sprite;
    Point position, size;
    public ButtonActor (Point position, Point size, String source) {
        this.position = position;
        this.size = size;
        this.sprite = new Sprite(new Texture(Gdx.files.internal(source)));
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        sprite.setBounds(position.getX(), position.getY(), size.getX(), size.getY());
        sprite.draw(batch);
    }

}
