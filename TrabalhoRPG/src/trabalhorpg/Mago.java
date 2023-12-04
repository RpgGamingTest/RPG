package trabalhorpg;

public class Mago extends Personagem {

    Criar sel = new Criar() {};

    public Mago(String nomePer, int lvl, String classe, String descPer, int iddPer, int idClasse) {
        super(nomePer, lvl, classe, descPer, iddPer, idClasse);
        this.limP = 3;
        this.limV = 1;
        this.limF = -2;
        this.Forc += this.limF;
        this.Pod += this.limP;
        this.Vit += this.limV;
    }
}
