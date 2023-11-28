package trabalhorpg;

import java.util.ArrayList;
import java.util.Random;

public class EXP_Batalha {

    Random R = new Random();

    public int batalhar() {
        int XP = R.nextInt(1001);
        return XP;

    }

    public int rolar(int nl) {
        return R.nextInt(nl) + 1;
    }

    public void batalha(int iniciativa) {
        ArrayList<Inimigo> inimigo = new ArrayList();
        inimigo.add(new Inimigo(1, 7, 3, 2, "Goblin"));
        inimigo.add(new Inimigo(2, 6, 4, 1, "Lobo"));
        inimigo.add(new Inimigo(2, 10, 5, 3, "Gnol"));
        int in = R.nextInt(3);
        inimigo.get(in);
        int inia = rolar(20) + inimigo.get(in).getIniciativa();
        int ini = rolar(20) +iniciativa;
        System.out.println(inimigo.get(in).getNome());
        System.out.println("Iniciativa " + ini);

    }
}
