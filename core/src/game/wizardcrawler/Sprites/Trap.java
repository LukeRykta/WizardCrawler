package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;
import game.wizardcrawler.Sprites.Wizard;

public class Trap extends InteractiveTileObject{
    public Trap(Play screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter((WizardCrawlerApp.TRAP_BIT));
    }

    @Override
    public void onHeadHit() {
        WizardCrawlerApp.inRange = true;
        Gdx.app.log("Trap", "Collision detected");
        WizardCrawlerApp.isDead = true;
    }

    @Override
    public void duringContact() {

    }

}
