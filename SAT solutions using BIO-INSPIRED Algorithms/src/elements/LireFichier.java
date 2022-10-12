package elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class LireFichier {
	//LECTURE DES FICHIER CNF 
	 Vector<Clause> clauses = new Vector<Clause>(); //taille 325
	 Vector<Litteral> litteraux = new Vector<Litteral>(); //taille 150de -75 à 75
	private int nombreTotalClause;
	public File f;
	private   int nombreTotalLitteraux;
	
	  
	  //constructeur
	 public LireFichier(File f) {
		 this.f=f;
	 }
	 //méthode
	public void readFile() {
		

	Vector<Clause> l = new Vector<Clause>();
	String fichier; /* = "C:\\Users\\Dell\\Desktop\\Bioinspiré projects\\UF75.325.100\\uf75-01.cnf";*/
	fichier=f.getPath();	fichier=fichier.replace("\\", "/");
	
	try{
		InputStream stream=new FileInputStream(fichier); 
		InputStreamReader reader=new InputStreamReader(stream);
		BufferedReader br=new BufferedReader(reader);
		String line;
		String l1=new String();
		String l2=new String();
		String l3=new String();
		int i = 0;
		Clause c =new Clause();
	
		while ((line=br.readLine())!=null){ //tant que c pas la fin du fichier
			
			if(line.startsWith("c")||line.startsWith("%")||line.startsWith("0")||line.startsWith("p")|| line.startsWith("P")|| line.isEmpty())
			{if(line.startsWith("P") || line.startsWith("p") ) { 
				config(line); //nombre de littéraux = 75
				initialiserLitteraux(); // de -75 jusqu'à 75 littéraux  en excluant le 0
																}
			}
			else{
				if(line.startsWith(" ")){ 
					i = 1; //éviter  le 1er espace dela ligne des littéraux
										}
				
				while((line.charAt(i)!=' ')&&(i<line.length())){
					l1=l1 + line.charAt(i);
					i++;
				}
				int val=Integer.parseInt(l1);
				Litteral e=new Litteral(val);
				c.setLitteraux(e); //ajouter le littéral auvecteur des clauses
				//e.addClause(c);
				l1=""; //rénitialiser le l1
				i++;
				while((line.charAt(i)!=' ')&&(i<line.length())){
					l2=l2+line.charAt(i);
					i++;
				}
				int val2=Integer.parseInt(l2);
				Litteral e2=new Litteral(val2);
				c.setLitteraux(e2);
				//e2.addClause(c);
				
			
				l2="";
				i++;
				while((line.charAt(i)!=' ')&&(i<line.length())){
					l3 = l3+line.charAt(i);
					i++;
				}
				int val3=Integer.parseInt(l3);
				Litteral e3=new Litteral(val3);
				c.setLitteraux(e3);
				//e3.addClause(c);
				l3="";
				l.add(c);
			}
			c=new Clause();
					
		i=0;	
		}
		br.close();
		
	
		
	}
	catch (Exception e){
		System.out.println(e);
	}
	this.clauses=l;
	chainageLiteralClauses();
	this.nombreTotalClause=this.clauses.size();
	
	}
	
	


	
	//ajouter les clauses qui appartiennent à ce littéraux dans le vecteur des clauses dans littéral
	public void chainageLiteralClauses(){
		for(Litteral litteral: this.litteraux) {
			for(Clause clause:this.clauses) {
				for(Litteral ll:clause.getLitteraux())
				if(ll.getLitteralNum()==litteral.getLitteralNum()) {litteral.addClause(clause);}
			}
		}
	}
	
	//les littéreaux  de  -75 à 75 en exluant le 1
	private void initialiserLitteraux(){
		// -nbMax --> +nbMax
		for(int i=-nombreTotalLitteraux;i<=nombreTotalLitteraux;i++){ if(i!=0) this.litteraux.add(new Litteral(i)); }
	}
	
	//calculer le nombre de littéraux = 75
	private void config(String line){ 
		this.nombreTotalLitteraux = Integer.parseInt(line.replaceAll("[^0-9 ]", "").replaceAll("  ", " ").trim().split(" ")[0]);
	}
	
	//getters and setters
	public Vector<Clause> getClauses() {
		return clauses;
	}
	public void setClauses(Vector<Clause> clauses) {
		this.clauses = clauses;
	}
	public Vector<Litteral> getLitteraux() {
		return litteraux;
	}
	public void setLitteraux(Vector<Litteral> litterals) {
		this.litteraux = litterals;
	}
	
	public int getNombreTotalClauses() {
		return nombreTotalClause;
	}
	public int getNombreTotalLitteraux() {
		return nombreTotalLitteraux;
	}
	public void setNombreTotalLitteraux(int nombreTotalLitterals) {
		this.nombreTotalLitteraux = nombreTotalLitterals;
	}

	
}

