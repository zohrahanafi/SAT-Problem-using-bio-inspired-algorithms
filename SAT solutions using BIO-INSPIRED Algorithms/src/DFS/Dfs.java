package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import elements.*;
public class Dfs {

	
	private static LinkedList<Noeud> ouverte= new LinkedList<>(); //pile de noeuds
	protected static String sol; //si l'instance est satisfaite
	public static String time;
	public static String Sat;
	public static Vector<Litteral> solution;
	
	
public static void chercheSolution(LireFichier lr){ 
	
	long lStartTime = (System.nanoTime()/1000000);
	long strt=System.currentTimeMillis();
	
	// le noeud initial reçoit les clauses et les littéraux lu a partir du fichier  
	    Noeud noeud = new Noeud(lr.getClauses(),lr.getLitteraux(),lr.getNombreTotalClauses());
		ArrayList<Noeud> pere = new ArrayList<>();
		Noeud fg , fd ;
		ouverte = new LinkedList<>(); 
		
		//empiler le premier noeud dans ouverte dans ouvert
		addNode(noeud);
		
		while(!ouverte.isEmpty()){
			//dépiler node de ouvert depuis le dernier
			noeud = ouverte.getLast();
			ouverte.removeLast();
			
			//tester si l'instance est satisfaite:
			if(noeud.sat()) //il ne reste aucune clause
			{
				solution=noeud.getSolution();
				
			    long lEndTime = (System.nanoTime()/1000000);
			    time="\n \nle temps d'éxécution est : "+(lEndTime-lStartTime)+"  ms";
			    return;
			}
			else{
				// sinon développer les prochains noeuds

				pere = noeud.instancier();
				if( pere.size()>0 && (fg = pere.get(0)) != null && (fd = pere.get(1)) !=null ) {
					addNode(fg);
					addNode(fd);	
				}
			}
			pere.clear();
			
			// si le temps écoulé  dépasse 5min on arrete
			if(System.currentTimeMillis()>(strt+300000)) {
				solution=noeud.getSolution();
				Sat="le nombre de clauses SAT est :";
				Sat=Sat+""+noeud.getBestFitness();
				return;
				
			}
		}
		
		sol="not SAT";
		long lEndTime = System.nanoTime();
		time=" "+(lEndTime-lStartTime/1000000000);

		return;
	}
	private static void addNode(Noeud node){		
			ouverte.add(node);
	}

}
