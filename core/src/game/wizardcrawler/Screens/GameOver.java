package game.wizardcrawler.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.WizardCrawlerApp;

import static game.wizardcrawler.Scenes.Hud.score;

public class GameOver implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;
    private Label outputLabel;
    private Music overMusic;
    public static float mastervol = .08f;

    public GameOver(Game game){
        this.game = game;
        viewport = new StretchViewport(WizardCrawlerApp.V_WIDTH, WizardCrawlerApp.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((WizardCrawlerApp) game).batch);

        /*overMusic = WizardCrawlerApp.manager.get("Audio/Sounds/gameover.mp3", Music.class);
        overMusic.setLooping(true);
        overMusic.setVolume(mastervol);
        overMusic.play();
        */

        Table testTable = new Table();
        testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Backgrounds/game-over.jpg"))));
        testTable.setFillParent(true);
        stage.addActor(testTable);

        if (score == null) {
            score = 0;
        }
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label gameOverLabel = new Label("Score: " + score, font);
        testTable.top();
        testTable.add(gameOverLabel);

    }

    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            overMusic.stop();
            System.exit(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            overMusic.stop();
            game.setScreen(new Play((WizardCrawlerApp) game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0,0,0,1);
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
        overMusic.dispose();
    }
}
