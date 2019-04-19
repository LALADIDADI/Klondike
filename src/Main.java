
import Panels.Panel;

import javax.swing.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;


public class Main extends JFrame {

    private Panel sp;
    public Main(){

        JMenuBar menubar=new JMenuBar();
        JMenu m1=new JMenu("游戏");
        JMenu m2=new JMenu("关于");
        JMenuItem start=new JMenuItem("新游戏");
        JMenuItem stop=new JMenuItem("结束游戏");
        JMenuItem help=new JMenuItem("帮助");
        JMenuItem link=new JMenuItem("报错");
        start.addActionListener(new ActionListenerL());
        stop.addActionListener(new ActionListenerL());
        help.addActionListener(new ActionListenerL());
        link.addActionListener(new ActionListenerL());
        m1.add(start);
        m1.add(stop);
        m2.add(help);
        m2.add(link);
        menubar.add(m1);
        menubar.add(m2);
        add(menubar,BorderLayout.NORTH);

        sp = new Panel();
        add(sp,BorderLayout.CENTER);

        //添加背景音乐
        new Play0("D:\\JETBRAINS\\myProjects\\SolGame\\pic2\\song1.mp3").start();

        setSize(Panel.width,Panel.height);
        setTitle("Solitaire纸牌游戏");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

    }
    public class ActionListenerL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("新游戏")){
                setVisible(false);
                new Main();

            }
            if(e.getActionCommand().equals("结束游戏")){
                System.exit(0);
            }
            if(e.getActionCommand().equals("帮助")){
                JOptionPane.showMessageDialog(null,"请百度Solitaire纸牌查看规则");
            }
            if(e.getActionCommand().equals("报错")){
                JOptionPane.showMessageDialog(null,"请联系850393834@qq.com");
            }
        }

    }
    //添加背景音乐
    class Play0 extends Thread{
        Player player;
        String music;
        public Play0(String file) {
            this.music = file;
        }
        public void run() {
            try {
                play();
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        }
        public void play() throws FileNotFoundException, JavaLayerException {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
            player = new Player(buffer);
            player.play();

        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
