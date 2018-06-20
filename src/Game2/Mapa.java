package Game2;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;

public class Mapa {
    
    private Scanner m;
    private String[] mapa = new String[21];

    private Image grass, wall, target, enemy;

    public Mapa() {
        ImageIcon img = new ImageIcon(Game.dir + "floor.jpg");
        grass = img.getImage();
        img = new ImageIcon(Game.dir + "wall.jpg");
        wall = img.getImage();
        img = new ImageIcon(Game.dir + "finish.jpg");
        target = img.getImage();
        img = new ImageIcon(Game.dir + "enemy.jpg");
        enemy = img.getImage();

        loadMap();
    }

    public Image getGrass() {
        return grass;
    }
    public Image getWall() {
        return wall;
    }
    public Image getTarget(){  
        return target;
    }
    public Image getEnemy(){  
        return enemy;
    }

    public String getMap(int x, int y) {
        if(x >= 0 && x < 21 && y >= 0 && y < 21)
            return mapa[y].substring(x, x + 1);
        else
            return "";
    }
    
    public void setMap(int x, int y, String index) {
        System.out.println(y);
        if(y >= 0 && y < 21)
            mapa[y] = ( x > 0 ? mapa[y].substring(0, x) : "" ) + index + ( x < 21 ? mapa[y].substring(x+1) : "" );
    }

    private void loadMap() {
        try{
            m = new Scanner(new File(Game.dir + "mapa1.txt"));
            
            while(m.hasNext()) {
                for(int i = 0; i < 21; i++) {
                    mapa[i] = m.next();
                }
            }
        }catch(Exception e){
            System.out.println("Blad Ladowania mapy");
        }finally{
            if(m != null)
                m.close();
        }
    }
}


