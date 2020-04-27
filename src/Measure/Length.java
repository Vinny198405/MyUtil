package Measure;

import java.util.Objects;

public class Length {
    private float number;
    private LengthUnit unit;

    public Length(float number, LengthUnit unit) {
        this.number = number;
        this.unit = unit;
    }

    public Length plus(Length length) {
        return new Length((makeNumber() + length.makeNumber()) / unit.getValue(), unit);
    }

    public Length minus(Length length) {
        return new Length((makeNumber() - length.makeNumber()) / unit.getValue(), unit);
    }

    public Length convert(LengthUnit lengthUnit) {
        return new Length(number * unit.getValue() / lengthUnit.getValue(), lengthUnit);
    }

    public float makeNumber() {
        return number * unit.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;
        return Float.compare(length.number, number) == 0 &&
                unit == length.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, unit);
    }

    @Override
    public String toString() {
        return number + unit.toString();
    }

    public float getNumber() {
        return number;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setUnit(LengthUnit unit) {
        this.unit = unit;
    }
}
