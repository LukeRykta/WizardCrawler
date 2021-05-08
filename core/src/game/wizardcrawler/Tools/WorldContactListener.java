package game.wizardcrawler.Tools;

import com.badlogic.gdx.physics.box2d.*;
import game.wizardcrawler.AI.goldWizard;
import game.wizardcrawler.Sprites.Enemy;
import game.wizardcrawler.Sprites.Wizard;
import game.wizardcrawler.WizardCrawlerApp;
import game.wizardcrawler.Sprites.InteractiveTileObject;

public class  WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) { //when two figures begin to collide
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
        switch (cDef){
            case WizardCrawlerApp.ENEMY_HEAD_BIT | WizardCrawlerApp.WIZARD_BIT:
                if(fixA.getFilterData().categoryBits == WizardCrawlerApp.ENEMY_HEAD_BIT)
                    //((goldWizard)fixA.setUserData()).hitOnHead();
                    System.out.print("");
                else if(fixB.getFilterData().categoryBits == WizardCrawlerApp.ENEMY_HEAD_BIT)
                   // ((goldWizard)fixA.setUserData()).hitOnHead();
                    System.out.print("");

        }
    }

    @Override
    public void endContact(Contact contact) { //when two figures disconnect from each other
        WizardCrawlerApp.inRange = false;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { //once something has collided, can change the characteristics of object

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { //results of collision (angles, etc)

    }
}
