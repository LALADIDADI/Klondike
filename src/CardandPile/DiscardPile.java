package CardandPile;

public class DiscardPile extends CardPile {
    public DiscardPile(int x,int y){
        super(x,y);
    }

    @Override
    public void addCard(Object card) {
        Card thecard = (Card)card;
        if(!(thecard.isFaceup())){
            ((Card) card).setFaceup(true);
        }
        thePile.push(thecard);
    }
}
