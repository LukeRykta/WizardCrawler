package game.wizardcrawler.AI;

import com.badlogic.gdx.math.Rectangle;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.Sprites.InteractiveTileObject;

public class Health extends InteractiveTileObject {

    public Health(Play screen, Rectangle bounds) {
        super(screen, bounds);
    }

    @Override
    public void onHeadHit() {

    }
    // TODO Look for collision then decrease hp


}
