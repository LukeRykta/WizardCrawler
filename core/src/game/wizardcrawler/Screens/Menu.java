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
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.WizardCrawlerApp;

public class Menu implements Screen {
    private Stage stage;
    private Game game;
    private Viewport viewport;
    public static float mastervol = .08f;
    private Music gamemusic;

    public Menu(Game game) {
        this.game = game;

        viewport = new FitViewport(WizardCrawlerApp.V_WIDTH, WizardCrawlerApp.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((WizardCrawlerApp) game).batch);

        Table testTable = new Table();
        testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Backgrounds/main-menu2.jpg"))));
        testTable.setFillParent(true);
        testTable.setDebug(true);
        stage.addActor(testTable);

        gamemusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/menuMusic.mp3"));
        gamemusic.setLooping(true);
        gamemusic.setVolume(mastervol);
        gamemusic.play();

    }

    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            System.exit(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            gamemusic.stop();
            gamemusic.dispose();
            game.setScreen(new Play((WizardCrawlerApp) game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
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
        game.dispose();
    }
}
