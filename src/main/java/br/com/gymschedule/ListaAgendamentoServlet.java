package br.com.gymschedule;

import dao.AgendamentoDAO;
import infra.ConnectionFactory;
import model.Agendamento;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/listaAgendamentos")
public class ListaAgendamentoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        AgendamentoDAO dao = new AgendamentoDAO(connection);

        List<Agendamento> agendamentos = dao.listarTodos();

        request.setAttribute("agendamentos", agendamentos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-agendamentos.jsp");
        dispatcher.forward(request, response);
    }
}
