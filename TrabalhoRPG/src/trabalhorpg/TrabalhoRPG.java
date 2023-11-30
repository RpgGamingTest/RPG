package trabalhorpg;

import java.util.ArrayList;
import java.util.Scanner;

public class TrabalhoRPG {
    // ArrayList<ArrayList<Criar>> pm = new ArrayList();

    public static void main(String[] args) {
        int pr, resp, respM = 0;
        Scanner ler = new Scanner(System.in);
        ArrayList<ArrayList<Criar>> perso = new ArrayList();
        System.out.println("Olá! Seja bem vindo ao jogo de RPG");
        EXP_Batalha b = new EXP_Batalha();
        perso.add(new ArrayList());
        pr = 0;
        perso = CiarPersonagem(pr, perso);
        System.out.println("Indo para o MENU PRINCIPAL");
        do {
            System.out.println("+=======================================+");
            System.out.println("|\t\tMENU\n| 1 - Criar novo personagem"
                    + "\n| 2 - Visualizar personagem\n| 3 - Batalhar com personagem"
                    + "\n| 4 - Adicionar Arma Secundária\n| 5 - Preencher atributos"
                    + "\n| 6 - Finalizar programa");
            System.out.println("+=======================================+");
            do {
                respM = ler.nextInt();
                if (respM > 5 || respM < 1) {
                    System.out.println("Valor inválido! Digite novamente.");
                }
            } while (respM > 5 || respM < 1);
            switch (respM) {
                case 1:
                    pr++;
                    perso.add(new ArrayList());
                    perso = CiarPersonagem(pr, perso);
                    System.out.println("Indo para o MENU PRINCIPAL");
                    break;
                case 2:
                    System.out.println("De qual personagem deseja visualizar os status?");
                    PrintarListPerso(pr, perso);
                    int pers = 0;
                    do {
                        try {
                            pers = ler.nextInt();
                            if (pers < 0 || pers > pr) {
                                System.out.println("Valor inválido! Digite novamente.");
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR");
                        }
                    } while (pers < 0 || pers > pr);
                    System.out.println("Visualizando STATUS do personagem: " + perso.get(pers).get(0).getNomePer());
                    PrintarInfo(pers, perso);
                    break;
                case 3:
                    break;
                case 4:
                    int per = 0;
                    System.out.println("De qual personagem deseja adicionar o  novo armamento?");
                    PrintarListPerso(pr, perso);
                    do {
                        try {
                            per = ler.nextInt();
                            if (per < 0 || per > pr) {
                                System.out.println("Valor inválido! Digite novamente.");
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR");
                        }
                    } while (per < 0 || per > pr);
                    if (perso.get(per).get(3).getDano() > 0 || perso.get(per).get(3).getDefesa() > 0) {
                        System.out.println("O personagem já possui uma ARMA secundária!");
                    } else {
                        Arma a = new Arma(0, 0, 0, false, null, null, 0);
                        try {
                            perso.get(per).remove(3);
                            perso.get(per).add(a.CriarArm(false));
                        } catch (Exception e) {
                            System.out.println("Erro");
                        }
                    }
                    break;
                case 5:
                    System.out.println("De qual personagem deseja alterar os atributos?");
                    PrintarListPerso(pr, perso);
                    do{
                        resp = ler.nextInt();
                        if(resp < 0 || resp > pr){
                            System.out.println("Valor inválido! Digite novamente");
                        }
                    } while (resp < 0 || resp > pr);
                    Atrib(resp, perso);
                    break;
                case 6:
                    System.out.println("Finalizando programa!");
                    break;
            }
        } while (respM != 6);

    }

    public static ArrayList<ArrayList<Criar>> CiarPersonagem(int p, ArrayList<ArrayList<Criar>> perso) {
        Scanner ler = new Scanner(System.in);
        Arma c = new Arma(0, 0, 0, false, null, null,0);
        int resp, idClasse = 0, lvl = 0, idd;
        String NomeP = "", classe = "", nomeH, descH, descPer;

        // posição personagem --> 0:0
        System.out.println("Qual é sua classe?\n1 - Guerreiro\n"
                + "2 - Arqueiro\n3 - Mago");
        do {
            resp = ler.nextInt();
            if (resp > 3 || resp < 1) {
                System.out.println("Valor inválido! Digite novamente.");
            }
        } while (resp > 3 || resp < 1);
        ler.nextLine();
        System.out.println("Qual é o nome do seu personagem?");
        NomeP = ler.nextLine();
        NomeP = NomeP.toUpperCase();
        System.out.println("Qual é o nível do seu personagem? \n"
                + "Min: 0\nMax: 30");
        do {
            lvl = ler.nextInt();
            if (lvl < 0 || lvl > 30) {
                System.out.println("Valor inválido!");
            }
        } while (lvl < 0 || lvl > 30);
        System.out.println("Qual é a idade do seu personagem?");
        do{
            idd = ler.nextInt();
            if(idd < 0){
                System.out.println("A idade não pode possuir um valor negativo!");
            } else if(idd > 100){
                System.out.println("Seu personagem não pode ser tão velho!");
            } else if (idd < 18){
                System.out.println("Seu personagem não pode ser muito jovem!");
            }
        } while (idd < 0 || idd > 100 || idd < 18);
        ler.nextLine();
        System.out.println("Agora descreva como é o seu personagem!");
        descPer = ler.nextLine();
        switch (resp) {
            case 1:
                classe = "Guerreiro";
                Guerreiro G = new Guerreiro(NomeP, lvl, classe, descPer, idd);
                perso.add(new ArrayList());
                perso.get(p).add(G);
                idClasse = 1;
                break;
            case 2:
                classe = "Arqueiro";
                Arqueiro Arq = new Arqueiro(NomeP, lvl, classe, descPer, idd);
                perso.add(new ArrayList());
                perso.get(p).add(Arq);
                idClasse = 2;
                break;
            case 3:
                classe = "Mago";
                Mago M = new Mago(NomeP, lvl, classe, descPer, idd);
                perso.add(new ArrayList());
                perso.get(p).add(M);
                idClasse = 3;
                break;
        }
        // posição Habilidade --> 0:1
        System.out.println("Ótimo, confira suas habilidades!");
        System.out.println("+--------------------------------------+");
        switch (idClasse) {
            case 1:
                nomeH = "FURIA DE BATALHA";
                descH = "Ao ser ativada seus atributos FORÇA \n| e VITALIDADE aumentam em"
                        + " 4 PONTOS por 2 Turnos";

                System.out.println("| Nome da Habilidade: " + nomeH + "\n| Descrição da Habilidade: " + descH);
                Habilidades_especiais h = new Habilidades_especiais(nomeH, descH, 3, idClasse);
                perso.get(p).add(h);
                break;
            case 2:
                nomeH = "TIRO PRECISO";
                descH = "Ao ser ativada, você acertará seu inimigo com dano crítico!"
                        + "\n| (apenas para armas de longa distância)";

                System.out.println("| Nome da Habilidade: " + nomeH + "\n| Descrição da Habilidade: " + descH);
                Habilidades_especiais h2 = new Habilidades_especiais(nomeH, descH, 3, idClasse);
                perso.get(p).add(h2);
                break;
            case 3:
                nomeH = "BOLA DE FOGO";
                descH = "Ao ser ativada, dispara uma BOLA DE FOGO \n| no inimigo causando 15 de DANO e podendo deixar queimando por 3 turnos";

                System.out.println("| Nome da Habilidade: " + nomeH + "\n| Descrição da Habilidade: " + descH);
                Habilidades_especiais h3 = new Habilidades_especiais(nomeH, descH, 3, idClasse);
                perso.get(p).add(h3);
                break;
        }
        System.out.println("+--------------------------------------+");
        System.out.println("Iremos preencher seus ATRIBUTOS agora!");
        Atrib(p, perso);
        // posição arma --> 0:2
        System.out.println("Agora iremos escolher seu armamento!");
        perso.get(p).add(c.CriarArm(true));
        Arma Arm = new Arma(0, 0, 0, false, null, null, 0);
        perso.get(p).add(Arm);
        return perso;
    }
    
    public static void Atrib(int p, ArrayList<ArrayList<Criar>> perso) {
        perso.get(p).get(0).PreencherAtributos(perso, p);
    }

    public static void PrintarListPerso(int pr, ArrayList<ArrayList<Criar>> perso){
        for (int i = 0; i <= pr; i++) {
                        System.out.println(i + " - "
                                + perso.get(i).get(0).getNomePer() + " --> "
                                + perso.get(i).get(0).getClasse());
                    }
    }

    public static void PrintarInfo(int p, ArrayList<ArrayList<Criar>> perso) {
        System.out.println("\tStatus do personagem ");
        System.out.println("+----------------------------------------+");
        System.out.println("| NOME: " + perso.get(p).get(0).getNomePer()
                + "\n| NÍVEL: " + perso.get(p).get(0).getLvl()
                + "\n| IDADE: " + perso.get(p).get(0).getIddPer()
                + "\n| DESCRIÇÃO: " + perso.get(p).get(0).getDescPer()
                + "\n| PONTOS: " + perso.get(p).get(0).getPontos());
        System.out.println("+----------------------------------------+");
        System.out.println("|\tAtributos\n| FORÇA: " + perso.get(0).get(0).getForc()
                + "\n| DESTREZA: " + perso.get(p).get(0).getDest()
                + "\n| VITALIDADE: " + perso.get(p).get(0).getVit()
                + "\n| PODER: " + perso.get(p).get(0).getPod());
        System.out.println("+----------------------------------------+");
        System.out.println("|\tHabilidades Especiais\n| NOME: " + perso.get(0).get(1).getNomeHab()
                + "\n| DESCRIÇÃO: " + perso.get(p).get(1).getDescHab()
                + "\n| PODER MÍNIMO: " + perso.get(p).get(1).getPodMin());
        System.out.println("+----------------------------------------+");
        System.out.println("|\tArmamento Primário\n| Tipo da ARMA: " + perso.get(0).get(2).getTipo()
                + "\n| NOME: " + perso.get(p).get(2).getNomeArm()
                + "\n| DANO: " + perso.get(p).get(2).getDano()
                + "\n| DEFESA: " + perso.get(p).get(2).getDefesa()
                + "\n| PESO: " + perso.get(p).get(2).getPeso());
        System.out.println("|\tArmamento Secundário\n| Tipo da ARMA: " + perso.get(p).get(3).getTipo()
                + "\n| NOME: " + perso.get(p).get(3).getNomeArm()
                + "\n| DANO: " + perso.get(p).get(3).getDano()
                + "\n| DEFESA: " + perso.get(p).get(3).getDefesa()
                + "\n| PESO: " + perso.get(p).get(3).getPeso());
        System.out.println("+----------------------------------------+");

    }
}
