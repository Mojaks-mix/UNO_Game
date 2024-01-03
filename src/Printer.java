import java.util.Scanner;

        public class Printer{
                // ~~~~~~~~ Attributes ~~~~~~~~
                private static final String INDENT = "\t\t\t      ";


                // ~~~~~~~~ Methods ~~~~~~~~
                public static void calibrate(Scanner finish) {
                    clear();

                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println(Color.RESET + INDENT + "\b\b\b\b\b\b\b" +
                            "please use (cntrl, +) and (cntrl, -) to fit this line to your screen");

                    System.out.println("<~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>");
                    finishEnter(finish);
                }

                public static void printMenu() {
                    clear();
                    System.out.println(Color.RESET);

                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println(INDENT + "\t       " + "🃏 <@••••••. UNO Game .••••••@> 🀄️");
                    System.out.print("\n\n");
                    System.out.println(INDENT + "\t      " + "            1. New game");
                    System.out.print("\n");
                    System.out.println(INDENT + "\t      " + "              2. exit");
                    System.err.println(INDENT + "\t       " + "🀄️ <@••••••••••••••••••••••••@> 🃏");
                    System.out.print("\n\n");
                    System.out.print(  INDENT + "\t      " + "                '_'? ");
                }

                public static void getNumberOfThePlayers() {
                    clear();

                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.print(INDENT + "\b\b\b" +
                            "Please enter the number of the players (1 < n < 10):  ");
                }

                public static void getPlayerName(int playerNum) {
                    clear();

                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.print(INDENT + "\b\b\b\b\b\b\b\b\b\b\b" +
                            "Please type the name of the player" + playerNum +":  ");
                }

                public static void getPlayerChoosenColor() {
                    System.out.print("You uesed a wild card. now choose a color ( " + Color.WHITE +
                            Color.RED_B + " 1 " + Color.RESET+
                            ", " + Color.WHITE +
                            Color.YELLOW_B + " 2 " + Color.RESET+
                            ", " + Color.WHITE +
                            Color.GREEN_B + " 3 " + Color.RESET +
                            ", " + Color.WHITE +
                            Color.BLUE_B + " 4 " + Color.RESET +
                            " ) :   ");
                }

                public static void printGameBoard(Card theCardOnTheBoard, Color colorOnTheBoard) {
                    clear();
                    for (int j = 1; j <= 5; j++) {
                        System.out.print(INDENT + "\t\t\b" + theCardOnTheBoard.toString(j));
                        if (j == 3)
                            System.out.print("    " + colorOnTheBoard + "table color" + (Color.RESET));

                        System.out.print("\n");
                    }

                    System.out.print("\n\n\n");
                }

                public static void printPlayerCards(Player player) {

                    for (int j = 0; j < player.getPlayerCards().size(); j += 9) {
                        for (int i = 1; i <= 6; i++) {
                            System.out.print("\t\b");
                            for (int k = j; (k < j+9) && (k < player.getPlayerCards().size()); k++)
                            {
                                System.out.print(player.getPlayerCards().get(k).toString(i) + "  ");

                                if (i == 6)
                                    //      space = length of the previous line - current line length
                                    for(int space =  player.getPlayerCards().get(k).toString(i-1).length() - player.getPlayerCards().get(k).toString(i).length();
                                        space > 0; space--)
                                        System.out.print(" ");
                            }
                            System.out.print("\n");
                        }
                        System.out.print("\n");
                    }
                }

                public static void getPlayerChoice(Player player) {
                    System.out.print("\nhey " + Color.BLACK_BRIGHT_B +
                                     player.getName() + Color.RESET +
                                    " choose a Card (enter the code of your chosen card):  ");
                }

                public static void printScores(CircularQueue<Player> players, Scanner finish) {
                    clear();
                    System.out.print(Color.WHITE  + "\n\n\n\n\n\n\n");

                    System.out.println(INDENT + "\b\b\b\b\b\bPlayers  name |  Players  Score         Number Of Players Cards");
                    System.out.println(INDENT + "\b\b\b\b\b\b–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");

                    String name = "";
                    Player currPlayer;
                    for (int index = 0; index < players.size(); index++) {
                        currPlayer = players.peek();

                        if(currPlayer.getStatus() == Player.STATUS[2])
                            name = currPlayer.getName();

                        System.out.printf("%s\b\b\b\b\b\b%13s :  %7d                      %8d\n", INDENT,
                                currPlayer.getName(),
                                currPlayer.getScore(),
                                currPlayer.getPlayerCards().size());
                        players.serve();
                    }
                    System.out.print("Congratulation "+ name + " is the winner" +
                            "\n\n\n\n" + Color.RESET);
                    finishEnter(finish);
                }

                public static void inValidInputError(Scanner finish) {
                    System.out.println(INDENT + "\t         " +
                                        Color.YELLOW + Color.RED +
                                        "<@ ! YOUR INPUT IS INVALID ! @>" +
                                        Color.RESET);
                    finishEnter(finish);
                }

                public static void noChoiceError(Scanner finish) {
                    System.out.println("\t\t\t" +
                                        Color.YELLOW + Color.RED +
                                        "<@ ! YOU CAN'T CHOOSE ANY CARD. ONE CARD HAVE GIVEN TO YOU ! @>" +
                                        Color.RESET);

                    finishEnter(finish);
                }


                // this method wait until player push 'enter' bottom
                private static void finishEnter(Scanner inputsSource) {
                    System.out.println(INDENT + "\t\t    " + "(press enter to continue)");
                    inputsSource.nextLine();
                }

                // this method clear the terminal
                private static void clear() {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
        }
