<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="br.com.dao.VeiculoDAO" %>
 <%@ page import="br.com.estacionamento.model.Veiculo" %>
 <%@ page import="java.text.DecimalFormat" %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Relat�rio Estadia</title>
</head>
<body>

	
	<%
	 int veiculoId = Integer.parseInt(request.getParameter("veiculoId"));
     Veiculo veiculo = VeiculoDAO.getVeiculoById(veiculoId);
    
    if (veiculo != null) {
        double horasEstadia = VeiculoDAO.calcularHorasEstadia(veiculo);
        double valorEstadia = VeiculoDAO.calcularValorEstadia(horasEstadia);
        
        
        if (VeiculoDAO.excluirVeiculo(veiculoId)) {
            // Ve�culo exclu�do com sucesso, redirecione para a p�gina de relat�rio
            response.setContentType("text/html");
   	 		out.println();
        } else {
            // Lidere com o erro, exiba uma mensagem de erro, por exemplo
            out.println("Erro ao encerrar o ve�culo.");
        }
    %>

        
       
<p>Horas no estacionamento:  <%= new DecimalFormat("#.#").format(horasEstadia) %> horas</p>
<p>Valor da estadia: R$ <%= new DecimalFormat("#.##").format(valorEstadia) %></p>
<%
    }
%>

	<a href="tabelaveiculos.jsp">Tabela Ve�culos</a>
	<a href="registroveiculo.jsp">Registrar Ve�culo</a>
</body>
</html>