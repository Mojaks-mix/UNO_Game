public enum Color {
    BLACK("BLACK"), RED("RED"), YELLOW("YELLOW"), GREEN("GREEN"), BLUE("BLUE"), WHITE("WHITE");
    private String sColor;

    private Color(String c){c = sColor;}

    @Override
    public String toString() {return sColor;}
}
