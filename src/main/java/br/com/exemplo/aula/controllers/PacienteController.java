package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.services.PacienteService;
import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping()
    public PacienteResponseDTO salvarPaciente(@RequestBody PacienteRequestDTO request,
                                              //@RequestParam("date")
                                              @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        return pacienteService.salvarPaciente(request);


    }

    @GetMapping()
    public List<PacienteResponseDTO> listarPacientes() {
        var Pacientes = pacienteService.listarPacientes();
        if (Pacientes.isEmpty()){
            return null;
        } else {
            return Pacientes;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> search(@PathVariable long id) {
        PacienteResponseDTO response = pacienteService.buscarPaciente(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        pacienteService.removerPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> update(@PathVariable long id,
                                                      @RequestBody PacienteRequestDTO request,
                                                      //@RequestParam("date")
                                                      @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        PacienteResponseDTO paciente = pacienteService.atualizarPaciente(id, request);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            throw new RuntimeException("404");
        }
    }



    
}
