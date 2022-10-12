package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Noeud implements Comparable<Object> {
	
	private Litteral litteral;
	private Vector<Clause>   clauses   = new Vector<>(); //toutes les clauses 
	private Vector<Litteral> litteraux = new Vector<>(); //de -75 à 75
	private Vector<Litteral> solution = new Vector<Litteral>();
	private int bestFitness=0;
	private int NombreTotalClauses=0;
	private int fitness = 0; 
	private int heuristique = 0; 

	@SuppressWarnings("unchecked")
	//constructeur 1
	//initialiser avec toutes les clauses et les littéraux du fichier et le nbr total de clauses
	public Noeud(Vector<Clause> clauses, Vector<Litteral> litteraux,int NombreTotalClauses) 
	  {
		this.clauses = (Vector<Clause>) clauses.clone();
		this.litteraux = (Vector<Litteral>) litteraux.clone();
		this.NombreTotalClauses=NombreTotalClauses;
	  }
	@SuppressWarnings("unchecked")
	//constructeur 2 
	public Noeud(Litteral litteral,Noeud noeud) 
      {
		this.clauses = (Vector<Clause>) noeud.clauses.clone();//initialiser le evecteur des clauses
		this.litteraux = (Vector<Litteral>) noeud.litteraux.clone();//initialiser le vecteur  des littéraux
		this.solution=(Vector<Litteral>) noeud.solution.clone();//initialiser le vecteur des solutions
		//System.out.println(lit.getClauses().size());
		clauses.removeAll(litteral.getClauseDuLitteal()); //enelever toutes les clauses qui contiennent ce littéral
		litteraux.remove(Litteral.getOpposite(this.litteraux, litteral)); //enlever les clauses qui contiennent l'opposé
		litteraux.remove(litteral);
		this.litteral = litteral;
		this.NombreTotalClauses=noeud.NombreTotalClauses;
		calculerH(); //h
		setFitness(); //f
		//choisir la meilleure fitness
		  if(this.getFitness()>this.getBestFitness()) 
		  {
			this.setBestFitness(this.getFitness());
		  }
      }
	
	//permet de developper les noeud suivants
	//retourner une liste de taille 2 : fils gauche et fils droit
	public ArrayList<Noeud> instancier()
	{
		ArrayList<Noeud> pere= new ArrayList<Noeud>();
		if(this.litteraux.size()>0)//il existe un litteral
		{ 
		    Litteral litteral = Litteral.random(this.litteraux); //generer un litteral aleatoire
		    Litteral litteralOppose = Litteral.getOpposite(this.litteraux, litteral); //get le contraire de ce literal
		    Noeud fg =new Noeud(litteral,this); //fils gauche du litteral est positif
		    Noeud fd= new Noeud(litteralOppose,this); //fils droite du litteral est negatif
		    fg.setSolution(litteral); 
		    fd.setSolution(litteralOppose); 
		    pere.add(fg);pere.add(fd); //empiler les fils
		}
		return pere;	
	}
	
	//calculer la fréquence du littéral dans l'ensemble des clauses h(x)
	private void calculerH(){
		int cpt = 0 ;
		for(Clause clause:this.clauses)
			if(this.litteral.getClauseDuLitteal().contains(clause)) 
				cpt++;
		
		heuristique  = cpt ;
						 }
	
    
	//fonction fitness
	private void setFitness() {
		//le nombre total de clauses - les clauses qui ne contiennent pas ce litteral
        int clauseSat = NombreTotalClauses - this.clauses.size(); //g(x)
        //fonction fitness
        this.fitness  = clauseSat + heuristique ; //f(x)=g(x)+h(x)
        //System.out.println(clauseSat);
	}
	
	
	//comparer la fonction fitness de deux noeuds
	@Override
	public int compareTo(Object o) {
		if(o==this) return 0;
		if((o instanceof Noeud)){
			Noeud node = (Noeud) o ;
		return fitness   < node.fitness  ? 1 : -1 ;
			
								}
		return 0;
       								}
	
	
	// Test si l'instance est satisfiable cad supprimer toutes les clauses
	//si on a supprimé toutes les clauses et il n'en reste aucune
	public boolean sat() {
		if(this.clauses.size()==0) {
			return true;
								  }
		return false;
						}
	
	
// getters and Setters 
	public Litteral getLitteral() {
		return litteral;
	}
	public void setLitteral(Litteral l) {
		this.litteral = l;
	}
	public int getBestFitness() {
		return bestFitness;
	}
	
	public void setBestFitness(int bestFitness) {
		this.bestFitness = bestFitness;
	}

	private int getFitness() {
	     return fitness ;
						  }

	public Vector<Clause> getClauses() {
		return clauses;
	}
	public void setClauses(Vector<Clause> clauses) {
		this.clauses = clauses;
	}
	public Vector<Litteral> getLitteraux() {
		return litteraux;
	}


	public void setLitteraux(Vector<Litteral> litteraux) {
		this.litteraux = litteraux;
	}
	
	public Vector<Litteral> getSolution() {
		return solution;
	}

	public void setSolution(Litteral solution) {
		this.solution.add(solution);
	}
	public int getNombreTotalClauses() {
		return NombreTotalClauses;
	}
	public void setNombreTotalClauses(int nombreTotalClauses) {
		NombreTotalClauses = nombreTotalClauses;
	}
	public int getHeuristique() {
		return heuristique;
	}
	public void setHeuristique(int heuristique) {
		this.heuristique = heuristique;
	}

}
