import java.util.Random;
//batalhar. Os valores a serem gerados terá limitação de 1000 de XP.
public class EXP_Batalha {
    public int batalhar() {
        Random R = new Random();
        int XP = R.nextInt(1001); 
        return XP;
    }
    
}
