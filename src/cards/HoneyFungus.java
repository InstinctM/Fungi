package cards;

public class HoneyFungus extends Mushroom{
    public HoneyFungus(CardType t){
        super(t, "honeyfungus");
        super.sticksPerMushroom = 1;
        super.flavourPoints = 1;
    }
}

