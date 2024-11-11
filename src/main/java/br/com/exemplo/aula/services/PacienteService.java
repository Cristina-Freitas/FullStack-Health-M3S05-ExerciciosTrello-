package br.com.exemplo.aula.services;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;


    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteResponseDTO> listarPacientes() {
        return pacienteRepository.findAll().stream().map(
                paciente -> new PacienteResponseDTO(
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getDataNascimento(),
                        paciente.getCpf(),
                        paciente.getTelefone(),
                        paciente.getEmail()
                )
        ).collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPaciente(Long id){
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            return new PacienteResponseDTO(
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getDataNascimento(),
                    paciente.getCpf(),
                    paciente.getTelefone(),
                    paciente.getEmail()
            );
        }
        return null;
    }

    public PacienteResponseDTO salvarPaciente(PacienteRequestDTO request) {
        Paciente paciente = mapearRequest(request);
        Paciente entitySalva = pacienteRepository.save(paciente);

        return new PacienteResponseDTO(entitySalva.getId(),
                entitySalva.getNome(),
                entitySalva.getDataNascimento(),
                entitySalva.getCpf(),
                entitySalva.getTelefone(),
                entitySalva.getEmail()
        );
    }

    private Paciente mapearRequest(PacienteRequestDTO source){
        Paciente target = new Paciente();
        target.setNome(source.getNome());
        target.setDataNascimento(source.getDataNascimento());
        target.setCpf(source.getCpf());
        target.setTelefone(source.getTelefone());
        target.setEmail(source.getEmail());
        return target;
    }

    public PacienteResponseDTO atualizarPaciente(Long id, PacienteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        assert paciente != null;
        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setCpf(request.getCpf());
        paciente.setTelefone(request.getTelefone());
        paciente.setEmail(request.getEmail());

        pacienteRepository.save(paciente);

        return new PacienteResponseDTO(paciente.getId(),
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail()
        );
    }

    public void removerPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
    
    
}
