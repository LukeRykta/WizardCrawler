package game.wizardcrawler.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.wizardcrawler.WizardCrawlerApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		// starts application in fullscreen mode
		Graphics.DisplayMode desktopMode = LwjglApplicationConfiguration.getDesktopDisplayMode();

		// creates new instance of configuration
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// sets title of window
		config.title = "Wizard Crawler";

		// pushes fullscreen configuration
		config.setFromDisplayMode(desktopMode);

		// runs app with config settings
		new LwjglApplication(new WizardCrawlerApp(), config);
	}
}
