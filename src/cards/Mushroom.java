package cards;

public abstract class Mushroom extends EdibleItem {
    protected int sticksPerMushroom;

    public Mushroom(CardType t, String cn){
        super(t, cn);
    }

    public int getSticksPerMushroom(){
        return sticksPerMushroom;
    }
}

