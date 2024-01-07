public class CustomCard extends Card{
    public CustomCard(Color cardColor, int cardCode, int cardScore = 0) {
        super(cardColor, cardCode, cardScore);
    }

    public String toString(int lineNumber) {
        switch (lineNumber) {
            case 1:
            case 5:
                return (Color.WHITE) + "•~~~~~~~•" + (Color.RESET);

            case 2:
                return (Color.WHITE) + "|       |" + (Color.RESET);

            case 3:
                return  ((Color.WHITE) + "|" + (Color.RED) + "C" +
                        (Color.YELLOW) + "us" + (Color.GREEN) + "to" +
                        (Color.BLUE) + "m " + (Color.WHITE) + "|" + (Color.RESET));

            case 4:
                return (Color.WHITE) + "|       ︎|" + (Color.RESET);

            case 6:
                return (Color.WHITE) + "code: " + super.getCardCode() + (Color.RESET);
        }
        return null;
    }
}
