<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="br.com.estacionamneto.dao.VeiculoDAO" %>
 <%@ page import="br.com.estacionamento.model.Veiculo" %>
 <%@ page import="java.text.DecimalFormat" %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/encerrar.css">
<title>Relatório Estadia</title>
</head>
<body>

	<h1>OI</h1>
	
	<%
	 int veiculoId = Integer.parseInt(request.getParameter("veiculoId"));
     Veiculo veiculo = VeiculoDAO.getVeiculoById(veiculoId);
    
    if (veiculo != null) {
        double horasEstadia = VeiculoDAO.calcularHorasEstadia(veiculo);
        double valorEstadia = VeiculoDAO.calcularValorEstadia(horasEstadia);
        
        
        if (VeiculoDAO.excluirVeiculo(veiculoId)) {
            // Veículo excluído com sucesso, redirecione para a página de relatório
            response.setContentType("text/html");
   	 		out.println();
        } else {
            // Lidere com o erro, exiba uma mensagem de erro, por exemplo
            out.println("Erro ao encerrar o veículo.");
        }
    %>

        
       
<p>Horas no estacionamento:  <%= new DecimalFormat("#.#").format(horasEstadia) %> horas</p>
<p>Valor da estadia: R$ <%= new DecimalFormat("#.##").format(valorEstadia) %></p>
<%
    }
%>

	<a class="btn" href="tabelaveiculos.jsp">Tabela Veículos</a>
	<a class="btn" href="registroveiculo.jsp">Registrar Veículo</a>
</body>
</html>