package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Scenes.Hud;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

import static game.wizardcrawler.Screens.Play.mastervol;

public class Mushroom extends InteractiveTileObject{
    public Mushroom(Play screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter((WizardCrawlerApp.COIN_BIT));
    }

    @Override
    public void onHeadHit() {
        WizardCrawlerApp.inRange = true;
        Sound mushroom = Gdx.audio.newSound(Gdx.files.internal("Audio/Sounds/mushroom.mp3"));
        mushroom.play(0.2f);
        Gdx.app.log("Mushroom", "Collision detected");
        getCell().setTile(null);
        setCategoryFilter(WizardCrawlerApp.ACCESSED_BIT);
        Hud.addScore(250);
    }

    @Override
    public void duringContact() {

    }
}
