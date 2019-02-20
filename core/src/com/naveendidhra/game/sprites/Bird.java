package com.naveendidhra.game.sprites;

//needs to know the position of the bird in our game
//needs to know the texture to be drawn to the screen
//needs to know the velocity and the direction of the bird (up/down/left/right)

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int GRAVITY = -15; // for bird
    private static final int MOVEMENT =100;
    private Vector3 position; //holds the x and y axis (because its only a 2-D game)
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    private Texture bird;

    public Bird(int x,int y){ // for the starting positions
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0); // because we are not moving the bird
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f);
        bounds = new Rectangle(x,y,texture.getWidth() / 3,texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        if (dt >0) {
            birdAnimation.update(dt);
            if (position.y > 0)
                velocity.add(0, GRAVITY, 0);
            velocity.scl(dt);
            position.add(MOVEMENT * dt, velocity.y, 0);
            if (position.y < 0)
                position.y = 0;
            velocity.scl(1 / dt);
            bounds.setPosition(position.x, position.y);

        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y=250;
        flap.play();
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }

}
