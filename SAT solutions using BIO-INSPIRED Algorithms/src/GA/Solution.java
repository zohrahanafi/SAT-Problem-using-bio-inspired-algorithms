package GA;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import elements.Clause;
import elements.Litteral;

public class Solution {
protected ArrayList<Litteral> sol= new ArrayList();
private Vector<Clause> clauses;
private Vector<Litteral> litteraux;
private int fitness;
private int  nbrTotalC;


public Solution(Vector<Clause> clauses, Vector<Litteral> litteraux,int nbrTotalC) {
	super();
	this.clauses = (Vector<Clause>) clauses.clone();
	this.litteraux = (Vector<Litteral>) litteraux.clone();
	this.nbrTotalC=nbrTotalC;
}
// constructeur 2 :
public Solution(Solution solution,Vector<Clause> clauses, Vector<Litteral> litteraux,int nbrTotalC) {
	this.clauses = (Vector<Clause>) clauses.clone();
	this.litteraux = (Vector<Litteral>) litteraux.clone();
	this.nbrTotalC=nbrTotalC;
	for (int i=0;i<solution.sol.size();i++) 
	{
		this.sol.add(solution.sol.get(i));
	}
}
public Solution(int size,Vector<Clause> clauses, Vector<Litteral> litteraux,int nbrTotalC) { /* Constructeur pour creer une solution vide */
	this.clauses = (Vector<Clause>) clauses.clone();
	this.litteraux = (Vector<Litteral>) litteraux.clone();
	this.nbrTotalC=nbrTotalC;
	Litteral l= new Litteral(0);
	for(int i=0; i<size; i++)
		this.sol.add(l);
}




public ArrayList<Litteral> getSol() {
	return sol;
}

public void setSol(ArrayList<Litteral> sol) {
	this.sol = sol;
}
// generer une solution aléatoirement 
public void generateSolution(){
	Vector<Litteral> lits=new Vector<Litteral>();
	for(int i=1;i<(this.litteraux.size()/2)+1;i++) {
		lits.add(new Litteral(i));
	}
	
	while (this.sol.size()<this.litteraux.size()/2) {
		double prob=1.0*Math.random();
		Litteral l=lits.firstElement();
		Litteral nonl= new Litteral(-l.getLitteralNum());
		lits.remove(l);
		lits.remove(nonl);
		if(prob>0.5) {
		if(this.sol.contains(l)|| this.sol.contains(nonl)) {}else {
		    this.sol.add(l);
		}
	}else {
		if(this.sol.contains(l)|| this.sol.contains(nonl)) {}else {
		    this.sol.add(nonl);
		
	}
	}
	}

}

// calculer le nombre de clause satifaites par la solution générer 
public int getFitness() {
	for(Litteral litteral: this.sol) {
		for(Clause clause:this.clauses) {
			for(Litteral ll:clause.getLitteraux())
			if(ll.getLitteralNum()==litteral.getLitteralNum()) {litteral.addClause(clause);}
		}
	}
	int cpt=0;
	for(Litteral l:sol) {
		this.clauses.removeAll(l.getClauseDuLitteal());
		cpt=this.nbrTotalC-this.clauses.size();	
	}
	this.fitness=cpt;
	return this.fitness;
}
public boolean changerLitteral(int position, int value) { /* Change truth value of literal in position "position" */
	if((position < 0) || (position >= this.sol.size())) /* Error : index out of array's bounds */
		return false;
	Litteral l= new Litteral(value);
	this.sol.set(position, l);

	return true;
}


// getters and setters 
public void setFitness(int fitness) {
	this.fitness = fitness;
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
public int getNbrTotalC() {
	return nbrTotalC;
}
public void setNbrTotalC(int nbrTotalC) {
	this.nbrTotalC = nbrTotalC;
}

}
