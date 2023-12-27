import java.util.ArrayList;
import java.util.Random;

public abstract class Game {
    // ~~~~~~~~ Attributes ~~~~~~~~
    protected CircularQueue<Player> players = new CircularQueue<>(10);
    protected static ArrayList<Card> gameCards = new ArrayList<>();
    protected static int cardCode = 0;
    protected int initialNumberOfCards;//for player
    protected Card groundCard;


    // ~~~~~~~~ Methods ~~~~~~~~
    private void setGroundCard(){
        groundCard = gameCards.get(0);
        gameCards.remove(0);
    }

    public abstract void play();
    /*
        //set the players
        if(this.validNumberOfPlayers()){//start the game
            //ground card , turns and print them  until one wins
        }
        else
            System.out.println("There isn't enough players in the game!");
   */

    private void addPlayer(String playerName) {//there might be some changes here that not output provided
        if (!players.isFull()) {
            players.enqueue(new Player(playerName));
        }
        else
            System.out.println("Sorry, the game party is full!");
    }

    private boolean validNumberOfPlayers(){
        if(players.isFull() || players.size() < 2)
            return false;
        return true;
    }

    public void distributeCards() {
        for (int n = 0; n < this.initialNumberOfCards; n++) {
            for (int i = 0; i < players.size(); ++i) {
                // get the card to the player
                Player p = players.serve();
                p.addCard(gameCards.get(0));
                gameCards.remove(0);
            }
        }
    }

    public boolean isThereWinner(){
        for (int i = 0; i < players.size(); ++i) {
            // get the card to the player
            Player p = players.serve();
            if(p.getStatus() == Player.STATUS[2])
                return true;
        }
        return false;
    }

    public static void preparationGameCards() {
        makeGameCards();
        suffleCards();
    }

    private static void makeGameCards() {
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

    private static void makeCards(Color cardColor) {
        // set the first and second set of cards
        for(int i = 0; i < 2; ++i) {
            for(int j = i; j < 10; ++j){
                gameCards.add(new NumberCard(j, cardColor, ++cardCode));
            }
            // set the skip cards
            gameCards.add(new SkipCard(cardColor, ++cardCode));

            // set the reverse cards
            gameCards.add(new ReverseCard(cardColor, ++cardCode));

            // set the draw2 cards
            gameCards.add(new Draw2Card(cardColor, ++cardCode));
        }
    }

    private static void suffleCards() {
        // hold the code number of the suffled cards
        ArrayList<Integer> suffledCards = new ArrayList<>();

        // hold the cards for swap
        Card holdCard;

        // for make random numbers
        Random rand = new Random();

        // hold the random numbers
        int randNum1 = 0, randNum2 = 0;


        // shuffle 80 cards (40 * 2 cards)
        for (int n = 0; n < 40; n++)
        {
            //   * generate the first random number *
            // while find a valid number
            while (true) {
                // generate the random number
                randNum1 = rand.nextInt(gameCards.size());

                // check the generated number
                if (!suffledCards.contains(randNum1))
                    break;
            }
            suffledCards.add(randNum1);


            //   * generate the second random number *
            // while find a valid number
            while (true) {
                // generate the random number
                randNum2 = rand.nextInt(gameCards.size());

                // check the generated number
                if (!suffledCards.contains(randNum2) && randNum2 != randNum1)
                    break;
            }
            suffledCards.add(randNum2);

            // swap the cards
            holdCard = gameCards.get(randNum1);
            gameCards.set(randNum1, gameCards.get(randNum2));
            gameCards.set(randNum2, holdCard);
        }
    }

    public void reset() {
        players = new CircularQueue<>(10);
        gameCards = new ArrayList<>();
       // penaltyCards = new ArrayList<>();
    }
}
