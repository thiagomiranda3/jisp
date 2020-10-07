package br.com.tommiranda.ast;

import java.math.BigDecimal;

public class Value {

    private TypeValue type;
    private Boolean boolValue;
    private String stringValue;
    private BigDecimal bigValue;

    public Value(BigDecimal value) {
        this.bigValue = value;
        this.type = TypeValue.Number;
    }

    public Value(String value) {
        this.stringValue = value;
        this.type = TypeValue.String;
    }

    public Value(Boolean value) {
        this.boolValue = value;
        this.type = TypeValue.Boolean;
    }

    public BigDecimal getAsNumber() {
        if (type != TypeValue.Number) {
            throw new IllegalArgumentException("This value is of type " + TypeValue.Number.name());
        }

        return bigValue;
    }

    public String getAsString() {
        if (type != TypeValue.String) {
            throw new IllegalArgumentException("This value is of type " + TypeValue.String.name());
        }

        return stringValue;
    }

    public Boolean getAsBoolean() {
        if (type != TypeValue.Boolean) {
            throw new IllegalArgumentException("This value is of type " + TypeValue.Boolean.name());
        }

        return boolValue;
    }

    public TypeValue getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(
                switch (getType()) {
                    case Boolean -> getAsBoolean();
                    case String -> getAsString();
                    case Number -> getAsNumber();
                }
        );
    }

    public enum TypeValue {
        Number, String, Boolean;
    }
}
