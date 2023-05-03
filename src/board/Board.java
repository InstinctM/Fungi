package board;

import cards.*;
import java.util.ArrayList;

public class Board {
    private static CardPile forestCardPile;
    private static CardList forest;
    private static ArrayList<Card> decayPile;

    public static void initialisePiles(){
        forestCardPile = new CardPile();
        forest = new CardList();
        decayPile = new ArrayList<Card>();
    }

    public static void setUpCards(){
        for (int i=0; i<3; i++){
            Card Butter = new Butter();
            forestCardPile.addCard(Butter);
        }

        for (int i=0; i<3; i++){
            EdibleItem Cider = new Cider();
            forestCardPile.addCard(Cider);
        }

        for (int i=0; i<11; i++){
            Card Pan = new Pan();
            forestCardPile.addCard(Pan);
        }

        for (int i=0; i<5; i++){
            Card Basket = new Basket();
            forestCardPile.addCard(Basket);
        }

        Card Stick = new Stick();
        
        for (int i=0; i<10; i++){
            Mushroom HoneyFungus = new HoneyFungus(CardType.DAYMUSHROOM);
            forestCardPile.addCard(HoneyFungus);
        }
        Mushroom HoneyFungus = new HoneyFungus(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(HoneyFungus);

        for (int i=0; i<8; i++){
            Mushroom TreeEar = new TreeEar(CardType.DAYMUSHROOM);
            forestCardPile.addCard(TreeEar);
        }
        Mushroom TreeEar = new TreeEar(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(TreeEar);

        for (int i=0; i<6; i++){
            Mushroom LawyersWig = new LawyersWig(CardType.DAYMUSHROOM);
            forestCardPile.addCard(LawyersWig);
        }
        Mushroom LawyersWig = new LawyersWig(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(LawyersWig);

        for (int i=0; i<5; i++){
            Mushroom Shiitake = new Shiitake(CardType.DAYMUSHROOM);
            forestCardPile.addCard(Shiitake);
        }
        Mushroom Shiitake = new Shiitake(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(Shiitake);

        for (int i=0; i<5; i++){
            Mushroom HenOfWoods = new HenOfWoods(CardType.DAYMUSHROOM);
            forestCardPile.addCard(HenOfWoods);
        }
        Mushroom HenOfWoods = new HenOfWoods(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(HenOfWoods);

        for (int i=0; i<4; i++){
            Mushroom BirchBolete = new BirchBolete(CardType.DAYMUSHROOM);
            forestCardPile.addCard(BirchBolete);
        }
        Mushroom BirchBolete = new BirchBolete(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(BirchBolete);

        for (int i=0; i<4; i++){
            Mushroom Porcini = new Porcini(CardType.DAYMUSHROOM);
            forestCardPile.addCard(Porcini);
        }
        Mushroom Porcini = new Porcini(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(Porcini);

        for (int i=0; i<4; i++){
            Mushroom Chanterelle = new Chanterelle(CardType.DAYMUSHROOM);
            forestCardPile.addCard(Chanterelle);
        }
        Mushroom Chanterelle = new Chanterelle(CardType.NIGHTMUSHROOM);
        forestCardPile.addCard(Chanterelle);

        for (int i=0; i<3; i++){
            Mushroom Morel = new Morel(CardType.DAYMUSHROOM);
            forestCardPile.addCard(Morel);
        }
    }

    public static CardPile getForestCardsPile(){
        return forestCardPile;
    }

    public static CardList getForest(){
        return forest;
    }

    public static ArrayList<Card> getDecayPile(){
        return decayPile;
    }

    public static void updateDecayPile(){
        if (decayPile.size() == 4){
            decayPile.clear();;
        }

        decayPile.add(forest.getElementAt(7));
        forest.removeCardAt(7);
    }
}

