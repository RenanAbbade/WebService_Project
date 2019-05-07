/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;

/** Renan
 *TIME (id, nome, ano de fundação, cidade, estado)
Renan
 * @author Dell
 */
public class Time {
    private long id;
    private String nome;
    private String cidade;
    private String estado;
    
    
    public Time(){
        //Constructor
    }
    
    public Time(long id, String nome, String cidade, String estado){
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
        this.nome = nome;
    }
    
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeTime() {
        return nome;
    }

    public void setNomeTime(String nomeTime) {
        this.nome = nomeTime;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
    
    
    
}



