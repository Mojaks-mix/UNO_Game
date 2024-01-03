public class Draw2Card extends Card{
    // ~~~~~~~~ Methods ~~~~~~~~
    public Draw2Card(Color cardColor, int cardCode) {super(cardColor, cardCode, 20);}

    @Override
    public String toString(int lineNumber) {
        switch (lineNumber) {
            case 1:
            case 5:
                return cardColor + "•~~~~~~~•" + (Color.RESET);

            case 2:
                return cardColor + "|+2     |" + (Color.RESET);

            case 3:
                return cardColor + "| Draw2 |" + (Color.RESET);

            case 4:
                return cardColor + "|     +2|" + (Color.RESET);

            case 6:
                return (Color.WHITE) + "code: " + super.getCardCode() + (Color.RESET);
        }
        return null;
    }
}
