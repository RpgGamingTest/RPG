package trabalhorpg;

public class Arqueiro extends Personagem {

    Criar sel = new Criar() {};

    public Arqueiro(String nomePer, int lvl, String classe, String descPer, int iddPer, int idClasse) {
        super(nomePer, lvl, classe, descPer, iddPer, idClasse);
        this.limD = 3;
        this.limF = -1;
        this.Forc += this.limF;
        this.Dest += this.limD;
    }
}
