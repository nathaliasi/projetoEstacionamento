package br.com.estacionamento.control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dao.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Use a ConnectionFactory para obter uma conexão com o banco de dados
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (verificarCredenciais(username, password, conn)) {
                // Credenciais corretas, redirecione para a página de admin
            	response.sendRedirect("registroveiculo.jsp");
            	 //response.setContentType("text/html");
            	 //PrintWriter out = response.getWriter();
            	 //out.println("FOI PORRAAAAAAAAAAAAAAAAAAAAAAA.");
            } else {
                // Credenciais incorretas, exiba uma mensagem de erro
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("Credenciais incorretas. Tente novamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidere com exceções de conexão aqui
        }
    }

    // lógica para verificar as credenciais no banco de dados
    private boolean verificarCredenciais(String username, String password, Connection conn) {
    	 PreparedStatement stmt = null;
    	    ResultSet rs = null;

    	    try {
    	        // Consulta SQL para verificar as credenciais
    	        String sql = "SELECT * FROM administradores WHERE nome_usuario=? AND senha=?";
    	        stmt = conn.prepareStatement(sql);
    	        stmt.setString(1, username);
    	        stmt.setString(2, password);
    	        rs = stmt.executeQuery();

    	        // Se o resultado da consulta contiver pelo menos uma linha, as credenciais são válidas
    	        return rs.next();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        // Lidere com exceções de SQL aqui
    	        return false;
    	    } finally {
    	        // Feche os recursos
    	        try {
    	            if (rs != null) {
    	                rs.close();
    	            }
    	            if (stmt != null) {
    	                stmt.close();
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
    }
}

