package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Agendamento;

public class AgendamentoDAO implements IAgendamentoDAO {
    private final Connection connection;

    public AgendamentoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Agendamento salvar(Agendamento agendamento) {
        try {
            String[] returnId = { "BATCHID" };

            String sql = "INSERT INTO Agendamentos (data, horario) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, returnId);
            preparedStatement.setString(1, agendamento.getData());
            preparedStatement.setString(2, agendamento.getHorario());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return agendamento;
    }

    @Override
    public List<Agendamento> listarTodos() {
        String sql = "SELECT id, data, horario FROM Agendamentos";

        List<Agendamento> agendamentos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String data = rs.getString("data");
                String horario = rs.getString("horario");

                Agendamento agendamento = new Agendamento(id, data, horario);
                agendamentos.add(agendamento);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return agendamentos;
    }

    @Override
    public void deletar(Long id) {
        try {
            String sql = "DELETE FROM Agendamentos WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Optional<Agendamento> listarPorId(Long id) {
        String sql = "SELECT id, data, horario FROM Agendamentos WHERE id = ?";

        Agendamento agendamento = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String data = rs.getString("data");
                String horario = rs.getString("horario");

                agendamento = new Agendamento(pKey, data, horario);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(agendamento);
    }

    @Override
    public Agendamento update(Agendamento agendamento) {
        try {
            String sql = "UPDATE Agendamentos SET data = ?, horario = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, agendamento.getData());
            preparedStatement.setString(2, agendamento.getHorario());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return agendamento;
    }

    @Override
    public boolean existeRegistroDataHorario(Agendamento agendamento) {
        int id = 0;
        try {
            String sql = "SELECT id from Agendamentos WHERE data = ? AND horario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, agendamento.getData());
            preparedStatement.setString(2, agendamento.getHorario());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return id > 0;
    }
}
