package fighting;

/**
 * @author bnhh
 * @Description
 * @date 2026/10/19-19:39
 */
public class FightingGameTest {
    public static void main(String[] args) {
        FightingGame r1 = new FightingGame("莉莉",100,'女');
        FightingGame r2 = new FightingGame("山山",100,'男');

        while (true){
            r1.attack(r2);
            if (r2.getBlood()==0) {
                System.out.println((r1.getName() + "最终K.O了" + r2.getName()));
                break;
            }
            r2.attack(r1);
            if (r1.getBlood()==0) {
                System.out.println((r2.getName() + "最终K.O了" + r1.getName()));
                break;
            }

        }
    }
}
