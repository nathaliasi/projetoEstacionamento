package br.com.estacionamento.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import br.com.dao.ConnectionFactory;
import br.com.dao.VeiculoDAO;
import br.com.estacionamento.model.Veiculo;

@WebServlet("/VeiculoServlet")
public class VeiculoServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String tipoVeiculo = request.getParameter("tipoVeiculo");
	        String placa = request.getParameter("placa");
	        String modelo = request.getParameter("modelo");
	        String cor = request.getParameter("cor");
	        String dataEntradaStr = request.getParameter("dataEntrada");
	        String horaEntradaStr = request.getParameter("horaEntrada");

	        // Combina a data e a hora de entrada em um formato adequado para inserção no banco de dados
	        String dataHoraEntradaStr = dataEntradaStr + " " + horaEntradaStr + ":00:00";

	        // Converte a data e hora de entrada para java.sql.Timestamp
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        java.util.Date parsedDate = null;
	        try {
	            parsedDate = sdf.parse(dataHoraEntradaStr);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        Timestamp dataHoraEntrada = new Timestamp(parsedDate.getTime());
	        
	        
	     // Crie um objeto Veiculo com os dados do formulário
	        Veiculo veiculo = new Veiculo();
	        veiculo.setTipoVeiculo(tipoVeiculo);
	        veiculo.setPlaca(placa);
	        veiculo.setModelo(modelo);
	        veiculo.setCor(cor);
	        veiculo.setDataHoraEntrada(dataHoraEntrada);

	        // Chame o DAO para inserir o veículo no banco de dados
	        boolean inseridoComSucesso = VeiculoDAO.inserirVeiculo(veiculo);

	        if (inseridoComSucesso) {
	        	response.setContentType("text/html");
           	    PrintWriter out = response.getWriter();
           	    out.println("CADASTRADO COM SUCESSO.");
	            //response.sendRedirect("sucesso.jsp"); // Redireciona para uma página de sucesso após o registro
	        } else {
	        	 response.setContentType("text/html");
	             PrintWriter out = response.getWriter();
	             out.println("Bosta, não deu certo...");
	            //response.sendRedirect("erro.jsp"); // Redireciona para uma página de erro em caso de falha
	        }
	    }
	}