package Entitys;

import java.util.Random;

public class Dice {
    private final Random generator;

    public Dice(){
        generator = new Random();
    }

    /**
     * имитирует бросок кубика
     * @return выпавшее значение кубика
     */
    public int rollDice(){
        return generator.nextInt(6) + 1;
    }

    /**
     * позволет получить рандомное значение в диапазоне от minNum до maxNum
     * @return полученное число
     */
    public int getRandomNumberFromRange(int minNum, int maxNum){
        return generator.nextInt(maxNum - minNum + 1) + minNum;
    }
}

