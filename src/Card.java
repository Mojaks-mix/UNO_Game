public abstract class Card {
    // ~~~~~~~~ Attributes ~~~~~~~~

    protected int cardCode;// the code of the card. (UNO game has 108 cards)

    protected Color cardColor;

    // ~~~~~~~~ Methods ~~~~~~~~

    public Card(Color cardColor, int cardCode) {
        this.cardColor = cardColor;
        this.cardCode = cardCode;
    }

    public int getCardCode(){return this.cardCode;}

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
}
