package example.main.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.main.modelo.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{

}
