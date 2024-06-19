package entity;

public class Candidato {
    private int numero;
    private String nome;

    public Candidato(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }
}
