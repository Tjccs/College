package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aluno
 *
 */
@Entity

public class Aluno implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private int numeroDoAluno;
	private String nomeDoAluno;
	private List<Disciplina> disciplinas;
	
	
	public Aluno() {
		super();
	}


	public String getNomeDoAluno() {
		
		return nomeDoAluno;
	}


	public int getNumeroDeAluno() {
		
		return numeroDoAluno;
	}
	public List<Disciplina> getDisciplina(){
		return disciplinas;
	}
   
}
