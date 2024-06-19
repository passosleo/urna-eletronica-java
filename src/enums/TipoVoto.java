package enums;

public enum TipoVoto {
    BRANCO(1),
    NULO(2);

    private int codigo;

    TipoVoto(int i) {
        this.codigo = i;
    }

    public int getCodigo() {
        return codigo;
    }
}
