package GA;

import java.util.ArrayList;
import java.util.Random;

import elements.LireFichier;



public class AlgorithmeGenetic {

	
	public Solution RechercheAvecGA(LireFichier clset, int taillePopoltion, int nombreIlteration, int tauxMutation, int tauxCroivement) {
		ArrayList<Chrosome> population = new ArrayList<Chrosome>(); 
		int[] ChromosomeChoisit = new int[2]; // les 2 chromosomes choisit pour le croisement
		Chrosome[] child = new Chrosome[2]; // resulats d croisement et mutation
		Random random = new Random(); 
		float mr; // un random qui gere la mutation
		int pointdecroisement; // un random qui gere le point de croisement
		
		
		population= initialisation(taillePopoltion, clset); //intialiation de la population aleatoirement
		for (int i=0;i<nombreIlteration;i++) {
			do { // on doit choisir 2 chromosome differents, les meme n'auront aucun impacte sur le resultat
				ChromosomeChoisit[0]= random.nextInt(taillePopoltion);
				ChromosomeChoisit[1]= random.nextInt(taillePopoltion);
			}while(ChromosomeChoisit[0]==ChromosomeChoisit[1]);
			if (random.nextFloat()*100 <tauxCroivement) { // on accpete le coisement 
				pointdecroisement=random.nextInt(clset.getNombreTotalLitteraux());
				child[0] = AlgorithmeGenetic.croisement(clset, population.get(ChromosomeChoisit[0]), population.get(ChromosomeChoisit[1]), pointdecroisement, true);
				child[1] = AlgorithmeGenetic.croisement(clset, population.get(ChromosomeChoisit[0]), population.get(ChromosomeChoisit[1]), pointdecroisement, false);
			
			mutation(clset, tauxMutation, child); // la mutation 
			for (int j=0;j<population.size();j++) { // l'evaluation et remplacement
			
				if ((child[0]!=null) && (population.get(j).getEvaluation() < child[0].getEvaluation()) ) {
					population.set(j, new Chrosome(child[0],clset)); // si on trouve un chromosome avec une solution mieux , on remplace dans population 
					child[0] = null; // pour qu'on utilise ce fils une seule fois
				}
				else if((child[1] != null) && (population.get(j).getEvaluation() < child[1].getEvaluation())) {
					population.set(j, new Chrosome(child[1],clset)); // si on trouve un chromosome avec une solution mieux , on remplace dans population 
					child[1] = null; // pour qu'on utilise ce fils une seule fois
				}
				if((child[0] == null) && (child[1] == null))
					break; // si les 2 chromosomes fils sont remplacer on quitte la boucle 
				}}}
		// on recupere la optimal
		Solution GbestSol = GetBestSolution(population, clset);
		return GbestSol;
		
	}
	
	// fonction pour l'incialiation de la population iniciale 
	private ArrayList<Chrosome> initialisation (int taillePopoltion,LireFichier clset) {
		ArrayList<Chrosome> population = new ArrayList<Chrosome>();
		Chrosome tmpChr =null; // chromosome temporaille pour la creation de la population iniciale
		int nbrChromosome=0;
		int index=0;
		// la creation de la population iniciale :
		while (nbrChromosome<taillePopoltion) {
			
			tmpChr= new Chrosome(clset); //la creation d'un chromosome aleatoirement
			// verifier si il n'existe pas un chromosome dans population qui est egale a la solution, si ca existe on quitte
			for (index=0;index<population.size(); index++) 
				if (tmpChr.getSolution().equals(population.get(index).getSolution()))
					break;
					//si on arrive a la fin et on trouve que la solution n'existe pas 
				if (index==population.size()) {
				//	System.out.println(tmpChr.getEvaluation());
					population.add(new Chrosome(tmpChr,clset)); // on ajoute tmp a la population 
					nbrChromosome++; // on incremente le nombre de population
					}
					
		} // end hile loop
		return population;	

		
	}
	
	
	
	//fonction pour recuperer la bestSol de la population

	private static Solution GetBestSolution (ArrayList<Chrosome> population, LireFichier clset) {
		
		Solution bestSol = new Solution(clset.getNombreTotalLitteraux(),clset.getClauses(),clset.getLitteraux(),clset.getNombreTotalClauses()); 
		for(Chrosome chro : population) 
			if(bestSol.getFitness() < chro.getSolution().getFitness())
				bestSol = chro.getSolution(); // mise a jour de bestSol  
		
		return bestSol;
	}
	
	private  void mutation (LireFichier clset,int tauxMutation, Chrosome [] child) {
		float mr;
		Random random= new Random();
		//la mutation
		if ((mr=random.nextFloat()*100)<tauxMutation) { // on accepte la mutation
			//on creer une liste de taille le nombre de litteral )
			ArrayList <Integer> availableLitterels = new ArrayList<Integer>();
			
			for (int j=0;j<clset.getNombreTotalLitteraux();j++) {
				availableLitterels.add(j);
			}
			child[0].Mutation(clset, availableLitterels.remove(random.nextInt(availableLitterels.size())));
			} // end of if 
		
		if((mr = random.nextFloat()*100) < tauxMutation) { /*  on accepte la mutation */
			ArrayList<Integer> availableLiterals = new ArrayList<Integer>();
			for (int j = 0; j < clset.getNombreTotalLitteraux(); j++) {
				availableLiterals.add(j);
			}
			child[1].Mutation(clset, availableLiterals.remove(random.nextInt(availableLiterals.size())));
			
		} // end of 2nd if
	} 
	
	
	
	
	
	private static Chrosome croisement(LireFichier clset, Chrosome ind1, Chrosome ind2, int place, boolean premier) {
		Solution resultSol = new Solution(clset.getNombreTotalLitteraux(),clset.getClauses(),clset.getLitteraux(),clset.getNombreTotalClauses());
	
		if(premier) {
			for(int i=0; i<place; i++)
				resultSol.changerLitteral(i, ind1.getSolution().sol.get(i).getLitteralNum()); 

			for(int i=place; i<resultSol.sol.size(); i++)
				resultSol.changerLitteral(i, ind2.getSolution().sol.get(i).getLitteralNum());
		}else { /* Second cross */
			for(int i=0; i<place; i++)
				resultSol.changerLitteral(i, ind2.getSolution().sol.get(i).getLitteralNum());

			for(int i=place; i<resultSol.sol.size(); i++)
				resultSol.changerLitteral(i, ind1.getSolution().sol.get(i).getLitteralNum());
		}

		return(new Chrosome(resultSol,clset));
	}

}
