package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.ConsultaRequestDTO;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseDTO;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseListDTO;
import br.com.exemplo.aula.entities.Consulta;
import br.com.exemplo.aula.repositories.ConsultaRepository;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final NutricionistaRepository nutricionistaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, NutricionistaRepository nutricionistaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.nutricionistaRepository = nutricionistaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // Listar consultas apenas com Data/Hora, nome de Nutricionista e nome de Paciente.
    public List<ConsultaResponseListDTO> listarConsultas() {
        return consultaRepository.findAll().stream().map(
                consulta -> new ConsultaResponseListDTO(
                        consulta.getId(),
                        consulta.getNutricionista().getNome(),
                        consulta.getPaciente().getNome(),
                        consulta.getData()
                )
        ).collect(Collectors.toList());

    }

    public ConsultaResponseDTO buscarConsulta(Long id){
        Consulta consulta = consultaRepository.findById(id).orElse(null);
        if (consulta != null) {
            return new ConsultaResponseDTO(
                    consulta.getId(),
                    consulta.getNutricionista(),
                    consulta.getPaciente(),
                    consulta.getData(),
                    consulta.getObservacoes()
            );
        }
        return null;
    }

    public ConsultaResponseDTO salvarConsulta(ConsultaRequestDTO request) {
        Consulta consulta = mapearRequest(request);
        Consulta entitySalva = consultaRepository.save(consulta);

        return new ConsultaResponseDTO(entitySalva.getId(),
                entitySalva.getNutricionista(),
                entitySalva.getPaciente(),
                entitySalva.getData(),
                entitySalva.getObservacoes()
        );
    }

    private Consulta mapearRequest(ConsultaRequestDTO source){
        Consulta target = new Consulta();
        target.setData(source.getData());
        target.setObservacoes(source.getObservacoes());
        target.setNutricionista(nutricionistaRepository.findById(source.getIdNutricionista()).orElse(null));
        target.setPaciente(pacienteRepository.findById(source.getIdPaciente()).orElse(null));
        return target;
    }


}
