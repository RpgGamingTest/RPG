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

    public void batalha(int iniciativa, int ForcP, int Pod, int HpPer, int idClass, int idArm, String ArmaP, String ArmaS, int DanoArmP, int DanoArmS) {
        int hpPer = 0, hpadd = 0, ord = 0, turno = -1, resp = 0, cont = 0, cont2 = 0, forcadd = 0, dano = 0, dado = 0, danoIn = 0;
        boolean HabAtiv = false, queim = false;
        switch (idClass) {
            case 1:
                hpPer = HpPer * 120;
                break;
            case 2:
                hpPer = HpPer * 100;
                break;
            case 3:
                hpPer = hpPer * 110;
                break;
        }
        ArrayList<Inimigo> inimigo = new ArrayList();
        inimigo.add(new Inimigo(1, 700, 3, 20, "Goblin"));
        inimigo.add(new Inimigo(2, 650, 4, 10, "Lobo"));
        inimigo.add(new Inimigo(2, 1100, 6, 36, "Gnol"));
        int in = R.nextInt(3);
        int inicInmig = rolar(20) + inimigo.get(in).getIniciativa();
        int inicPer = rolar(20) + iniciativa;
        int hpInim = inimigo.get(in).getVida();
        if (inicPer > inicInmig) {
            System.out.println("Você encontrou um " + inimigo.get(in).getNome() + "! Se prepare para atacar!");
            ord = 1;
        } else if (inicPer < inicInmig) {
            System.out.println("Um " + inimigo.get(in).getNome() + " te encontrou! Se prepare para o ataque!");
            ord = 2;
        }
        while (hpPer > 0 && hpInim > 0) {
            turno++;
            if (cont > 0) {
                cont--;
            }
            if (cont2 > 0) {
                cont2--;

            } else if (cont2 == 0) {
                HabAtiv = false;
            }
            switch (ord) {
                case 1:
                    System.out.println("Como você deseja atacar seu inimigo?"
                            + "\n1 - Arma primária\n2-Arma Secundária\n3-Ativar Habilidade Especial(1x em cada 4 Turnos)");
                    do {
                        resp = R.nextInt();
                        if (resp < 1 || resp > 3) {
                            System.out.println("Resposta inválida! Digite novamente.");
                        }
                    } while (resp < 1 || resp > 3);
                    switch (resp) {
                        case 1:
                            System.out.println("Você escolheu sua arma: " + ArmaP + "!");
                            dado = rolar(20);
                            if (dado == 20) {
                                hpInim -= dano = (DanoArmP * 6) + (ForcP * 6);
                                System.out.println("Você deu dano CRÍTICO em seu inimigo! Você tirou " + dano + " de hp do seu inimigo!");
                            } else if (dado > 10) {
                                hpInim -= dano = (DanoArmP * 3) + (ForcP * 3) - inimigo.get(in).getDefesa();
                                System.out.println("Você deu tirou " + dano + " de hp do seu inimigo!");
                            } else if (dado < 10) {
                                System.out.println("Você errou o ataque! Você não tirou nenhum dano de seu inimigo!");
                            }
                            break;
                        case 2:
                            System.out.println("Você escolheu sua arma: " + ArmaS + "!");
                            dado = rolar(20);
                            if (dado == 20) {
                                hpInim -= dano = (DanoArmS * 5) + (ForcP * 5);
                                System.out.println("Você deu dano CRÍTICO em seu inimigo! Você tirou " + dano + " de hp do seu inimigo!");
                            } else if (dado > 10) {
                                hpInim -= dano = (DanoArmS * 2) + (ForcP * 2) - inimigo.get(in).getDefesa();
                                System.out.println("Você deu tirou " + dano + " de hp do seu inimigo!");
                            } else if (dado < 10) {
                                System.out.println("Você errou o ataque! Você não tirou nenhuma gota de sangue do seu inimigo!");
                            }
                            break;
                        case 3:
                            if (Pod < 3) {
                                System.out.println("Seu personagem não é capaz de utilizar sua habilidade especial!");
                            } else {
                                if (HabAtiv == false) {
                                    switch (idClass) {
                                        case 1:
                                            cont = 2;
                                            hpadd = 4 * 120;
                                            forcadd = 4 * 6;
                                            hpPer += hpadd;
                                            dano += forcadd;
                                            HabAtiv = true;
                                            System.out.println("4 pontos foram adicionados para FORÇA e VITALIDADE!");
                                            ord = 2;
                                            break;
                                        case 2:
                                            System.out.println("Você ativou a habilidade TIRO PRECISO e deu dano CRÍTICO em seu inimigo!");
                                            hpInim -= dano = (DanoArmP * 6) + (ForcP * 6);
                                            HabAtiv = true;
                                            ord = 2;
                                            break;
                                        case 3:
                                            HabAtiv = true;
                                            int DanBola = Pod * 150;
                                            System.out.println("Você usou a bola de fogo! Você tirou 450 de vida do seu inimigo!");
                                            hpInim -= DanBola;
                                            int chan = rolar(100);
                                            if (chan > 30 || chan < 60) {
                                                System.out.println("Sua habilidade deixou seu inimigo em chamas pelos próximos 3 turnos!");
                                                cont = 3;
                                                queim = true;
                                            }
                                            break;
                                    }
                                    cont2 = 4;
                                } else if (HabAtiv == true) {
                                    System.out.println("Sua habilidade está ativa, não é possível ativá-la novamente!");
                                }
                            }
                            break;
                    }
                    break;
                case 2:
                    dado = rolar(20);
                    if (dado == 20) {
                        hpPer -= danoIn = (inimigo.get(in).getAtaque() * 25);
                        System.out.println("Seu inimigo acertou um dano CRÍTICO em você! Você perdeu " + danoIn + " de HP!");
                    } else if (dado > 10) {
                        hpPer -= danoIn = (inimigo.get(in).getAtaque() * 15);
                        System.out.println("Seu inimigo acertou seu ataque! Você perdeu " + danoIn + "de HP!");
                    } else if (dado < 10) {
                        System.out.println("Seu inimigo errou o ataque! Aproveite para revidar!");
                    }
                    break;
            }
        }
    }
}
