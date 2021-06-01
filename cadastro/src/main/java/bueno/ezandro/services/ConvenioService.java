package bueno.ezandro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bueno.ezandro.entities.Cliente;
import bueno.ezandro.entities.Convenio;
import bueno.ezandro.exceptions.IntegrityViolationException;
import bueno.ezandro.exceptions.ObjectNotFoundException;
import bueno.ezandro.repositories.ConvenioRepository;
import bueno.ezandro.utils.MessageUtils;

@Service
public class ConvenioService {

	@Autowired
	private ConvenioRepository convenioRepository;

	@Autowired
	private ClienteService clienteService;

	@Transactional(readOnly = true)
	public List<Convenio> findAll() {
		return this.convenioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Convenio findById(Integer convenio_id) {
		Optional<Convenio> convenio = this.convenioRepository.findById(convenio_id);
		return convenio.orElseThrow(() -> new ObjectNotFoundException(MessageUtils.OBJECT_NOT_FOUND));
	}

	@Transactional
	public Convenio create(Integer prontuario_id, Convenio convenio) {
		convenio.setId(null);
		Cliente cli = this.clienteService.findById(prontuario_id);
		convenio.setCliente(cli);
		return this.convenioRepository.save(convenio);
	}

	@Transactional
	public Convenio update(Integer convenio_id, Convenio convenio) {
		Convenio convenioUpdate = this.findById(convenio_id);
		updateData(convenioUpdate, convenio);
		return this.convenioRepository.save(convenioUpdate);
	}

	@Transactional
	public void delete(Integer convenio_id) {
		Convenio convenio = this.findById(convenio_id);

		if (convenio != null) {
			throw new IntegrityViolationException(MessageUtils.DATA_INTEGRITY_VIOLATION);
		}

	}

	private void updateData(Convenio convenioUpdate, Convenio convenio) {
		convenioUpdate.setNumCarteirinhaConvenio(convenio.getNumCarteirinhaConvenio());
		convenioUpdate.setValidadeCarteirinha(convenio.getValidadeCarteirinha());
	}

}