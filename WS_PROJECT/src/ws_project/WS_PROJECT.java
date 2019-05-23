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
          Jogo jogw = new Jogo(3,"J","X",3,4);
          JogoDAO x = new JogoDAO();
          x.create(jogw);   

    }
    
}
