package A_etoile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;
import elements.*;
public class AStar {

	
	private static LinkedList<Noeud> ouverte; //liste de noeuds représentant les étapes d'instanciation selon f
	protected static String reponse; //si l'instance est satisfaite
	public static String time;
	public static String Sat;  //le fichier
	public static Vector<Litteral> solution; //vecteur des solutions
	
public static void chercheSolution(LireFichier lf){
	//long lStartTime2 = System.nanoTime();
	long lStartTime = (System.nanoTime()/1000000);
	long strt=System.currentTimeMillis();
	
	    Noeud noeud = new Noeud(lf.getClauses(),lf.getLitteraux(), lf.getNombreTotalClauses()); //tous les littéraux
		ArrayList<Noeud> fils = new ArrayList<>(); // nouvelle liste de noeuds
		Noeud fg , fd ;
		ouverte = new LinkedList<>(); //initialement vide
		ajouerNoeud(noeud); //ajouter un noeud à la liste ouverte
		while(!ouverte.isEmpty()){ 

			Collections.sort(ouverte, Noeud::compareTo); //ordonner les noeuds par  rapport à la fonction f
			noeud = ouverte.getFirst(); //retirer le 1er noeud de ouvert
			ouverte.removeFirst(); //supprimer le 1er noeud de ouverte
			
			if(noeud.sat()) //si il ne reste aucune clause
			{
				solution=noeud.getSolution() ;
				
			    long lEndTime = (System.nanoTime()/1000000);
			    time="\n \nle temps d'éxécution est : "+(lEndTime-lStartTime)+"  ms";
			    return; //sortir de la boucle while
			}
			else{
				fils = noeud.instancier(); //voir le noeud suivant elle retourne une liste de 2 éléments
				if( fils.size()>0 && (fg = fils.get(0)) != null && (fd = fils.get(1)) !=null ) {
					ajouerNoeud(fg);
					ajouerNoeud(fd);	
				}
			}
			
			fils.clear();
			// si le temps écoulé  dépasse 5min on arrete
		if(System.currentTimeMillis()>(strt+300000)) {
				solution=noeud.getSolution() ;
				Sat="le nombre de clauses SAT est :";
				Sat=Sat+""+noeud.getBestFitness();
				return;
				
			}
		}
		
		reponse="not SAT";
		long lEndTime = System.nanoTime();
		time=" "+(lEndTime-lStartTime/1000000000);

		return;
	}
	private static void ajouerNoeud(Noeud node){		
			ouverte.add(node);
	}

}
