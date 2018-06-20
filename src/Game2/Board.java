package Game2;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JPanel implements ActionListener  {
    private static int ILOSC_KLATEK_NA_SEK = 40;
    private static float CO_ILE_SEK_RUCH_ENEMY = 0.3f;
    private static int CO_ILE_MILISEK_RUCH_ENEMY = (int)(1000 * CO_ILE_SEK_RUCH_ENEMY);
    private static long LIMIT_CZASU = 1000 * 30; // w milisekundach
    
    private long aktualnyCzasGry = 0; // w milisekundach
    
    private Timer timer;

    private Mapa m;
    private Character c;
    private boolean win = false;
    private boolean lose = false;

    private String message="";

    private Font font = new Font("Serif" , Font.BOLD,48);

    public Board() {
        m = new Mapa();
        
        for(int y = 0; y < 21; y++){
            for(int x = 0; x < 21; x++){
                if(m.getMap(x, y).equals("s")){
                    c = new Character(x, y);
                    break;
                }
            }
        }
        if(c == null)
            throw new RuntimeException("Nie wskazano punktu startu");
            
        addKeyListener(new Al(m, c));
        setFocusable(true);
        
        timer = new Timer(1000/ILOSC_KLATEK_NA_SEK,this);
        timer.start();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        

        if(win || lose) {
            g.setColor(Color.RED);		
            g.setFont(font);		
            g.drawString(message, 150,305);
            
            timer.stop();
        }
        
        if(!win && !lose) {
            aktualnyCzasGry += 1000/ILOSC_KLATEK_NA_SEK;
            if(aktualnyCzasGry >= LIMIT_CZASU){
                message = "KONIEC CZASU :(";
                lose = true;
            }

            if(aktualnyCzasGry % CO_ILE_MILISEK_RUCH_ENEMY == 0)
                moveEnemies();

            for(int y = 0; y < 21; y++) {
                for(int x = 0; x < 21; x++) {
                    String mapId = m.getMap(x, y);
                    if(mapId.equals("f"))
                        g.drawImage(m.getTarget(), x*32, y*32, null);
                    
                    if(mapId.equals("g") || mapId.equals("s") || mapId.equals("e"))
                        g.drawImage(m.getGrass(),x * 32 ,y * 32, null);
                    
                    if(mapId.equals("w"))
                        g.drawImage(m.getWall(), x * 32 ,y * 32, null);
                    
                    if(mapId.equals("e"))
                        g.drawImage(m.getEnemy(), x*32, y*32, null);
                }	
            }
            
            g.drawImage(c.getCharacter(), c.getTileX() * 32, c.getTileY() * 32, null);	
            
            if(m.getMap(c.getTileX(), c.getTileY()).equals("e")){
                message = "PRZEGRALES :(";
                lose = true;
            }
        }
    }
    
    public void moveEnemies(){
        System.out.println("move");
        ArrayList<Integer> pozycjeX = new ArrayList<Integer>();
        ArrayList<Integer> pozycjeY = new ArrayList<Integer>();
        
        for(int y = 0; y < 21; y++){
            for(int x = 0; x < 21; x++){
                if(m.getMap(x, y).equals("e")){
                    pozycjeX.add(x);
                    pozycjeY.add(y);
                }
            }
        }
        
        while(pozycjeX.size() > 0){
            moveEnemy(pozycjeX.get(pozycjeX.size()-1), pozycjeY.get(pozycjeY.size()-1));
            pozycjeX.remove(pozycjeX.size()-1);
            pozycjeY.remove(pozycjeY.size()-1);
        }
    }
    
    public void moveEnemy(int pozX, int pozY){
        ArrayList<String> dostepneRuchy = new ArrayList<String>();
        if(m.getMap(pozX, pozY-1).equals("g"))
            dostepneRuchy.add("g");
        if(m.getMap(pozX-1, pozY).equals("g"))
            dostepneRuchy.add("l");
        if(m.getMap(pozX+1, pozY).equals("g"))
            dostepneRuchy.add("p");
        if(m.getMap(pozX, pozY+1).equals("g"))
            dostepneRuchy.add("d");
        
        if(dostepneRuchy.size() > 0){
            int wybranyRuch = (int)(Math.random()*dostepneRuchy.size());
            if(wybranyRuch > dostepneRuchy.size()-1)
                wybranyRuch = dostepneRuchy.size()-1;
            
            m.setMap(pozX, pozY, "g");
            switch(dostepneRuchy.get(wybranyRuch)){
                case "g":
                    m.setMap(pozX, pozY-1, "e");
                    break;
                case "l":
                    m.setMap(pozX-1, pozY, "e");
                    break;
                case "p":
                    m.setMap(pozX+1, pozY, "e");
                    break;
                case "d":
                    m.setMap(pozX, pozY+1, "e");
                    break;
            }
        }     
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(m.getMap(c.getTileX(), c.getTileY()).equals("f")) {
            message = "ZWYCIESTWO :)";
            win = true;
        }
        
        repaint();
    }
}
		



