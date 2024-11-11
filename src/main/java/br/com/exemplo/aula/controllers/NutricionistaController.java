package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.services.NutricionistaService;
import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.controllers.dto.NutricionistaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/nutricionistas")
public class NutricionistaController {

    private final NutricionistaService nutricionistaService;


    public NutricionistaController(NutricionistaService nutricionistaService) {
        this.nutricionistaService = nutricionistaService;
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_NUTRI')")
//    @PreAuthorize("hasAuthority('SCOPE_NUTRI')")
    @PostMapping()
    public NutricionistaResponseDTO salvarNutricionista(@RequestBody NutricionistaRequestDTO request) {
        return nutricionistaService.salvarNutricionista(request);
    }

    @GetMapping()
    public List<NutricionistaResponseDTO> listarnutricionistas(
            @RequestHeader(name="Authorization")
            String token
             // bearer shadkjasdasjhk
    ) {

        var nutricionistas = nutricionistaService.
                listarNutricionistas();
        if (nutricionistas.isEmpty()){
            return null;
        } else {
            return nutricionistas;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaResponseDTO> search(@PathVariable long id) {
        NutricionistaResponseDTO response = nutricionistaService.buscarNutricionista(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        nutricionistaService.removerNutricionista(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaResponseDTO> update(@PathVariable long id, @RequestBody NutricionistaRequestDTO request) {
        NutricionistaResponseDTO nutricionista = nutricionistaService.atualizarNutricionista(id, request);
        if (nutricionista != null) {
            return ResponseEntity.ok(nutricionista);
        } else {
            throw new RuntimeException("404");
        }
    }
    
    
    
    
}
