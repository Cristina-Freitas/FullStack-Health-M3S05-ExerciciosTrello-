package br.com.exemplo.aula.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class ConsultaRequestDTO {

    private Long idNutricionista;
    private Long idPaciente;

    @JsonDeserialize
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String observacoes;

    public ConsultaRequestDTO(){
    }

    public ConsultaRequestDTO(Long idNutricionista, Long idPaciente, LocalDate data, String observacoes) {
        this.idNutricionista = idNutricionista;
        this.idPaciente = idPaciente;
        this.data = data;
        this.observacoes = observacoes;
    }

    public Long getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(Long idNutricionista) {
        this.idNutricionista = idNutricionista;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
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
