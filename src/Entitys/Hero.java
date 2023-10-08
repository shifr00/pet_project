package Entitys;


public class Hero extends Entity implements WhoCanHeal {

    private int valueOfHealPotions = 4;

    public Hero(int defence, int attack, int maxHealthPoint,int dmgMinValue, int dmgMaxValue){
        super(defence,attack,maxHealthPoint,dmgMinValue,dmgMaxValue);
    }
    public Hero(){
        super(20,10,200,10,15);
    }


    @Override
    public void getInfo(){
        System.out.println("Hero stats:");
        super.getInfo();
    }

    public void showTotalHealthPoint(){
        int totalHP = getTotalHealthPoint();
        System.out.printf("Hero hp: %d\n",totalHP);

    }

    /**
     * позволяет востановить здороье на 30% от максимального Здоровья,
     * если количество зелий (valueOfHealPotions) не равно 0
     */
    @Override
    public void drinkHealPotion() {
        if (this.valueOfHealPotions > 0) {
            this.totalHealthPoint += Math.round((this.maxHealthPoint * 0.3));
            this.decreaseValueOfHealthPotion();
            if(this.totalHealthPoint >= this.maxHealthPoint){
                this.totalHealthPoint = this.maxHealthPoint;
            }
        } else {
            System.out.println("You don't have any heal potions");
        }

    }
    private void decreaseValueOfHealthPotion(){
        this.valueOfHealPotions -= 1;
    }

}
