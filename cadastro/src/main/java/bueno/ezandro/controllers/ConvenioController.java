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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bueno.ezandro.entities.Convenio;
import bueno.ezandro.services.ConvenioService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/convenios")
public class ConvenioController {

	@Autowired
	private ConvenioService convenioService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Convenio>> findAll() {
		List<Convenio> convenios = this.convenioService.findAll();
		return ResponseEntity.ok().body(convenios);
	}

	@GetMapping(value = "/{convenio_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Convenio> findById(@PathVariable Integer convenio_id) throws ObjectNotFoundException {
		Convenio convenio = this.convenioService.findById(convenio_id);
		return ResponseEntity.ok().body(convenio);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Convenio> create(@RequestParam(value = "paciente", defaultValue = "0") Integer prontuario_id,
			@Valid @RequestBody Convenio convenio) {
		Convenio convenioCreate = this.convenioService.create(prontuario_id, convenio);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/pacientes/{id}")
				.buildAndExpand(convenioCreate.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{convenio_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Convenio> update(@PathVariable Integer convenio_id, @Valid @RequestBody Convenio convenio) {
		Convenio convenioUpdate = this.convenioService.update(convenio_id, convenio);
		return ResponseEntity.ok().body(convenioUpdate);
	}

	@DeleteMapping(value = "/{convenio_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Integer convenio_id) {
		this.convenioService.delete(convenio_id);
		return ResponseEntity.noContent().build();
	}

}