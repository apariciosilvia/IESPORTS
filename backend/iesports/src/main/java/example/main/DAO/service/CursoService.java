package example.main.DAO.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.main.DAO.repository.CursoRepository;
import example.main.modelo.Curso;

@Service
public class CursoService {

	@Autowired
	CursoRepository cr;
	
	
	//Método que se encarga de recoger todos los cursos posibles
	public ArrayList<Curso> getCursos(){
		return (ArrayList<Curso>)cr.findAll();
	}
	
	
	
}
