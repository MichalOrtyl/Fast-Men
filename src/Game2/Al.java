package Game2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Al extends KeyAdapter {
    
    private Mapa m;
    private Character c;
    
    public Al(Mapa m, Character c){
        this.m = m;
        this.c = c;
    }
    
    public void keyPressed(KeyEvent e){
        int keycode = e.getKeyCode();

        if(keycode == KeyEvent.VK_W) {
            if(!m.getMap(c.getTileX(), c.getTileY() - 1).equals("w"))
                c.move(0, -1);
        }
        if(keycode == KeyEvent.VK_S) {
            if(!m.getMap(c.getTileX(), c.getTileY() + 1).equals("w"))
                c.move(0, 1);
        }
        if(keycode == KeyEvent.VK_A) {
            if(!m.getMap(c.getTileX() - 1, c.getTileY() ).equals("w"))
                c.move(-1, 0);
        }
        if(keycode == KeyEvent.VK_D) {
            if(!m.getMap(c.getTileX() + 1, c.getTileY() ).equals("w"))
                c.move(1, 0);
        }
    }
    
    public void keyRelased(KeyEvent e) {
        
    }

    public void keyTyped(KeyEvent e) {
        
    }
}