import java.util.*;

public class Dealer extends Participant {

  /* Constructs an AI Dealer
  * @params int money to give the Dealer
  * uses super constructor with a set name and
  * the given money.
  */
  public Dealer(int money) {
    super("Dealer", money);
  }

  /* @params string name, int money
  * constructs a dealer object that is controlled
  * by a player.
  * uses super constructor with name and money
  * as arguments.
  */
  public Dealer(String name, int money) {
    super(name, money);
  }

  /* @params Card newCard
  * adds newCard to the dealer's hand
  * if the hand is empty, newCard is dealt faceDown
  * @returns None
  */
  public void addCard(Card newCard) {
    if (hand.size() == 0) {
      newCard.setFaceUp(false);
    }
    hand.add(newCard);
    handValue += newCard.getValue();
    numCards++;
  }

  /* @params Player p
  * adds p's current bet to the dealer's money
  * and then resets p's bet to 0
  */
  public void collect(Player p){
    money += p.getBet();
    p.setBet(0);
  }

  /* @params int cash
  * subtracts cash from the dealer's money
  */
  public void payout(int cash) {
    money -= cash;
    if (money <= 0) {
      money = 1000;
    }
  }

  /* flips the dealer's first card face up
  */
  public void flipUp() {
    hand.get(0).setFaceUp(true);
  }

}