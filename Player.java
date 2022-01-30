import java.util.*;

public class Player extends Participant {
  private int curBet;
  private boolean win;

  /* @params String name, int money
  * Constructs a player object
  * uses super constructor with arguments
  * name and money
  * resets curBet and sets win to false
  */
  public Player(String name, int money) {
    super(name, money);
    curBet = 0;
    win = false;
  }

  /* @params int cash
  * subtracts cash from the player's money
  * and adds it to the current bet of the player
  */
  public void bet(int cash) {
    if (cash <= 0) {
      System.out.println("You must bet some amount of money");
      return;
    }
    if (cash > money) {
      curBet += money;
      money = 0;
    } else {
      money -= cash;
      curBet += cash;
    }
  }

  // @returns the current bet of the player
  public int getBet() {
    return curBet;
  }

  /* @params int bet
  * sets the current bet of the player to bet
  */
  public void setBet(int bet) {
    if (bet < 0) {
      curBet = 0;
    } else {
      curBet = bet;
    }
  }

  /* @params card newCard
  * If it is the player's first turn, allows them
  * to double. Double's their current bet and
  * hits once
  */
  public void Double(Card newCard) {
    bet(curBet);
    hit(newCard);
  }

  // @returns whether or not a player has won yet
  public boolean getWin() {
    return win;
  }

  // sets win to false
  public void unWin() {
    win = false;
  }

  /* adds to the player's money, resets their current
  * bet to zero, sets win to true,
  * prints out celebratory message
  */
  public void win() {
    win = true;
    System.out.println(name + " wins!");
    money += curBet*2;
    curBet = 0;
  }

  /* adds current bet to money
  * resets current bet to 0
  * prints out tie message
  */
  public void tie() {
    System.out.println(name + " tied the dealer");
    money += curBet;
    curBet = 0;
  }

}