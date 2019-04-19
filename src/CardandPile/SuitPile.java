package CardandPile;

public class SuitPile extends CardPile {
    public SuitPile(int xl, int yl) {
        super(xl, yl);
    }

    @Override
    public boolean canTake(Card card) {
        if(isEmpty())
        {
            return card.getNum()==0;
        }
        Card topCard = top();
        return (card.getType() == topCard.getType()) && (card.getNum() == topCard.getNum()+1);
    }

    public int pileSize(){
        return thePile.size();
    }
}
