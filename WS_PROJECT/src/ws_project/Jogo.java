/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;

public class Jogo {
    private int id;
    private String timeA;
    private String timeB;
    private int GolsTimeA;
    private int GolsTimeB;
    
    
    public Jogo(){
        //Constructor
    }
    
    public Jogo(int id, String timeA, String timeB,int GolsTimeA, int GolsTimeB){
        this.id = id;
        this.timeA = timeA;
        this.timeB = timeB;
        this.GolsTimeA = GolsTimeA;
        this.GolsTimeB = GolsTimeB;
    }

    public int getGolsTimeA() {
        return GolsTimeA;
    }

    public int getGolsTimeB() {
        return GolsTimeB;
    }

    public long getId() {
        return id;
    }

    public String getTimeA() {
        return timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public void setGolsTimeA(int GolsTimeA) {
        this.GolsTimeA = GolsTimeA;
    }

    public void setGolsTimeB(int GolsTimeB) {
        this.GolsTimeB = GolsTimeB;
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

