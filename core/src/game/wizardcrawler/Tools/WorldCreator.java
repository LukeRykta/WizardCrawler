package game.wizardcrawler.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import game.wizardcrawler.Screens.Play;
import game.wizardcrawler.Sprites.CoinCollisionHandler;
import game.wizardcrawler.Sprites.Mushroom;
import game.wizardcrawler.Sprites.Ore;
import game.wizardcrawler.Sprites.Trap;
import game.wizardcrawler.WizardCrawlerApp;

public class WorldCreator {
    public WorldCreator(Play screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //defines what body consists of
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //wall boundaries
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the ground) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / WizardCrawlerApp.PPM, (rect.getY() + rect.getHeight() / 2) / WizardCrawlerApp.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / WizardCrawlerApp.PPM, (rect.getHeight() / 2) / WizardCrawlerApp.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }


        // mushroom object
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Mushroom(screen, rect);
        }

        //trap object
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Trap(screen, rect);
        }

        //coin object
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new CoinCollisionHandler(screen, rect);
        }

        //ore object
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ore(screen, rect);
        }



    /*
        //create ground bodies/fixtures
        //the "2" in map.getLayers().get(2) references the index of the object layer in Tiled from starting from the ground up (0.background->1.graphics->*2*.ground)
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the ground) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create brick bodies/fixtures (index 5)
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rect);
        }

        //create pipe bodies/fixtures (index 3)
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the ground) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            //this is so when enemy collides with pipe he can turn around (and not when he falls on ground or something else)
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
            body.createFixture(fdef);
        }

        //create coin bodies/fixtures (index 4)
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);
            */
    }
}
