import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // ~~~~~~~~ Attributes ~~~~~~~~

    public static final String[] STATUS = {"Playing","UNO!","Winner"};

    protected String playerName;

    protected ArrayList<Card> playerCards;

    protected String playerStatus;//the status: Playing, UNO!, and Winner


    // ~~~~~~~~ Methods ~~~~~~~~

    public Player(String name) {
        this.playerName = name;
        this.playerStatus = Player.STATUS[0];
        ArrayList<Card> playerCards = new ArrayList<>();
    }

    private void updateStatus() {
        if(playerCards.size() == 1)
            playerStatus = Player.STATUS[1];
        else if (playerCards.size() == 0)
            playerStatus = Player.STATUS[2];
    }

    public String getStatus() {return this.playerStatus;}

    public String getName() {return playerName;}

    public void addCard(Card c){playerCards.add(c);}

    public Card removeCard(int cardCodeToRemove) {
        Card cardToRemove = null;
        for (Card card: playerCards)
        {
            if (card.getCardCode() == cardCodeToRemove)
            {
                cardToRemove = card;
                this.updateStatus();
                break;
            }
        }

        playerCards.remove(cardToRemove);

        return cardToRemove;
    }

    public boolean haveCard(int cardCode) {
        for (Card card: playerCards) {
            if (card.getCardCode() == cardCode)
                return true;
        }

        return false;
    }
}
