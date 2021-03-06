package game.wizardcrawler.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.WizardCrawlerApp;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(Play screen, Rectangle bounds) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        //define the type of properties our body will contain
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / WizardCrawlerApp.PPM, (bounds.getY() + bounds.getHeight() / 2) / WizardCrawlerApp.PPM);

        //add this body to our box2d world
        body = world.createBody(bdef);
        
        //setAsBox = define fixture
        shape.setAsBox((bounds.getWidth() / 2) / WizardCrawlerApp.PPM, (bounds.getHeight() / 2) / WizardCrawlerApp.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        // capture barrier in fixture
        fixture = body.createFixture(fdef);
    }

    public abstract void onHeadHit();

    public abstract void duringContact();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    /*
    The purpose of the method below is to remove an object visually from the map
        -we use getCell() to get the layer of our graphics (in this case 1)
        -we then want to pass the x and y values of our graphic
            -to get x, we typecast int and get the body and the position of that body in box2d world
                -we scale the position up because we previously scaled it down so it looks identical to the tiled map layer
                -divide by tile size to get x coordinate of cell
            -to get y, we typecast int and get the body and the position of that body in box2d world
                -we scale the position just like x
                -divide same way as x
     */
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);
        return layer.getCell((int)(body.getPosition().x * WizardCrawlerApp.PPM / 32),
                (int)(body.getPosition().y * WizardCrawlerApp.PPM / 32));
    }
}
