package game.wizardcrawler.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.Sprites.InteractiveTileObject;



public class Attack extends InteractiveTileObject {

    // TODO Draw ball at 288, 32, 32, 32

    public Attack(Play screen, Rectangle bounds){
        // TODO Fireball animation
        super(screen, bounds);
        fixture.setUserData(this);

    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Attack", "Collision");
    }
}