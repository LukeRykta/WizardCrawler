package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.Screens.GameOver;
import game.wizardcrawler.Screens.Menu;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

public class Trap extends InteractiveTileObject{
    public Trap(Play screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter((WizardCrawlerApp.ORE_BIT));
    }

    @Override
    public void onHeadHit() {
        WizardCrawlerApp.inRange = true;
        Gdx.app.log("Trap", "Collision detected");
        WizardCrawlerApp.manager.get("Audio/Sounds/death.mp3", Sound.class).play();
        Wizard.wizardIsDead = true;
    }

    @Override
    public void duringContact() {

    }
}
