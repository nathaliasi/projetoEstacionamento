package br.com.estacionamento.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EstacionamentoService;
import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Veiculo;


@WebServlet("/VeiculoServlet")
public class VeiculoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoCadastro = request.getParameter("tipoCadastro");

        if ("cliente".equals(tipoCadastro)) {
            cadastrarCliente(request, response);
        } else if ("veiculo".equals(tipoCadastro)) {
            cadastrarVeiculo(request, response);
        } else {
            // Trate a lógica para outros tipos de cadastro, se necessário
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Tipo de cadastro inválido.");
        }
    }

    private void cadastrarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);

        EstacionamentoService estacionamentoService = new EstacionamentoService();
        boolean cadastradoComSucesso = estacionamentoService.cadastrarCliente(cliente);

        if (cadastradoComSucesso) {
            response.sendRedirect("tabelaclientes.jsp");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Falha ao cadastrar cliente.");
        }
    }

    private void cadastrarVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoVeiculo = request.getParameter("tipoVeiculo");
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String cor = request.getParameter("cor");
        String dataEntradaStr = request.getParameter("dataEntrada");
        String horaEntradaStr = request.getParameter("horaEntrada");

        // Combina a data e a hora de entrada em um formato adequado para inserção no banco de dados
        String dataHoraEntradaStr = dataEntradaStr + " " + horaEntradaStr + ":00"; // Adicionado ":00" para os segundos

        // Converte a data e hora de entrada para java.sql.Timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date parsedDate = null;

        try {
            parsedDate = sdf.parse(dataHoraEntradaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Falha ao converter data e hora.");
            return;
        }

        Timestamp dataHoraEntrada = new Timestamp(parsedDate.getTime());

        // Cria um objeto Veiculo com os dados do formulário
        Veiculo veiculo = new Veiculo();
        veiculo.setTipoVeiculo(tipoVeiculo);
        veiculo.setPlaca(placa);
        veiculo.setModelo(modelo);
        veiculo.setCor(cor);
        veiculo.setDataHoraEntrada(dataHoraEntrada);

        // Chama o serviço para inserir o veículo no banco de dados
        EstacionamentoService estacionamentoService = new EstacionamentoService();
        boolean inseridoComSucesso = estacionamentoService.cadastrarVeiculo(veiculo);

        if (inseridoComSucesso) {
            response.sendRedirect("tabelaveiculos.jsp");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Falha ao cadastrar veículo.");
        }
    }
}
