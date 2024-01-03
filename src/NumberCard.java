public class NumberCard extends Card{
    // ~~~~~~~~ Attributes ~~~~~~~~
    private int cardNumber;

    // ~~~~~~~~ Methods ~~~~~~~~
    public NumberCard(int cardNumber, Color cardColor, int cardCode) {
        super(cardColor, cardCode, cardNumber);
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {return cardNumber;}

    @Override
    public String toString(int lineNumber) {
        switch (lineNumber) {
            case 1:
            case 5:
                return cardColor + "•~~~~~~~•" + (Color.RESET);

            case 2:
                return cardColor + "|" + cardNumber + "      |" + (Color.RESET);

            case 3:
                return cardColor + "|   " + cardNumber + "   |" + (Color.RESET);

            case 4:
                return cardColor + "|      " + cardNumber + "|" + (Color.RESET);

            case 6:
                return (Color.WHITE) + "code: " + super.getCardCode() + (Color.RESET);
        }
        return null;
    }
}
