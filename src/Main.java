import Entitys.DefaultMonster;
import Entitys.Hero;

public class Main {
    public static void main(String[] args) {

        //создание Героя и монстра
        Hero myHero = new Hero(20,20,200,20,23);
        DefaultMonster monster = new DefaultMonster(10,5,9000,6,10);

        //цикл боя, будет продолжаться, пока один из них не умрет
        int roundCount = 1;
        while(!myHero.isItDead() && !monster.isItDead()){

            System.out.printf("round %d\n",roundCount);
            System.out.println("*****************");

            if (myHero.getTotalHealthPoint() <= myHero.getMaxHealthPoint() * 0.5){
                myHero.drinkHealPotion();
            }
            myHero.attackEntity(monster);
            monster.attackEntity(myHero);

            myHero.showTotalHealthPoint();
            monster.showTotalHealthPoint();

            System.out.println("*****************");
            roundCount++;
        }

        if(!myHero.isItDead()){
            System.out.println("\n##### You win #####\n");
        } else {
            System.out.println("\n##### You lose #####\n");
        }
        monster.getInfo();
        myHero.getInfo();

    }
}