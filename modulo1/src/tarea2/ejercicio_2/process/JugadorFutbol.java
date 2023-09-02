package tarea2.ejercicio_2.process;

import java.util.Objects;

public class JugadorFutbol {
    private String name;
    private int shirtNumber;
    private boolean isCapitain;
    private boolean hasTeam;

    // Constructor
    public JugadorFutbol(String name, int shirtNumber, boolean isCapitain) {
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.isCapitain = isCapitain;
        hasTeam = false;
    }

    public String getName() {
        return name;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public boolean isCapitain() {
        return isCapitain;
    }

    public boolean getHasTeam() {
        return hasTeam;
    }

    public void setHasTeam(boolean hasTeam) {
        this.hasTeam = hasTeam;
    }

    @Override
    public String toString() {
        return "JugadorFutbol{" +
                "name='" + name + '\'' +
                ", shirtNumber=" + shirtNumber +
                ", isCapitain=" + isCapitain +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JugadorFutbol that = (JugadorFutbol) o;
        return shirtNumber == that.shirtNumber && isCapitain == that.isCapitain && Objects.equals(name, that.name);
    }

}
