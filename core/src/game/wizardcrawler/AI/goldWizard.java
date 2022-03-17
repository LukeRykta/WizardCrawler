package game.wizardcrawler.AI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;



public class goldWizard extends Sprite {

    private float stateTime;
    public World world;
    public Body b2body;
    private TextureRegion wizardShoot;



    public goldWizard(Play screen) {

        /*super(screen.getAtlas().findRegion("isowizard"));

       AssetManager assetManager = new AssetManager();
        assetManager.load("WizardGFX/isowizard.png", Texture.class);
        assetManager.get("WizardGFX/isowizard.png");
          walkAnimation = new Animation(0.1f, frames);

        stateTime = 0;

        attackWizard = new TextureRegion(getTexture(), 288, 0, 32, 32);

        setBounds(getX(), getY(), 32 / WizardCrawlerApp.PPM,32 / WizardCrawlerApp.PPM);
        setRegion(attackWizard); */


        super(screen.getAtlas().findRegion("isowizard"));
        this.world = screen.getWorld();
        stateTime = 0;

        wizardShoot = new TextureRegion(getTexture(), 288, 0, 32, 32);


        defineEnemy();
        setBounds(0, 0, 32 / WizardCrawlerApp.PPM, 32 / WizardCrawlerApp.PPM);
        setRegion(wizardShoot);


    }

    public void update(float dt){

        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(wizardShoot);

    }


    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        //bdef.position.set(getX(), getY());

        bdef.position.set(900 / WizardCrawlerApp.PPM, 64 / WizardCrawlerApp.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(14 / WizardCrawlerApp.PPM);

        fdef.filter.categoryBits = WizardCrawlerApp.ENEMY_BIT;
        //these are the things wizard can collide with
        fdef.filter.maskBits = WizardCrawlerApp.GROUND_BIT |
                WizardCrawlerApp.WIZARD_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        // Create head

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-5, 8).scl(1 / WizardCrawlerApp.PPM);
        vertice[1] = new Vector2(5, 8).scl(1 / WizardCrawlerApp.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / WizardCrawlerApp.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / WizardCrawlerApp.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = WizardCrawlerApp.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);

    }
}