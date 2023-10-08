package Entitys;

public interface WhoCanTakeDamage {

    int getDefenceValue();
    boolean isItDead();
    void changeDeadStatusTrue();
    void takeDamage(int takenDamage);
}
