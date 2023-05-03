package board;

import cards.*;
import java.util.ArrayList;

class Display implements Displayable {
    private ArrayList<Card> displayList = new ArrayList<>();

    public void add(Card c){
        displayList.add(c);
    }

    public int size(){
        return displayList.size();
    }

    public Card getElementAt(int i){
        return displayList.get(i);
    }

    public Card removeElement(int j){
        return displayList.remove(j);
    }
}
