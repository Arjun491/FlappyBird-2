package com.naveendidhra.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.naveendidhra.game.FlappyBird;

public class GameOver extends State {
  private Texture background;
  private Texture go;
  public GameOver(GameStateManager gsm) {
    super(gsm);
    cam.setToOrtho(false,FlappyBird.WIDTH/2,FlappyBird.HEIGHT/2);
    background = new Texture("bg.png");
    go = new Texture("go.png");
  }

  @Override
  public void handleInput() {
    if(Gdx.input.justTouched()){
      gsm.set(new MenuState(gsm));

    }
  }

  @Override
  public void update(float dt) {
    handleInput();
  }

  @Override
  public void render(SpriteBatch sb) {
    sb.setProjectionMatrix(cam.combined);
    sb.begin(); // to open the container
    sb.draw(background,0,0);
    sb.draw(go,cam.position.x-go.getWidth()/2,cam.position.y);
    sb.end();
  }

  @Override
  public void dispose() {
    background.dispose();
    go.dispose();
  }
}
