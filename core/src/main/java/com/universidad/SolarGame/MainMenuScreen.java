package com.universidad.SolarGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {
    final SolarGame game;
    private Stage stage;
    private Skin skin;
    private SpriteBatch spriteBatch;
    private Texture menuTexture;   

    public MainMenuScreen(final SolarGame game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        menuTexture = new Texture("IMG/menu_background.jpg");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));

        TextButton playButton = new TextButton("Jugar", skin);
        TextButton optionsButton = new TextButton("Opciones", skin);
        TextButton exitButton = new TextButton("Salir", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
                dispose();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            Gdx.app.exit();
           }
        });

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        table.add(playButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(optionsButton).fillX().uniformX();
        table.row();
        table.add(exitButton).fillX().uniformX();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        
        float textureWidth = menuTexture.getWidth();
        float textureHeight = menuTexture.getHeight();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float x = (screenWidth - textureWidth) / 2;
        float y = (screenHeight - textureHeight) / 2;

        spriteBatch.draw(menuTexture, x, y);

        spriteBatch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        // Lógica opcional cuando se muestra la pantalla
    }

    @Override
    public void hide() {
        // Lógica opcional cuando se oculta la pantalla
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        menuTexture.dispose();
    }
}
