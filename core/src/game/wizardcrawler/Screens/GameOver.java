package game.wizardcrawler.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.WizardCrawlerApp;

public class GameOver implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;
    private Label outputLabel;

    public GameOver(Game game){
        this.game = game;
        viewport = new FitViewport(WizardCrawlerApp.V_WIDTH, WizardCrawlerApp.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((WizardCrawlerApp) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        table.add(gameOverLabel).expandX();

        stage.addActor(table);

        Label.LabelStyle fontSpace = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table tableTwo = new Table();
        tableTwo.bottom();
        tableTwo.setFillParent(true);

        Label gameOverSpace = new Label("Press Space to Exit", fontSpace);
        tableTwo.add(gameOverSpace).expandX();

        stage.addActor(tableTwo);

        Label.LabelStyle fontEnter = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table tableThree = new Table();
        tableThree.top();
        tableThree.setFillParent(true);

        Label gameOverEnter = new Label("Press Enter to Restart", fontEnter);
        tableThree.add(gameOverEnter).expandX();

        stage.addActor(tableThree);

    }

    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            System.exit(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
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
