package org.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolynomialTest {

    @Test
    void test_add1() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^2+x+1");
        Polynomial p = new PolynomialString().parsePolynomial("x^2+1");
        Polynomial q = new PolynomialString().parsePolynomial("x");
        Polynomial result = p.add(q);
        assertEquals(expected, result);
    }

    @Test
    void test_add2() {
        Polynomial expected = new PolynomialString().parsePolynomial("7x^2+3x-11");
        Polynomial p = new PolynomialString().parsePolynomial("x^2-7");
        Polynomial q = new PolynomialString().parsePolynomial("6x^2+3x-4");
        Polynomial result = p.add(q);
        assertEquals(expected, result);
    }

    @Test
    void test_add3() {
        Polynomial expected = new PolynomialString().parsePolynomial("10x^2+6x+1");
        Polynomial p = new PolynomialString().parsePolynomial("7x^2+2x+3");
        Polynomial q = new PolynomialString().parsePolynomial("3x^2+4x-2");
        Polynomial result = p.add(q);
        assertEquals(expected, result);
    }

    @Test
    void test_add4() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^2+2x+8");
        Polynomial p = new PolynomialString().parsePolynomial("3x^2+2x-7");
        Polynomial q = new PolynomialString().parsePolynomial("-2x^2+15");
        Polynomial result = p.add(q);
        assertEquals(expected, result);
    }

    @Test
    void test_add5() {
        Polynomial expected = new PolynomialString().parsePolynomial("6x^3+16x^2-27x+8");
        Polynomial p = new PolynomialString().parsePolynomial("6x^3+12x^2-15x");
        Polynomial q = new PolynomialString().parsePolynomial("4x^2-12x+8");
        Polynomial result = p.add(q);
        assertEquals(expected, result);
    }

    @Test
    void test_subtract1() {
        Polynomial expected = new PolynomialString().parsePolynomial("5x^2+4x+1");
        Polynomial p = new PolynomialString().parsePolynomial("3x^3+5x^2+6x+2");
        Polynomial q = new PolynomialString().parsePolynomial("3x^3+2x+1");
        Polynomial result = p.subtract(q);
        assertEquals(expected, result);
    }

    @Test
    void test_subtract2() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^2-3x+2");
        Polynomial p = new PolynomialString().parsePolynomial("x^3-3x+2");
        Polynomial q = new PolynomialString().parsePolynomial("x^3-x^2");
        Polynomial result = p.subtract(q);
        assertEquals(expected, result);
    }

    @Test
    void test_subtract3() {
        Polynomial expected = new PolynomialString().parsePolynomial("-x^4+8x^3-4x^2-3x+1");
        Polynomial p = new PolynomialString().parsePolynomial("4x^3-3x+1");
        Polynomial q = new PolynomialString().parsePolynomial("x^4-4x^3+4x^2");
        Polynomial result = p.subtract(q);
        assertEquals(expected, result);
    }

    @Test
    void test_subtract4() {
        Polynomial expected = new PolynomialString().parsePolynomial("2");
        Polynomial p = new PolynomialString().parsePolynomial("x+1");
        Polynomial q = new PolynomialString().parsePolynomial("x-1");
        Polynomial result = p.subtract(q);
        assertEquals(expected, result);
    }

    @Test
    void test_subtract5() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^2-2x+2");
        Polynomial p = new PolynomialString().parsePolynomial("x^2+2");
        Polynomial q = new PolynomialString().parsePolynomial("2x");
        Polynomial result = p.subtract(q);
        assertEquals(expected, result);
    }

    @Test
    void test_multiply1() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^2-x-2");
        Polynomial p = new PolynomialString().parsePolynomial("x+1");
        Polynomial q = new PolynomialString().parsePolynomial("x-2");
        Polynomial result = p.multiply(q);
        assertEquals(expected, result);
    }

    @Test
    void test_multiply2() {
        Polynomial expected = new PolynomialString().parsePolynomial("2x^5+x^4-2x^3-2x^2+1");
        Polynomial p = new PolynomialString().parsePolynomial("2x^3+x^2-1");
        Polynomial q = new PolynomialString().parsePolynomial("x^2-1");
        Polynomial result = p.multiply(q);
        assertEquals(expected, result);
    }

    @Test
    void test_multiply3() {
        Polynomial expected = new PolynomialString().parsePolynomial("-x^3+x");
        Polynomial p = new PolynomialString().parsePolynomial("x");
        Polynomial q = new PolynomialString().parsePolynomial("1-x^2");
        Polynomial result = p.multiply(q);
        assertEquals(expected, result);
    }

    @Test
    void test_multiply4() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^3-2x^2-x+2");
        Polynomial p = new PolynomialString().parsePolynomial("x^2-3x+2");
        Polynomial q = new PolynomialString().parsePolynomial("x+1");
        Polynomial result = p.multiply(q);
        assertEquals(expected, result);
    }

    @Test
    void test_multiply5() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^3+5x^2+12x-18");
        Polynomial p = new PolynomialString().parsePolynomial("x-1");
        Polynomial q = new PolynomialString().parsePolynomial("x^2+6x+18");
        Polynomial result = p.multiply(q);
        assertEquals(expected, result);
    }

    @Test
    void test_divide1() {
        Polynomial expectedQuotient = new PolynomialString().parsePolynomial("x");
        Polynomial expectedRemainder = new PolynomialString().parsePolynomial("2");
        Polynomial p = new PolynomialString().parsePolynomial("x^2+2");
        Polynomial q = new PolynomialString().parsePolynomial("x");
        ArrayList<Polynomial> result = p.divide(q);
        assertEquals(expectedQuotient, result.get(0));
        assertEquals(expectedRemainder, result.get(1));
    }

    @Test
    void test_divide2() {
        Polynomial expectedQuotient = new PolynomialString().parsePolynomial("1");
        Polynomial expectedRemainder = new PolynomialString().parsePolynomial("2");
        Polynomial p = new PolynomialString().parsePolynomial("x+1");
        Polynomial q = new PolynomialString().parsePolynomial("x-1");
        ArrayList<Polynomial> result = p.divide(q);
        assertEquals(expectedQuotient, result.get(0));
        assertEquals(expectedRemainder, result.get(1));
    }

    @Test
    void test_divide3() {
        Polynomial expectedQuotient = new PolynomialString().parsePolynomial("1");
        Polynomial expectedRemainder = new PolynomialString().parsePolynomial("0");
        Polynomial p = new PolynomialString().parsePolynomial("x^2+1");
        Polynomial q = new PolynomialString().parsePolynomial("x^2+1");
        ArrayList<Polynomial> result = p.divide(q);
        assertEquals(expectedQuotient, result.get(0));
        assertEquals(expectedRemainder, result.get(1));
    }
    @Test
    void test_divide4() {
        Polynomial expectedQuotient = new PolynomialString().parsePolynomial("x-1");
        Polynomial expectedRemainder = new PolynomialString().parsePolynomial("0");
        Polynomial p = new PolynomialString().parsePolynomial("x+3");
        Polynomial q = new PolynomialString().parsePolynomial("x^2+2x-3");
        ArrayList<Polynomial> result = p.divide(q);
        assertEquals(expectedQuotient, result.get(0));
        assertEquals(expectedRemainder, result.get(1));
    }
    @Test
    void test_divide5() {
        Polynomial expectedQuotient = new PolynomialString().parsePolynomial("8x^2+2x+1");
        Polynomial expectedRemainder = new PolynomialString().parsePolynomial("8");
        Polynomial p = new PolynomialString().parsePolynomial("16x^3-4x^2+7");
        Polynomial q = new PolynomialString().parsePolynomial("2x-1");
        ArrayList<Polynomial> result = p.divide(q);
        assertEquals(expectedQuotient, result.get(0));
        assertEquals(expectedRemainder, result.get(1));
    }
    @Test
    void test_derive1() {
        Polynomial expected = new PolynomialString().parsePolynomial("2x");
        Polynomial p = new PolynomialString().parsePolynomial("x^2");
        Polynomial result = p.derive();
        assertEquals(expected, result);
    }
    @Test
    void test_derive2() {
        Polynomial expected = new PolynomialString().parsePolynomial("3x^2+4x+5");
        Polynomial p = new PolynomialString().parsePolynomial("x^3+2x^2+5x");
        Polynomial result = p.derive();
        assertEquals(expected, result);
    }
    @Test
    void test_derive3() {
        Polynomial expected = new PolynomialString().parsePolynomial("20x^19+2x");
        Polynomial p = new PolynomialString().parsePolynomial("x^20+x^2+3");
        Polynomial result = p.derive();
        assertEquals(expected, result);
    }
    @Test
    void test_derive4() {
        Polynomial expected = new PolynomialString().parsePolynomial("5x^4+3x^2+2x");
        Polynomial p = new PolynomialString().parsePolynomial("x^5+x^3+x^2");
        Polynomial result = p.derive();
        assertEquals(expected, result);
    }
    @Test
    void test_derive5() {
        Polynomial expected = new PolynomialString().parsePolynomial("4x^3+3");
        Polynomial p = new PolynomialString().parsePolynomial("x^4+3x-4");
        Polynomial result = p.derive();
        assertEquals(expected, result);
    }
    @Test
    void test_integrate1() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^3+x^2-x");
        Polynomial p = new PolynomialString().parsePolynomial("3x^2+2x-1");
        Polynomial result = p.integrate();
        assertEquals(expected, result);
    }
    @Test
    void test_integrate2() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^4+3x^2");
        Polynomial p = new PolynomialString().parsePolynomial("4x^3+6x");
        Polynomial result = p.integrate();
        assertEquals(expected, result);
    }
    @Test
    void test_integrate3() {
        Polynomial expected = new PolynomialString().parsePolynomial("x^2");
        Polynomial p = new PolynomialString().parsePolynomial("2x");
        Polynomial result = p.integrate();
        assertEquals(expected, result);
    }
    @Test
    void test_integrate4() {
        Polynomial expected = new PolynomialString().parsePolynomial("x");
        Polynomial p = new PolynomialString().parsePolynomial("1");
        Polynomial result = p.integrate();
        assertEquals(expected, result);
    }
    @Test
    void test_integrate5() {
        Polynomial expected = new PolynomialString().parsePolynomial("12x");
        Polynomial p = new PolynomialString().parsePolynomial("12");
        Polynomial result = p.integrate();
        assertEquals(expected, result);
    }
}