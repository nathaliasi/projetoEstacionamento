package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    
    public static List<Veiculo> getVeiculos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> veiculos = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM veiculos";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setTipoVeiculo(rs.getString("tipoVeiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setDataHoraEntrada(rs.getTimestamp("dataEntrada"));

                veiculos.add(veiculo);
            }

            return veiculos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Tratar o erro adequadamente na sua aplicação
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
    
    public static Veiculo getVeiculoById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM veiculos WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setTipoVeiculo(rs.getString("tipoVeiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setDataHoraEntrada(rs.getTimestamp("dataEntrada"));

                return veiculo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conn);
        }

        return null; // Retorna null se o veículo não for encontrado
    }

    
    public static double calcularHorasEstadia(Veiculo veiculo) {
        // Obtém a data e hora de entrada do veículo
        Timestamp dataHoraEntrada = veiculo.getDataHoraEntrada();

        // Obtém a data e hora de saída (atual)
        Timestamp dataHoraSaida = new Timestamp(System.currentTimeMillis());

        // Calcula a diferença de tempo em milissegundos
        long diferencaTempoMillis = dataHoraSaida.getTime() - dataHoraEntrada.getTime();

        // Converte a diferença de tempo de milissegundos para horas com duas casas decimais
        return ((double) diferencaTempoMillis / (1000 * 60 * 60));
    }


    
    public static double calcularValorEstadia(double horasEstadia) {
        // Define o preço por hora
        double precoPorHora = 2.0;

        // Calcula o valor da estadia
        return horasEstadia * precoPorHora;
    }

}

