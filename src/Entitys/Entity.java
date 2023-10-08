package Entitys;

import java.time.temporal.ValueRange;
import java.util.Random;

public abstract class Entity implements WhoCanTakeDamage, WhoCanAttack {
    protected int defenceValue;
    protected int attackValue;
    protected int totalHealthPoint;
    protected int maxHealthPoint;
    protected boolean entityIsDead = false;
    protected int minDamageValue;
    protected int maxDamageValue;
    protected Dice myDice;

    public Entity(int defence, int attack, int maxHealthPoint,int dmgMinValue, int dmgMaxValue){

        if(defence > 30){
            this.defenceValue = 30;
        } else if (defence < 1) {
            this.defenceValue = 1;
        } else {
            this.defenceValue = defence;
        }

        if(attack > 30){
            this.attackValue = 30;
        } else if (attack < 1) {
            this.attackValue = 1;
        } else {
            this.attackValue = attack;
        }

        if(maxHealthPoint <= 0){
            this.totalHealthPoint = 1;
            this.maxHealthPoint = 1;
        } else {
            this.totalHealthPoint = maxHealthPoint;
            this.maxHealthPoint = maxHealthPoint;
        }

        if (dmgMaxValue > 0 && dmgMinValue > 0){
            if (dmgMinValue == Math.min(dmgMaxValue,dmgMinValue)){
                this.minDamageValue = dmgMinValue;
                this.maxDamageValue = dmgMaxValue;
            } else {
                this.minDamageValue = dmgMaxValue;
                this.maxDamageValue = dmgMinValue;
            }
        } else {
            this.minDamageValue = 1;
            this.maxDamageValue = 6;
        }


        this.myDice = new Dice();
    }

    public void getInfo(){
        System.out.println("************************************");
        System.out.printf("defenceValue : %d\n",this.defenceValue);
        System.out.printf("attackValue: %d\n",this.attackValue);
        System.out.printf("TotalHP: %d \n",this.totalHealthPoint);
        System.out.printf("MaxHp: %d\n",this.maxHealthPoint);
        System.out.printf("DMG %d - %d\n",this.minDamageValue, this.maxDamageValue);
        System.out.println("************************************\n");
    }

    public int getTotalHealthPoint(){
        return this.totalHealthPoint;
    }
    public int getMaxHealthPoint(){
        return this.maxHealthPoint;
    }

    @Override
    public int getDefenceValue() {
        return this.defenceValue;
    }

    @Override
    public boolean isItDead() {
        return this.entityIsDead;
    }

    @Override
    public void changeDeadStatusTrue() {
        this.entityIsDead = true;
    }

    /**
     * отнимает takenDamage у текущего значения Здоровья
     * @param takenDamage значение полученного урона
     */
    @Override
    public void takeDamage(int takenDamage) {
        if (this.totalHealthPoint - takenDamage <= 0){
            this.totalHealthPoint = 0;
            this.changeDeadStatusTrue();
        } else {
            this.totalHealthPoint -= takenDamage;
        }
    }

    /**
     *сначало расчитывается attackValue (по формуле из getDiceRollsCount)
     *После этого кидаем кубик на атаку attackValue раз, и если выпавшее число >= 5
     *то entityTakeDmg наносится урон в диапазоне урона атакующего объекта
     * @param entityTakeDmg существо, которое мы будем атаковать
     */
    @Override
    public void attackEntity(WhoCanTakeDamage entityTakeDmg) {
        int attackValue = getDiceRollsCount(entityTakeDmg);

        for (int i = 0; i < attackValue; i++){
            if(entityTakeDmg.isItDead()) break;

            if(this.myDice.rollDice() >= 5 ){
                int takeDamage = getNumFromDamageRange();
                entityTakeDmg.takeDamage(takeDamage);
            }
        }

    }

    /**
     * берет рандомное число из диапазона от minDamageValue и maxDamageValue
     * @return количество наносимого урона урона
     */
    private int getNumFromDamageRange(){
        return this.myDice.getRandomNumberFromRange(minDamageValue,maxDamageValue);
    }

    /**
     * рассчитывает количество бросков кубиков на атаку по формуле
     * attack атакующего - (defence получаюзего урон + 1)
     * @param entityTakeDmg обект, получающий урон
     * @return значение формулы или 1, если значение получилось <= 1
     */
    private int getDiceRollsCount(WhoCanTakeDamage entityTakeDmg){
        if (this.attackValue - (entityTakeDmg.getDefenceValue()+1) <= 1){
            return 1;
        } else {
            return this.attackValue - (entityTakeDmg.getDefenceValue()+1);
        }
    }


}
