package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

public class Ore extends InteractiveTileObject{

    public Ore(Play screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter((WizardCrawlerApp.ORE_BIT));
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Ore", "Collision detected");
        WizardCrawlerApp.inRange = true;
        Hud.addScore(200);
        //WizardCrawlerApp.manager.get("audio/sounds/pickaxe.wav", Sound.class).play();
        setCategoryFilter(WizardCrawlerApp.ACCESSED_BIT);
    }
}
