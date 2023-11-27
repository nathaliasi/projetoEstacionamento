<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style/registroveiculo.css">
    <title>Formulário de Registro de Veículo</title>
</head>
<body>
<div class="logout">
	 <a  class="btn" href="loginadm.jsp">Logout</a>
</div>
<div class="body">
	<div class="container">
<h1>Registro de Veículo</h1>
<form action="VeiculoServlet" method="post">
    <!-- Adicione um campo oculto para indicar o tipo de cadastro -->
    <input type="hidden" name="tipoCadastro" value="veiculo">

    <label for="nome">Cliente</label>
    <input type="text" name="nome" id="nome" required>

    <label for="telefone">Telefone</label>
    <input type="text" name="telefone" id="telefone" required>

    <label for="tipoVeiculo">Tipo de Veículo:</label>
    <select name="tipoVeiculo" id="tipoVeiculo">
        <option value="carro">Carro</option>
        <option value="moto">Moto</option>
    </select>

    <label for="placa">Placa:</label>
    <input type="text" name="placa" id="placa" required>

    <label for="modelo">Modelo:</label>
    <input type="text" name="modelo" id="modelo" required>

    <label for="cor">Cor:</label>
    <select name="cor" id="cor">
        <option value="branco">Branco</option>
        <option value="preto">Preto</option>
        <option value="vermelho">Vermelho</option>
        <option value="prata">Prata</option>
        <option value="azul">Azul</option>
        <option value="amarelo">Amarelo</option>
        <option value="outro">Outro</option>
    </select>

    <label for="dataEntrada">Data de Entrada:</label>
    <input type="date" name="dataEntrada" id="dataEntrada" required>

    <label for="horaEntrada">Hora de Entrada:</label>
    <input type="time" name="horaEntrada" id="horaEntrada" required>

    <input class="btn" type="submit" value="Registrar">
    <a class="btn" href="tabelaveiculos.jsp">Tabela Veículo</a>
</form>

</body>
</html>
