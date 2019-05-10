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
public class timeDAO {
    
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
    
    @SuppressWarnings("CallToPrintStackTrace")
    public timeDAO(){ //Constructor
    try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Project", "app", "app");
            System.out.println("Conexão estabelecida");
            this.stmC = this.conn.prepareStatement("INSERT INTO time_fut(nome, cidade, estado) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM time_fut");
            this.stmU = this.conn.prepareStatement("UPDATE time_fut SET nome=?, cidade=?, estado=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM time_fut WHERE id=?");
        
            
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
    
    @SuppressWarnings("CallToPrintStackTrace")
    public Time create(Time novoTime) {//C
        try {
            this.stmC.setString(1, novoTime.getNomeTime());
            this.stmC.setString(2, novoTime.getCidade());
            this.stmC.setString(3, novoTime.getEstado());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                novoTime.setId(id);
                return novoTime;
            }
        
        }catch(SQLException e) {
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
    public boolean atualizar(Time time){//U
        try{
            this.stmU.setString(1, time.getNomeTime());
            this.stmU.setString(2, time.getCidade());
            this.stmU.setString(3, time.getEstado());
            this.stmU.setInt(4, time.getId());
            
            return this.stmU.executeUpdate() > 0 ;
   
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
     @SuppressWarnings("CallToPrintStackTrace")
     public boolean apagar(int id){//D
         try{
             this.stmD.setInt(1,id);
             return this.stmD.executeUpdate() > 0;
             
         }catch(SQLException e){
             e.printStackTrace();
         }
         return false;
         
     }
 

 
}

