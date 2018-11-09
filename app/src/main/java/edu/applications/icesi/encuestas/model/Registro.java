package edu.applications.icesi.encuestas.model;

public class Registro {

    private String id;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;

    public Registro() {
    }

    public Registro(String r1, String r2, String r3, String r4, String r5) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getR4() {
        return r4;
    }

    public void setR4(String r4) {
        this.r4 = r4;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Registro){
            Registro c = (Registro) obj;
            return this.getId().equals(c.getId());
        }
        return false;
    }

}
