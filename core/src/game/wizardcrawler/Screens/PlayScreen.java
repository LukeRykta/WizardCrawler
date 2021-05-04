package game.wizardcrawler.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.WizardCrawlerApp;

public class PlayScreen implements Screen {
    private WizardCrawlerApp game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr; //graphical representation of our fixtures and bodies within our 2d world

    //Realizes wizard character
    private Wizard player;

    //sets master volume
    private Music gamemusic;
    public static float mastervol = .08f;

    public PlayScreen(WizardCrawlerApp game){
        this.game = game;
        //create cam used to follow wizard through cam world
        createCamera();

        hud = new Hud(game.batch);
        maploader = new TmxMapLoader();

    }

    private void createCamera(){
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(WizardCrawlerApp.V_WIDTH * 2 / WizardCrawlerApp.PPM, WizardCrawlerApp.V_HEIGHT * 2 / WizardCrawlerApp.PPM, gamecam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
