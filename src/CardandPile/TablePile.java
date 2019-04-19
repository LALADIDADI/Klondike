package CardandPile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class TablePile extends CardPile{
    private int notFlipNum;
    private int cardNum;
    final private static int separation = 30;
    final private static int unFlipCardSeparation = 10;

    public TablePile(int x,int y,int notFilpNum){
        super(x,y);
        this.notFlipNum = notFilpNum;
        cardNum = notFilpNum+1;
    }

    @Override
    public boolean includes(int tx, int ty) {
        int beginX,beginY,endX,endY;
        beginX = x;//由牌堆类继承
        beginY = y;
        endX = x+Card.width;
        if(thePile.size() >0){
            endY = beginY+unFlipCardSeparation*notFlipNum+separation*(thePile.size()-1-notFlipNum)+Card.height
            ;
        }else{
            endY = beginY+Card.height;
        }
        return (beginX <= tx && endX >= tx)&&(beginY <= ty && endY >= ty);
    }

    //返回牌堆还剩多少牌
    @Override
    public int select(int tx, int ty) {
        if (!isEmpty()){
            int beginX,beginY,endX,endY;
            beginX = x;
            beginY = y+unFlipCardSeparation*notFlipNum;
            endX = x+Card.width;
            endY = beginY+unFlipCardSeparation*notFlipNum+separation*(thePile.size()-1-notFlipNum)+Card.height;
            boolean flip_include = beginX <= tx && tx <= endX && beginY <= ty && endY >= ty;
            if(flip_include){
                int c = (ty-beginY)/separation +notFlipNum;
                if( c>=thePile.size()){
                    c = thePile.size()-1;
                }
                return c;
            }else {
                return -1;
            }
        }else {
            return -1;
        }
    }

    //拖动添加牌的方法
    @Override
    public void addCard(Object card) {
        ArrayList<Card> cardList = (ArrayList<Card>)card;
        cardNum += cardList.size();
        for(int i = 0;i < cardList.size();i++){
            thePile.push(cardList.get(i));
        }
    }

    @Override
    public Card pop() {
        cardNum--;
        return super.pop();
    }

    @Override
    public boolean canTake(Card card) {
        if(isEmpty()){
            return card.getNum() == 12;
        }
        Card topCard = top();
        return (card.getColor()!=topCard.getColor()&&card.getNum() == topCard.getNum()-1);
    }

    @Override
    public void display(Graphics g) {
         if(isEmpty()){
             Image image = null;
             String pic_src = "pic/0.png";
             try {
                 image = ImageIO.read(new File(pic_src));
             }catch (IOException e)
             {
                 e.printStackTrace();
                 System.out.println("桌面堆读取图片错误");
             }
             g.drawImage(image,this.x,this.y,Card.width,Card.height,null);
         }else {
             int localy = y;
             for(Enumeration e = thePile.elements();e.hasMoreElements();){
                 Card aCard = (Card) e.nextElement();
                 aCard.setX(x);
                 aCard.setY(localy);
                 aCard.draw(g);
                 if(aCard.isFaceup())
                 {
                     localy+=separation;
                 }else {
                     localy+=unFlipCardSeparation;
                 }
             }
         }
    }

    public int getNotFlipNum(){
        return notFlipNum;
    }
    public void setNotFlipNum(int notFlipNum){
        this.notFlipNum = notFlipNum;
    }
    public int getCardNum(){
        return cardNum;
    }
    public void setCardNum(int cardNum){
        this.cardNum = cardNum;
    }
    public static int getSeparation(){
        return separation;
    }
    public static int getUnFlipCardSeparation(){
        return unFlipCardSeparation;
    }

}


