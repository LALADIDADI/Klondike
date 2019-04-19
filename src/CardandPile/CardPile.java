package CardandPile;
/*
缺省牌堆用于桌面堆，花色堆，丢弃堆，待用堆的继承
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

public class CardPile {

    protected int x;
    protected int y;
    public Stack<Card> thePile;

    public CardPile(int xl,int yl){
        x = xl;
        y = yl;
        thePile = new Stack<Card>();
    }
    public Card top(){
        if(!(thePile.empty())){
            return (Card)thePile.peek();
        }else{
            return null;
        }
    }

    public boolean isEmpty()
    {
        return thePile.empty();
    }
    public Card pop(){
        try{
            return thePile.pop();
        }catch (EmptyStackException e){
            return null;
        }
    }
    public boolean includes(int tx,int ty){
        return  this.x <= tx && tx <= (this.x+Card.width) &&
                this.y <= ty && ty<= (this.y+Card.height);
    }
    public int select (int tx,int ty){
        if(includes(tx,ty)){
            if(isEmpty())
            {
                return -2;
            }else {
                return thePile.size() - 1;//从零开始计数
            }
        }else {
            return -1;
        }
    }
    //Object准备被重写
    public void addCard(Object card){
        thePile.push((Card)card);
    }
    public void display(Graphics g){
        if(isEmpty()){
            Image image = null;
            String pic_src = "pic/0.png";
            try{
                image = ImageIO.read(new File(pic_src));
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("牌堆类中加载图片失败");
            }
            g.drawImage(image, this.x,this.y, Card.width, Card.height, null);
        }else{
            top().setX(x);
            top().setY(y);
            top().draw(g);
        }
    }

    public boolean canTake(Card card)
    {
        return false;
    }


}
