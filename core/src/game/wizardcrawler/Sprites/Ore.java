package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        WizardCrawlerApp.inRange = true;
        Gdx.app.log("Ore", "Collision detected");
        getCell().setTile(null);
        setCategoryFilter(WizardCrawlerApp.ACCESSED_BIT);
        WizardCrawlerApp.manager.get("Audio/Sounds/pickaxe.mp3", Sound.class).play();
        Hud.addScore(1000);
    }

    @Override
    public void duringContact() {
        Gdx.app.log("Ore", "Collision2 detected");
    }
}
