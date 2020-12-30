package player;

import manager.EnvironmentVariable;

public class AttributeGame {
    public int numberArrow = EnvironmentVariable.INIT_NUMBER_ARROW;
    public int bloodPlayer;
    public int experience = 0, level = 1;
    public int numberGreenGem,
            numberRedGem,
            numberPinkGem,
            numberBlueGem,
            numberYellowGem;

    public int getNumberArrow() {
        return numberArrow;
    }

    public void setNumberArrow(int numberArrow) {
        this.numberArrow = numberArrow;
    }

    public int getBloodPlayer() {
        return bloodPlayer;
    }

    public void setBloodPlayer(int bloodPlayer) {
        this.bloodPlayer = bloodPlayer;
    }

    public int getNumberGreenGem() {
        return numberGreenGem;
    }

    public void setNumberGreenGem(int numberGreenGem) {
        this.numberGreenGem = numberGreenGem;
    }

    public int getNumberRedGem() {
        return numberRedGem;
    }

    public void setNumberRedGem(int numberRedGem) {
        this.numberRedGem = numberRedGem;
    }

    public int getNumberPinkGem() {
        return numberPinkGem;
    }

    public void setNumberPinkGem(int numberPinkGem) {
        this.numberPinkGem = numberPinkGem;
    }

    public int getNumberBlueGem() {
        return numberBlueGem;
    }

    public void setNumberBlueGem(int numberBlueGem) {
        this.numberBlueGem = numberBlueGem;
    }

    public int getNumberYellowGem() {
        return numberYellowGem;
    }

    public void setNumberYellowGem(int numberYellowGem) {
        this.numberYellowGem = numberYellowGem;
    }
}
