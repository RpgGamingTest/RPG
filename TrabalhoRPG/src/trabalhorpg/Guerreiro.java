package trabalhorpg;

public class Guerreiro extends Personagem {

    public Guerreiro(String nomePer, int lvl, String classe, String descPer, int iddPer, int idClasse) {
        super(nomePer, lvl, classe, descPer, iddPer, idClasse);
        this.limV = 2;
        this.limF = 3;
        this.limP = -2;
        this.Pod += this.limP;
        this.Vit += this.limV;
        this.Forc += this.limF;
    }

}
