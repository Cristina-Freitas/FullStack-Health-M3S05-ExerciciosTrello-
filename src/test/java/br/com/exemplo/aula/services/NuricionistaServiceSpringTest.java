package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("Test")
class NutricionistaServiceSpringTest {

    @Autowired
    NutricionistaService nutricionistaService;

    @MockBean
    NutricionistaRepository nutricionistaRepository;

    Nutricionista nutricionista;

    @BeforeEach
    public void setup(){
        nutricionista = new Nutricionista(
        );
    }

    @Test
    void listarNutricionistas() {

        List<Nutricionista> nutricionistas = new ArrayList<>();
        nutricionistas.add(nutricionista);

        when(nutricionistaRepository.findAll()).thenReturn(nutricionistas);

        var resultado = nutricionistaService.listarNutricionistas();

        assertNotNull(resultado);
        assertEquals(nutricionistas.get(0).getId(), resultado.get(0).getId());
        verify(nutricionistaRepository).findAll();

    }

    @Test
    void salvarNutricionista() {

        NutricionistaRequestDTO request = new NutricionistaRequestDTO(
                "Nome de nutricionista",
                "Número de matrícula",
                1,
                7L,
                "Número de CRN",
                "Especialidade"
        );

        when(nutricionistaRepository.save(any())).thenReturn(nutricionista);

        var resultado = nutricionistaService.salvarNutricionista(request);

        assertNotNull(resultado);
        assertEquals(nutricionista.getNome(), resultado.getNome());
        verify(nutricionistaRepository).save(any());

    }
}