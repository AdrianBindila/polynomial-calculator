package org.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialString {

    public Polynomial parsePolynomial(String string) {
        string = removeSpaces(string);
        final Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        final Matcher matcher = pattern.matcher(string);
        Polynomial polynomial = new Polynomial();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {

                parseMonomialsIntoPoly(matcher.group(i), polynomial);
            }
        }
        return polynomial;
    }

    private String removeSpaces(String string) {
        final Pattern pattern = Pattern.compile("\\s*", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(string);
        string = matcher.replaceAll("");
        return string;
    }

    private void parseMonomialsIntoPoly(String string, Polynomial polynomial) {
        string = removeSpaces(string);
        final String regex = "([+-]?)(\\d*)?([*]?[a-z])?([\\^])?([\\d]*)?";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            if (!matcher.group().isEmpty() && !matcher.group().isBlank()) {
                Monomial monomial = new Monomial();
                if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                    monomial.setCoefficient(Double.parseDouble(matcher.group(2)));
                } else {
                    monomial.setCoefficient(1);
                }
                if (matcher.group(1).contains("-")) {
                    monomial.setCoefficient(-monomial.getCoefficient());
                }
                if (matcher.group(4) != null && !matcher.group(4).isEmpty()) {
                    monomial.setExponent(Integer.parseInt(matcher.group(5)));
                } else {
                    if (matcher.group(3) != null && !matcher.group(3).isEmpty()) {
                        monomial.setExponent(1);
                    } else {
                        monomial.setExponent(0);
                    }
                }
                polynomial.addMonomial(monomial);
            }
        }
    }
}
