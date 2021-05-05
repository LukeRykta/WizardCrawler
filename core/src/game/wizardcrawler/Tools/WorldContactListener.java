package game.wizardcrawler.Tools;

import com.badlogic.gdx.physics.box2d.*;
import game.wizardcrawler.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) { //when two figures begin to collide
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) { //when two figures disconnect from each other
        WorldContactListener.inRange = false;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { //once something has collided, can change the characteristics of object

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { //results of collision (angles, etc)

    }
}
