package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

public class CoinCollisionHandler extends InteractiveTileObject{

    public CoinCollisionHandler(Play screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter((WizardCrawlerApp.COIN_BIT));
    }
    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision detected");
        getCell().setTile(null);
        setCategoryFilter(WizardCrawlerApp.ACCESSED_BIT);
        Hud.addScore(100);
        WizardCrawlerApp.manager.get("Audio/Sounds/pickaxe.mp3", Sound.class).play();
    }

    @Override
    public void duringContact() {

    }
}