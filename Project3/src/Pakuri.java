public class Pakuri {
    //attributes of an individual pakuri
    private String species;
    private int attack, defense, speed;

    //constructor
    public Pakuri(String species) {
        this.species = species;
        this.attack = (species.length() * 7) + 9;
        this.defense = (species.length() * 5) + 17;
        this.speed = (species.length() * 6) + 13;
    }

    //setters and getters
    public String getSpecies() {
        return species;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAttack(int newAttack) {
        this.attack = newAttack;
    }

    //evolving pakuri
    public void evolve(){
        this.attack = attack * 2;
        this.defense = defense * 4;
        this.speed = speed * 3;

    }
}
