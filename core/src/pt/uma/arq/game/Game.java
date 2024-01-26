package pt.uma.arq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.Bullet;
import pt.uma.arq.entities.Item;
import pt.uma.arq.entities.Items;
import pt.uma.arq.entities.Player;



public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private BackgroundManagement backgroundManagement;
    private BitmapFont font;

    private Player spriteTest;

    private Items items;

    private boolean gameOver, gamePaused,win;




    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(1280, 800);
        batch = new SpriteBatch();

        spriteTest=new Player(100,100,batch);
        spriteTest.create();

         items=new Items(batch);
        items.create();

        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);
        backgroundManagement = new BackgroundManagement(batch);




    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundManagement.render();

        BitmapFront.drawText(10,750,"Score: "+spriteTest.getScore()+"\n"+"Health: "+spriteTest.getHealth()+"\n"+"Shots: "+spriteTest.getShots(),batch);

        spriteTest.render();




        items.render();

        for (Bullet bullet : spriteTest.getBullets()) {
            if (bullet.getIsVisible()) {
                for (Item item : items.getItemsList()) {
                    if (item.getVisible()) {
                        bullet.isCollided(item);

                    };
                };
            };
        };

        if (!gamePaused) {
            items.update(spriteTest);
            spriteTest.update();

            if (spriteTest.getHealth() <= 0 ) {
                gameOver= true;
                gamePaused= true;
            } else if (items.getItemsList().isEmpty()) {
                gamePaused= true;
                win= true;
            }
            //System.out.println(spriteTest.getBullets().size());
        }

        if (gameOver&& gamePaused)BitmapFront.drawText(560, 400, "GAME OVER", batch);

        if (win && gamePaused)BitmapFront.drawText(560, 470, "WINNER! \nScore: " + spriteTest.getScore(), batch);

        if (gamePaused && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {

            Gdx.app.exit();
        }

        batch.end();

    };

    public Player getSpriteTest() {
        return spriteTest;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}