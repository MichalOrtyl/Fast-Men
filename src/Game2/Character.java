package Game2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Character{
    private int tileX,tileY;
    private Image character;

    public Character(int tileX, int tileY){
        ImageIcon img = new ImageIcon(Game.dir + "ghost.jpg");
        character = img.getImage();

        this.tileX = tileX;
        this.tileY = tileY;
    }

    public Image getCharacter() {
        return character;
    }
    public int getTileX() {
        return tileX;
    }
    public int getTileY() {
        return tileY;
    }

    public void move(int tx, int ty) {
        tileX += tx;
        tileY += ty;
    }
}
