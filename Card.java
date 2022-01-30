public class Card {
  private String suit;
  private int value;
  private boolean faceUp;
  private String face;

  /* Constructs a Card object
  * @params: card suit, card face value
  * creates a card with a suit, a face value,
  * and a blackjack value based on its face value.
  * card is automatically face up.
  */
  public Card(String s, String num) {
    suit = s;
    face = num;
    if (face.equals("Ace")) {
      value = 11;
    } else if (face.equals("King") || face.equals("Queen") || face.equals("Jack")) {
      value = 10;
    } else {
      value = Integer.parseInt(face);
    }
    faceUp = true;
  }

  /* returns the suit of the card
  */
  public String getSuit() {
    return suit;
  }

  /* returns the blackjack value of the card
  */
  public int getValue() {
    return value;
  }

  /* sets the blackjack value of the card
  * (only used in the case of an Ace).
  */
  public void setValue(int v) {
    value = v;
  }

  /* returns whether the card is faceUp
  */
  public boolean getFaceUp() {
    return faceUp;
  }

  /* @params
  * boolean bool (what to set faceUp to)
  * sets faceup to equal bool
  */
  public void setFaceUp(boolean bool) {
    faceUp = bool;
  }

  /* Returns a string containing the 
  * name of the card
  */
  public String toString() {
    String myString = face + " of " + suit;
    return myString;
  }

}