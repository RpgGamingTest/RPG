package trabalhorpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Criar {

    protected String nomePer, descPer, tipo, nomeHab, descHab, NomeArm, MaxF = "", MaxV = "", MaxD = "", MaxP = "";
    protected int Forc = 1, Vit = 1, Dest = 1, Pod = 1, pontos = 6, dano, peso, def, podMin, lvl = 0, lvl2 = 0, resp,
            limF, limV, limD, limP, iddPer, idArm, idClasse;
    protected String classe;
    protected Scanner l = new Scanner(System.in);
    protected boolean primaria;
    protected ArrayList<Criar> personagens = new ArrayList();
    protected ArrayList<ArrayList<Criar>> pe = new ArrayList<>();
    protected Scanner r = new Scanner(System.in);

    public String getNomePer() {
        return nomePer;
    }

    public int getIdClasse() {
        return idClasse;
    }    

    public String getDescPer() {
        return descPer;
    }

    public int getIddPer() {
        return iddPer;
    }

    public int getLvl() {
        return lvl;
    }

    public int getForc() {
        return Forc;
    }

    public int getVit() {
        return Vit;
    }

    public int getDest() {
        return Dest;
    }

    public int getPod() {
        return Pod;
    }

    public int getPontos() {
        return pontos;
    }

    public String getNomeHab() {
        return nomeHab;
    }

    public String getDescHab() {
        return descHab;
    }

    public int getPodMin() {
        return podMin;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDano() {
        return dano;
    }

    public int getPeso() {
        return peso;
    }

    public int getDefesa() {
        return def;
    }

    public boolean isPrimaria() {
        return primaria;
    }

    public String getNomeArm() {
        return NomeArm;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getIdArm() {
        return idArm;
    }
    

    public void PreencherAtributos(ArrayList<ArrayList<Criar>> perso, int p) {
        int resp = 0, resp2 = 0;
        if (lvl2 != perso.get(p).get(0).getLvl()) {
            this.pontos -= this.lvl2;
            this.lvl2 = perso.get(p).get(0).getLvl();
            this.pontos += this.lvl2;
        }
        while (this.pontos != 0 && resp != 5) {
            System.out.println("Deseja atribuir seus pontos para qual habilidade?"
                    + "\nPontos restantes: " + this.pontos);
            System.out.println("\n1 - Força: " + this.Forc + this.MaxF
                    + "\n2 - Vitalidade: " + this.Vit + this.MaxV
                    + "\n3 - Destreza: " + this.Dest + this.MaxD
                    + "\n4 - Poder: " + this.Pod + this.MaxP
                    + "\n5 - Sair");
            resp = l.nextInt();
            if (resp < 1 || resp > 5) {
                System.out.println("Valor inválido! Digite novamente.");
            } else if (resp > 0 && resp < 5) {
                System.out.println("quantos pontos deseja atribuir?");
                System.out.println("Pontos restantes: " + this.pontos);
                do {
                    resp2 = l.nextInt();
                    if (resp2 > this.pontos) {
                        System.out.println("Valor inválido!");
                    }
                } while (resp2 > this.pontos);
            }
            switch (resp) {
                case 1:
                    if (resp2 == 0) {
                        System.out.println("Valor inválido.");
                    } else if (this.Forc + resp2 <= 10 + this.limF) {
                        System.out.println("\n" + resp2 + " ponto(s) adiocinado em FORÇA!");
                        this.Forc += resp2;
                        this.pontos -= resp2;
                        if (this.Forc == 10 + this.limF) {
                            MaxF = " - MAX";
                        }
                    } else if (this.Forc == 10 + this.limF) {
                        System.out.println("O atributo FORÇA está no nível máximo");
                    }
                    break;
                case 2:
                    if (resp2 == 0) {
                        System.out.println("Valor inválido.");
                    } else if (this.Vit + resp2 <= 10 + this.limV) {
                        System.out.println("\n" + resp2 + " ponto(s) adiocinado em VITALIDADE!");
                        this.Vit += resp2;
                        this.pontos -= resp2;
                        if (this.Vit == 10 + this.limV) {
                            MaxV = " - MAX";
                        }
                    } else if (this.Vit == 10 + this.limV) {
                        System.out.println("O atributo VITALIDADE está no nível máximo");
                    }
                    break;
                case 3:
                    if (resp2 == 0) {
                        System.out.println("Valor inválido.");
                    } else if (this.Dest + resp2 <= 10 + this.limD) {
                        System.out.println("\n" + resp2 + " ponto(s) adiocinado em DESTREZA!");
                        this.Dest += resp2;
                        this.pontos -= resp2;
                        if (this.Dest == 10 + this.limD) {
                            MaxD = " - MAX";
                        }
                    } else if (this.Dest == 10 + this.limD) {
                        System.out.println("O atributo DESTREZA está no nível máximo");
                    }
                    break;
                case 4:
                    if (resp2 == 0) {
                        System.out.println("Valor inválido.");
                    } else if (this.Pod + resp2 <= 10 + this.limP) {
                        System.out.println("\n" + resp2 + " ponto(s) adiocinado em PODER!");
                        this.Pod += resp2;
                        this.pontos -= resp2;
                        if (this.Pod == 10 + this.limP) {
                            MaxP = " - MAX";
                        }
                    } else if (this.Pod == 10 + this.limP) {
                        System.out.println("O atributo PODER está no nível máximo");
                    }
                    break;
                case 5:
                    System.out.println("Finalizando!");
                    break;
            }
        }
    }

    public static Arma Pergunta(String tipo, boolean primaria, int id) {
        int dano, peso, def = 0;
        String NomeArm;
        Scanner r = new Scanner(System.in);
        System.out.println("Você escolheu um(a) " + tipo + "! Qual é o dano do(a) " + tipo + "?");
        do {
            dano = r.nextInt();
            if (dano < 1) {
                System.out.println("Sua arma deve conter dano MAIOR que 0. Digite novamente!");
            } else if(dano > 100){
                System.out.println("Sua arma deve conter um dano MENOR que 100. Digite novamente!");
            }
        } while (dano < 1 || dano > 100);
        System.out.println("Qual é o peso que o(a) " + tipo + " tem?");
        do {
            peso = r.nextInt();
            if (peso < 1) {
                System.out.println("Sua arma deve conter peso MAIOR que 0. Digite novamente!");
            }
        } while (peso < 1);
        System.out.println("E por ultimo, mas não menos importante, qual é o NOME do(a) seu(sua) " + tipo + "?");
        r.nextLine();
        NomeArm = r.nextLine();
        NomeArm = NomeArm.toUpperCase();
        Arma a = new Arma(dano, peso, def, primaria, tipo, NomeArm, id);
        return a;
    }

}
