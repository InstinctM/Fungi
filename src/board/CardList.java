package board;

import cards.*;
import java.util.ArrayList;

public class CardList {
    private ArrayList<Card> cList;

    public CardList(){
        cList = new ArrayList<Card>();
    }

    public void add(Card c){
        cList.add(0, c);
    }

    public int size(){
        return cList.size();
    }

    public Card getElementAt(int i){
        return cList.get(i);
    }

    public Card removeCardAt(int j){
        return cList.remove(j);
    }
}