package tarea2.ejercicio_1.process;

import java.util.Objects;

public class Pasajero {
    private String nombreCompleto;
    private int numeroBoleto;
    private boolean tieneBoleto;

    public Pasajero(String nombreCompleto, int numeroBoleto) {
        this.nombreCompleto = nombreCompleto;
        this.numeroBoleto = numeroBoleto;
        this.tieneBoleto = true;
    }

    public Pasajero(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        this.numeroBoleto = -1;
        this.tieneBoleto = false;
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public int getNumeroBoleto() {
        return numeroBoleto;
    }

    public boolean tieneBoleto() {
        return tieneBoleto;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", numeroBoleto=" + numeroBoleto +
                ", tieneBoleto=" + tieneBoleto +
                '}';
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Pasajero otroViajero = (Pasajero) objeto;
        return numeroBoleto == otroViajero.numeroBoleto && tieneBoleto == otroViajero.tieneBoleto && Objects.equals(nombreCompleto, otroViajero.nombreCompleto);
    }

}
