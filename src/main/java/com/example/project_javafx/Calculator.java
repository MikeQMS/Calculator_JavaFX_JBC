package com.example.project_javafx;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator {

    String input;
    Double resultCalculator;

    public Calculator(String input) {
        setInput(input);
    }

    public String getInput() {
        return input;
    }

    private void setInput(String input) {
        this.input = input;
    }

    public Double getResultCalculator() {
        return resultCalculator;
    }

    public void setResultCalculator(Double resultCalculator) {
        this.resultCalculator = resultCalculator;
    }

    public Calculator() {
    }

    public Double calculate(){
        String a = input.trim();
        ArrayList<String> b = new ArrayList<>();
        ArrayList d = new ArrayList<>();

        for (String p : a.split("\\s")) {
            b.add(p.replaceAll("\\s+", ""));
        }

        for (String number : b) {
            if (number.contains("√")) {
                d.add(Double.parseDouble(number.substring(0, number.indexOf("√"))));
                d.add("√");
                d.add(Double.parseDouble(number.substring((number.indexOf("√")+1))));
            }
            else if((number).equals("+") || (number).equals("-") || (number).equals("*") || (number).equals("/") || (number).equals("^") || (number).equals("%")  || (number).equals("|AM|") ) {
                d.add(number);
            } else
            {
                var c = Double.parseDouble(number);
                d.add(c);
            }
        }
        if (Collections.frequency(d, "%") > 0 || Collections.frequency(d, "^") > 0 || Collections.frequency(d, "√") > 0 || Collections.frequency(d, "/") > 0 || Collections.frequency(d, "*") > 0 || Collections.frequency(d, "-") > 0 || Collections.frequency(d, "+") > 0 || Collections.frequency(d, "|AM|") > 0) {
            for (int i = Collections.frequency(d, "|AM|"); i > 0; i--) {
                double result = aegypt((double)d.get(d.indexOf("|AM|") - 1), (double)d.get(d.indexOf("|AM|") + 1));
                d.remove(d.indexOf("|AM|") - 1);
                d.remove(d.indexOf("|AM|") + 1);
                d.add(d.indexOf("|AM|"), result);
                d.remove(d.indexOf("|AM|"));
            }
            for (int i = Collections.frequency(d, "√"); i > 0; i--) {
                double result = Math.pow((double)d.get(d.indexOf("√") + 1), 1/(double)d.get(d.indexOf("√") - 1));
                d.remove(d.indexOf("√") - 1);
                d.remove(d.indexOf("√") + 1);
                d.add(d.indexOf("√"), result);
                d.remove(d.indexOf("√"));
            }
            for (int i = Collections.frequency(d, "^"); i > 0; i--) {
                double result = Math.pow((double)d.get(d.indexOf("^") - 1), (double)d.get(d.indexOf("^") + 1));
                d.remove(d.indexOf("^") - 1);
                d.remove(d.indexOf("^") + 1);
                d.add(d.indexOf("^"), result);
                d.remove(d.indexOf("^"));
            }
            for (int i = Collections.frequency(d, "%"); i > 0; i--) {
                double result = (double)d.get(d.indexOf("%") - 1) % (double)d.get(d.indexOf("%") + 1);
                d.remove(d.indexOf("%") - 1);
                d.remove(d.indexOf("%") + 1);
                d.add(d.indexOf("%"), result);
                d.remove(d.indexOf("%"));
            }
            for (int i = Collections.frequency(d, "*"); i > 0; i--) {
                double result = (double)d.get(d.indexOf("*") - 1) * (double)d.get(d.indexOf("*") + 1);
                d.remove(d.indexOf("*") - 1);
                d.remove(d.indexOf("*") + 1);
                d.add(d.indexOf("*"), result);
                d.remove(d.indexOf("*"));
            }
            for (int i = Collections.frequency(d, "/"); i > 0; i--) {
                double result = (double)d.get(d.indexOf("/") - 1) / (double)d.get(d.indexOf("/") + 1);
                d.remove(d.indexOf("/") - 1);
                d.remove(d.indexOf("/") + 1);
                d.add(d.indexOf("/"), result);
                d.remove(d.indexOf("/"));
            }
            for (int i = Collections.frequency(d, "-"); i > 0; i--) {
                double result = (double)d.get(d.indexOf("-") - 1) - (double)d.get(d.indexOf("-") + 1);
                d.remove(d.indexOf("-") - 1);
                d.remove(d.indexOf("-") + 1);
                d.add(d.indexOf("-"), result);
                d.remove(d.indexOf("-"));
            }
            for (int i = Collections.frequency(d, "+"); i > 0; i--) {
                double result = (double)d.get(d.indexOf("+") - 1) + (double)d.get(d.indexOf("+") + 1);
                d.remove(d.indexOf("+") - 1);
                d.remove(d.indexOf("+") + 1);
                d.add(d.indexOf("+"), result);
                d.remove(d.indexOf("+"));
            }
        }
        setResultCalculator((double)d.get(0));
        return (double)d.get(0);
    }

    public double aegypt(double zahl1, double zahl2) {
        int a = (int)zahl1;
        int b = (int)zahl2;

        int ergebnis = 0;

        while (a > 0) {
            int rest = a % 2;          //Modulo (Rest)
            if (rest == 1) {
                ergebnis = ergebnis + b;
                a = a - 1;
            }
            b = b << 1;            //# Verdopplung
            a = a >> 1;            //# Halbieren ohne Rest
        }
        return ergebnis;

    }
}
