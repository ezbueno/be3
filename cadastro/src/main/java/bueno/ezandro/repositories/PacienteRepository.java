package bueno.ezandro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bueno.ezandro.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Query("SELECT paciente FROM Paciente paciente WHERE paciente.cpf = :cpf")
	Optional<Paciente> findByCPF(@Param(value = "cpf") String cpf);

}