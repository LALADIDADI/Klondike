package Logic;

import CardandPile.*;

import java.awt.*;
import java.util.ArrayList;

public class MoveCardPile {
    private static ArrayList<Card> cardList;
    private CardPile fromPile;
    private int x;
    private int y;
    final private static int separation = 30;

    public MoveCardPile(){
        cardList = new ArrayList<Card>();
    }
    public int size(){
        return cardList.size();
    }
    public boolean isEmpty(){
        return cardList.isEmpty();
    }

    public void addCard(Card card){
        //插入指定位置
        cardList.add(0,card);
    }
    public Card getCard(){
        if(cardList.size()>0)
        {
            return cardList.get(0);
        }else{
            return null;
        }
    }
    public Card removeCard(){
        if(cardList.size()>0){
            return cardList.remove(0);
        }else {
            return null;
        }
    }

    public static ArrayList<Card> clear(){
        ArrayList<Card> list = cardList;
        cardList = new ArrayList<Card>();
        return list;
    }
    //还没搞清楚逻辑
    public void display(Graphics g,int tx,int ty){
        x = tx - Card.width/2;
        y = ty - Card.height/2;
        int localy = y;
        for(int i = 0;i<cardList.size();i++){
            Card aCard = (Card) cardList.get(i);
            aCard.setX(x);
            aCard.setY(localy);
            if(!(aCard.isFaceup())){
                aCard.setFaceup(true);
            }
            aCard.draw(g);
            localy += separation;
        }
    }
    public ArrayList<Card> getCardList(){
        return cardList;
    }
    public void setCardList(ArrayList<Card> cardList){
         MoveCardPile.cardList = cardList;
    }
    public CardPile getFromPile(){
        return fromPile;
    }
    public void setFromPile(CardPile fromPile){
        this.fromPile = fromPile;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int Y){
        this.y = y;
    }
}
