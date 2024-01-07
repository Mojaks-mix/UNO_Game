import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Game {
    // ~~~~~~~~ Attributes ~~~~~~~~
    protected static Scanner inputs = new Scanner(System.in);
    protected CircularQueue<Player> players = new CircularQueue<>(10);
    protected  ArrayList<Card> gameCards = new ArrayList<>();
    protected  ArrayList<Card> drawnCards = new ArrayList<>();
    protected  ArrayList<Card> discardPile = new ArrayList<>();
    protected  int cardCode = 0;
    protected int initialNumberOfCards = 5;//for player
    protected Card groundCard;
    protected Color groundColor;


    // ~~~~~~~~ Methods ~~~~~~~~


    //~~~~~~~~~~~~~~~~~~   Your Methods ~~~~~~~~~~~~~~~~
    protected abstract void play();//Look for it in the documentation
    protected boolean isCustomValid(){
        return false;
    }//when the card is valid to be played
    protected void makeCustomCards(){}//make sure to increment the cardCode
    protected void applyCustom(){}//The action that will be done
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~Check the Valid position of a card~~~~~~~
    private  boolean checkChoose(Card playerChoosenCard, Player player){
        // check the wild cards
        if (playerChoosenCard instanceof WildCard)
            return true;

        // check the wild draw cards
        if (playerChoosenCard instanceof WildDrawCard) {
            return checkWildCard(playerChoosenCard, player);
        }

        // check draw2 cards if it is not the first draw two "draw 2 stacked!!!"
        if (groundCard instanceof Draw2Card && drawnCards.size() != 0) {
            checkDraw2Card(playerChoosenCard, player);
        }

        // revers card case "stacked!!!"
        if (groundCard instanceof ReverseCard && playerChoosenCard instanceof ReverseCard)
            return true;

        // check the color of cards, this is where the first pile of the stack
        if (playerChoosenCard.getCardColor() == groundColor)
            return true;

        // check the number of number cards
        if (playerChoosenCard instanceof NumberCard && groundCard instanceof NumberCard)
            if (((NumberCard) playerChoosenCard).getCardNumber() == ((NumberCard) groundCard).getCardNumber())
                return true;

        // check the skip cards
        if (playerChoosenCard instanceof SkipCard && groundCard instanceof SkipCard)
            return true;

        if(playerChoosenCard instanceof CustomCard)
            return isCustomValid();

        return false;
    }

    protected boolean checkWildCard(Card playerChoosenCard, Player player){
        if(player.getPlayerCards().size() == 1)//special case
            return true;
        for (Card card: player.getPlayerCards())
        {
            if (card instanceof WildDrawCard)
                continue;

            if (checkChoose(card, player))//if there is other valid card the player can't play wild draw 4
                return false;
        }


        return true;
    }

    protected boolean checkDraw2Card(Card playerChoosenCard, Player player){
            if (playerChoosenCard instanceof Draw2Card)
                return true;
            else
                return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~Applying the action of the Card~~~~~~~~
    private  void applyChoose(Card playerChoosenCard, Color choosenColor, Player player){
        changeGroundCard(playerChoosenCard);
        groundColor = choosenColor;

        if (playerChoosenCard instanceof WildCard){
            chooseGroundColor(playerChoosenCard,player);
        }

        else if (playerChoosenCard instanceof WildDrawCard) {
            chooseGroundColor(playerChoosenCard,player);
            addToBeDrawn(4);
        }

        else if (playerChoosenCard instanceof Draw2Card) {
            addToBeDrawn(2);
        }

        else if (playerChoosenCard instanceof SkipCard)
            skipTurn();

        else if (playerChoosenCard instanceof ReverseCard) {
            reverse();
        }

        else if(playerChoosenCard instanceof CustomCard){
            applyCustom();
        }
    }

    protected void applyDraw2OnCurrentPlayer(Player currentPlayer){
        boolean check = false;
        for (Card card: currentPlayer.getPlayerCards()) {
            if (card instanceof Draw2Card) {
                check = true;
                break;
            }
        }

        if (!check) {
            int n = drawnCards.size();
            drawCards(currentPlayer,n);
        }
    }

    protected void applyWildDrawOnCurrentPlayer(Player currentPlayer){
        players.serve();
        currentPlayer = players.peek();
        int n = drawnCards.size();
        drawCards(currentPlayer, n);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~~Common Actions in UNO~~~~~~~~~~~~~~~~~
    protected void reverse(){
        if (!players.isEmpty()) {
            if (players.size() > 1) {
                players.serve();
                int size = players.size();
                Player[] tempArray = new Player[size];

                int index = 0;
                while (!players.isEmpty()) {
                    tempArray[index++] = players.dequeue();
                }

                for (int i = size - 1; i >= 0; i--) {
                    players.enqueue(tempArray[i]);
                }
            }
        }
    }

    protected void drawCards(Player p, int n){
        for(int i = 0; i < n; ++i) {
            p.addCard(drawnCards.get(0));
            drawnCards.remove(0);
        }
    }

    protected void skipTurn(){
        players.serve();
    }

    protected void addToBeDrawn(int number){
        for (int n = 0; n < number; n++) {
            drawnCards.add(gameCards.get(0));
            gameCards.remove(0);
        }
    }

    protected  void shuffleCards(ArrayList<Card> sCards) {
        ArrayList<Integer> shuffledCards = new ArrayList<>();
        Card holdCard;
        Random rand = new Random();
        int randNum1 = 0, randNum2 = 0;

        // shuffle 80 cards (40 * 2 cards)
        for (int n = 0; n < 40; n++) {
            while (true) {
                randNum1 = rand.nextInt(sCards.size());
                if (!shuffledCards.contains(randNum1))
                    break;
            }
            shuffledCards.add(randNum1);

            while (true) {
                randNum2 = rand.nextInt(sCards.size());
                if (!shuffledCards.contains(randNum2) && randNum2 != randNum1)
                    break;
            }
            shuffledCards.add(randNum2);

            holdCard = sCards.get(randNum1);
            sCards.set(randNum1, sCards.get(randNum2));
            sCards.set(randNum2, holdCard);
        }
    }

    protected void distributeCards(ArrayList<Card> dist) {
        for (int n = 0; n < this.initialNumberOfCards; n++) {
            for (int i = 0; i < players.size(); ++i) {
                // get the card to the player
                Player p = players.serve();
                p.addCard(dist.get(0));
                dist.remove(0);
            }
        }
    }

    protected void chooseGroundColor(Card playerChoosenCard, Player currentPlayer){
       String holdInput;

       // while player choose a correct input
       while (true) {
           // ask the player chosen color
           Printer.getPlayerChoosenColor();
           holdInput = inputs.nextLine();

           // check the player input
           if (holdInput.length() == 1 && holdInput.charAt(0) > '0' && holdInput.charAt(0) < '5')
               break;


           // say that player input is incorrect
           Printer.inValidInputError(inputs);

           // show the board,and current player cards
           Printer.printGameBoard(groundCard, groundColor);
           Printer.printPlayerCards(currentPlayer);
       }

       switch (holdInput) {
           case "1":
               groundColor = Color.RED;
               break;

           case "2":
               groundColor = Color.YELLOW;
               break;

           case "3":
               groundColor = Color.GREEN;
               break;

           case "4":
               groundColor = Color.BLUE;
               break;
       }

   }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~~The main logic of the game~~~~~~~~~~~~
    protected  void runGameTurns() {
        //  * Required variables *
        Player currentPlayer;
        Card playerChoosenCard;
        String holdInput; // hold the player inputs

        setGroundCard();//first card on the ground

        while (!isThereWinner()) {
            //for the game to keep going
            if(gameCards.isEmpty()) {
                shuffleCards(discardPile);
                gameCards = discardPile;
                discardPile = new ArrayList<Card>();
            }

            currentPlayer = players.peek();

            //the draw 2 cards case
            if (drawnCards.size() != 0) {
                applyDraw2OnCurrentPlayer(currentPlayer);
                // go to the next player
                players.serve();
                continue;
            }

            // check the player cards if his turn will be "pass"
            if (!checkPlayerCards(currentPlayer)) {
                // get a card to player
                currentPlayer.addCard(gameCards.get(0));
                gameCards.remove(0);

                // check the player cards again
                if (!checkPlayerCards(currentPlayer)) {
                    //show the board,and current player cards
                    Printer.printGameBoard(groundCard, groundColor);
                    Printer.printPlayerCards(currentPlayer);
                    Printer.noChoiceError(inputs, currentPlayer);

                    // go to the next player
                    players.serve();
                    continue;
                }
            }

            //while turn choice is valid
            while (true) {
                // while player choose a valid card code
                while (true) {
                    // show the board, and current player cards
                    Printer.printGameBoard(groundCard, groundColor);
                    Printer.printPlayerCards(currentPlayer);


                    // ask the player choice
                    Printer.getPlayerChoice(currentPlayer);
                    holdInput = inputs.nextLine();

                    // check player choice
                    if (holdInput.length() > 0 && holdInput.length() < 4 && isInt(holdInput))
                        if (Integer.valueOf(holdInput) <= cardCode  &&  Integer.valueOf(holdInput) > 0)
                            if (currentPlayer.haveCard(Integer.valueOf(holdInput)))
                                break;


                    // say that player input is incorrect
                    Printer.inValidInputError(inputs);
                }

                // get player chosen card
                playerChoosenCard = currentPlayer.removeCard(Integer.valueOf(holdInput));

                // check the player chosen card
                if (checkChoose(playerChoosenCard, currentPlayer)) {
                    applyChoose(playerChoosenCard, playerChoosenCard.getCardColor(), currentPlayer);
                    break;
                }

                // give back the card to the player
                currentPlayer.addCard(playerChoosenCard);

                // say that player input is incorrect
                Printer.inValidInputError(inputs);
            }

            // wild draw case
            if (playerChoosenCard instanceof WildDrawCard){
                applyWildDrawOnCurrentPlayer(currentPlayer);
                continue;
            }

            //move to the next player
            players.serve();
        }

        //return or print the winner and other players info
        Printer.printScores(players, inputs);
    }

    private boolean checkPlayerCards(Player player) {
        for (Card card: player.getPlayerCards())
        {
            if (checkChoose(card, player))
                return true;
        }

        return false;
    }

    private void setGroundCard(){
        groundCard = gameCards.get(0);
        groundColor = groundCard.getCardColor();
        gameCards.remove(0);
        discardPile.add(groundCard);
    }

    private void changeGroundCard(Card playerChosenCard){
        groundCard = playerChosenCard;
        discardPile.add(playerChosenCard);
    }

    protected void addPlayer(String playerName) {
        if (!players.isFull()) {
            players.enqueue(new Player(playerName));
        }
    }

    private boolean isThereWinner(){
        for (int i = 0; i < players.size(); ++i) {
            Player p = players.serve();
            if(p.getStatus() == Player.STATUS[2])
                return true;
        }
        return false;
    }

    protected  void preparationGameCards() {
        makeGameCards();
        shuffleCards(gameCards);
    }

    private  void makeGameCards() {
        //make custom cards
        makeCustomCards();
        // make red cards
        makeCards(Color.RED);
        // make yellow cards
        makeCards(Color.YELLOW);
        // make green cards
        makeCards(Color.GREEN);
        // make blue cards
        makeCards(Color.BLUE);
        // make wild cards
        for (int n = 0; n < 4; n++)
            gameCards.add(new WildCard(++cardCode));
        // make wild draw cards
        for (int n = 0; n < 4; n++)
            gameCards.add(new WildDrawCard(++cardCode));
    }

    private  void makeCards(Color cardColor) {
        // set the first and second set of cards
        for(int i = 0; i < 2; ++i) {
            for(int j = i; j < 10; ++j){
                gameCards.add(new NumberCard(j, cardColor, ++cardCode));
            }
            gameCards.add(new SkipCard(cardColor, ++cardCode));// set the skip cards
            gameCards.add(new ReverseCard(cardColor, ++cardCode));// set the reverse cards
            gameCards.add(new Draw2Card(cardColor, ++cardCode));// set the draw2 cards
        }
    }

    protected void reset() {
        players = new CircularQueue<>(10);
        gameCards = new ArrayList<>();
        drawnCards = new ArrayList<>();
        discardPile = new ArrayList<>();
    }

    private  boolean isInt(String stringToCheck) {
        for (int n = 0; n < stringToCheck.length(); n++) {
            if (!('0' <= stringToCheck.charAt(n) && stringToCheck.charAt(0) <= '9'))
                return false;
        }

        return true;
    }
}
