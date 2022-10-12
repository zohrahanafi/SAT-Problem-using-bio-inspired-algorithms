package ACS;

import java.util.Vector;


//chaque fourmis a un vecteur de solution de taille 75 et une evalution : fitness
public class Ant 
{
	private Vector<Integer> solution = new Vector<Integer>();
	private int evaluation;
	
	//constructeur
	public Ant(Vector<Integer> solution, int evalution)
	{
		this.solution = solution;
		this.evaluation = evalution;
	}
	
	//Getters and Setters
	public Vector<Integer> getSolution() {
		return solution;
	}

	public void setSolution(Vector<Integer> solution) {
		this.solution = solution;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
}
