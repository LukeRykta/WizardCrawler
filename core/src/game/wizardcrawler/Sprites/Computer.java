package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

public class Computer extends InteractiveTileObject{

    public Computer(Play screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        //setCategoryFilter(WizardCrawlerApp.COMPUTER_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Computer", "Collision");
        WizardCrawlerApp.inRange = true;
        //setCategoryFilter(WarLock.ACCESSED_BIT);
    }
}
