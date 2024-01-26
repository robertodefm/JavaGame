package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Player extends AnimatedSprite {
    public Player(){
        super();
    }
    private int score, health, shots;
    private ArrayList<Bullet> bullets;




    public Player(int x, int y, SpriteBatch batch){

        super( x,  y,  batch,  "frog_run.png",  12,  1);
        this.health=100;
        this.score=0;
        this.shots=15;
        bullets = new ArrayList<Bullet>();
        for (int i = 0; i < 15; i++) {
            bullets.add(new Bullet(batch, "laser-bolts.png", 2, 1));
        }

    };

    @Override
    public void update() {

        motion();
        updateBullets();
        shoot();


        }
    public void motion(){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && this.x <1225){
            this.x+=7;
            this.boundingBox.setLocation(x,y);
            this.animator.setFlip(false);

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.x > 0){
            this.x-=7;
            this.animator.setFlip(true);
        }

        else{

        };
    };

    private void shoot() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && shots>0) {

            for (Bullet bullet : bullets) {
                if (!bullet.getIsVisible()) {
                    bullet.shoot(this.x , this.y );
                    this.shots-=1;
                    break;
                }
            }
        }
    }

    private void updateBullets() {
        for (Bullet bullet : bullets) {
            if (bullet.getIsVisible()) {
                bullet.update();
                bullet.render();

            };
        };

        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet bullet = it.next();
            if (bullet.y<0 || bullet.y>800){

                it.remove();

            };
        };




    }
    public int getHealth() {
        return health;
    };

    public void setHealth(int damage){
        this.health-=damage;
    };

    public int getScore() {
        return score;
    };

    public void setScore(int plus) {
        this.score = score+ plus;
    };


    public ArrayList<Bullet> getBullets() {
        return bullets;
    };

    public int getShots() {
        return shots;
    }
}
