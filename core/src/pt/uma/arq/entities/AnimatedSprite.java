package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

import java.awt.*;

public abstract class AnimatedSprite {
    protected Animator animator;
    protected int x,y;
    protected Rectangle boundingBox;
    protected boolean collided;

    public AnimatedSprite(){
        x=0;
        y=0;
        boundingBox= new Rectangle();
        collided=false;
        animator=new Animator();
    };

    public AnimatedSprite(int x, int y, SpriteBatch batch, String path, int columns, int rows){
        this.x=x;
        this.y=y;
        this.collided=false;
        this.animator=new Animator(batch,path,columns,rows);


    };

    public void create(){
        this.animator.create();
        this.boundingBox = new Rectangle(x,y,animator.getWidth(),animator.getHeight());
    }

    public  void render(){
        this.animator.render(x,y);
    };

    public abstract void update();

    //public  void collidedWith();


}
