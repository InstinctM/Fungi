package board;

import java.util.ArrayList;

import cards.*;

public class Player {
    private Hand h;
    private Display d;
    private int score;
    private int handlimit = 8;
    private int sticks = 0;

    public Player(){
        h = new Hand();
        d = new Display();
        d.add(new Pan());
    }
    
    public int getScore(){
        return score;
    }

    public int getHandLimit(){
        return handlimit;
    }

    public int getStickNumber(){
        return sticks;
    }

    public void addSticks(int i){
        sticks = sticks + i;

        while (i>0){
            d.add(new Stick());
            i--;
        }
    }

    public void removeSticks(int j){     
        sticks = sticks - j;

        for (; j>0;j--)
            for (int i=0; i<d.size(); i++){
                if (d.getElementAt(i).getType() == CardType.STICK){
                    d.removeElement(i);
                    break;
                }
            }
        
    }

    public Hand getHand(){
        return h;
    }

    public Display getDisplay(){
        return d;
    }

    public void addCardtoHand(Card c){
        h.add(c);

        for (int i=0; i<h.size();i++){
            if (h.getElementAt(i).getType() == CardType.BASKET){
            h.removeElement(i);
            d.add(new Basket());
            handlimit = handlimit + 2;
            }   
        }
    }

    public void addCardtoDisplay(Card c){
        d.add(c);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean takeCardFromTheForest(int i){
        // Exceeds handlimit
        if ((h.size()+1) > handlimit){
            return false;
        }

        // Test if input is invalid
        if (i<0 || i> Board.getForest().size()){
            return false;
        }

        // Input is 1 or 2
        if (i==1 || i==2){
            if (Board.getForest().getElementAt(8-i).getType() == CardType.BASKET){
                d.add(new Basket());
                handlimit = handlimit +2;
            }
            else {
                h.add(Board.getForest().getElementAt(8-i));
            }

        Board.getForest().removeCardAt(8-i);
        return true;
        }

        // Input is 3-9
        if (i>2 && i<9){
            if (getStickNumber() >= (i-2)){
                if (Board.getForest().getElementAt(8-i).getType() == CardType.BASKET){
                    d.add(new Basket());
                    handlimit = handlimit +2;
                }
                else {
                    h.add(Board.getForest().getElementAt(8-i));
                    }
            }
            else {
                return false;
            }
            
        Board.getForest().removeCardAt(8-i);
        removeSticks(i-2);

        return true;
        }
        return false;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean takeFromDecay(){
        int x = handlimit; // Handlimit after all baskets taken from Decay Pile
        int y = Board.getDecayPile().size(); // Decay Pile size without baskets

        for (int i=0; i<Board.getDecayPile().size(); i++){
            if (Board.getDecayPile().get(i).getType() == CardType.BASKET){
                x = x +2;
                y--;
            }
        }

        if (h.size()+ y <= x){
            for (int i=0; i<Board.getDecayPile().size(); i++){
                if (Board.getDecayPile().get(i).getType() == CardType.BASKET){
                    d.add(new Basket());
                    handlimit = handlimit +2;
                }
                else {
                    h.add(Board.getDecayPile().get(i));
                }
            }
        Board.getDecayPile().clear();
        return true;
        }        
        return false;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean cookMushrooms(ArrayList<Card> c){
        boolean cHavePan = false;
        boolean dHavePan = false;
        boolean haveBC = false;
        String s = "";
        int a = 0; // Counter for how many "day mushrooms"

        // Check if inputted array have pan
        for (int i=c.size()-1; i>=0; i--){
            if (c.get(i).getType() == CardType.PAN){
                cHavePan = true;
            }
        }
        
        // Check if display have pan
        if (cHavePan == false){
            for (int i=d.size()-1; i>=0; i--){
                if (d.getElementAt(i).getType() == CardType.PAN){
                    d.removeElement(i);
                    dHavePan = true;
                    break;
                }
            }
        }

        // Return false if both doesn't contain a pan
        if (cHavePan == false && dHavePan == false){
            return false;
        }

        // Check if C contains non-edible objects
        for (int i=c.size()-1; i>=0; i--){
            if (c.get(i).getType() == CardType.BASKET){
                return false;
            }
        }

        // Grab the first mushroom it sees and store in s for comparsion
        for (int i=c.size()-1; i>=0; i--){
            if (c.get(i).getType() == CardType.DAYMUSHROOM){
                s = c.get(i).getName();
                break;
            }

            else if (c.get(i).getType() == CardType.NIGHTMUSHROOM){
                s = c.get(i).getName();
                break;
            }
        }

        // Count how many "day mushroom" and check if C contains more than one type of mushrooms
        for (int i=c.size()-1; i>=0; i--){
            if (c.get(i).getType() == CardType.DAYMUSHROOM){
                if (c.get(i).getName().equals(s)){
                    a++;
                }
                else {
                    return false;
                }
            }

            else if (c.get(i).getType() == CardType.NIGHTMUSHROOM){
                if (c.get(i).getName().equals(s)){
                    a = a +2;
                }
                else {
                    return false;
                }
            }

            else if (c.get(i).getType() == CardType.BUTTER){
                haveBC = true;
            }

            else if (c.get(i).getType() == CardType.CIDER){
                haveBC = true;
            }
        }

        // Check if there is enough cards to cook
        if (a<=2){
            return false;
        }

        // Check if the player wants to use butter or cider when they cook 3 mushrooms
        if (a<4 && haveBC == true){
            return false;
        }

        // Check if butter and cider is enough
        if (a>3 && haveBC == true){
            int x = a;

            for (int i=c.size()-1; i>=0; i--){
                if (c.get(i).getType() == CardType.BUTTER){
                    x = x -4;
                }
                else if (c.get(i).getType() == CardType.CIDER){
                    x = x -5;
                }
            }

            if (x < 0){
                return false;
            }

            // Add extra points for cider and butter
            while (a>0){
                if (a>=5){
                    for (int i=c.size()-1; i>=0; i--){
                        if (c.get(i).getType() == CardType.CIDER){
                            c.remove(i);
                            a = a -5;
                            score = score + 5;

                            for (int j=h.size()-1; j>=0; j--){
                                if (h.getElementAt(j).getType() == CardType.CIDER){
                                    h.removeElement(j);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }

                if (a>=4){
                    for (int i=c.size()-1; i>=0; i--){
                        if (c.get(i).getType() == CardType.BUTTER){
                            c.remove(i);
                            a = a -4;
                            score = score + 3;

                            for (int j=h.size()-1; j>=0; j--){
                                if (h.getElementAt(j).getType() == CardType.BUTTER){
                                    h.removeElement(j);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                
                if (a<4){
                    break;
                }
            }
        }

        // Add points for the type of mushrooms
        for (int i=c.size()-1; i>=0; i--){
            if (c.get(i).getType() == CardType.DAYMUSHROOM){
                score = score + ((Mushroom)c.get(i)).getFlavourPoints();
            }
            else if (c.get(i).getType() == CardType.NIGHTMUSHROOM){
                score = score + 2*((Mushroom)c.get(i)).getFlavourPoints();
            }
        }
        
        // Remove used cards from hand
        for (int i=c.size()-1; i>=0; i--){
            for (int j=h.size()-1; j>=0; j--){
                if (c.get(i).getName().equals(s)){
                    c.remove(i);
                    h.removeElement(j);
                    break;         
                }
            }
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean sellMushrooms(String s, int i){
        int a = 0; //Check how many cards is with the same card name
        int b = 0; //Counter for how many mushroom sold

        // Remove unnecessary stuff from input
        s = s.replaceAll("\\p{Punct}", "").replaceAll("\\s", "").toLowerCase();

        // Invalid input
        if (i<2){
            return false;
        }

        // Count how many "day mushroom" need to be sold
        for (int j=0; j<h.size();j++){  
            if (h.getElementAt(j).getName().equals(s)){
                if (h.getElementAt(j).getType() == CardType.NIGHTMUSHROOM){
                    a = a+2;
                }
                else if (h.getElementAt(j).getType() == CardType.DAYMUSHROOM){
                    a++;
                }
            }
        }            

        if (a < i){     // Not enough cards to sell
            return false;
        }

        // Sell night mushroom first
        for (int k=0; k<h.size(); k++){
            if (h.getElementAt(k).getName().equals(s)){
                if (h.getElementAt(k).getType() == CardType.NIGHTMUSHROOM){
                    addSticks(2*((Mushroom)h.getElementAt(k)).getSticksPerMushroom());
                    h.removeElement(k);
                    b = b+2;
                    break;
                
                    // if (b > i-2){
                    //     break;
                    // }
                }
            }
        }
        
        // Sell day mushroom
        if (b<i){
            for (int k=h.size()-1; k>=0; k--){
                if (h.getElementAt(k).getName().equals(s)){
                    if (h.getElementAt(k).getType() == CardType.DAYMUSHROOM){
                        // c = ((Mushroom)h.getElementAt(k)).getSticksPerMushroom();
                        addSticks(((Mushroom)h.getElementAt(k)).getSticksPerMushroom());
                        h.removeElement(k);
                        b++;

                        if (b==i){
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean putPanDown(){
    for (int i=0; i<h.size(); i++){
        if (h.getElementAt(i).getType() == CardType.PAN){
            h.removeElement(i);
            d.add(new Pan());
            return true;}
        }
        return false;
    }
}
