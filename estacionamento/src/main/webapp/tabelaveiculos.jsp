<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="br.com.estacionamneto.dao.VeiculoDAO" %>
 <%@ page import="br.com.estacionamento.model.Veiculo" %>
 <%@ page import="br.com.estacionamento.model.Cliente" %>
 <%@ page import="br.com.estacionamneto.dao.ClienteDAO" %>
 <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/tabelaveiculos.css">
<title>Tabel De Veículos</title>
</head>
<body>
<div class="logout">
	 <a  class="btn" href="loginadm.jsp">Logout</a>
</div>
	 <table class="tabela" border="1">
    <tr>
        <th>Cliente</th>
        <th>Tipo de Veículo</th>
        <th>Placa</th>
        <th>Modelo</th>
        <th>Cor</th>
        <th>Data e Hora de Entrada</th>
    </tr>
    
    <%
        List<Veiculo> veiculos = VeiculoDAO.getVeiculos();
        for (Veiculo veiculo : veiculos) {
            Cliente cliente = ClienteDAO.getClienteById(veiculo.getClienteId());
    %>
        <tr>
            <td><%= veiculo.getCliente().getId() %></td>
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

    <a class="btn" href="registroveiculo.jsp">Registrar Veículo</a>
</body>
</html>