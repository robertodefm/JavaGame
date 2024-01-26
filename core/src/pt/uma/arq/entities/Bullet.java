package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Objects;

public class Bullet extends AnimatedSprite {

    private boolean isVisible;

    public Bullet(SpriteBatch batch, String path, int columns, int rows) {
        super(0, 0, batch, path, columns, rows);
        create();
        isVisible = false;
    }

    public void shoot(int playerX, int playerY) {

        x = playerX;
        y = playerY;

        setIsVisible(true);
    }

    @Override
    public void update() {
        if (isVisible) {

            y += 3;


            if (y > Gdx.graphics.getHeight()) {
                isVisible = false;
            }
        }
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible){
        this.isVisible=isVisible;
    }

    public boolean isCollided(Item item) {


        if ((x+boundingBox.width<item.x) ||(x>item.x+item.boundingBox.width)){
            collided=false;

        }else if ((y+boundingBox.height<item.y) ||(y>item.y+item.boundingBox.height)){
            collided=false;

        }else {
            collided = true;

            item.setCollided();
            item.y=0;
            this.y=-3;
            item.setVisible(false);
            this.setIsVisible(false);



        };



        return collided;
    };
}
