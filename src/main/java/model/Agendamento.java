package model;

import java.util.Date;

public class Agendamento {
    private Long id;
    private String data;
    private String horario;

    public Agendamento(Long id, String data, String horario) {
        this.id = id;
        this.data = data;
        this.horario = horario;
    }

    public Agendamento(String data, String horario) {
        this(null, data, horario);
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
