package br.com.exemplo.aula.controllers.dto;

import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class ConsultaResponseDTO {

    private Long id;
    private Nutricionista nutricionista;
    private Paciente paciente;

    @JsonSerialize
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private String observacoes;

    public ConsultaResponseDTO(){}

    public ConsultaResponseDTO(Long id, Nutricionista nutricionista, Paciente paciente, LocalDate data, String observacoes) {
        this.id = id;
        this.nutricionista = nutricionista;
        this.paciente = paciente;
        this.data = data;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
