package org.model;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.max;

public class Polynomial {
    private ArrayList<Monomial> terms;
    private int degree;

    public Polynomial() {
        degree = 0;
        terms = new ArrayList<>();
        terms.add(0, new Monomial());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        if (this.degree != that.degree) {
            return false;
        }
        for (int i = 0; i < terms.size(); i++) {
            if (this.terms.get(i).getExponent() != that.terms.get(i).getExponent() ||
                    this.terms.get(i).getCoefficient() != that.terms.get(i).getCoefficient()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(terms, degree);
    }

    public Polynomial(Polynomial other) {
        this.degree = other.degree;
        this.terms = other.terms;
    }

    public ArrayList<Monomial> getTerms() {
        return this.terms;
    }


    protected Monomial search(int degree) {
        for (Monomial monomial :
                terms) {
            if (monomial.getExponent() == degree) {
                return monomial;
            }
        }
        return new Monomial(degree,0);
    }

    protected void addMonomial(Monomial monomial) {
        if (this.terms.isEmpty()) {
            for (int i = 0; i <= monomial.getExponent(); i++) {
                this.terms.add(new Monomial(i, 0));
            }
            this.degree = monomial.getExponent();
        } else if (monomial.getExponent() > this.degree) {
            for (int i = this.degree + 1; i <= monomial.getExponent(); i++) {
                this.terms.add(new Monomial(i, 0));
            }
            this.degree = monomial.getExponent();
        }
        this.terms.set(monomial.getExponent(), this.terms.get(monomial.getExponent()).add(monomial));
        this.updateDegree();
    }

    public void updateDegree() {
        int degree = 0;
        for (Monomial monomial :
                this.terms) {
            if (monomial.getCoefficient() != 0) {
                degree = monomial.getExponent();
            }
        }
        this.degree = degree;
        if (this.terms.size() > this.degree + 1) {
            this.terms.subList(this.degree + 1, this.terms.size()).clear();
        }
    }

    public Polynomial add(final Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (int i = 0; i <= max(this.degree, polynomial.degree); i++) {
            result.addMonomial(this.search(i).add(polynomial.search(i)));//add terms of degree i to result
        }
        result.updateDegree();
        return result;
    }

    public Polynomial subtract(final Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (int i = 0; i <= max(this.degree, polynomial.degree); i++) {
            result.addMonomial(this.search(i).subtract(polynomial.search(i)));
        }
        result.updateDegree();
        return result;
    }

    public Polynomial multiply(final Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Monomial term :
                this.terms) {
            for (Monomial term2 :
                    polynomial.terms) {
                result.addMonomial(term.multiply(term2));
            }
        }
        return result;
    }

    public Polynomial multiply(final Monomial monomial) {
        Polynomial result = new Polynomial();
        for (Monomial term :
                this.terms) {
            result.addMonomial(term.multiply(monomial));
        }
        return result;
    }

    public Monomial getLeadingTerm() {
        Monomial term = new Monomial();
        for (Monomial monomial :
                this.terms) {
            if (monomial.getCoefficient() != 0) {
                if (monomial.getExponent() > term.getExponent()) {
                    term.setExponent(monomial.getExponent());
                    term.setCoefficient(monomial.getCoefficient());
                }
            }
        }
        return term;
    }

    public ArrayList<Polynomial> divide(final Polynomial that) {
        ArrayList<Polynomial> results = new ArrayList<>();
        Polynomial dividend;
        Polynomial divisor;
        if (this.degree >= that.degree) {
            dividend = this;
            divisor = that;
        } else {
            dividend = that;
            divisor = this;
        }
        if (divisor.degree == 0 && divisor.terms.get(0).getCoefficient() == 0) {
            return null;
        } else {
            Polynomial quotient = new Polynomial();
            Polynomial remainder = new Polynomial(dividend);
            while (remainder.degree >= 0 && remainder.degree >= divisor.degree) {
                Monomial term = remainder.getLeadingTerm().divide(divisor.getLeadingTerm());
                quotient.addMonomial(term);
                remainder = remainder.subtract(divisor.multiply(term));
            }
            results.add(quotient);
            results.add(remainder);
        }
        return results;
    }

    public Polynomial derive() {
        Polynomial result = new Polynomial(this);
        for (Monomial monomial :
                result.terms) {
            monomial.derive();
        }
        for (int i = 0; i <result.terms.size()-1; i++) {
            result.terms.set(i,result.terms.get(i+1));
        }

        result.updateDegree();
        return result;
    }

    public Polynomial integrate() {
        Polynomial result = new Polynomial(this);
        for (Monomial monomial :
                result.terms) {
            monomial.integrate();
        }
        result.terms.add(new Monomial());
        for (int i = result.terms.size()-1; i >0; i--) {
            result.terms.set(i,result.terms.get(i-1));
        }
        result.terms.set(0,new Monomial());
        result.updateDegree();
        return result;
    }

    public void clearPolynomial() {
        this.degree = 0;
        this.terms = new ArrayList<>();
        terms.add(0, new Monomial());
    }
}