package Game2;

import javax.swing.JFrame;

public class Game {
    public static String dir = "C://Users//orzechtbg//Desktop//Projekt java//Fast Men//bin//img//";
    
    public Game() {
        JFrame f = new JFrame();
        f.setTitle("Fast Men");
        f.add(new Board());
        f.setSize(690, 710);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }	
    
    public static void main(String[] args) {
        new Game();
    }
}
