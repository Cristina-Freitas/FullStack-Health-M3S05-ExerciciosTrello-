package br.com.exemplo.aula.controllers.dto;

import java.time.LocalDate;
import java.util.Date;

public class ConsultaResponseListDTO {

    private Long id;
    private String nomeNutricionista;
    private String nomePaciente;
    private LocalDate data;

    public ConsultaResponseListDTO(Long id, String nomeNutricionista, String nomePaciente, LocalDate data) {
        this.id = id;
        this.nomeNutricionista = nomeNutricionista;
        this.nomePaciente = nomePaciente;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeNutricionista() {
        return nomeNutricionista;
    }

    public void setNomeNutricionista(String nomeNutricionista) {
        this.nomeNutricionista = nomeNutricionista;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
