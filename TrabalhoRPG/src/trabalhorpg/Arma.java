package trabalhorpg;
//Armas devem possuir as seguintes características:

//○ Dano de ataque/quantidade de defesa
//○ Peso
//○ Primária ou secundária
//○ Tipo (espada, machado, pistola, escopeta, escudo)

public class Arma extends Criar {

    public Arma(int dano, int peso, int defesa, boolean primaria, String tipo, String nome) {
        this.dano = dano;
        this.peso = peso;
        this.def = defesa;
        this.primaria = primaria;
        this.tipo = tipo;
        this.NomeArm = nome;
    }

    public Criar CriarArm(boolean Pri) {
        System.out.println("Qual o tipo de arma que você deseja obter?"
                + "\n1 - Espada\n2 - Machado\n3 - Pistola\n4 - Arco e Flecha\n5 - Escudo");
        // posição 0:2 = armamentos primários
        do {
            resp = r.nextInt();
            if (resp < 1 || resp > 5) {
                System.out.println("Valor inválido! Digite novamente.");
            }
        } while (resp < 1 || resp > 5);
        switch (resp) {
            case 1:
                def = 0;
                primaria = Pri;
                tipo = "ESPADA";
                personagens.add(Pergunta(tipo, primaria));
                break;
            case 2:
                def = 0;
                primaria = Pri;
                tipo = "MACHADO";
                personagens.add(Pergunta(tipo, primaria));
                break;
            case 3:
                def = 0;
                primaria = Pri;
                tipo = "PISTOLA";
                personagens.add(Pergunta(tipo, primaria));
                break;
            case 4:
                def = 0;
                primaria = Pri;
                tipo = "ARCO E FLECHA";
                personagens.add(Pergunta(tipo, primaria));
                break;
            case 5:
                dano = 0;
                primaria = true;
                tipo = "ESCUDO";
                System.out.println("Você escolheu um " + tipo + "! Qual é a defesa do " + tipo + "?");
                do {
                    def = r.nextInt();
                    if (def < 1) {
                        System.out.println("Seu Escudo deve conter defesa MAIOR que 0. Digite novamente!");
                    }
                } while (def < 1);
                System.out.println("Qual é o peso que o " + tipo + " tem?");
                do {
                    peso = r.nextInt();
                    if (peso < 1) {
                        System.out.println("Sua arma deve conter peso MAIOR que 0. Digite novamente!");
                    }
                } while (peso < 1);
                System.out.println("E por ultimo, mas não menos importante, qual é o NOME do seu " + tipo + "?");
                r.nextLine();
                NomeArm = r.nextLine();
                NomeArm = NomeArm.toUpperCase();
                personagens.add(new Arma(dano, peso, def, primaria, tipo, NomeArm));
                break;
        }
        return personagens.get(0);
    }
}
