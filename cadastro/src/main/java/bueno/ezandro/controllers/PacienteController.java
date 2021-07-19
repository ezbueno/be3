package bueno.ezandro.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bueno.ezandro.entities.Paciente;
import bueno.ezandro.exceptions.ArgumentNotValidException;
import bueno.ezandro.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> findAll() {
		List<Paciente> pacientes = this.pacienteService.findAll();
		return ResponseEntity.ok().body(pacientes);
	}

	@GetMapping(value = "/{prontuario_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> findById(@PathVariable Integer prontuario_id) {
		Paciente paciente = this.pacienteService.findById(prontuario_id);
		return ResponseEntity.ok().body(paciente);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> create(@Valid @RequestBody Paciente paciente) throws ArgumentNotValidException {
		Paciente pacienteCreate = this.pacienteService.create(paciente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pacienteCreate.getProntuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{prontuario_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> update(@PathVariable Integer prontuario_id, @Valid @RequestBody Paciente paciente)
			throws ArgumentNotValidException {
		Paciente pacienteUpdate = this.pacienteService.update(prontuario_id, paciente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pacienteUpdate.getProntuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{prontuario_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Integer prontuario_id) {
		this.pacienteService.delete(prontuario_id);
		return ResponseEntity.noContent().build();
	}

}