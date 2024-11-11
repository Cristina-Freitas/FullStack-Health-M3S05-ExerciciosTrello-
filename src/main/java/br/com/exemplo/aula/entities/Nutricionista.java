package br.com.exemplo.aula.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nutricionista")
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nutricionista")
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String matricula;
    private int tempoExperiencia;

    @Column(nullable = false)
    private String crn;

    private String especialidade;

    @ElementCollection
    private Set<String> certificacoes = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<String> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(Set<String> certificacoes) {
        this.certificacoes = certificacoes;
    }
}
