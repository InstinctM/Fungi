package cards;

public abstract class EdibleItem extends Card {

    protected int flavourPoints;

    public EdibleItem(CardType t, String cn){
        super(t,cn);
    }

    public int getFlavourPoints(){
        return flavourPoints;
    }
}

