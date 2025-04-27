package example.main.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import example.main.DAO.service.CursoService;
import example.main.modelo.Curso;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cr;

	@GetMapping("/getCursos")
	public ArrayList<Curso> getCursos(){
		return cr.getCursos();
	}
	
	
	
	
}
