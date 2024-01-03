public class SkipCard extends Card{
    public SkipCard(Color cardColor, int cardCode) {
        super(cardColor, cardCode, 20);
    }
    public String toString(int lineNumber) {
        switch (lineNumber) {
            case 1:
            case 5:
                return cardColor + "•~~~~~~~•" + (Color.RESET);

            case 2:
                return cardColor + "|//     |" + (Color.RESET);

            case 3:
                return cardColor + "|S K I P|" + (Color.RESET);

            case 4:
                return cardColor + "|     //|" + (Color.RESET);

            case 6:
                return (Color.WHITE) + "code: " + super.getCardCode() + (Color.RESET);
        }
        return null;
    }
}
