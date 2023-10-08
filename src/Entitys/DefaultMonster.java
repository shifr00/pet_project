package Entitys;

public class DefaultMonster extends Entity {
    public DefaultMonster(int defence, int attack, int maxHealthPoint,int dmgMinValue, int dmgMaxValue){
        super(defence,attack,maxHealthPoint,dmgMinValue,dmgMaxValue);
    }
    public DefaultMonster(){
        super(10,5,100,6,10);
    }
    @Override
    public void getInfo(){
        System.out.println("Default monster stat:");
        super.getInfo();
    }
    public void showTotalHealthPoint(){
        int totalHP = getTotalHealthPoint();
        System.out.printf("Monster hp: %d\n",totalHP);

    }
}
