package example.main.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.main.modelo.Curso;

//Posible error, estoy utilizando Integer y en curso int
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

}
