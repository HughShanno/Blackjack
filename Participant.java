import java.util.*;

public class Participant {
  protected List<Card> hand;
  protected int handValue;
  protected int money;
  protected String name;
  protected int numCards;

  /* @params String n, int m
  * constructs a new participant with name n
  * and money m, with handValue 0 and an
  * empty hand.
  */
  public Participant(String n, int m) {
    hand = new ArrayList<Card>();
    handValue = 0;
    money = m;
    name = n;
    numCards = 0;
  }

  /* returns the name of the participant
  */
  public String getName() {
    return name;
  }

  // returns the money of the participant
  public int getMoney() {
    return money;
  }

  // returns the value of the participant's hand
  public int getHandValue() {
    return handValue;
  }

  // clears the participant's hand and 
  // resets their hand value to 0
  public void clear() {
    hand.clear();
    handValue = 0;
  }
  // @returns the number of cards in the
  // participant's hand
  public int getNumCards() {
    return numCards;
  }
  
  /* @params Card newCard
  * adds newCard to the participant's hand
  * adds the value of the new Card to the 
  * participant's handValue
  * searches for an ace if the handvalue exceeds 21
  * and lowers the handValue accordingly
  */
  public void hit(Card newCard) {
    hand.add(newCard);
    handValue += newCard.getValue();
    numCards++;
    boolean temp = false;
    if (handValue > 21) {
      for (Card card : hand) {
        if (card.getValue() == 11 && !temp) {
          card.setValue(1);
          handValue -= 10;
          temp = true;
        }
      }
    }
  }

  /* Prints out the participant's hand
  */
  public void printHand() {
    for (Card card: hand) {
      if (card.getFaceUp()) {
        System.out.println(card.toString());
      } else {
        System.out.println("Face Down Card");
      }
    }
  }

  /* prints out the last card in the participant's hand
  */
  public void printLast() {
    System.out.println(hand.get(hand.size()-1).toString());
  }
}