package main;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.Aluno;
import domain.Disciplina;

/**
 * Entity implementation class for Entity: Main
 *
 */

public class Main implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projeto_jpa");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Aluno> q = em.createQuery("select a from Aluno a", Aluno.class);
		
		for (Aluno a : q.getResultList()){
			System.out.println("O aluno " + a.getNomeDoAluno() + "(" + a.getNumeroDeAluno() + ") está inscrito a:");
			for (Disciplina d : a.getDisciplina()){
				System.out.println(d.getDesignacao() + "(" + d.getCodigoDaDisciplina() + ")");
				
			}
		}
	}
   
}
