package ACS;

import java.util.Vector;

import A_etoile.AStar;
import elements.LireFichier;

public class ACO 
{
	public static Ant bestAnt = new Ant(null,0); 
	
	public static void algorithmeACO(LireFichier fr,float alpha, float beta, float q0, float ro, int nbrFourmis, int nbrIter)
	{
		//initialisation
		Vector<Pheromone_Table> pt = new Vector<Pheromone_Table>(); //vecteur de pheromone de taille 150 contient l'index du litteral et sa phéromone
		Vector<Ant> ants = new Vector<Ant>(); //vecteur de fourmis
		int indiceBest = 0;
		
			//pour ajouter les soutions et leurs évaluations
		Vector<Integer> solution0 = new Vector<Integer>();
		
		//appel de l'lgorithme A* pour intialiser la solution et son evaluation
		AStar.chercheSolution(fr);
		for(int i =0 ; i < AStar.solution.size(); i++)
		{
			solution0.add(AStar.solution.get(i).getLitteralNum());
		}
		
		int evaluation = Methodes.evaluate(fr, solution0); //evaluer la solution generée par A*

		bestAnt.setSolution(solution0); //initialiser la solution best
		bestAnt.setEvaluation(evaluation); //initialiser l'évaluation de cette solution 
		
	//	bestAnt = new Ant(null,0);
				
		for(int i = -75; i < 76; i++) //pour le tableau des états (index du litteral et sa phéromone initiale)
		{
			if(i!=0) {
				Pheromone_Table p = new Pheromone_Table(i, 0.1f);
				pt.add(p);
					}
			
		}

		for(int i = 0; i < nbrFourmis; i++) //initialiser les fourmis
		{
			ants.add(new Ant(null,0));
		}
		
		//début de traitement
		for(int i = 0; i < nbrIter ;i++) //parcourir le nombre des itérations
		{
			for(int j = 0; j< nbrFourmis; j++) //pour chaque fourmis
			{
				Vector<Integer> solution1 = new Vector<Integer>(); 
				solution1 = Methodes.build_solution(pt, q0, alpha, beta, fr); //construire une solution
				ants.get(j).setSolution(solution1); //ajouter l'évaluation de cette solution au vecteur des solution
				ants.get(j).setEvaluation(Methodes.evaluate(fr, solution1)); //inserer l'valuation de cette solution
				Methodes.onlineUpdate(ants, pt, ro, j); //maj en ligne
			}
			indiceBest = Methodes.bestSolution(ants, bestAnt); // déterminer l'indice de la meilleure solution du vecteur des solutions
			Methodes.offlineUpdate(ants, indiceBest, pt, fr, ro); //maj hors ligne
		}
	}
}
