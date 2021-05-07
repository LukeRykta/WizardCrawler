package game.wizardcrawler.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

import javax.xml.soap.Text;

public class Coin extends Sprite {
    public enum State {APPEAR, DISAPPEAR};
    public Coin.State currentState;
    public Coin.State previousState;
    public World world;
    public Body b2body;

    private Animation<TextureRegion> coinStandard;
    private Animation<TextureRegion> coinGrab;

    private float stateTimer;


    public Coin(Play screen){
        super(screen.getAtlas().findRegion("coin2"));
        this.world = screen.getWorld();
        currentState = Coin.State.APPEAR;
        previousState = Coin.State.APPEAR;
        stateTimer = 0;

        // creates an array of texture regions to pass the constructor for the animations
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i*6, 0, 6, 8));
        coinStandard = new Animation(0.1f, frames);

        for(int i = 0; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i*16, 0, 6, 16));
        coinGrab = new Animation(0.1f, frames);
        frames.clear();

        defineCoin();
        //setBounds(0, 0, 32 / WizardCrawlerApp.PPM, 32 / WizardCrawlerApp.PPM);
        //idk how to set bounds
        //setRegion(coinStandard);
    }


    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        //returns the appropriate frame that will display the sprite's texture region
        setRegion(getFrame(dt));
    }


    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case DISAPPEAR:
                region = coinGrab.getKeyFrame(stateTimer); //run the disappear animation
                break;
            default:
                region = coinStandard.getKeyFrame(stateTimer,true); // this is a looping animation, if returns to end, will return to first frame
                break;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if (b2body.getLinearVelocity().x != 0 || b2body.getLinearVelocity().y != 0) // if the coin is touched, run the animation
            return State.DISAPPEAR;
        else
            return State.APPEAR;
    }

    //Coin's body and fixture definitions
    public void defineCoin(){
        BodyDef bdef = new BodyDef();

        Rectangle rect = ((RectangleMapObject) object).getRectangle();

        //define the type of properties our body (the ground) will contain
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / WizardCrawlerApp.PPM, (rect.getY() + rect.getHeight() / 2) / WizardCrawlerApp.PPM);

        //add this body to our box2d world
        body = world.createBody(bdef);

        //setAsBox = define fixture
        shape.setAsBox((rect.getWidth() / 2) / WizardCrawlerApp.PPM, (rect.getHeight() / 2) / WizardCrawlerApp.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef);
    }
}
