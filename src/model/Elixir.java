package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Elixir {
    private DoubleProperty elixirValue ;

    public Elixir(double elixirValue) {
        this.elixirValue = new SimpleDoubleProperty(elixirValue);
    }

    public void add(double value){
        this.elixirValue.set(elixirValue.add(value).get());
        if (elixirValue.greaterThan(10).get()){
            elixirValue.set(10);
        }
    }

    public double getElixirValue() {
        return elixirValue.get();
    }

    public DoubleProperty elixirValueProperty() {
        return elixirValue;
    }

    public void setElixirValue(double elixirValue) {
        this.elixirValue.set(elixirValue);
    }
}
