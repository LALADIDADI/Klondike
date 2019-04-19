package Panels;

import Logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Panel extends JPanel implements MouseListener, ActionListener, MouseMotionListener {

    private boolean isDrag = false;
    private int x;
    private int y;
    private boolean win;

    public static int width = 1290;
    public static int height = 960;
    Game game = new Game();
    public Panel(){
        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(width,height);
        this.setOpaque(false);
        setLayout(null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0,0,width,height);
        File f = new File("pic2/table.png");
        File f2 = new File("pic2/win.png");
        BufferedImage image;
        try{
            image = ImageIO.read(f);
            g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
            for(int i=0;i<13;i++){
                game.allPiles[i].display(g);
            }
            game.moveCard.display(g,x,y);//这一句现在是不是没用
            if(win){
                image = ImageIO.read(f2);
                g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        isDrag = false;
        boolean isSelect = false;
        isSelect = game.testDeckPile(x,y);
        if(!isSelect){
            isSelect = game.testDiscardPile(x, y);
            if(isSelect){
                isDrag = true;
            }
            if(!isSelect){
                isSelect = game.testTablePile(x, y);
                if(isSelect){
                    isDrag = true;
                }
            }

        }
        isDrag = false;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        if(isDrag &&  game.moveCard.size() > 0 ){
            boolean isCanAdd =false;
            isCanAdd = game.isCanAddToSuitPile(x,y);
            if(!isCanAdd){
                isCanAdd = game.isCanAddToTablePile(x, y);
            }else {
                win = game.isWin();
            }

            if(!isCanAdd ){
                game.returnToFromPile();
            }
            else{
                game.refreshTablePile();
            }

            isDrag = false;
            repaint();
        }
        else{
            if(game.moveCard.size() > 0)
                game.returnToFromPile();
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        isDrag = true;
        x = e.getX();
        y = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
