<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Registro de Veículo</title>
</head>
<body>
    <h1>Registro de Veículo</h1>
    <form action="VeiculoServlet" method="post">
        <label for="tipoVeiculo">Tipo de Veículo:</label>
        <select name="tipoVeiculo" id="tipoVeiculo">
            <option value="carro">Carro</option>
            <option value="moto">Moto</option>
        </select>
        <br>

        <label for="placa">Placa:</label>
        <input type="text" name="placa" id="placa" required>
        <br>

        <label for="modelo">Modelo:</label>
        <input type="text" name="modelo" id="modelo" required>
        <br>

        <label for="cor">Cor:</label>
        <input type="text" name="cor" id="cor" required>
        <br>

        <label for="dataEntrada">Data de Entrada:</label>
        <input type="date" name="dataEntrada" id="dataEntrada" required>
        <br>

        <label for="horaEntrada">Hora de Entrada:</label>
        <input type="time" name="horaEntrada" id="horaEntrada" required>
        <br>

        <input type="submit" value="Registrar">
    </form>
</body>
</html>
