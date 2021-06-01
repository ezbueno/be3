package bueno.ezandro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bueno.ezandro.entities.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {
}