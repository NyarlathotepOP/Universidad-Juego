package com.universidad.SolarGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private SolarGame game;
    private Texture shipTexture;
    private SpriteBatch spriteBatch;
    private float shipX, shipY;
    private float shipSpeed = 400;

    public GameScreen(SolarGame game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        shipTexture = new Texture(Gdx.files.internal("IMG/player_ship.png"));
        shipX = Gdx.graphics.getWidth() / 2 - shipTexture.getWidth() / 2;
        shipY = 50;
    }

    private void updateShipPosition(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            shipX -= shipSpeed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            shipX += shipSpeed * delta;
        }
        if (shipX < 0) {
            shipX = 0;
        }
        if (shipX > Gdx.graphics.getWidth() - shipTexture.getWidth()) {
            shipX = Gdx.graphics.getWidth() - shipTexture.getWidth();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateShipPosition(delta);

        spriteBatch.begin();
        spriteBatch.draw(shipTexture, shipX, shipY);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shipTexture.dispose();
    }
}
