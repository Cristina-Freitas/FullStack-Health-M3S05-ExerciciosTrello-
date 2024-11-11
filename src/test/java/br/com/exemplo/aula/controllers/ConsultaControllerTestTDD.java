package br.com.exemplo.aula.controllers;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseDTO;
import br.com.exemplo.aula.entities.Consulta;
import br.com.exemplo.aula.repositories.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaControllerTestTDD {

    @Autowired
    MockMvc mvc;

    @MockBean
    ConsultaRepository consultaRepository;

    Consulta consulta;
    ConsultaResponseDTO consultaResponse;

    @BeforeEach
    public void setup() {
        consulta = new Consulta(
        );
    }

    @Test
    void buscarConsultaPorId() throws Exception {

        when(consultaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(consulta));

        mvc.perform(get("/consultas/1")
                        .header("Authorization", "token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(consultaRepository).findById(1L);

    }

    @Test
    void alterarConsultaPorId() throws Exception {

        when(consultaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(consulta));
        when(consultaRepository.save(any())).thenReturn(consulta);

        mvc.perform(put("/consultas/1")
                        .header("Authorization", "token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "idNutricionista": 1,
                                    "idPaciente": 1,
                                    "data": "11/11/2024",
                                    "observacoes": "observações."
                    
                            }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(consultaRepository).findById(1L);
        verify(consultaRepository).save(consulta);
    }

    @Test
    void deletarConsultaPorId() throws Exception {

        when(consultaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(consulta));

        mvc.perform(delete("/consultas/1")
                        .header("Authorization", "token"))
                .andExpect(status().isNoContent());

        verify(consultaRepository).deleteById(1L);
    }
}
