package Logic;



import CardandPile.*;


import java.util.ArrayList;
import java.util.Random;

public class Game {
    public ArrayList<Card> allCard;
    public CardPile allPiles[];
    public DeckPile deckPile;
    public DiscardPile discardPile;
    public TablePile tablePile[];
    public SuitPile suitPile[];
    public MoveCardPile moveCard;

    public Game(){
        //初始化纸牌
        allCard = new ArrayList<Card>();
        for(int i = 0;i <=12;i++)
        {
            for(int j = 0;j<4 ;j++){
                allCard.add(new Card(i,j));
            }
        }
        //洗牌
        Random generator = new Random();
        for(int i = 0;i <52;i++){
            int j = Math.abs(generator.nextInt()%52);
            Card temp = allCard.get(i);
            allCard.set(i,allCard.get(j));
            allCard.set(j,temp);
        }
        //初始化牌堆
        allPiles = new CardPile[13];
        suitPile = new SuitPile[4];
        tablePile = new TablePile[7];
        deckPile = new DeckPile(100,75);
        discardPile = new DiscardPile(100+(Card.width+40),75);

        allPiles[0] = deckPile;
        allPiles[1] = discardPile;
        for(int i = 0;i <4;i++){
            allPiles[2+i] = suitPile[i] = new SuitPile(573+(37+Card.width) * i, 75);
        }
        for(int i = 0;i<7;i++){
            allPiles[6+i] = tablePile[i] = new TablePile(75+(50+Card.width) * i, Card.height+120, i);
        }
        //为桌面堆分配牌,并掀开第一张
        for (int i = 0; i < 7; i++){
            ArrayList<Card> al = new ArrayList<Card>();
            for(int j = 0;j < tablePile[i].getCardNum();j++){
                al.add(allCard.remove(allCard.size()-1));
            }
            tablePile[i].addCard(al);
            tablePile[i].setCardNum(tablePile[i].getNotFlipNum()+1);//这句功能重复了吧
            tablePile[i].top().setFaceup(true);
        }

        //将剩余的24张牌放入待用堆
        int rest = allCard.size();
        for(int i = 0;i<rest;i++){
            deckPile.addCard(allCard.remove(allCard.size()-1));
        }
        //初始化移动中的牌堆
        moveCard = new MoveCardPile();


        }

     //游戏成功判定方法
    public boolean isWin(){
        int sum = 0;
        for(int i =0;i<4;i++){
            sum+=suitPile[i].pileSize();
        }
        if(sum==52){
            return true;
        }
        return false;
    }

    //丢弃堆到待用堆
    public void transferFromDiscardToDeck() {
        while (!(discardPile.isEmpty())) {
            Card card = discardPile.pop();
            card.setFaceup(false);
            deckPile.addCard(card);
        }
    }
    //各牌堆状态的遍历
    public boolean testDeckPile(int x,int y){
        int selectNum = deckPile.select(x,y);
        if(selectNum>=0){
            discardPile.addCard(deckPile.pop());
            return true;
        }else if(selectNum == -2){
            transferFromDiscardToDeck();
            return true;
        }else {
            return false;
        }
    }
    public boolean testDiscardPile(int x,int y){
        int selectNum = discardPile.select(x,y);
        if(selectNum>=0){
            moveCard.clear();
            moveCard.addCard(discardPile.pop());
            moveCard.setFromPile(discardPile);
            return true;
        }
        return false;
    }
    //将选择的牌堆放入MoveCardPile
    public boolean testTablePile(int x,int y){
        boolean isDrag = false;
        for(int i = 0;i<tablePile.length;i++){
            int selectNum = tablePile[i].select(x,y);
            if (selectNum>=0){
                moveCard.clear();
                int num = tablePile[i].getCardNum();
                for(int j =selectNum;j<num;j++){
                    moveCard.addCard(tablePile[i].pop());
                }
                moveCard.setFromPile(tablePile[i]);
                return true;
            }
        }
        return isDrag;
    }
    public boolean isCanAddToSuitPile(int x,int y){
        if(moveCard.size() == 1){
            for(int i=0;i<4;i++){
                if(suitPile[i].includes(x,y)){
                    if(suitPile[i].canTake(moveCard.getCard())){
                        suitPile[i].addCard(moveCard.removeCard());
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isCanAddToTablePile(int x,int y){
        for(int i=0;i<7;i++){
            if(tablePile[i].includes(x,y)){
                //确保不是同一桌面堆
                if(tablePile[i].hashCode()!=moveCard.getFromPile().hashCode()){
                    if(tablePile[i].canTake(moveCard.getCard())){
                        tablePile[i].addCard(moveCard.clear());
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //自动翻开桌面堆
    public void refreshTablePile(){
        for(int i=0;i<7;i++){
            if(tablePile[i].top()!=null){
                if(!(tablePile[i].top().isFaceup())){
                    tablePile[i].top().setFaceup(true);
                    tablePile[i].setNotFlipNum(tablePile[i].getNotFlipNum()-1);
                }
            }
        }
    }
    //没拖到地方自动返回原来位置
    public void returnToFromPile(){
        if (moveCard.getFromPile()!=null){
            //分为桌面堆和丢弃堆
            if(moveCard.getFromPile().hashCode()==discardPile.hashCode()){
                while (!(moveCard.isEmpty())){
                    moveCard.getFromPile().addCard(moveCard.removeCard());
                }
            }else {
                moveCard.getFromPile().addCard(moveCard.clear());
            }
        }
    }






}
