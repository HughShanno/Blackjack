Author: Hugh Shanno

Title: Blackjack

This project is an object oriented blackjack games played in the command line.

Games are loaded from a text file placed in the same folder as the game itself. Whichever text file is used for a specific game is determined through commandline input.

Text files should be written in the form:

name,amountofmoney

Where the amount of money is an integer value and the name is a string.
One pairing of name and money amount per line in the text file, no other text should exist

Access the game through the commandline with the text file as the required first argument and an optional second argument that if it is not empty will assign a player to be the dealer at random. Otherwise, the dealer will be played by a computer.

Example 1: java Blackjack game1.txt dealer
Example 2: java Blackjack game1.txt

Each player will be able to "hit" or pass in turn, with the dealer playing following the conclusion of all players. Winnings are then distributed, and players are able to decide whether or not they want to play another round.
