package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * The type Elixir.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Elixir {
    // value of elixir
    private DoubleProperty elixirValue;

    /**
     * Instantiates a new Elixir.
     *
     * @param elixirValue the elixir value
     */
    public Elixir(double elixirValue) {
        this.elixirValue = new SimpleDoubleProperty(elixirValue);
    }

    /**
     * Add.
     *
     * @param value the value
     */
    public void add(double value) {
        this.elixirValue.set(elixirValue.add(value).get());
        if (elixirValue.greaterThan(10).get()) {
            elixirValue.set(10);
        }
    }

    /**
     * Gets elixir value.
     *
     * @return the elixir value
     */
    public double getElixirValue() {
        return elixirValue.get();
    }

    /**
     * Elixir value property double property.
     *
     * @return the double property
     */
    public DoubleProperty elixirValueProperty() {
        return elixirValue;
    }

    /**
     * Sets elixir value.
     *
     * @param elixirValue the elixir value
     */
    public void setElixirValue(double elixirValue) {
        this.elixirValue.set(elixirValue);
    }
}
