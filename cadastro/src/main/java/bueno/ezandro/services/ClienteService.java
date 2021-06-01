package bueno.ezandro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bueno.ezandro.entities.Cliente;
import bueno.ezandro.exceptions.ArgumentNotValidException;
import bueno.ezandro.exceptions.ForbiddenException;
import bueno.ezandro.exceptions.ObjectNotFoundException;
import bueno.ezandro.repositories.ClienteRepository;
import bueno.ezandro.utils.MessageUtils;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(MessageUtils.OBJECT_NOT_FOUND));
	}

	@Transactional
	public Cliente create(Cliente cliente) throws ArgumentNotValidException {
		cliente.setProntuario(null);
		//Cliente pessoaFisica = this.clienteRepository.findByCPF(cliente.getCpf());
		Optional<Cliente> pessoaFisica = this.clienteRepository.findByCPF(cliente.getCpf());

		if (pessoaFisica.isPresent()) {
			throw new ArgumentNotValidException(MessageUtils.CPF_ALREADY_EXISTS);
		}

		return this.clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente update(Integer prontuario_id, Cliente cliente) {
		Cliente clienteUpdate = this.findById(prontuario_id);
		Optional<Cliente> pessoaFisica = this.clienteRepository.findByCPF(cliente.getCpf());
		
		if (pessoaFisica.isPresent()) {
			throw new ArgumentNotValidException(MessageUtils.CPF_ALREADY_EXISTS);
		}

		updateData(clienteUpdate, cliente);
		return this.clienteRepository.save(clienteUpdate);
	}

	@Transactional
	public void delete(Integer prontuario_id) {
		Cliente cliente = this.findById(prontuario_id);

		if (cliente != null) {
			throw new ForbiddenException(MessageUtils.FORBIDDEN);
		}

	}

	private void updateData(Cliente clienteUpdate, Cliente cliente) {
		clienteUpdate.setNome(cliente.getNome());
		clienteUpdate.setSobrenome(cliente.getSobrenome());
		clienteUpdate.setDataNascimento(cliente.getDataNascimento());
		clienteUpdate.setGenero(cliente.getGenero());
		clienteUpdate.setRg(cliente.getRg());
		clienteUpdate.setUf(cliente.getUf());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setCelular(cliente.getCelular());
		clienteUpdate.setTelefone(cliente.getTelefone());
	}

}