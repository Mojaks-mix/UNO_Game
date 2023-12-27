import java.util.Scanner;

public class Main {
    static String[][] xoblocks = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

    static int setXOBlocks(String[][] tempXOBlocks, String blockNumber, String xoValue) {
        int z = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tempXOBlocks[i][j].equals(blockNumber)) {
                    tempXOBlocks[i][j] = xoValue;
                    return z = 1;
                }
            }
        }
        z = 0;
        return z;
    }

    static void printXOBlocks() {
        for (int i = 0; i < 3; i++) {
            System.out.println("---------------");
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        System.out.print("|  " + xoblocks[i][j] + " |  ");
                        break;
                    case 1:
                        System.out.print(xoblocks[i][j] + " |  ");
                        break;
                    case 2:
                        System.out.println(xoblocks[i][j] + " |");
                }
            }
        }
        System.out.println("---------------");
    }

    public static void main(String[] args){
        printXOBlocks();
        Scanner scanner = new Scanner(System.in);
        for (int x = 0; x < 10; x++) {
            String blockNumber;
            String xoValue;

            System.out.println("Please choose the block number:");
            blockNumber = scanner.next();
            System.out.println("Please choose X or O:");
            xoValue = scanner.next();

            // Clear console screen (alternative to system("CLS") in C++)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            setXOBlocks(xoblocks, blockNumber, xoValue);
            printXOBlocks();
        }
    }
}