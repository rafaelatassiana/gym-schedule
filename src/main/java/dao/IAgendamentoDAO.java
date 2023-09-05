package dao;

import model.Agendamento;

import java.util.List;
import java.util.Optional;

public interface IAgendamentoDAO {
    Agendamento salvar(Agendamento agendamento);

    List<Agendamento> listarTodos();

    void deletar(Long id);

    Optional<Agendamento> listarPorId(Long id);

    boolean existeRegistroDataHorario(Agendamento agendamento);

    Agendamento update(Agendamento agendamento);
}
