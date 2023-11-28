package trabalhorpg;

public class Inimigo {
    private int iniciativa, vida, ataque, defesa;
    private String nome;

    
    public Inimigo(int iniciativa, int vida, int ataque, int defesa, String nome) {
        this.iniciativa = iniciativa;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getIniciativa() {
        return iniciativa;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIniciativa(int iniciativa) {
        this.iniciativa = iniciativa;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    
}
