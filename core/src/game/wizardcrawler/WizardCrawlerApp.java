package game.wizardcrawler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import game.wizardcrawler.Screens.GameOver;
import game.wizardcrawler.Screens.Menu;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.Tools.KeyController;

public class WizardCrawlerApp extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;

	// Pixels per meter: use this float (divisional reasons) to scale everything that has pixel size (positional locations, viewports, character models)
	public static final float PPM = 100;

	// Update these to true objects
	public static final short DEFAULT_BIT = 1;
	public static final short GROUND_BIT = 2;
	public static final short WIZARD_BIT = 4;
	public static final short ORE_BIT = 8;
	public static final short ACCESSED_BIT = 16;

	public SpriteBatch batch;
	public static boolean inRange = false;

	public static Texture background;
	public static TextureRegion backgroundRegion;

	public KeyController controller;

	/* NOTE: using AssetManager in a static way can cause issues. We may want to pass around Assetmanager to
	the classes that need it. It is used in a static context here temporarily */
	public static AssetManager manager;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	@Override
	public void create () {
		controller = new KeyController();
		Gdx.input.setInputProcessor(controller);

		manager = new AssetManager();
		//manager.load("audio/sounds/pickaxe.wav", Sound.class);
		//this boxes all assets for the time being
		manager.finishLoading();

		batch = new SpriteBatch();
		//setScreen(new Play(this));
		setScreen(new Menu(this));
	}

	@Override
	public void render () {
		super.render();
		manager.update();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		manager.dispose();
		batch.dispose();
		background.dispose();
	}
}
