package cards;

public class Morel extends Mushroom{
    public Morel(CardType t){
        super(t, "morel");
        super.sticksPerMushroom = 4;
        super.flavourPoints = 6;
    }
}

