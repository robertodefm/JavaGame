package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import pt.uma.arq.game.Animator;
import java.awt.*;
import java.util.Objects;

public class Item extends AnimatedSprite {

    private boolean isVisible, collided;
    Vector2 direction;

    public Item(){
        x=0;
        y=0;
        boundingBox= new Rectangle();
        collided=false;
        animator=new Animator();
        isVisible=false;
        direction= new Vector2();

    };

    public Item(int x, int y, SpriteBatch batch, String path, int columns, int rows,Vector2 vector){
        this.x=x;
        this.y=y;
        this.collided=false;
        this.animator=new Animator(batch,path,columns,rows);
        this.isVisible=false;
        this.direction= vector;
    };

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean getVisible(){
        return this.isVisible;
    }

    public Rectangle getBoundingBox(){
        return boundingBox;
    }
    @Override
    public void update() {


        this.x += direction.x;
        this.y += direction.y;

        if (x<=0 || x >= 1240 ){
         this.direction.x *= -1;
        }


    }

    public boolean isCollided(Player player) {


        if ((x+boundingBox.width<player.x) ||(x>player.x+player.boundingBox.width)){
            collided=false;

        }else if ((y+boundingBox.height<player.y) ||(y>player.y+player.boundingBox.height)){
            collided=false;
        }else {
            collided = true;
            if(!Objects.equals(animator.getPath(), "razor_disc.png") && !animator.getPath().equals("spiked_stone.png")  && !animator.getPath().equals("square_stone.png") ){player.setScore(20);}
            else  {player.setHealth(20);};

        };
       return collided;
    };

    public boolean getCollided(){
        return collided;
    }

    public void setCollided(){
        this.collided=true;
        //System.out.println(collided);
    }
};
