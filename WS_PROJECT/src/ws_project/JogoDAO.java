/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dell
 */
public class JogoDAO {
    
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
    
    public JogoDAO(){ //Constructor
    try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Project","app","app");
            
            this.stmC = this.conn.prepareStatement("INSERT INTO jogo_fut(TIMEA, TIMEB, GOLSTIMEA, GOLSTIMEB) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM jogo_fut");
            this.stmU = this.conn.prepareStatement("UPDATE jogo_fut SET timeA=?, timeB=?, golsTIMEA=?, golsTIMEB=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM jogo_fut WHERE id=?");        
            
            
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("Driver não encontrado");
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("Erro de conexão");
        }    

    }
    
    public void close(){
        try{
            this.conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public Jogo create(Jogo novoJogo) {//C
        try {
            this.stmC.setString(1, novoJogo.getTimeA());
            this.stmC.setString(2, novoJogo.getTimeB());
            this.stmC.setInt(3, novoJogo.getGolsTimeA());
            this.stmC.setInt(4, novoJogo.getGolsTimeB());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoJogo.setId(id);
            
            return novoJogo;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
}
    
    public List<Jogo> read() {//R
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Jogo> jogos = new ArrayList<>();
            
            while(rs.next()) {
                Jogo p = new Jogo();
                p.setId(rs.getLong("id"));
                p.setTimeA(rs.getString("Time A"));
                p.setTimeB(rs.getString("Time B"));
                p.setGolsTimeA(rs.getInt("Gols A"));
                p.setGolsTimeB(rs.getInt("Gols B"));
             
                jogos.add(p);
            }
            
            return jogos;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @SuppressWarnings("CallToPrintStackTrace")
    public List<Time> lerTodos(){//R
        try{
            ResultSet rs = this.stmR.executeQuery();
            List<Time> times = new ArrayList<>();
            
            while(rs.next()){
                Time aux = new Time();
                aux.setId(rs.getInt("id"));
                aux.setNomeTime(rs.getString("nome"));
                aux.setCidade(rs.getString("cidade"));
                aux.setEstado("estado");
                times.add(aux);
            }
            return times;
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null; //Se o banco tiver vazio.
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean update(Jogo jogo){//U
        try{
            this.stmU.setString(1, jogo.getTimeA());
            this.stmU.setString(2, jogo.getTimeB());
            this.stmU.setInt(3, jogo.getGolsTimeA());
            this.stmU.setInt(4, jogo.getGolsTimeB());
            
            return this.stmU.executeUpdate() > 0 ;
   
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
     @SuppressWarnings("CallToPrintStackTrace")
     public boolean delete(int id){//D
         try{
             this.stmD.setInt(1,id);
             return this.stmD.executeUpdate() > 0;
             
         }catch(SQLException e){
             e.printStackTrace();
         }
         return false;
         
     }

 
}

