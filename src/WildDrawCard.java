public class WildDrawCard extends Card{
    public WildDrawCard(int cardCode) {
        super(Color.WHITE, cardCode, 20);
    }
    public String toString(int lineNumber) {
        switch (lineNumber) {
            case 1:
            case 5:
                return (Color.WHITE) + "•~~~~~~~•" + (Color.RESET);

            case 2:
                return (Color.WHITE) + "|+4     |" + (Color.RESET);

            case 3:
                return  (Color.WHITE) + "|" + (Color.RED) + "W " +
                        (Color.YELLOW) + "i " + (Color.GREEN) + "l " +
                        (Color.BLUE) + "d" + (Color.WHITE) + "|" + (Color.RESET);

            case 4:
                return (Color.WHITE) + "|     +4|" + (Color.RESET);

            case 6:
                return (Color.WHITE) + "code: " + super.getCardCode() + (Color.RESET);
        }
        return null;
    }
}
