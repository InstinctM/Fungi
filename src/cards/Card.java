package cards;

    public abstract class Card {
        protected CardType type;
        protected String cardName;

        public Card(CardType t, String cn){
            type = t;
            cardName = cn;
        }

        public CardType getType(){
            return type;
        }

        public String getName(){
            return cardName;
        }
    }