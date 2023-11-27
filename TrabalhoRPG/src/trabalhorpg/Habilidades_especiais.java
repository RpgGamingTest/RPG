package trabalhorpg;
//Habilidades especiais são talentos que o personagem pode possuir e que
//facilitam na execução de alguma ação. Habilidades só podem ser
//relacionadas a um personagem se esse possuir a quantidade mínima de
//atributo poder que essa habilidade necessita. Por exemplo: para um
//personagem ter uma habilidade “Detectar Armadilhas” necessita de um poder
//mínimo que essa habilidade exige. Habilidades devem possuir as seguintes
//características:
//○ Nome
//○ Descrição
//○ Poder mínimo

public class Habilidades_especiais extends Criar {
    int idClass;
    /*id = 1 --> Guerreiro
     * id = 2 --> Arqueiro
     * id = 3 --> Mago
    */

    public Habilidades_especiais(String nome, String descri, int poder_min, int idClass) {
        this.idClass = idClass;
        this.nomeHab = nome;
        this.descHab = descri;
        this.podMin = poder_min;
    }
}
