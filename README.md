Object oriented blackjack game written in Java by Hugh Shanno
Games are loaded from a text file placed in the same folder as the game itself. Whichever text file is used for a specific game is determined through commandline input.

Text files should be written in the form:
name,amountofmoney
Where the amount of money is an integer value and the name is a string.
One pairing of name and money amount per line in the text file, no other text should exist

Access the game through the commandline with the text file as the required first argument and an optional second argument that if it is not empty will assign a player to be the dealer at random. Otherwise, the dealer will be played by a computer.
