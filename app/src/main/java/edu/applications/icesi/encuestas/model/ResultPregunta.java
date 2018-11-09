package edu.applications.icesi.encuestas.model;

import java.util.ArrayList;

public class ResultPregunta {

    private double porcentajeA;
    private double porcentajeB;
    private double porcentajeC;
    private double porcentajeD;

    public ResultPregunta(ArrayList<String> results) {

        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;

        for (int i = 0; i < results.size(); i++){
            if (results.get(i).charAt(0) == 'a'){
                a ++;
            }
            if (results.get(i).charAt(0) == 'b'){
                b ++;
            }
            if (results.get(i).charAt(0) == 'c'){
                c ++;
            }
            if (results.get(i).charAt(0) == 'd'){
                d ++;
            }
        }

        porcentajeA = a/results.size() * 100.0;
        porcentajeB = b/results.size() * 100.0;
        porcentajeC = c/results.size() * 100.0;
        porcentajeD = d/results.size() * 100.0;

    }

    public double getPorcentajeA() {
        return porcentajeA;
    }

    public void setPorcentajeA(double porcentajeA) {
        this.porcentajeA = porcentajeA;
    }

    public double getPorcentajeB() {
        return porcentajeB;
    }

    public void setPorcentajeB(double porcentajeB) {
        this.porcentajeB = porcentajeB;
    }

    public double getPorcentajeC() {
        return porcentajeC;
    }

    public void setPorcentajeC(double porcentajeC) {
        this.porcentajeC = porcentajeC;
    }

    public double getPorcentajeD() {
        return porcentajeD;
    }

    public void setPorcentajeD(double porcentajeD) {
        this.porcentajeD = porcentajeD;
    }
}
