package br.com.exemplo.aula.controllers.dto;

public class NutricionistaRequestDTO {

    private String nome;
    private String matricula;
    private int tempoExperiencia;
    private Long idEndereco;
    private String crn;
    private String especialidade;

    public NutricionistaRequestDTO() {
    }

    public NutricionistaRequestDTO(String nome, String matricula, int tempoExperiencia, Long idEndereco, String crn, String especialidade) {
        this.nome = nome;
        this.matricula = matricula;
        this.tempoExperiencia = tempoExperiencia;
        this.idEndereco = idEndereco;
        this.crn = crn;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTempoExperiencia() {
        return tempoExperiencia;
    }

    public void setTempoExperiencia(int tempoExperiencia) {
        this.tempoExperiencia = tempoExperiencia;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
