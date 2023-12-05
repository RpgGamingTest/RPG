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
     * nivel 1 1000
     * nivel 2 2000+1000
     * nivel 3 3000+3000
     * nivel 4 4000+6000
     * nivel 5 5000+10000
     * 
     */
    public int rolar(int nl) {
        return R.nextInt(nl) + 1;
    }

    /*
     * Posições
     * Personagem e atributos: (0,1)
     * Habilidade: (0,2)
     * Armas primárias e secundárias: (0,3)
     */
    public void batalha(int iniciativa, ArrayList<ArrayList<Criar>> perso, int per, int idClass) {

        int hpPer = 0, hpadd = 0, ord = 0, turno = -1, resp = 0, cont = 0, cont2 = 0, forcadd = 0, dano = 0, dado = 0,
                danoIn = 0, lim = 0, defEsc = 0;
        boolean HabAtiv = false, queim = false;
        /* Definir HP do personagem */
        Scanner ler = new Scanner(System.in);
        switch (idClass) {
            case 1:
                hpPer = perso.get(per).get(0).getVit() * 120;
                break;
            case 2:
                hpPer = perso.get(per).get(0).getVit() * 100;
                break;
            case 3:
                hpPer = perso.get(per).get(0).getVit() * 110;
                break;
        }
        /* Criar inimigos */
        ArrayList<Inimigo> inimigo = new ArrayList();
        inimigo.add(new Inimigo(1, 700, 6, 20, "Goblin"));
        inimigo.add(new Inimigo(10, 650, 14, 10, "Lobo"));
        inimigo.add(new Inimigo(15, 1100, 16, 36, "Gnol"));
        /* Inimigo sortido */
        int in = R.nextInt(3);
        /* Definir iniciativas */
        int inicInmig = rolar(20) + inimigo.get(in).getIniciativa();
        int inicPer = rolar(20) + iniciativa;
        /* Definir HP do inimigo */
        int hpInim = inimigo.get(in).getVida();
        /* Definir ordem de turnos */
        if (inicPer > inicInmig) {
            System.out.println("Você encontrou um " + inimigo.get(in).getNome() + "! Se prepare para atacar!");
            ord = 1;
        } else if (inicPer < inicInmig) {
            System.out.println("Um " + inimigo.get(in).getNome() + " te encontrou! Se prepare para o ataque!");
            ord = 2;
        }
        /* Começar batalha */
        while (hpPer > 0 && hpInim > 0) {
            /* Introdução */
            System.out.println("\n+=============================================+");
            System.out.println("|\tStatus inimigo\n| HP: " + hpInim + "\n| Nome: " + inimigo.get(in).getNome());
            System.out.println("+=============================================+");
            System.out.println("|\tStatus do Personagem\n| HP: " + hpPer
                    + "\n| Nome: " + perso.get(per).get(0).getNomePer());
            System.out.println("+=============================================+");
            /* Contador de turnos, de habilidade e de tempo de uso para habilidade */
            turno++;
            dano = 0;
            if (cont > 0 && lim == 0) {
                cont--;
                lim = 1;
            } else if (cont == 0 && lim == 1) {
                /* Quando chegar a 0, redefine os status dos personagens */
                switch (idClass) {
                    case 1:
                        hpPer -= hpadd;
                        dano -= forcadd;
                        break;
                    case 2:
                        break;
                    case 3:
                        queim = false;
                        break;
                }
                lim = 0;
            }
            if (cont2 > 0) {
                cont2--;

            } else if (cont2 == 0) {
                HabAtiv = false;
            }
            /* Selecionar a vez de quem irá atacar */
            switch (ord) {
                /* Personagem */
                case 1:
                    /* Selecionar forma de ataque */
                    System.out.println("Como você deseja atacar seu inimigo?"
                            + "\n1 - Arma primária\n2 - Arma Secundária\n3 - Ativar Habilidade Especial(1x em cada 4 Turnos)");
                    do {
                        resp = ler.nextInt();
                        if (resp < 1 || resp > 3) {
                            System.out.println("Resposta inválida! Digite novamente.");
                        }
                    } while (resp < 1 || resp > 3);/* Verificar escolha */
                    switch (resp) {
                        case 1:
                            System.out.println("Você escolheu sua arma: " + perso.get(per).get(2).getNomeArm() + "!");
                            dado = rolar(20);
                            /* verifica se é escudo */
                            if (perso.get(per).get(2).getIdArm() != 5) {
                                /* Se não for: */
                                if (dado == 20) {
                                    /* Crítico */
                                    hpInim -= dano = (perso.get(per).get(2).getDano() * 6)
                                            + (perso.get(per).get(0).getForc() * 6);
                                    System.out.println("Você deu dano CRÍTICO em seu inimigo! Você tirou " + dano
                                            + " de hp do seu inimigo!");
                                } else if (dado >= 10) {
                                    /* Acerto */
                                    hpInim -= dano = (perso.get(per).get(2).getDano() * 3)
                                            + (perso.get(per).get(0).getForc() * 3) - inimigo.get(in).getDefesa();
                                    System.out.println("Você deu tirou " + dano + " de hp do seu inimigo!");
                                } else if (dado < 10) {
                                    /* Erro */
                                    System.out.println(
                                            "Você errou o ataque! Você não tirou nenhuma gota de sangue do seu inimigo!");
                                }
                            } else if (perso.get(per).get(2).getIdArm() == 5) {
                                /* Se for escudo: */
                                if (dado == 20) {
                                    /* Gera uma defesa para todo tipo de dano */
                                    defEsc = perso.get(per).get(2).getDefesa() * 1000;
                                    System.out.println("Você conseguirá defender quaisquer"
                                            + " ataques recebidos no turno de seu adversário!");
                                } else if (dado >= 10) {
                                    /* Gera uma defesa razoável */
                                    defEsc = perso.get(per).get(2).getDefesa() * 2;
                                    System.out.println("Você irá defender " + defEsc + " no próximo ataque!");
                                } else if (dado < 10) {
                                    System.out.println("Você não irá conseguir defendender o próximo ataque!");
                                    defEsc = 0;
                                }
                            }
                            ord = 2;
                            break;
                        case 2:
                            System.out.println("Você escolheu sua arma: " + perso.get(per).get(3).getNomeArm() + "!");
                            /* Rolar dado */
                            if (perso.get(per).get(3).getIdArm() != 5) {
                                dado = rolar(20);
                                if (dado == 20) {
                                    /* Crítico */
                                    hpInim -= dano = (perso.get(per).get(3).getDano() * 6)
                                            + (perso.get(per).get(0).getForc() * 6);
                                    System.out.println("Você deu dano CRÍTICO em seu inimigo! Você tirou " + dano
                                            + " de hp do seu inimigo!");
                                } else if (dado >= 10) {
                                    /* Acerto */
                                    hpInim -= dano = (perso.get(per).get(3).getDano() * 3)
                                            + (perso.get(per).get(0).getForc() * 3) - inimigo.get(in).getDefesa();
                                    System.out.println("Você deu tirou " + dano + " de hp do seu inimigo!");
                                } else if (dado < 10) {
                                    /* Erro */
                                    System.out.println(
                                            "Você errou o ataque! Você não tirou nenhuma gota de sangue do seu inimigo!");
                                }
                            } else if (perso.get(per).get(3).getIdArm() == 5) {
                                if (dado == 20) {
                                    defEsc = perso.get(per).get(3).getDefesa() * 1000;
                                    System.out.println("Você conseguirá defender quaisquer"
                                            + " ataques de seu inimigo!");
                                } else if (dado >= 10) {
                                    defEsc = perso.get(per).get(3).getDefesa() * 2;
                                    System.out.println("Você irá defender " + defEsc + " no próximo ataque!");
                                } else if (dado < 10) {
                                    System.out.println("Você não irá conseguir defendender o próximo ataque!");
                                    defEsc = 0;
                                }
                            }
                            ord = 2;
                            break;
                        case 3:
                            /* Habilidade especial */
                            if (perso.get(per).get(0).getPod() < 3) {
                                /* Confere poder do usuário para usar a habilidade */
                                System.out.println("Seu personagem não é capaz de utilizar sua habilidade especial!");
                            } else {
                                if (HabAtiv == false) {/* Verifica se a habilidade já foi utilizada */
                                    switch (idClass) {
                                        case 1:
                                            cont = 2;
                                            /* Define quantidade de turnos que irá funcionar a habilidade */
                                            // Aumenta atributos
                                            hpadd = 4 * 120;
                                            forcadd = 4 * 6;
                                            hpPer += hpadd;
                                            dano += forcadd;
                                            /* Ativa verificador de habilidade */
                                            HabAtiv = true;
                                            System.out.println("4 pontos foram adicionados para FORÇA e VITALIDADE!");
                                            break;
                                        case 2:
                                            /* Verifica se o personagem tem uma arma de longa distância no inventário */
                                            if (perso.get(per).get(2).getIdArm() == 3
                                                    || perso.get(per).get(2).getIdArm() == 4
                                                    || perso.get(per).get(3).getIdArm() == 3
                                                    || perso.get(per).get(3).getIdArm() == 4) {
                                                int arm = 0;
                                                boolean armp = false, arms = false;
                                                /* Ativa dano crítico */
                                                if(perso.get(per).get(2).getIdArm() == 4 || perso.get(per).get(2).getIdArm() == 3){
                                                    armp = true;
                                                }
                                                if (perso.get(per).get(3).getIdArm() == 4 || perso.get(per).get(3).getIdArm() == 3){
                                                    arms = true;
                                                }
                                                if (armp == arms){
                                                    if(perso.get(per).get(2).getDano() > perso.get(per).get(3).getDano()){
                                                        arm = 2;
                                                    } else if (perso.get(per).get(2).getDano() < perso.get(per).get(3).getDano()){
                                                        arm = 3;
                                                    }
                                                } else if (armp){
                                                    arm = 2;
                                                } else if (arms){
                                                    arm = 3;
                                                }
                                                dano = ((perso.get(per).get(arm).getDano() * 6)
                                                        + (perso.get(per).get(0).getDest() * 6));
                                                hpInim -= dano;
                                                /* Ativia verificador de habilidade */
                                                HabAtiv = true;
                                                System.out.println("Você ativou a habilidade TIRO PRECISO"
                                                        + " e deu dano CRÍTICO em seu inimigo! Seu inimigo perdeu "
                                                        + dano + " de HP para o ataque de sua arma!");
                                                /* Muda quem ataca */
                                                ord = 2;
                                            } else {
                                                System.out.println("Você não possui nenhuma arma de longa distância!"
                                                        + "\nVocê não utilizou um turno para isso.");
                                            }
                                            break;
                                        case 3:
                                            HabAtiv = true;
                                            int DanBola = perso.get(per).get(0).getPod() * 50;/*
                                                                                               * Define o dano da bola
                                                                                               * de fogo
                                                                                               */
                                            System.out.println("Você usou a bola de fogo! Você tirou " + DanBola
                                                    + " de vida do seu inimigo!");
                                            hpInim -= DanBola;
                                            int chan = rolar(100);
                                            if (chan > 30 || chan < 60) {
                                                /*
                                                 * Define chance de 70:30 para deixar o inimigo queimando
                                                 * chance de deixar em chamas: 30%
                                                 */
                                                System.out.println(
                                                        "Sua habilidade deixou seu inimigo em chamas pelos próximos 3 turnos!");
                                                cont = 3;
                                                queim = true;
                                            }
                                            ord = 2;
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
                /* Inimigo */
                case 2:
                    /* Rola dado para ver o tipo de ataque que irá fazer */
                    dado = rolar(20);
                    if (dado == 20) {
                        /* Caso 20 */
                        if (defEsc >= 1000) {
                            /*
                             * Se você escolheu escudo e tirou 20 com ele, defenderá o ataque completamente
                             */
                            System.out.println("Você defendeu completamente o ataque de seu inimigo!");
                        } else if (defEsc < 1000 && defEsc > 0) {
                            /*
                             * Caso tenha escolhido o escudo, porém não tirou 20, levará todo o dano de seu
                             * inimigo
                             */
                            System.out.println("Seu inimigo causou dano crítico! Você não foi"
                                    + " capaz de defender seu ataque!");
                            danoIn = (inimigo.get(in).getAtaque() * 25);
                            hpPer -= danoIn;
                            System.out.println("Você perdeu " + hpPer + " de HP");
                        } else if (defEsc == 0) {
                            /* Caso não tenha escolhido o escudo, levará todo o dano de seu inimigo */
                            hpPer -= danoIn = (inimigo.get(in).getAtaque() * 25);
                            System.out.println("Você perdeu " + danoIn + " de HP");
                        }
                    } else if (dado >= 10) {
                        danoIn = (inimigo.get(in).getAtaque() * 15);
                        if (defEsc > 1000) {
                            System.out.println("Você defendeu completamente o ataque de seu inimigo!");
                        } else if (defEsc > danoIn && defEsc > 0) {
                            System.out.println("Você defendeu o ataque de seu inimigo por completo!");
                        } else if (defEsc < danoIn && defEsc > 0) {
                            danoIn -= defEsc;
                            hpPer -= danoIn;
                            System.out.println("Você defendeu parte do ataque de seu inimigo! Você perdeu apenas "
                                    + danoIn + " de HP!");
                        } else if (defEsc == 0) {
                            hpPer -= danoIn;
                            System.out.println("Seu inimigo te acertou! Você perdeu " + danoIn + " de HP!");
                        }
                    } else if (dado < 10) {
                        System.out.println("Seu inimigo errou o ataque! Aproveite para revidar!");
                    }
                    ord = 1;
                    defEsc = 0;
                    break;
            }
        }
    }

}
