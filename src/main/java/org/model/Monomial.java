package org.model;

public class Monomial {
    private double coefficient;
    private int exponent;

    Monomial() {
        coefficient = 0;
        exponent = 0;
    }

    Monomial(int exponent, double coefficient) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }


    protected Monomial add(final Monomial monomial) {
        Monomial result = new Monomial();
        result.coefficient = this.coefficient + monomial.coefficient;
        result.exponent = this.exponent;
        return result;
    }

    protected Monomial subtract(final Monomial monomial) {
        Monomial result = new Monomial();
        result.coefficient = this.coefficient - monomial.coefficient;
        result.exponent = this.exponent;
        return result;
    }

    protected Monomial multiply(final Monomial monomial) {
        Monomial result = new Monomial();
        result.coefficient = this.coefficient * monomial.coefficient;
        result.exponent = this.exponent + monomial.exponent;
        return result;
    }

    protected Monomial divide(final Monomial monomial) {
        Monomial result = new Monomial();
        if (monomial != null && monomial.coefficient != 0) {
            result.coefficient = this.coefficient / monomial.coefficient;
            result.exponent = this.exponent - monomial.exponent;
        } else {
            System.out.println("Divide by 0");
        }
        return result;
    }

    protected void derive() {
        if (exponent > 0) {
            coefficient *= exponent;
            exponent--;
        } else {
            coefficient = 0;
            exponent = 0;
        }
    }

    protected void integrate() {
        exponent++;
        coefficient /= exponent;
    }
}
