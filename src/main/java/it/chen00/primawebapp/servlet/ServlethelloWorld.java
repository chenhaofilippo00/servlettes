/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.chen00.primawebapp.servlet;

import com.google.gson.Gson;
import it.chen00.primewebapp.pojo.Classi;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author pbone
 */
public class ServlethelloWorld extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        try
            {
                Class.forName(DRIVER);
            }
            catch(ClassNotFoundException e)
            {
                System.out.println(e);
            }
        try {
         String URL_mioDB = "jdbc:mysql://localhost:3306/fi_itis_meucci?serverTimezone=UTC";
         String query = "select * from class";
         
         Connection connessione = this.getConnection();
         Statement statement = connessione.createStatement();
         ResultSet resultset = statement.executeQuery(query);
         List<Classi> elencoClassi = new ArrayList();        
        while(resultset.next())
         {
             String nomeClasse = resultset.getString(3);
                Classi cl = new Classi();
                cl.setNome(nomeClasse);
                elencoClassi.add(cl);
         }
            String ret = new Gson().toJson(elencoClassi);            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(ret);
            out.flush();
        } 
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            out.close();
        }
        
    }
    private Connection getConnection() {
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver"; 
            String dbURL = "jdbc:mysql://localhost:3306/"; 
            // Database name to access 
            String dbName = "FI_ITIS_MEUCCI"; 
            String dbUsername = "bonex"; 
            String dbPassword = "pietro001"; 

            Class.forName(dbDriver); 
            Connection con = DriverManager.getConnection(dbURL + dbName + "?serverTimezone=UTC", 
                                                         dbUsername,  
                                                         dbPassword); 
            return con;
        } catch (Exception e) {
             e.printStackTrace();
            return null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServlethelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServlethelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServlethelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServlethelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
