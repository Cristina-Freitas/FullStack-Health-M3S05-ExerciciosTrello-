package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PacienteController.class)
@AutoConfigureMockMvc
class PacienteControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PacienteService pacienteService;

    @Test
    void salvarPaciente() throws Exception {
        when(pacienteService.salvarPaciente(any())).thenReturn(new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1979, 6, 17),
                "000.000.000-00",
                "(48) 91111-1111",
                "email@teste.com"));

        mvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                            "nome": "Nome de paciente",
                            "dataNascimento": "17/06/1979",
                            "cpf": "000.000.000-00",
                            "telefone": "(48) 91111-1111",
                            "email": "email@teste.com",
                            "idEndereco": 1
                            }
                            """)
                        .with(csrf()) // Adiciona o CSRF necessário para requisições POST
                        .with(user("admin").roles("ADMIN"))) // Simula um usuário autenticado com o papel ADMIN
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Nome de paciente"));

        verify(pacienteService).salvarPaciente(any());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void listarPacientes() throws Exception {
        PacienteResponseDTO pacienteResponse = new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1979, 6, 17),
                "000.000.000-00",
                "(48) 91111-1111",
                "email@teste.com"
        );

        when(pacienteService.listarPacientes()).thenReturn(List.of(pacienteResponse));

        mvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nome").value(pacienteResponse.getNome()));

        verify(pacienteService).listarPacientes();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void search() throws Exception {
        when(pacienteService.buscarPaciente(any())).thenReturn(new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1979, 6, 17),
                "000.000.000-00",
                "(48) 91111-1111",
                "email@teste.com"));

        mvc.perform(get("/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome de paciente"));

        verify(pacienteService).buscarPaciente(any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void remove() throws Exception {
        mvc.perform(delete("/pacientes/1"))
                .andExpect(status().isNoContent()); // Verifica o status 204 No Content
        verify(pacienteService).removerPaciente(any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void update() throws Exception {
        when(pacienteService.atualizarPaciente(any(), any())).thenReturn(new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1979, 6, 17),
                "000.000.000-00",
                "(48) 91111-1111",
                "email@teste.com"));

        mvc.perform(put("/pacientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                             {
                            "nome": "Nome de paciente",
                            "dataNascimento": "17/06/1979",
                            "cpf": "000.000.000-00",
                            "telefone": "(48) 91111-1111",
                            "email": "email@teste.com",
                            "idEndereco": 1
                            }
                            """))
                .andExpect(status().isOk()) // Verifica o status 200 OK
                .andExpect(jsonPath("$.nome").value("Nome de paciente"));
        verify(pacienteService).atualizarPaciente(any(), any());
    }

}
