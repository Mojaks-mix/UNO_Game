public class MyGame extends Game{

    public boolean isCustomValid(){
        return true;
    }
    public void makeCustomCards(){
        for(int i = 0; i < 4; ++i)
            gameCards.add(new CustomCard(Color.RED,++cardCode,20));
    }
    public void applyCustom(){
        players.serve();
    }

    public void play(){
        // calibrate the font size of the terminal
        Printer.calibrate(inputs);

        //  * required variables *
        String holdInput;
        int numberOfPlayers;
        String newPlayerName;


        // while player choose exit option
        while (true) {
            // while player choose valid option
            while (true) {
                // show the game menu tho the player and get his/her choice
                Printer.printMenu();
                holdInput = inputs.nextLine();

                // check the player input
                if (holdInput.length() == 1 && (holdInput.charAt(0) == '1' || holdInput.charAt(0) == '2'))
                    break;
                else
                    Printer.inValidInputError(inputs);
            }


            switch (holdInput) {
                case "1":

                    // while the player choose a valid int
                    while (true) {
                        // get the player choice
                        Printer.getNumberOfThePlayers();
                        holdInput = inputs.nextLine();

                        // check the player input
                        Integer intHoldInputs = Integer.valueOf(holdInput);
                        if ((holdInput.length() == 1 || holdInput.length() == 2) && intHoldInputs > 0 && intHoldInputs < 10)
                            break;
                        else
                            Printer.inValidInputError(inputs);
                    }


                    // set the number of the players
                    numberOfPlayers = (int)holdInput.charAt(0) - (int)'0';

                    // get the players details
                    for (int n = 0; n < numberOfPlayers; n++)
                    {
                        // get the player name
                        Printer.getPlayerName(n+1);
                        newPlayerName = inputs.nextLine();
                        // create new player
                        addPlayer(newPlayerName);
                    }


                    // get the cards to the players
                    preparationGameCards();
                    distributeCards();

                    // run the game
                    runGameTurns();

                    // reset the game
                    reset();

                    break;

                case "2":
                    return;
            }
        }
    }
}

