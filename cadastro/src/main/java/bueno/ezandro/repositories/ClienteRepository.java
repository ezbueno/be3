package bueno.ezandro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bueno.ezandro.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT cli FROM Cliente cli WHERE cli.cpf = :cpf")
	Optional<Cliente> findByCPF(@Param(value = "cpf") String cpf);

}