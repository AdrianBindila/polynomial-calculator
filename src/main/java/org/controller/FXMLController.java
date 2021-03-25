package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.model.Monomial;
import org.model.Polynomial;
import org.model.PolynomialString;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {
    @FXML
    private Label polynomialSelector;
    @FXML
    private Label opIndicator;
    @FXML
    private TextField inputBar;
    @FXML
    private TextField resultBar;
    @FXML
    private TextField remainderBar;
    @FXML
    private Button helpBtn;

    private String currentOp;
    private String currentPolySymbol;
    private Polynomial currentPoly;
    private Polynomial polynomialP;
    private Polynomial polynomialQ;
    private Polynomial polynomialRes;

    public FXMLController() {
        polynomialP = new Polynomial();
        polynomialQ = new Polynomial();
        polynomialRes = new Polynomial();
        currentPoly = polynomialP;
        currentPolySymbol = "P";
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void getInput() {
        PolynomialString polynomialString = new PolynomialString();
        if (currentPolySymbol.contains("P")) {
            polynomialP = polynomialString.parsePolynomial(inputBar.getText());
        } else {
            polynomialQ = polynomialString.parsePolynomial(inputBar.getText());
        }
    }

    @FXML
    private void add() {
        System.out.println("+");
        currentOp = "+";
        polynomialRes = polynomialP.add(polynomialQ);
        displayCurrentOp();
        selectResult();
    }

    @FXML
    private void subtract() {
        System.out.println("-");
        currentOp = "-";
        polynomialRes = polynomialP.subtract(polynomialQ);
        displayCurrentOp();
        selectResult();
    }

    @FXML
    private void multiply() {
        System.out.println("*");
        currentOp = "*";
        polynomialRes = polynomialP.multiply(polynomialQ);
        displayCurrentOp();
        selectResult();
    }

    @FXML
    private void divide() {
        System.out.println("/");
        currentOp = "/";
        ArrayList<Polynomial> result = polynomialP.divide(polynomialQ);
        if (result == null) {
            remainderBar.setText("CANNOT DIVIDE BY 0!");
        } else {
            displayPolynomial(result.get(0));
            displayPolynomial(result.get(1), 0);
        }
        displayCurrentOp();
    }

    @FXML
    private void derivate() {
        System.out.println("dx");
        currentOp = "dx";
        polynomialRes = currentPoly.derive();
        displayCurrentOp();
        selectResult();
    }

    @FXML
    private void integrate() {
        System.out.println("∫");
        currentOp = "∫";
        polynomialRes = currentPoly.integrate();
        displayCurrentOp();
        selectResult();
    }

    @FXML
    private void help() {
        System.out.println("?");
        helpBtn.setText("WIP");
    }

    @FXML
    private void clear() {
        System.out.println("C");
        currentPoly.clearPolynomial();
        inputBar.setText("");
    }

    @FXML
    private void clearEverything() {
        System.out.println("CE");
        polynomialP.clearPolynomial();
        polynomialQ.clearPolynomial();
        polynomialRes.clearPolynomial();
        currentPoly = polynomialP;
        currentOp = "Op";
        inputBar.setText("");
        remainderBar.setText("");
        resultBar.setText("");
    }

    @FXML
    private void selectP() {
        System.out.println("P");
        currentPoly = polynomialP;
        displayPolynomial(currentPoly);
        currentPolySymbol = "P";
        displayCurrentPolySymbol();
    }

    @FXML
    private void selectQ() {
        System.out.println("Q");
        currentPoly = polynomialQ;
        displayPolynomial(currentPoly);
        currentPolySymbol = "Q";
        displayCurrentPolySymbol();
    }

    @FXML
    private void selectResult() {
        System.out.println("=");
        displayPolynomial(polynomialRes);
        currentPolySymbol = "=";
    }

    @FXML
    private void displayCurrentPolySymbol() {
        System.out.println("displayCurrentPoly");
        polynomialSelector.setText(currentPolySymbol);
    }

    @FXML
    private void displayCurrentOp() {
        System.out.println("displayCurrentOp");
        opIndicator.setText(currentOp);
    }

    private StringBuilder prepareText(Polynomial p) {
        StringBuilder string = new StringBuilder();
        for (int i = p.getTerms().size() - 1; i >= 0; i--) {
            Monomial currentMonomial = p.getTerms().get(i);
            double coefficient = currentMonomial.getCoefficient();
            int exponent = currentMonomial.getExponent();
            if (i != (p.getTerms().size() - 1) && coefficient > 0) {
                string.append("+");
            }
            if (coefficient != 0) {
                if (coefficient == 1 && exponent > 1) {
                    string.append(String.format("x^%d", exponent));
                } else if (coefficient == -1 && exponent > 1) {
                    string.append(String.format("-x^%d", exponent));
                } else if (exponent == 1) {
                    if (coefficient == 1) {
                        string.append("x");
                    } else if (coefficient == -1) {
                        string.append("-x");
                    } else {
                        if (coefficient % 1 == 0) {
                            string.append(String.format("%.0fx", coefficient));
                        } else {
                            string.append(String.format("%.2fx", coefficient));
                        }
                    }
                } else if (exponent == 0) {
                    if (coefficient % 1 == 0) {
                        string.append(String.format("%.0f", coefficient));
                    } else {
                        string.append(String.format("%.2f", coefficient));
                    }
                } else {
                    if (coefficient % 1 == 0) {
                        string.append(String.format("%.0fx^%d", coefficient, exponent));
                    } else {
                        string.append(String.format("%.2fx^%d", coefficient, exponent));
                    }
                }
            }
        }
        return string;
    }

    private void displayPolynomial(Polynomial p) {
        if (p.getTerms().size() == 1 && p.getTerms().get(0).getCoefficient() == 0) {
            resultBar.setText("0");
        } else {
            resultBar.setText(prepareText(p).toString());
        }
    }

    private void displayPolynomial(Polynomial p, int i) {
        if (p.getTerms().size() == 1 && p.getTerms().get(0).getCoefficient() == 0) {
            remainderBar.setText("0");
        } else {
            remainderBar.setText(prepareText(p).toString());
        }
    }

}