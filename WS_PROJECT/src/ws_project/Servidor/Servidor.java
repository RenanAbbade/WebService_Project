/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project.Servidor;

/**
 *
 * @author Renan Henrique da Silva Abbade
 */
import ws_project.timeDAO;
import ws_project.TimeResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 *
 * @author Joaquim Pessôa Filho
 */
public class Servidor extends Application<Configuration> {

    @Override
    public void run(Configuration t, Environment e) throws Exception {
        timeDAO dao = new timeDAO();
        e.jersey().register(new TimeResource(dao));
    }
    
    public static void main(String args[]) throws Exception {
        Servidor s = new Servidor();
        s.run(new String[]{ "server" });
    }
}