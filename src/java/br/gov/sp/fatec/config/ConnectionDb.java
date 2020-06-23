/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.sp.fatec.config;

import br.gov.sp.fatec.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author guih_
 */
public class ConnectionDb implements ServletContextListener {
    public static final String jdbcUrl = "jdbc:sqlite:C:\\Users\\guih_\\projeto.db";
    
    public static String exceptionMessage = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Database creation";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(jdbcUrl);
            Statement stmt = con.createStatement();
            
            step = "Table 'users' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users("
                    + "name VARCHAR(200) NOT NULL,"
                    + "login VARCHAR(20) PRIMARY KEY,"
                    + "password_hash LONG NOT NULL,"
                    + "role VARCHAR(20) NOT NULL"
                    + ")");
            
            if(User.getUsers().isEmpty()){
                step = "Default admin user creation";
                stmt.executeUpdate("INSERT INTO users(name, login, password_hash, role)"
                        + "VALUES ('Administrador', 'admin', "+"123456".hashCode()+", 'ADMIN')");
                step = "Default fulano user creation";
                stmt.executeUpdate("INSERT INTO users(name, login, password_hash, role)"
                        + "VALUES ('Fulano da Silva', 'fulano', "+"123456".hashCode()+", 'USER')");
            }
            stmt.close();
            con.close();
        }catch(Exception ex){
            exceptionMessage = step + ": " + ex.getMessage();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}