/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Jogo {
    private int id;
    private String timeA;
    private String timeB;
    private  String golsTimeA;
    private  String golsTimeB;
    
    
    public Jogo(){
        //Constructor
    }
    
    public Jogo(int id, String timeA, String timeB,  String golsTimeA,  String golsTimeB){
        this.id = id;
        this.timeA = timeA;
        this.timeB = timeB;
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
    }

    public String getgolsTimeA() {
        return golsTimeA;
    }

    public String getgolsTimeB() {
        return golsTimeB;
    }

    public int getId() {
        return id;
    }

    public String getTimeA() {
        return timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public void setgolsTimeA(String golsTimeA) {
        this.golsTimeA = golsTimeA;
    }

    public void setgolsTimeB(String golsTimeB) {
        this.golsTimeB = golsTimeB;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeA(String timeA) {
        this.timeA = timeA;
    }

    public void setTimeB(String timeB) {
        this.timeB = timeB;
    }
    
    
    
    
    
    
}

