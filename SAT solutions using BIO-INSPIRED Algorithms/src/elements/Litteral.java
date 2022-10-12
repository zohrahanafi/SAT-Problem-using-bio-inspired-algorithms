package elements;


import java.util.Random;
import java.util.Vector;

public class Litteral {
	//Le num�ro du litteral entre -75 et 75
	private int litteralNum; 
	//Les clauses qui contiennent ce litt�ral
	private Vector<Clause> clauseDuLitteal; 
	
	//constructeur 1
	public Litteral(int litteralNum) {
		this.litteralNum=litteralNum; //initialiser le litt�ral
		clauseDuLitteal=new Vector<Clause>(); //initialiser le vecteur des clauses qui contiennent ce litt�ral
		
	}
	//constructeur 2
	public Litteral(Litteral l) {
		this.litteralNum=l.litteralNum; 
		this.clauseDuLitteal=l.getClauseDuLitteal();
		
	}
	
	//getters and setters
	public int getLitteralNum() {
		return litteralNum;
	}
	public void setLitteralNum(int litteralNum) {
		this.litteralNum = litteralNum;
	}
	public Vector<Clause> getClauseDuLitteal() {
		return clauseDuLitteal;
	}
	public void setClauseDuLitteal(Vector<Clause> clauseDuLitteal) {
		this.clauseDuLitteal = clauseDuLitteal;
	}
		
	//ajouter une clause
	public void addClause(Clause clause) {
		clauseDuLitteal.add(clause);
										 }

	//retourne le litt�ral oppos� 
	public static Litteral getOpposite(Vector<Litteral> vl,Litteral litteral){
		for(Litteral l:vl) {    //chercher le litt�ral dans le vecteur
			if(l.getLitteralNum()==-(litteral.getLitteralNum())) { return l; }
							}
		return null;
																	  }
	//inverser l'index
	public void bitFlip() {
		this.litteralNum=-(this.litteralNum);
	}
	//retourner un litteral ou noeud al�atoirement entre 1 et 75
	public static Litteral random (Vector<Litteral> vl){
		if(vl.size()>0){
			Random rand = new Random(); 
			int littRand = rand.nextInt(vl.size());
			Litteral litteral = vl.get(littRand);
			return litteral;}
		return null;
	}
													  }
