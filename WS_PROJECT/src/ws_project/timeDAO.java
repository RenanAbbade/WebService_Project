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
    
    public timeDAO(){ //Constructor
    try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Projeto");
            
            this.stmC = this.conn.prepareStatement("INSERT INTO time_fut(nome, cidade, estado) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
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
    public Time create(Time novoTime) {//C
        try {
            this.stmC.setString(1, novoTime.getNomeTime());
            this.stmC.setString(2, novoTime.getCidade());
            this.stmC.setString(2, novoTime.getEstado());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoTime.setId(id);
            
            return novoTime;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
}
    
    public List<Time> read() {//R
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Time> times = new ArrayList<>();
            
            while(rs.next()) {
                Time p = new Time();
                p.setId(rs.getLong("id"));
                p.setNomeTime(rs.getString("nome"));
                p.setCidade(rs.getString("matricula"));
                p.setEstado(rs.getString("matricula"));
             
                times.add(p);
            }
            
            return times;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 

 
}

