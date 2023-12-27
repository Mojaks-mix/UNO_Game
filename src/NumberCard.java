public class NumberCard extends Card{
    // ~~~~~~~~ Attributes ~~~~~~~~
    private int cardNumber;

    // ~~~~~~~~ Methods ~~~~~~~~
    public NumberCard(int cardNumber, Color cardColor, int cardCode) {
        super(cardColor, cardCode);
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {return cardNumber;}
}
