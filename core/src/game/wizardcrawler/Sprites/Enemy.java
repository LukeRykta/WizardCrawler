package game.wizardcrawler.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import game.wizardcrawler.Screens.Play;


public abstract class Enemy extends Sprite {

    protected World world;
    protected Play screen;
    public Body b2body;
    public Enemy(Play screen , float x, float y){

    this.world = screen.getWorld();
    this.screen = screen;
    setPosition(x, y);
    defineEnemy();

    }

    protected abstract void defineEnemy();


}
