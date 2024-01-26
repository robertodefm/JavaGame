package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import pt.uma.arq.game.Game;

import java.util.*;



public class Items {

    private Game game;
    private SpriteBatch batch;
    private ArrayList<Item> itemsList;

    private String[] itemsPaths = {"apple.png","melon.png","pineapple.png","razor_disc.png","spiked_stone.png","square_stone.png"};
    private int[] itemsColumns = {17,17,17,8,4,4};

    Timer timer = new Timer();
    int delayMillis = 2000;


    public  Items(){
        this.batch=new SpriteBatch();
        this.itemsList= new ArrayList<Item>();
    };

    public  Items(SpriteBatch batch){
        this.batch=batch;
        itemsList= new ArrayList<Item>();
    };

    public ArrayList<Item> getItemsList() {
        return itemsList;
    };

    public void create(){

        for (int i = 0; i < 50; i++) {
            Random random = new Random();

            int min = 20;
            int max = 1250;
            int randomNumber = random.nextInt(max - min + 1) + min;
            int randomVector = random.nextInt(3 - -3 + 1) + -3;
            Vector2 vector = new Vector2(randomVector,-2);

            Random randomIndex = new Random();

            int minIndex = 0;
            int maxIndex = 5;
            int randomNumberIndex = randomIndex.nextInt(maxIndex - minIndex + 1) + minIndex;

            Item item= new Item(randomNumber,800,batch,itemsPaths[randomNumberIndex],itemsColumns[randomNumberIndex],1, vector);
            item.create();
            itemsList.add(item);


        }

        for (final Item item:
             itemsList) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    item.setVisible(true);

                }
            },delayMillis);
            delayMillis+=2000;
        }


    };

    public void update( Player player ){



        for (int i = 0; i < itemsList.size(); i++) {
            if(itemsList.get(i).getVisible()){
                itemsList.get(i).update();
            };

        };

        Iterator<Item> it = itemsList.iterator();
        while(it.hasNext()){
            Item item = it.next();
            if (item.y<=0 || item.isCollided(player) || item.getCollided()){
                item.setVisible(false);
                it.remove();

            };
        };


    };

    public void render(){
        for (Item item:
                itemsList) {
            if(item.getVisible()){
                item.render();}

        }
       System.out.println(itemsList.size());
    }

}
