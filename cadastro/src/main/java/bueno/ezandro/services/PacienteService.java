package bueno.ezandro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bueno.ezandro.entities.Paciente;
import bueno.ezandro.exceptions.ArgumentNotValidException;
import bueno.ezandro.exceptions.ForbiddenException;
import bueno.ezandro.exceptions.ObjectNotFoundException;
import bueno.ezandro.repositories.PacienteRepository;
import bueno.ezandro.utils.MessageUtils;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		return this.pacienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Paciente findById(Integer id) {
		Optional<Paciente> paciente = this.pacienteRepository.findById(id);
		return paciente.orElseThrow(() -> new ObjectNotFoundException(MessageUtils.OBJECT_NOT_FOUND));
	}

	@Transactional
	public Paciente create(Paciente paciente) throws ArgumentNotValidException {
		paciente.setProntuario(null);
		Optional<Paciente> pessoaFisica = this.pacienteRepository.findByCPF(paciente.getCpf());

		if (pessoaFisica.isPresent()) {
			throw new ArgumentNotValidException(MessageUtils.CPF_ALREADY_EXISTS);
		}

		return this.pacienteRepository.save(paciente);
	}

	@Transactional
	public Paciente update(Integer prontuario_id, Paciente paciente) {
		Paciente pacienteUpdate = this.findById(prontuario_id);
		Optional<Paciente> pessoaFisica = this.pacienteRepository.findByCPF(paciente.getCpf());
		
		if (pessoaFisica.isPresent()) {
			throw new ArgumentNotValidException(MessageUtils.CPF_ALREADY_EXISTS);
		}

		updateData(pacienteUpdate, paciente);
		return this.pacienteRepository.save(pacienteUpdate);
	}

	@Transactional
	public void delete(Integer prontuario_id) {
		Paciente paciente = this.findById(prontuario_id);

		if (paciente != null) {
			throw new ForbiddenException(MessageUtils.FORBIDDEN);
		}

	}

	private void updateData(Paciente pacienteUpdate, Paciente paciente) {
		pacienteUpdate.setNome(paciente.getNome());
		pacienteUpdate.setSobrenome(paciente.getSobrenome());
		pacienteUpdate.setDataNascimento(paciente.getDataNascimento());
		pacienteUpdate.setGenero(paciente.getGenero());
		pacienteUpdate.setRg(paciente.getRg());
		pacienteUpdate.setUf(paciente.getUf());
		pacienteUpdate.setEmail(paciente.getEmail());
		pacienteUpdate.setCelular(paciente.getCelular());
		pacienteUpdate.setTelefone(paciente.getTelefone());
	}

}