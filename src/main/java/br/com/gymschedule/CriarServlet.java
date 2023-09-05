package br.com.gymschedule;

import dao.AgendamentoDAO;
import infra.ConnectionFactory;
import model.Agendamento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/criar")
public class CriarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = request.getParameter("data");
        String horario = request.getParameter("horario");

        String dataFormatada = formatarData(data);

        Agendamento agendamento = new Agendamento(dataFormatada, horario);

        Connection connection = ConnectionFactory.getConnection();

        AgendamentoDAO dao = new AgendamentoDAO(connection);

        boolean registroExiste = dao.existeRegistroDataHorario(agendamento);

        if (registroExiste) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Já existe um registro para o dia " + dataFormatada + " - " + horario + "');");
            out.println("location='criar-cliente.jsp';");
            out.println("</script>");
        } else {
            dao.salvar(agendamento);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Cliente criado com sucesso!!!');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
    }

    private String formatarData(String data) {
        try {
            Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(data);

            return new SimpleDateFormat("dd/MM/yyyy").format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}
