import java.util.*;
import java.io.*;

public class Blackjack {
  private List<Player> players;
  private Dealer dealer;
  private String fileName;
  private Queue<Card> deck;
  private boolean playerDealer;

  /* @params:
  * string file (file to load in), boolean deal 
  * (whether or not a player is a dealer)
  * Constructs a blackjack game.
  * Reads in the file to create all of the players
  * playing, and either creates an AI dealer or 
  * randomly assigns one of the players to 
  * be dealer.
  */
  public Blackjack(String file, boolean deal) {
    deck = new ArrayDeque<Card>();
    fileName = file;
    playerDealer = deal;
    players = new ArrayList<Player>();

    // reads in file and creates players
    Scanner scanner1 = null;
    File inputFile = new File(fileName);
    try {
      scanner1 = new Scanner(inputFile);
    } catch (FileNotFoundException e) {
      System.err.println(e);
      System.exit(1);
    }
    int count = 0;
    while (scanner1.hasNextLine() && count < 8) {
      String line = scanner1.nextLine();
      String[] myLine = line.split(",");
      players.add(new Player(myLine[0], Integer.parseInt(myLine[1]))); 
      count++;
    }

    // randomly assign a player to be dealer
    // if indicated in commandLine
    if (deal && players.size() > 1) {
      int toDeal = (int)Math.floor(Math.random() * (players.size()-1));
      dealer = new Dealer(players.get(toDeal).getName(), players.get(toDeal).getMoney());
      players.remove(toDeal);
      System.out.println(dealer.getName() + " is the dealer");
    } else {
      dealer = new Dealer(1000);
    }

  }

  /* @params None
  * @returns None
  * Creates and shuffles a deck of card objects
  */
  public void fillAndShuffle() {
    // create card objects
    deck.clear();
    List<String> suits = new ArrayList<String>();
    List<String> faces = new ArrayList<String>();
    Card[] cards = new Card[52];
    suits.add("Spades");
    suits.add("Clubs");
    suits.add("Hearts");
    suits.add("Diamonds");
    faces.add("2");
    faces.add("3");
    faces.add("4");
    faces.add("5");
    faces.add("6");
    faces.add("7");
    faces.add("8");
    faces.add("9");
    faces.add("10");
    faces.add("Jack");
    faces.add("Queen");
    faces.add("King");
    faces.add("Ace");
    int j = 0;
    for (String suit : suits) {
      for (String face : faces) {
        cards[j] = new Card(suit, face);
        j++;
      }
    }

    // shuffle card objects
    for (int k = cards.length - 1; k > 0; k--) {
      int swapIndex = (int)Math.floor(Math.random() * (k + 1));
      Card temp = cards[k];
      cards[k] = cards[swapIndex];
      cards[swapIndex] = temp;
    }

    // add cards to queue
    for (int i = 0; i < 52; i++) {
      deck.add(cards[i]);
    }
  }

  /* @params String file (the file that was used to 
  * create the game)
  * @returns None
  * Records the names and money of each player
  * to be used in the future
  */
  public void saveGame(String file) {
    try {
      FileWriter myWriter = new FileWriter(file);
      for (Player player : players) {
        String myString = "";
        myString = player.getName() + "," + player.getMoney() + "\n";
        myWriter.write(myString);
      }
      if (playerDealer) {
        String myString = "";
        myString = dealer.getName() + "," + dealer.getMoney() + "\n";
        myWriter.write(myString);
      }
      myWriter.close();
    } catch (IOException e) {
      System.err.println(e);
      System.exit(1);
    }
  }

  /* @params None
  * @returns None
  * Plays the game of blackjack for as many
  * rounds as the players want to.
  * Goes through each player's turn,
  * then the dealer's.
  * At the end, gives players the option to save
  * their game.
  */
  public void play() {
    Scanner scanner = new Scanner(System.in);
    String next = "continue";
    while (next.equals("continue")) {
      fillAndShuffle();
      dealer.clear();
      dealer.addCard(deck.poll());
      dealer.addCard(deck.poll());
      for (Player player : players) {
        player.clear();
        player.unWin();
        System.out.println("This is how much money " + player.getName() + " has: " + player.getMoney());
        System.out.println("How much would " + player.getName() +  " like to bet?");
        player.bet(Integer.parseInt(scanner.next()));
        player.hit(deck.poll());
        player.hit(deck.poll());
        if (player.getHandValue() == 21) {
          System.out.println("Blackjack!");
          player.printHand();
          dealer.payout(player.getBet());
          player.win();
        }
        System.out.println("");
      }
      for (Player player : players) {
        String pass = "";
        if (!player.getWin()) {
          System.out.println("It is " + player.getName() + "'s turn");
        }
        while (!pass.equals("stay") && !player.getWin()) {
          System.out.println("Dealer's Hand: ");
          dealer.printHand();
          System.out.println("Your Hand: ");
          player.printHand();
          System.out.println("hit, double, or stay?");
          pass = scanner.next();
          if (pass.equals("hit")) {
            player.hit(deck.poll());
          }
          if (pass.equals("double")) {
            if (player.getNumCards() > 2) {
              System.out.println("You can only double on the first turn!");
              return;
            } else {
              player.Double(deck.poll());
              System.out.println("This is your final hand");
              player.printHand();
              pass = "stay";
            }
          }
          if (player.getHandValue() > 21) {
            System.out.println("Oh No! The value of your hand is greater than 21! You lose!");
            System.out.println("Your hand: ");
            player.printHand();
            dealer.collect(player);
            pass = "stay";
          }
        }
        System.out.println("");
      }
      if (dealer.getName().equals("Dealer")) {
        dealer.flipUp();
        System.out.println("Dealer's hand: ");
        dealer.printHand();
        while (dealer.getHandValue() < 17) {
          dealer.hit(deck.poll());
          dealer.printLast();
        }
      } else {
        String pass = "";
        dealer.flipUp();
        System.out.println("It is the dealer's (" + dealer.getName() + "'s) turn");
        while (!pass.equals("stay")) {          
          System.out.println("Your Hand: ");
          dealer.printHand();
          System.out.println("hit or stay?");
          pass = scanner.next();
          if (pass.equals("hit")) {
            dealer.hit(deck.poll());
          }
          if (dealer.getHandValue() > 21) {
            System.out.println("Oh No! The value of your hand is greater than 21!");
            dealer.printHand();
            pass = "stay";
          }
        }
      }
      for (Player player: players) {
        if (player.getHandValue() > 21) {
          System.out.println(player.getName() + " has already lost.");
        } else if (player.getWin()){
          System.out.println(player.getName() + " already won with a blackjack!");
        } else if (dealer.getHandValue() > 21) {
          dealer.payout(player.getBet());
          player.win();
        } else if (dealer.getHandValue() < player.getHandValue()) {
          dealer.payout(player.getBet());
          player.win();
        } else if (dealer.getHandValue() == player.getHandValue()) {
          player.tie();
        } else {
          dealer.collect(player);
          System.out.println(player.getName() + " lost");
        }
      }
      System.out.println("Would you like to play another round?");
      System.out.println("continue or stop?");
      next = scanner.next();
    }
    System.out.println("Would you like to save your game? It will be saved to the same file that was loaded in.");
    System.out.println("yes or no?");
    String nextStep = scanner.next();
    if (nextStep.equals("yes")) {
      saveGame(fileName);
    }
    System.out.println("Thanks for Playing!");
    scanner.close();
  }


  public static void main(String[] args) {
    if (args.length == 0 || args.length > 2) {
      System.err.println("Java CommandLine Exception: incorrect number of arguments");
      System.exit(1);
    }
    if (args.length == 1) {
      Blackjack game = new Blackjack(args[0], false);
      game.play();
    } else if (args.length == 2) {
      Blackjack game = new Blackjack(args[0], true);
      game.play();
    }
  }

}