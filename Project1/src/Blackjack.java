import java.util.Scanner;
//Jayden McKenna -- jaydenmckenna@ufl.edu
public class Blackjack {
    public static void main(String[] args) {
        //input and deck
        Scanner input = new Scanner(System.in);
        P1Random rand = new P1Random();
        //starts game upon running program
        int game = 1;//player input based options
        int gameNumber = 1; //game counter
        int handValue = 0; //keeps track of player hand
        int dealerWin = 0, playerWin = 0, tie = 0; //keeps track of game stats

        //start of game
        System.out.println("START GAME #" + gameNumber);

        //loop for game until done
        while (game > Integer.MIN_VALUE && game != 4) {
            //option 3
            if (game == 3) {
                double winPercentage = (playerWin / ((playerWin + dealerWin + tie) * 1.0) * 100.0); //turn integer into float
                System.out.println("\nNumber of Player wins: " + playerWin); //win stat
                System.out.println("Number of Dealer wins: " + dealerWin); //loss stat
                System.out.println("Number of tie games: " + tie); //tie stat
                System.out.println("Total # of games played is: " + (gameNumber - 1)); //game stat
                if (playerWin == 0 && dealerWin == 0 && tie == 0){
                    winPercentage = 0.0;
                }
                System.out.println("Percentage of Player wins: " + winPercentage + "%"); //percentage stat

                System.out.println("\n1.  Get another card\n2.  Hold hand\n3.  Print statistics\n4.  Exit");
                System.out.print("\nChoose an option: ");
                game = input.nextInt(); //player chooses next
            }
            //option 2
            if (game == 2) {
                //get dealer hand
                int dealerHand = rand.nextInt(11) + 16;
                //display dealer hand + your hand
                System.out.println("\nDealer's hand: " + dealerHand);
                System.out.println("Your hand is: " + handValue);
                //compares dealer and your hand
                if (dealerHand > 21 || handValue > dealerHand) { //win
                    System.out.println("\nYou win!");
                    playerWin++; //adds player win count
                }
                if (handValue < dealerHand && dealerHand <= 21) { //loss
                    System.out.println("\nDealer wins!");
                    dealerWin++; //adds loss count
                }
                if (handValue == dealerHand) { //tie
                    System.out.println("\nIt's a tie! No one wins!");
                    tie++; //adds tie count
                }
                gameNumber++; //adding game number for next game and reset game == 1;
                handValue = 0; //restarting hand
                System.out.println("\nSTART GAME #" + gameNumber); //new game

            }
            if (game == 4){
                game = 4; //do option 4: exit
            } else game = 1; //automatic game progression

            //option 1
            if(game == 1) {
                // next card
                int cardValue = rand.nextInt(13) + 1;
                //if face card or ace
                if (cardValue == 13) {
                    System.out.println("\nYour card is a KING!");
                    handValue += 10;
                    System.out.println("Your hand is: " + handValue);
                } else if (cardValue == 12) {
                    System.out.println("\nYour card is a QUEEN!");
                    handValue += 10;
                    System.out.println("Your hand is: " + handValue);
                } else if (cardValue == 11) {
                    System.out.println("\nYour card is a JACK!");
                    handValue += 10;
                    System.out.println("Your hand is: " + handValue);
                } else if (cardValue == 1) {
                    System.out.println("\nYour card is a ACE!");
                    handValue += 1;
                    System.out.println("Your hand is: " + handValue);
                }
                //if non face value
                if (cardValue <= 10 && cardValue > 1) {
                    System.out.println("\nYour card is a " + cardValue + "!");
                    handValue += cardValue;
                    System.out.println("Your hand is: " + handValue);
                }
                //game continues
                if (handValue < 21) { //continue
                    System.out.println("\n1.  Get another card\n2.  Hold hand\n3.  Print statistics\n4.  Exit");
                    //what the player wants to do
                    System.out.print("\nChoose an option: ");
                    game = input.nextInt(); //player chooses what to do

                //game ends
                } else if (handValue == 21) {//automatic win
                    System.out.println("\nBLACKJACK! You win!");
                    gameNumber++;//adding game count
                    playerWin++; //adding win count
                    handValue = 0; //resets hand
                    System.out.println("\nSTART GAME #" + gameNumber);
                } else {//automatic loss
                    System.out.println("\nYou exceeded 21! You lose.");
                    gameNumber++; //adding game count
                    dealerWin++; //adding loss count
                    handValue = 0; //resets hand
                    System.out.println("\nSTART GAME #" + gameNumber);
                }
            }
            //if player enters invalid number
            while (game > 4 || game < 1){
                System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.");
                System.out.println("\n1.  Get another card\n2.  Hold hand\n3.  Print statistics\n4.  Exit");
                System.out.print("\nChoose an option: ");
                game = input.nextInt();
            }
        }
    }
}
