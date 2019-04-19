package CardandPile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Card类，定义Card所具有的属性
public class Card {
    final public static int width = 122;
    final public static int height = 166;
    final public static int HEART = 0;
    final public static int SPADE =1;
    final public static int DIAMOND = 2;
    final public static int CLUB = 3;

    private boolean faceup;
    private int num;
    private int type;
    private int x;
    private int y;

    public Card(int num,int type)
    {
        this.num = num;
        this.type = type;
        this.faceup = false;
    }

    public Color getColor(){
        if(isFaceup()){
            if(getType()==HEART ||getType()==DIAMOND){
                return Color.red;
            }else {
                return Color.black;
            }
        }else {
            return Color.yellow;
        }
    }

    public void draw(Graphics g){
        Image image = null;
        String pic_src = "";
        try{
            if(isFaceup()){
                pic_src = "pic2/"+this.type+"-"+this.num+".png";
                image = ImageIO.read(new File(pic_src));
            }else{
                pic_src = "pic2/backcard.png";
                image = ImageIO.read(new File(pic_src));
            }
        }catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("图片加载错误");
        }
        g.drawImage(image,getX(),getY(),Card.width,Card.height,null);
    }

    public boolean isFaceup(){
        return faceup;
    }
    public void setFaceup(boolean faceup){
        this.faceup = faceup;
    }
    public int getType()
    {
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getNum(){
        return num;
    }
    public void setNum(){
        this.num = num;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }




}
