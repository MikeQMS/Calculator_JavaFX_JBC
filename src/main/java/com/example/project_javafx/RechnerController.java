package com.example.project_javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.*;

public class RechnerController {
    public Label inputOutput;
    public Label berechnungsSchritte;
    public Button btnNumber1;
    public Button btnNumber2;
    public Button btnNumber3;
    public Button btnNumber4;
    public Button btnNumber5;
    public Button btnNumber6;
    public Button btnNumber7;
    public Button btnNumber8;
    public Button btnNumber9;
    public Button btnNumber0;
    public Button btnComma;
    public Button btnAddition;
    public Button btnSubstraction;
    public Button btnMultiplication;
    public Button btnDivision;
    public Button btnQuadrad;
    public Button btnWurzel;
    public Button btnTeiler;
    public Button btnFak;
    public Button btnMod;
    public Button btnReturn;
    public Button btnPlusMinus;
    public Button btnClear;
    double value;
    boolean accepted = false;
    StringBuilder lab = new StringBuilder();
    StringBuilder together = new StringBuilder();


    public BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static ArrayList<Long> teiler(String value){
        ArrayList<Long> returnValue = new ArrayList<>();
        long teiler;
        long eingabe = (long) Double.parseDouble(value);

        for(teiler = 1; teiler <= eingabe; teiler++) {

            if(eingabe%teiler == 0) {
                returnValue.add(teiler);
            }
        }
        return returnValue;
    }

    private void onClickActionInput(Button btnInput, StringBuilder together, StringBuilder lab) {
        String str = "";
        if(btnInput.equals(btnAddition) || btnInput.equals(btnSubstraction) || btnInput.equals(btnMultiplication) || btnInput.equals(btnDivision) || btnInput.equals(btnQuadrad)) {
            if (btnInput.equals(btnAddition))
                str = " + ";
            else if (btnInput.equals(btnSubstraction))
                str = " - ";
            else if (btnInput.equals(btnMultiplication))
                str = " * ";
            else if (btnInput.equals(btnDivision))
                str = " / ";
            else if (btnInput.equals(btnQuadrad))
                str = " ^ ";
            if (lab.length() == 0 && together.length() > 0 && together.indexOf("|AM|") > -1){
                together.setLength(together.length() - 6);
                together.append(str);
            }
            else if (lab.length() == 0 && together.length() > 0) {
                together.setLength(together.length() - 3);
                together.append(str);
            } else if (lab.length() > 0) {
                together.append(lab);
                together.append(str);
                inputOutput.setText(lab.toString());
                lab.setLength(0);
            }
            berechnungsSchritte.setText(together.toString());
        }
        if (btnInput.equals(btnComma)) {
            if (lab.indexOf(".") == -1)
                lab.append(".");
            if (lab.indexOf(".") == 0)
                lab.insert(0, 0);
            inputOutput.setText(lab.toString().replace(".", ","));
        }
        if (btnInput.equals(btnWurzel)){
            if (lab.length() > 0 && lab.indexOf("√") == -1) {
                lab.append("√");
                inputOutput.setText(lab.toString());}
            else
                inputOutput.setText("0");
        }
        if (btnInput.equals(btnTeiler)){
            if (lab.length() > 0 && lab.indexOf("Teiler") == -1 && together.length() <= 0) {
                together.append("Teiler(");
                if (lab.indexOf(".") != -1) {
                    together.append(lab.substring(0, lab.indexOf(".")));
                } else {
                    together.append(lab.toString());
                }
                together.append(")");
                berechnungsSchritte.setText(together.toString());
                inputOutput.setText(teiler(lab.toString()).toString());
                lab.setLength(0);
                together.setLength(0);
                inputOutput.setTooltip(showToolTip(inputOutput.getText()));
                berechnungsSchritte.setTooltip(showToolTip(berechnungsSchritte.getText()));

            }
        }
        if (btnInput.equals(btnFak)){
            if (lab.length() > 0 && lab.indexOf("!") == -1) {
                together.append("fact(" + ((int) Double.parseDouble(lab.toString())) + ")");
                berechnungsSchritte.setText(together.toString());
                BigInteger fact = factorial((int) Double.parseDouble(lab.toString()));
                lab.setLength(0);
                together.setLength(0);
                inputOutput.setText(fact.toString());
                inputOutput.setTooltip(showToolTip(inputOutput.getText()));
                berechnungsSchritte.setTooltip(showToolTip(berechnungsSchritte.getText()));
                lab.append(fact);

            }
        }
        if (btnInput.equals(btnMod)){
            if (lab.length() == 0 && together.length() > 0) {
                together.setLength(together.length()-1);
                together.append(" % ");
            }
            else{
                together.append(lab);
                together.append(" % ");
                inputOutput.setText(lab.toString());
                lab.setLength(0);
            }
            berechnungsSchritte.setText(together.toString());
        }
        if (btnInput.equals(btnPlusMinus)){
            if (lab.length() > 0 && lab.indexOf("-") == -1) {
                lab.insert(0, "-");}
            else {
                lab.deleteCharAt(lab.indexOf("-"));}
            inputOutput.setText(lab.toString());
        }
        if (btnInput.equals(btnNumber0) || btnInput.equals(btnNumber1) || btnInput.equals(btnNumber2) || btnInput.equals(btnNumber3) || btnInput.equals(btnNumber4) || btnInput.equals(btnNumber5) || btnInput.equals(btnNumber6) || btnInput.equals(btnNumber7) || btnInput.equals(btnNumber8) || btnInput.equals(btnNumber9))
        {
            lab.append(btnInput.getId().substring(btnInput.getId().length() - 1));
            setInputField();
        }
        if (btnInput.equals(btnReturn)) {
            if (lab.length() == 0 && together.length() == 0) {
                lab.setLength(0);
                together.setLength(0);
                inputOutput.setText("0");
                berechnungsSchritte.setText(together.toString().replace(".", ","));
            }
            else {
                DecimalFormat formatter = new DecimalFormat("#,###.########");

                if (lab.length() > 0){
                    together.append(lab.toString());
                }
                else{
                    together.setLength(together.length() - 1);
                }

                String expression = together.toString();
                Calculator calculator = new Calculator(expression);
                Double result = calculator.calculate();
                System.out.println("Input: " + calculator.getInput() + ", Output: " + calculator.getResultCalculator());
                inputOutput.setText(formatter.format(result));
                berechnungsSchritte.setText(together.toString().replace(".", ","));
                lab.setLength(0);
                together.setLength(0);
                lab.append(result);
            }
        }
        btnInput.requestFocus();
    }

    public void onClickActionButton(ActionEvent actionEvent) {
        onClickActionInput((Button)actionEvent.getSource(), together, lab);
    }

    private Tooltip showToolTip(String text) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setPrefWidth(400);
        tooltip.setWrapText(true);
        tooltip.setShowDuration(Duration.INDEFINITE);
        return tooltip;
    }

    public double exceptionHandler(TextField input){
        while(true) {
            try {
                value = Double.parseDouble(input.getText());
                accepted = true;
                break;
            }
            catch (NumberFormatException value) {
                accepted = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Falsche Eingabe!");
                alert.setContentText("Ooops, da hast du wohl keine Zahl eingegeben.\nVersuch es nochmal!");

                alert.showAndWait();
                input.setText("");
                break;
            }
        }
        return value;
    }

    public void keyInputEvent(KeyEvent keyEvent) {
        String inputValue = keyEvent.getText();
        Button keyInput = new Button();
        if (!keyEvent.isShiftDown() && inputValue.equals("1")){
                keyInput = btnNumber1;
            }
        else if (!keyEvent.isShiftDown() && inputValue.equals("2"))
        {
            keyInput = btnNumber2;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("3"))
        {
            keyInput = btnNumber3;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("4"))
        {
            keyInput = btnNumber4;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("5"))
        {
            keyInput = btnNumber5;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("6"))
        {
            keyInput = btnNumber6;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("7"))
        {
            keyInput = btnNumber7;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("8"))
        {
            keyInput = btnNumber8;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("9"))
        {
            keyInput = btnNumber9;
        }
        else if (!keyEvent.isShiftDown() && inputValue.equals("0"))
        {
            keyInput = btnNumber0;
        }

        else if (!keyEvent.isShiftDown() && inputValue.equals(",") && lab.indexOf(".") == -1) {
            keyInput = btnComma;
        }
        /*else if ((keyEvent.isShiftDown() && inputValue.equals("9"))) {
                if (braceCount1 > 0 && !lab.isEmpty()) {
                    braceCount1--;
                    together.append(lab);
                    lab.setLength(0);
                    inputOutput.setText("0");
                    together.append(")");
                }
        }
        */
        /*else if ((keyEvent.isShiftDown() && inputValue.equals("8"))) {
                if (together.indexOf("(") == -1 || braceCount1 == 0) {
                    braceCount1++;
                    together.append("(");
                }
                else if (together.length()-1 == together.lastIndexOf("(")) {
                    braceCount1++;
                    together.append("(");
                }
        }
        */
        else if (keyEvent.isShiftDown() && inputValue.equals("+") && together.length() > 0) {
            keyInput = btnMultiplication;
        }
        else if (keyEvent.isShiftDown() && inputValue.equals("7")) {
            keyInput = btnDivision;
        }
        else if (inputValue.equals("+") || inputValue.equals("-") || inputValue.equals("*") || inputValue.equals("/") ) {
            if (inputValue.equals("+"))
                keyInput = btnAddition;
            else if (inputValue.equals("-"))
                keyInput = btnSubstraction;
            else if (inputValue.equals("*"))
                keyInput = btnMultiplication;
            else
                keyInput = btnDivision;
        }
        else if (inputValue.equals("c") || inputValue.equals("^") || inputValue.equals("m") || inputValue.equals("n") ||
                inputValue.equals("t")) {
                if (inputValue.equals("c")) {
                    btnClear.requestFocus();
                    lab.setLength(0);
                    together.setLength(0);
                    inputOutput.setText("0");
                    berechnungsSchritte.setText(together.toString().replace(".", ","));
                }
            }
        else if (keyEvent.isShiftDown() && keyEvent.getCode().equals(ENTER)) {
                keyInput = btnReturn;
            }

        onClickActionInput(keyInput, together, lab);

   }

    private void setInputField() {
        inputOutput.setText(lab.toString().replace(".", ","));
    }


    public void onClickClear(ActionEvent actionEvent) {
        lab.setLength(0);
        together.setLength(0);
        inputOutput.setText("0");
        berechnungsSchritte.setText(together.toString().replace(".", ","));
    }

    public void onClickClose(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onClickAgypt(ActionEvent actionEvent) {
        if (lab.length() == 0 && together.length() > 0 && together.indexOf("|AM|") == -1) {
            together.setLength(together.length()-3);
            together.append(" |AM| ");
        }
        else if (together.indexOf("|AM|") == -1 && lab.length() > 0){
            together.append(lab);
            together.append(" |AM| ");
            inputOutput.setText(lab.toString());
            lab.setLength(0);
        }
        berechnungsSchritte.setText(together.toString());
    }

    public void onClickInformation(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hilfe Informationen");
        alert.setHeaderText(null);
        alert.getDialogPane().setPrefWidth(400);
        alert.getDialogPane().setPrefHeight(200);
        alert.setContentText("Dies ist ein kleiner Rechner für das Java GrundKurs Projekt.\n\n" +
                "Er unterstützt die Mathematischen Operationen: addieren, subtrahieren, " +
                "multipliezieren, dividieren, potenzieren, radizieren, sowie das bilden " +
                "von Teilern, der Fakultät, Modulu und Ägyptische Multiplikation.\n\n" +
                "Wer Fehler findet kann diese gern für sich behalten. :)");

        alert.showAndWait();
    }

    public void onClickAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wer hats Erfunden?");
        alert.setHeaderText(null);
        alert.setContentText("Micha & Dennis");

        alert.showAndWait();
    }
}
