package game.wizardcrawler.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.Sprites.Wizard;
import game.wizardcrawler.Tools.WorldContactListener;
import game.wizardcrawler.Tools.WorldCreator;
import game.wizardcrawler.WizardCrawlerApp;

public class Play implements Screen {
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

    public Play(WizardCrawlerApp game){

        atlas = new TextureAtlas("WizardGFX/wizard.pack");
        this.game = game;
        //create cam used to follow wizard through cam world
        createCamera();

        hud = new Hud(game.batch);

        //Load our map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("Maps/wizardMap1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / WizardCrawlerApp.PPM);

        //initially set our gamecam to be centered correctly at the start of the game
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //doSleep = true means bodies at rest won't get physics calculations.. saves time processing
        world = new World(new Vector2(0, -10), false);

        //creates green lines to debug boundaries
        b2dr = new Box2DDebugRenderer();

        world.setContactListener(new WorldContactListener());
        new WorldCreator(this);

        player = new Wizard(this);

        //plays music on start
        Music music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/gameMusic.mp3"));
        music.setLooping(true);
        music.setVolume(mastervol);
        music.play();

        //gamemusic = WizardCrawlerApp.manager.get("audio/music/virusmusic.mp3", Music.class);
        //gamemusic.setLooping(true);
        //gamemusic.setVolume(mastervol);
        //gamemusic.play();
    }

    private void createCamera(){
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(WizardCrawlerApp.V_WIDTH * 2 / WizardCrawlerApp.PPM, WizardCrawlerApp.V_HEIGHT * 2 / WizardCrawlerApp.PPM, gamecam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && player.b2body.getLinearVelocity().y == 0)
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            System.exit(0);
        }
    }

        // FIXME: 3/27/2021 Pause menu implementation?
        /*
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))){
                game.setScreen(new PauseMenu(game));
        }
         */


    public void update(float dt){
        handleInput(dt);
        //for box2d to execute our physics simulation, we must tell it how many times to calculate per second
        //velocity and position affect how two bodies interact during a collision: higher numbers = longer but more precise
        //timeStep = 1/60 = 60 times per second
        world.step(1/60f, 6, 2);

        player.update(dt);
        hud.update(dt);

        if(hud.worldTimer <= 0) {
            System.out.println("GAME OVER!");
            //gamemusic.stop();
            game.setScreen(new GameOver(game));
        }
        //attach our gamecam to our players.x coordinate
        gamecam.position.x = player.getX();
        gamecam.position.y = player.getY();

        //update our gamecam with correct coordinates after changes
        gamecam.update();
        //tell our renderer to draw only what our camera can see in our game world
        renderer.setView(gamecam);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines (green lines around objects represent collision areas) (also: gamecam.combined is the projection matrix)
        //b2dr.render(world, gamecam.combined);

        //main cam when running through the game
        game.batch.setProjectionMatrix(gamecam.combined);
        //open box to put all textures we want inside
        game.batch.begin();
        //giving sprite game batch to be drawn
        player.draw(game.batch);

        game.batch.end();

        //Set our batch to now draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        player.getTexture().dispose();
    }
}
