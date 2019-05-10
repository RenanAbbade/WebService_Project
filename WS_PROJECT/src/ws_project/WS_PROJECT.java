/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;

import java.util.List;

/**
 *Renan
 * @author Dell
 */
public class WS_PROJECT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        timeDAO dao = new  timeDAO();
        
        List<Time> times = dao.lerTodos();
        System.out.println(times.size());
        
        times.get(0).setNomeTime("São Paulo");
        times.get(0).setEstado("São Paulo");
        times.get(0).setCidade("SP");
     
        
        dao.atualizar(times.get(0));
    }
    
}
