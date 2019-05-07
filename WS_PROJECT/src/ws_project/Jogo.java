/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;

public class Jogo {
    private long id;
    private Time timeA;
    private Time timeB;
    private int GolsTimeA;
    private int GolsTimeB;
    
    
    public Jogo(){
        //Constructor
    }
    
    public Jogo(long id, Time timea, Time timeb,int golsTimeA, int golsTimeB){
        this.id = id;
        this.timeA = timea;
        this.timeB = timeb;
        this.GolsTimeA = golsTimeA;
        this.GolsTimeB = golsTimeB;
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

    public Time getTimeA() {
        return timeA;
    }

    public Time getTimeB() {
        return timeB;
    }

    public void setGolsTimeA(int GolsTimeA) {
        this.GolsTimeA = GolsTimeA;
    }

    public void setGolsTimeB(int GolsTimeB) {
        this.GolsTimeB = GolsTimeB;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimeA(Time timeA) {
        this.timeA = timeA;
    }

    public void setTimeB(Time timeB) {
        this.timeB = timeB;
    }
    
    
    
    
    
    
}



