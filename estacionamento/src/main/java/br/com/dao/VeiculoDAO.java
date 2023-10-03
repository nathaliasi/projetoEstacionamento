package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.estacionamento.model.Veiculo;

public class VeiculoDAO {
    public static boolean inserirVeiculo(Veiculo veiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "INSERT INTO veiculos (tipoVeiculo, placa, modelo, cor, dataEntrada) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getTipoVeiculo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getCor());
            stmt.setTimestamp(5, veiculo.getDataHoraEntrada());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se pelo menos uma linha foi inserida
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
}

