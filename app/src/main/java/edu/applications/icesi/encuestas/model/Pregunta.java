package edu.applications.icesi.encuestas.model;

public class Pregunta {

    private String nombre;
    private String enunciado;
    private String[] opciones;

    public Pregunta() {
        this.opciones = new String[4];
    }

    public Pregunta(String enunciado, String opcionUno, String opcionDos, String opcionTres, String opcionCuatro) {
        this.enunciado = enunciado;
        this.opciones = new String[4];
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}