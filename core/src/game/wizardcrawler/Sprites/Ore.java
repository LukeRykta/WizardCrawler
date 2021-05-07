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
        if (Gdx.input.isKeyPressed(Input.Keys.E) && WizardCrawlerApp.inRange) {
            getCell().setTile(null);
            setCategoryFilter(WizardCrawlerApp.ACCESSED_BIT);
            Hud.addScore(200);
        }
        //WizardCrawlerApp.manager.get("audio/sounds/pickaxe.wav", Sound.class).play();
    }
}
