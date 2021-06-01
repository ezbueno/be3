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

import bueno.ezandro.entities.Cliente;
import bueno.ezandro.exceptions.ArgumentNotValidException;
import bueno.ezandro.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clientes = this.clienteService.findAll();
		return ResponseEntity.ok().body(clientes);
	}

	@GetMapping(value = "/{prontuario_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> findById(@PathVariable Integer prontuario_id) {
		Cliente cliente = this.clienteService.findById(prontuario_id);
		return ResponseEntity.ok().body(cliente);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) throws ArgumentNotValidException {
		Cliente clienteCreate = this.clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteCreate.getProntuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{prontuario_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> update(@PathVariable Integer prontuario_id, @Valid @RequestBody Cliente cliente)
			throws ArgumentNotValidException {
		Cliente clienteUpdate = this.clienteService.update(prontuario_id, cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteUpdate.getProntuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{prontuario_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Integer prontuario_id) {
		this.clienteService.delete(prontuario_id);
		return ResponseEntity.noContent().build();
	}

}