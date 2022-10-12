package GA;

import java.util.Vector;

import elements.LireFichier;

public class Chrosome {

	private Solution solution;
	private int evaluation  ;
	
	// constructeur 1  : creation d'un indevidu(chromosome) aleatoirement pour l'inicialisation
	public Chrosome(LireFichier clset) {
		this.solution= new Solution(clset.getClauses(), clset.getLitteraux(), clset.getNombreTotalClauses()); 
		this.solution.generateSolution(); // gerer des solution aleatoire
		this.evaluation= this.solution.getFitness();// le nombre de clauses satisfaites
	}
	
	//constructeur 2 : creation d'un indevidu(chromosome) avec une solution specefique pour l'inicialisation
	public Chrosome(Solution sol,LireFichier clset) {
		this.solution= new Solution(sol,clset.getClauses(),clset.getLitteraux(), clset.getNombreTotalClauses()); 
		this.evaluation= sol.getFitness();
	}
	
	// constructeur 3 :Copier un chromosome:
	public Chrosome (Chrosome chro,LireFichier clset) {
		this.solution= new Solution(chro.solution,clset.getClauses(),clset.getLitteraux(), clset.getNombreTotalClauses()); 
		this.evaluation= chro.evaluation;
	}
	
	// la mutation :
	public void Mutation (LireFichier clset, int pposition) {
		this.solution.sol.get(pposition).bitFlip();
		this.evaluation=this.solution.getFitness(); // mise a jour (la solution a changee)
	}
	// getter et setters :

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
		
	}

}
