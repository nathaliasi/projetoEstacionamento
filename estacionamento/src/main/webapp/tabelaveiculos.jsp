<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="br.com.dao.VeiculoDAO" %>
 <%@ page import="br.com.estacionamento.model.Veiculo" %>
 <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tabel De Veículos</title>
</head>
<body>
	 <h1>Lista de Veículos Registrados</h1>
    <table border="1">
        <tr>
            <th>Tipo de Veículo</th>
            <th>Placa</th>
            <th>Modelo</th>
            <th>Cor</th>
            <th>Data e Hora de Entrada</th>
        </tr>
        
        <%
            List<Veiculo> veiculos = VeiculoDAO.getVeiculos();

            for (Veiculo veiculo : veiculos) {
        %>
            <tr>
                <td><%= veiculo.getTipoVeiculo() %></td>
                <td><%= veiculo.getPlaca() %></td>
                <td><%= veiculo.getModelo() %></td>
                <td><%= veiculo.getCor() %></td>
                <td><%= veiculo.getDataHoraEntrada() %></td>
                <td><a href="encerrar.jsp?veiculoId=<%= veiculo.getId() %>">Encerrar</a></td>
            </tr>
        <%
            }
        %>
    </table>
    <a href="registroveiculo.jsp">Registrar Veículo</a>
</body>
</html>