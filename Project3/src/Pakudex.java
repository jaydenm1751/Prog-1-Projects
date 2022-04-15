public class Pakudex {

    private Pakuri[] pakuris;
    private int capacity; //max we store in pakuri array
    private int size = 0; //number of pakuri in array

    //{[pikabu,  12, 45, 3], null, null, null, null, ...}
        //size does the number of pakuri -- capacity is the entire size

    public Pakudex() { //default constructor
        pakuris = new Pakuri[20];

    }

    public Pakudex(int capacity){ //constucts pakuris object
        pakuris = new Pakuri[capacity];

        for (int i = 0; i < getCapacity(); i++){
            pakuris[i] = null; //initialize each value to be null;
        }
    }

    //getters
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        //capacity is the total length of array
        capacity = pakuris.length;
        return capacity;
    }

    public int[] getStats(String species){
        //assign stats to each value in array
        int[] statistics = new int[3];
        Pakuri obj = null;

        if (size == 0){ //no pakudex entries
            return null;
        }

        //getting initializes the Pakuri obj
        for (int i = 0; i < size; i++){
            if(pakuris[i] != null){
                String currentPakuri = pakuris[i].getSpecies();
                if(currentPakuri.contentEquals(species)){
                    obj = pakuris[i];
                }
            }
        }

        //determines the stats array
        for (int i = 0; i < pakuris.length; i++){
            if (pakuris[i] != null) {
                String pSpecies = pakuris[i].getSpecies();
                if (pSpecies.equals(species)) {
                    statistics[0] = obj.getAttack();
                    statistics[1] = obj.getDefense();
                    statistics[2] = obj.getSpeed();
                }
            }
        }
        return statistics;
    }


    public void sortPakuri(){

        //swapping pakuris
        for (int i = 0; i < size - 1; i++){
            for (int j = i + 1; j < size; j++){
                String x = pakuris[i].getSpecies();
                String y = pakuris[j].getSpecies();
                if (x.compareTo(y) > 0){
                    Pakuri temp = pakuris[i];
                    pakuris[i] = pakuris[j];
                    pakuris[j] = temp;
                }
            }
        }

    }

    public boolean addPakuri(String species){

        //iterate to find duplicates
        for (int i = 0; i < size; i++){
            if(pakuris[i] != null) {
                String currentSpecies = pakuris[i].getSpecies();
                if(currentSpecies.contentEquals(species)){
                    return false;
                }
            }
        }

        //new pakuri creation
        pakuris[size] = new Pakuri(species);
        size++;
        return true;

    }

    public boolean evolveSpecies(String species){
        //evolution //doesn't change stats??
        Pakuri evolution = null;
        String currentSpecies;
        for (int i = 0; i < pakuris.length; i++) {
            if (pakuris[i] != null) {
                currentSpecies = pakuris[i].getSpecies();
                if (species.contentEquals(currentSpecies)) {
                    evolution = pakuris[i]; //decalres evolution object
                    break;
                }
            }
        }
        if (evolution == null){ //if there is no pakuri with the declared species
            return false;
        }

        evolution.evolve(); //evolves it
        return true;
    }


    public String[] getSpeciesArray(){

        if(size == 0){ //if the pakudex is empty
            return null;

        }

        //iterate through the pakuris array and retrieve names for each pakuri
        //return the species.
        String[] speciesArray = new String[size]; //declare length of array
        for (int i = 0; i < size; i++){
            if(pakuris[i] != null) {
                speciesArray[i] = pakuris[i].getSpecies(); //putting values into array
            }
        }

        return speciesArray;
    }
}
