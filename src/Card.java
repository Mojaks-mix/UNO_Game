public abstract class Card {
    // ~~~~~~~~ Attributes ~~~~~~~~

    protected int cardCode;// the code of the card. (UNO game has 108 cards)
    protected Color cardColor;
    private int cardScore;

    // ~~~~~~~~ Methods ~~~~~~~~

    public Card(Color cardColor, int cardCode, int cardScore) {
        this.cardColor = cardColor;
        this.cardCode = cardCode;
        this.cardScore = cardScore;
    }

    public int getCardCode(){return this.cardCode;}

    public int getCardScore(){return this.cardScore;}

    public Color getCardColor() {return cardColor;}

    @Override
    public boolean equals(Object obj) {
        // check the pointers
        if (obj == this)
            return true;

        // check the class
        if (!(obj instanceof Card))
            return false;

        Card card = (Card) obj;
        return cardCode == card.cardCode;
    }

    public  abstract String  toString(int lineNumber);
}
