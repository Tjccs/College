package domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Disciplina
 *
 */
@Entity

public class Disciplina implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private int codigoDeDisciplina;
	private String disciplina;
	public Disciplina() {
		super();
	}
	public String getDesignacao() {
		
		return disciplina;
	}
	public int getCodigoDaDisciplina() {
		
		return codigoDeDisciplina;
	}
   
}
