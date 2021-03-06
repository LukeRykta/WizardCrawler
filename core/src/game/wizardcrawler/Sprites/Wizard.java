package game.wizardcrawler.Sprites;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

import javax.xml.soap.Text;

public class Wizard extends Sprite {
    public boolean isRunningRight() {
        return runningRight;
    }
    public void setRunningRight(boolean runningRight) {
        this.runningRight = runningRight;
    }

    public enum State {STANDING, RUNNING, JUMPING, FALLING, DEAD, SHOOTING}
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 2, gravity = 60 * 1.8f;
    private TextureRegion wizardStand;
    private TextureRegion wizardShoot;
    private TextureRegion wizardDead;
    private Animation<TextureRegion> wizardFall;
    private Animation<TextureRegion> wizardRun;
    private Animation<TextureRegion> wizardJump;
    // tracks time between states
    private float stateTimer;

    private boolean runningRight;
    public static boolean wizardIsDead;

    private Play screen;

    public Wizard(Play screen){
        super(screen.getAtlas().findRegion("isowizard"));
        this.screen = screen;
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        // creates an array of texture regions to pass the constructor for the animations
        Array<TextureRegion> frames = new Array<>();
        for(int i = 1; i < 8; i++)
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        wizardRun = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 1; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), i * 32, 34, 32, 32));
        wizardJump = new Animation(0.1f, frames);
        wizardFall = new Animation(0.1f, frames);

        wizardStand = new TextureRegion(getTexture(), 0, 0, 32, 32);
        wizardDead = new TextureRegion(getTexture(), 256, 32, 32, 32);
        wizardShoot = new TextureRegion(getTexture(), 64, 32, 32, 32);


        defineWizard();
        setBounds(0, 0, 32 / WizardCrawlerApp.PPM, 32 / WizardCrawlerApp.PPM);
        setRegion(wizardStand);
    }

    public void update(float dt){

        if (screen.getHud().isTimeUp() && !isDead()) {
            die();
        }

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        //returns the appropriate frame that will display the sprite's texture region
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case DEAD:
                region = wizardDead;
            case JUMPING:
            case FALLING:
                region = wizardJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = wizardRun.getKeyFrame(stateTimer, true); // this is a looping animation, if returns to end, will return to first frame
                break;
            case STANDING:
            default:
                region = wizardStand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        //if our currentstate != previousstate we need to reset the timer
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public void die() {
        if (!isDead()) {
            WizardCrawlerApp.manager.get("Audio/Music/gameMusic.mp3", Music.class).stop();
            WizardCrawlerApp.manager.get("Audio/Sounds/death.mp3", Sound.class).play();
            wizardIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = WizardCrawlerApp.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }

            //b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    public State getState(){
        if(wizardIsDead)
            return State.DEAD;
        else if ((b2body.getLinearVelocity().y > 0) || (b2body.getLinearVelocity().y < 0 && previousState == State.STANDING)) // wizard moving upwards? stand
            return State.JUMPING;
        else if (b2body.getLinearVelocity().y < 0) // wizard going down? fall animation
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0 || b2body.getLinearVelocity().y != 0) // wizard not at a state of rest? run animation
            return State.RUNNING;
        else                                        // wizard doing something else? = stand animation
            return State.STANDING;
    }

    public boolean gameOverReached(){
        return true;
        // FIXME: 5/4/2021  add condition for game over being reached and return
    }

    public boolean isDead(){
        return wizardIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }

    //Wizard's body and fixture definitions
    public void defineWizard(){
        BodyDef bdef = new BodyDef();

        //test start position on map
        // FIXME: 5/4/2021 change for spawn location
        bdef.position.set(64 / WizardCrawlerApp.PPM, 480 / WizardCrawlerApp.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        //Now that the wizard's defined, we are able create in our world
        b2body = world.createBody(bdef);

        //defining wizard's body properties
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(14 / WizardCrawlerApp.PPM);

        fdef.filter.categoryBits = WizardCrawlerApp.WIZARD_BIT;
        //these are the things wizard can collide with
        fdef.filter.maskBits =
                  WizardCrawlerApp.GROUND_BIT
                | WizardCrawlerApp.ORE_BIT
                | WizardCrawlerApp.COIN_BIT
                | WizardCrawlerApp.DEFAULT_BIT
                | WizardCrawlerApp.ENEMY_HEAD_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        //where?
        head.set(new Vector2(-2 / WizardCrawlerApp.PPM, 8 / WizardCrawlerApp.PPM), new Vector2(2 / WizardCrawlerApp.PPM, 12 / WizardCrawlerApp.PPM));

        fdef.shape = head;
        // when you create a fixture definition that is a sensor, it no longer collides with anything in the world, it is just available for querying user data
        fdef.isSensor = true;

        // this will uniquely identify this head fixture as "head" so we can pull this in the future to see if this fixture is wizard's head
        b2body.createFixture(fdef).setUserData("head");

    }
}
