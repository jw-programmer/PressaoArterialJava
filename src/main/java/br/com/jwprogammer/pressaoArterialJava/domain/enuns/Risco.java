package br.com.jwprogammer.pressaoArterialJava.domain.enuns;

public enum Risco {
    NORMAL(0), LEVE(1), MODERADA(2), GRAVE(3);

    private final int risco;

    Risco(int risco) {
        this.risco = risco;
    }

    public int getRisco(){
        return risco;
    }

    public static Risco toEnun(int cod) {
        for (Risco x: Risco.values()) {
            if(cod == x.getRisco()){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
