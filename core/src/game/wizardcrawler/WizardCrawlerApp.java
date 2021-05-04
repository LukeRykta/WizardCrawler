package game.wizardcrawler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import game.wizardcrawler.Tools.KeyController;

public class WizardCrawlerApp extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;

	// Pixels per meter: use this float (divisional reasons) to scale everything that has pixel size (positional locations, viewports, character models)
	public static final float PPM = 100;

	// Update these to true objects
	public static final short GROUND_BIT = 1;
	public static final short WIZARD_BIT = 2;
	public static final short FURNITURE_BIT = 4;
	public static final short WALL_BIT = 8;
	public static final short ACCESSED_BIT = 16;

	public SpriteBatch batch;

	public static Texture background;
	public static TextureRegion backgroundRegion;

	public KeyController controller;

	/* NOTE: using AssetManager in a static way can cause issues. We may want to pass around Assetmanager to
	the classes that need it. It is used in a static context here temporarily */
	public static AssetManager manager;
	
	@Override
	public void create () {
		controller = new KeyController();
		Gdx.input.setInputProcessor(controller);

		manager = new AssetManager();
		batch = new SpriteBatch();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 0, 1);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
