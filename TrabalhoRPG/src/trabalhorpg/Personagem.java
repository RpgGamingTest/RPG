package trabalhorpg;

public class Personagem extends Criar{    

    public Personagem(String nomePer, int lvl, String classe, String descPer, int idd) {
        this.nomePer = nomePer;
        this.lvl = lvl;
        this.lvl2 = this.lvl;
        this.classe = classe;
        this.descPer = descPer;
        this.iddPer = idd;
        pontos += lvl2;
    }  
}
