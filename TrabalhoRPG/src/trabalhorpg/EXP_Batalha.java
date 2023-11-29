package trabalhorpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EXP_Batalha {

    Scanner j = new Scanner(System.in);
    Random R = new Random();

    public int batalhar() {
        int XP = R.nextInt(1001);
        return XP;
    }

    /*
    nivel 1 1000
    nivel 2 2000+1000
    nivel 3 3000+3000
    nivel 4 4000+6000
    nivel 5 5000+10000
    
     */

    public int rolar(int nl) {
        return R.nextInt(nl) + 1;
    }

    public int rolar(int poder, int ata, int defesa) {
        int rola = rolar(20);
        System.out.println("Oque deseja fazer?\n\n1 - atacar\n2 - magia\n3 - defender(prox. turno)\n");
        int resp = j.nextInt();
        switch (resp) {
            case 1:
                System.out.println("Como deseja atacar?\n\n1 - arma primaria\n2 - arma secundaria");
                int resp2 = j.nextInt();
                break;
            case 2:
                System.out.println("");
                break;
            case 3:
                System.out.println("");
        }

    }

    public void batalha(int iniciativa) {
        ArrayList<Inimigo> inimigo = new ArrayList();
        inimigo.add(new Inimigo(1, 7, 3, 2, "Goblin"));
        inimigo.add(new Inimigo(2, 6, 4, 1, "Lobo"));
        inimigo.add(new Inimigo(2, 10, 5, 3, "Gnol"));
        int in = R.nextInt(3);
        inimigo.get(in);
        int inia = rolar(20) + inimigo.get(in).getIniciativa();
        int ini = rolar(20) + iniciativa;
        System.out.println(inimigo.get(in).getNome());
        System.out.println("Iniciativa " + ini);

    }
}
