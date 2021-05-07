package game.wizardcrawler.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.WizardCrawlerApp;
import javafx.scene.control.Labeled;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    public Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label countdownLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 100;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(WizardCrawlerApp.V_WIDTH + 200, WizardCrawlerApp.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%02d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label wizzyLabel = new Label("WIZZY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label worldLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(wizzyLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }


    //keeps track of time count and subtracts 1 from world timer per second
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {

    }
}
