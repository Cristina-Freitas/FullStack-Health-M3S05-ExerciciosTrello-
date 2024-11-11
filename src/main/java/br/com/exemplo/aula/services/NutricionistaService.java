package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.controllers.dto.NutricionistaResponseDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NutricionistaService {

    private final NutricionistaRepository nutricionistaRepository;


    public NutricionistaService(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }

    public List<NutricionistaResponseDTO> listarNutricionistas() {
        return nutricionistaRepository.findAll().stream().map(
                nutricionista -> new NutricionistaResponseDTO(
                        nutricionista.getId(),
                        nutricionista.getNome(),
                        nutricionista.getMatricula(),
                        nutricionista.getTempoExperiencia(),
                        nutricionista.getCrn(),
                        nutricionista.getEspecialidade()
                )
        ).collect(Collectors.toList());
    }

    public NutricionistaResponseDTO buscarNutricionista(Long id){
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);
        if (nutricionista != null) {
            return new NutricionistaResponseDTO(
                    nutricionista.getId(),
                    nutricionista.getNome(),
                    nutricionista.getMatricula(),
                    nutricionista.getTempoExperiencia(),
                    nutricionista.getCrn(),
                    nutricionista.getEspecialidade()
            );
        }
        return null;
    }

    public NutricionistaResponseDTO salvarNutricionista(NutricionistaRequestDTO request) {
        if (nutricionistaRepository.findByNome(request.getNome()).isPresent()) {
            throw new RuntimeException("Já existe cadastro de nutricionista com este nome.");
        }
        Nutricionista nutricionista = mapearRequest(request);
        Nutricionista entitySalva = nutricionistaRepository.save(nutricionista);

        return new NutricionistaResponseDTO(entitySalva.getId(),
                entitySalva.getNome(),
                entitySalva.getMatricula(),
                entitySalva.getTempoExperiencia(),
                entitySalva.getCrn(),
                entitySalva.getEspecialidade()
        );
    }

    private Nutricionista mapearRequest(NutricionistaRequestDTO source){
        Nutricionista target = new Nutricionista();
        target.setNome(source.getNome());
        target.setMatricula(source.getMatricula());
        target.setTempoExperiencia(source.getTempoExperiencia());
        target.setCrn(source.getCrn());
        target.setEspecialidade(source.getEspecialidade());
        return target;
    }

    public NutricionistaResponseDTO atualizarNutricionista(Long id, NutricionistaRequestDTO request) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);

        assert nutricionista != null;
        if (!Objects.equals(nutricionista.getNome(), request.getNome()) &&
        nutricionistaRepository.findByNome(request.getNome()).isPresent()) {
            throw new RuntimeException("Já existe cadastro de nutricionista com este nome.");
        }

        nutricionista.setNome(request.getNome());
        nutricionista.setMatricula(request.getMatricula());
        nutricionista.setTempoExperiencia(request.getTempoExperiencia());
        nutricionista.setCrn(request.getCrn());
        nutricionista.setEspecialidade(request.getEspecialidade());

        nutricionistaRepository.save(nutricionista);
        return new NutricionistaResponseDTO(nutricionista.getId(),
                nutricionista.getNome(),
                nutricionista.getMatricula(),
                nutricionista.getTempoExperiencia(),
                nutricionista.getCrn(),
                nutricionista.getEspecialidade()
                );
    }

    public void removerNutricionista(Long id) {
        nutricionistaRepository.deleteById(id);
    }

    public void adicionarAnoExperiencia(Long id) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);
        assert nutricionista != null;
        nutricionista.setTempoExperiencia(nutricionista.getTempoExperiencia() + 1);
    }

    public void adicionarCertificacao(String novaCertificacao, Long id){
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);
        if (nutricionista != null) {
            Set<String> certificacoes = nutricionista.getCertificacoes();
            certificacoes.add(novaCertificacao);
        }
    }


}
