package trabalhorpg;

public class Arqueiro extends Personagem {

    Criar sel = new Criar() {};

    public Arqueiro(String nomePer, int lvl, String classe, String descPer, int iddPer) {
        super(nomePer, lvl, classe, descPer, iddPer);
        this.limD = 3;
        this.limF = -1;
        this.Forc += this.limF;
        this.Dest += this.limD;
    }
}
