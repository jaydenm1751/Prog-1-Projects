import java.util.Scanner;

public class PakuriProgram {
    public static void main(String[] args) {

        // pakuri > pokemon
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");


        int size = 0;
        while (size <= 0) {
            //receives any input
            try {
                System.out.print("Enter max capacity of the Pakudex: ");
                String size1 = input.next();
                size = Integer.parseInt(size1);
                if (size < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid size.");
            }
        }

        System.out.println("The Pakudex can hold " + size + " species of Pakuri.");
        Pakudex pakudex1 = new Pakudex(size);//pakudex object

        while (true) {
            Menu();
            System.out.print("What would you like to do? ");
            int option = 0;
            try {
                String selection = input.next();
                option = Integer.parseInt(selection);
            } catch (Exception e){

            }

            if (option == 1){ //option 1
                //gets species list
                String[] speciesList = pakudex1.getSpeciesArray();
                //no pakuri in pakudex yet
                if(speciesList == null){
                    System.out.println("No Pakuri in Pakudex yet! ");
                    continue;
                }

                System.out.println("Pakuri In Pakudex:");
                for (int i = 0; i < speciesList.length; i++){
                    System.out.println((i + 1) + ". " + speciesList[i]); //displays species list
                }

            } else if (option == 2){ //option 2
                //shows stats of species
                System.out.print("Enter the name of the species to display: ");
                String species = input.next();

                int[] statistics = pakudex1.getStats(species); //array of fighting stats
                //no pakuri available
                if (statistics == null){
                    System.out.println("Error: No such Pakuri! ");
                } else { //prints stats
                    System.out.println("Species: " + species);
                    System.out.println("Attack: " + statistics[0]);
                    System.out.println("Defense: " + statistics[1]);
                    System.out.println("Speed: " + statistics[2]);
                }


            } else if (option == 3){ //option 3

                int sizeCritters = pakudex1.getSize();
                if(sizeCritters == size) { // if pakudex entries are full
                    System.out.println("Error: Pakudex is full!");
                    continue;
                }

                //adds pakuri to pakudex
                System.out.print("Enter the name of the species to add: ");
                String species = input.next();
                boolean answer = pakudex1.addPakuri(species); //determines whether pakuri could be added
                if(answer){
                    System.out.println("Pakuri species " + species + " successfully added! ");

                } else{
                    System.out.println("Error: Pakudex already contains this species!");
                }


            } else if (option == 4){ //option 4 evolve

                System.out.print("Enter the name of the species to evolve: ");
                String species = input.next();
                boolean answer = pakudex1.evolveSpecies(species); //determines if it can be evolved
                if(answer){
                    System.out.println(species + " has evolved! ");
                } else {
                    System.out.println("Error: No such Pakuri! ");
                }

            } else if (option == 5){ //option 5 sort
                pakudex1.sortPakuri();
                System.out.println("Pakuri have been sorted!");

            } else if (option == 6){ //terminate
                System.out.println("Thanks for using Pakudex! Bye!");
                break;

            } else {
                System.out.println("Unrecognized menu selection!");
            }
        }

    }

    public static void Menu(){ //menu options
        System.out.println("\nPakudex Main Menu \n-----------------\n1. List Pakuri \n2. Show Pakuri\n3. Add Pakuri");
        System.out.println("4. Evolve Pakuri \n5. Sort Pakuri\n6. Exit \n");
    }
}
