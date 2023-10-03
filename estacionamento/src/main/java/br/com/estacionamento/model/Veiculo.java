package br.com.estacionamento.model;

import java.sql.Timestamp;

public class Veiculo {
    private int id;
    private String tipoVeiculo;
    private String placa;
    private String modelo;
    private String cor;
    private Timestamp dataHoraEntrada;

    // Construtor vazio
    public Veiculo() {}

    // Construtor com parâmetros
    public Veiculo(String tipoVeiculo, String placa, String modelo, String cor, Timestamp dataHoraEntrada) {
        this.tipoVeiculo = tipoVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.dataHoraEntrada = dataHoraEntrada;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Timestamp getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Timestamp dataHoraEntradaStr) {
        this.dataHoraEntrada = dataHoraEntradaStr;
    }
}
