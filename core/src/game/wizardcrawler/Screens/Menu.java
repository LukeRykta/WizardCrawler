package game.wizardcrawler.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.WizardCrawlerApp;

public class Menu extends ScreenAdapter {
    WizardCrawlerApp game;
    private Stage stage;
    OrthographicCamera guiCam;
    private Viewport viewport;
    public static float mastervol = .08f;
    private Music menumusic;

    public Menu(WizardCrawlerApp game) {
        this.game = game;
        //guiCam = new OrthographicCamera(WizardCrawlerApp.V_WIDTH, WizardCrawlerApp.V_HEIGHT);
        //guiCam.position.set(WizardCrawlerApp.V_WIDTH / 2, WizardCrawlerApp.V_HEIGHT / 2, 0);

        viewport = new StretchViewport(WizardCrawlerApp.V_WIDTH, WizardCrawlerApp.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        Table testTable = new Table();
        testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Backgrounds/main-menu.jpg"))));
        testTable.setFillParent(true);
        stage.addActor(testTable);

        menumusic = WizardCrawlerApp.manager.get("Sounds/menuMusic.mp3", Music.class);
        menumusic.setLooping(true);
        menumusic.setVolume(mastervol);
        menumusic.play();
    }

    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            System.exit(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            menumusic.stop();
            game.setScreen(new Play(game));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new Description(game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
        stage.dispose();
        menumusic.dispose();
        game.dispose();
    }
}
