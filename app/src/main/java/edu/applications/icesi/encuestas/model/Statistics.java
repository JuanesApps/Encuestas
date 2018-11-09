package edu.applications.icesi.encuestas.model;

import java.util.ArrayList;

public class Statistics {

    private ArrayList<Registro> registros;

    private ResultPregunta pregunta1;
    private ResultPregunta pregunta2;
    private ResultPregunta pregunta3;
    private ResultPregunta pregunta4;
    private ResultPregunta pregunta5;

    public Statistics(ArrayList<Registro> registros) {
        this.registros = registros;
        ArrayList<String> q1 = new ArrayList<>();
        ArrayList<String> q2 = new ArrayList<>();
        ArrayList<String> q3 = new ArrayList<>();
        ArrayList<String> q4 = new ArrayList<>();
        ArrayList<String> q5 = new ArrayList<>();

        for (int i = 0; i < registros.size(); i++){
            q1.add(registros.get(i).getR1());
            q2.add(registros.get(i).getR2());
            q3.add(registros.get(i).getR3());
            q4.add(registros.get(i).getR4());
            q5.add(registros.get(i).getR5());
        }

        pregunta1 = new ResultPregunta(q1);
        pregunta2 = new ResultPregunta(q2);
        pregunta3 = new ResultPregunta(q3);
        pregunta4 = new ResultPregunta(q4);
        pregunta5 = new ResultPregunta(q5);

    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    public ResultPregunta getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(ResultPregunta pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public ResultPregunta getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(ResultPregunta pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public ResultPregunta getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(ResultPregunta pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public ResultPregunta getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(ResultPregunta pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public ResultPregunta getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(ResultPregunta pregunta5) {
        this.pregunta5 = pregunta5;
    }

    @Override
    public String toString() {
        String s = "Pregunta 1:\n" +
                "\t A : " + pregunta1.getPorcentajeA() + "%\n" +
                "\t B : " + pregunta1.getPorcentajeB() + "%\n" +
                "\t C : " + pregunta1.getPorcentajeC() + "%\n" +
                "\t D : " + pregunta1.getPorcentajeD() + "%\n";

        return s;
    }
}
