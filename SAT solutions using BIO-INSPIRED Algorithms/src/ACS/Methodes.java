package ACS;

import java.util.Random;
import java.util.Vector;

import elements.Clause;
import elements.LireFichier;

//les méthodes utilisées
public class Methodes 
{
	//compter les clauses satisfaites à partir d'un nombre
	public static int satisfy(LireFichier fr, int index)
	{
		int n = 0;
		for(int i=0; i<fr.getNombreTotalClauses();i++)
		{
			for(int j=0; j<3;j++)
			{
				if(fr.getClauses().get(i).getLitteraux().get(j).getLitteralNum() == index) n++;
			}
		}
		return n;
	}
	
	//evaluer une solutiton
	public static int evaluate(LireFichier fr,Vector<Integer> solution )
	{
		Vector<Clause> vc = (Vector<Clause>) fr.getClauses().clone();

		int m=0;
		for(int s = 0; s<solution.size();s++)
		{
			for(int i=0; i<vc.size();i++)
			{
				for(int j=0; j<3;j++)
				{
					if(vc.get(i).getLitteraux().get(j).getLitteralNum() == solution.get(s))
						{  
							m++;
							vc.remove(i);
							break;
						}
				}
				
			}
		}
		return m;
	}
	
	//pour construire une solution
	public static Vector<Integer> build_solution(Vector<Pheromone_Table> pt, float q0, float alpha,float beta, LireFichier fr)
	{
		Vector<Integer> solution = new Vector<Integer>();
		Vector<Pheromone_Table> litteral = new Vector<Pheromone_Table>();
		litteral.addAll(pt);
		
		Vector<BuildSolution> build = new Vector<BuildSolution>();
		
		//pick a litteral from -75 to 75 and compute the clauses satisfied by this litteral
		for(int i =0; i < litteral.size(); i++) //size 150
		{
			int indexLitteral = litteral.get(i).getIndexLitteral();
			int satisfait = satisfy(fr,indexLitteral); //calculer le nombre de clauses satisfaites par ce litteral
			float h = satisfait/325f; //calculer l'heuristique
			float a = (float) (Math.pow(litteral.get(i).getPheromone(), alpha) * Math.pow(h, beta)); 
			
			build.add(new BuildSolution(indexLitteral,a));
		}
		
		while(!build.isEmpty())
		{
				Random rand = new Random();
				float q = rand.nextFloat() * (1 - 0) + 0; //q est un nombre aléatoire
				 int max_Litteral;
				 float max_prob;
				if( q <= q0)
				{
					max_Litteral = build.get(0).getIndexLitteral(); //intialaiser par l'index du 1er litteral (-75)
					max_prob = build.get(0).getProba(); //initialiser par la probabilié du premier littéral avec l'index -75
					
					for(int i = 0; i < build.size(); i++)
					{
						if(build.get(i).getProba() > max_prob)
						{
							max_Litteral = build.get(i).getIndexLitteral();
							max_prob = build.get(i).getProba();
						}
					}
				}else {  //pseudo random transition rule
					float probaGlobale = 0 ;
					for(int i = 0; i<build.size();i++)
					{
						probaGlobale = probaGlobale + build.get(i).getProba(); 
					}
					
					max_Litteral = build.get(0).getIndexLitteral();
					max_prob = build.get(0).getProba();
					
					for(int i = 0; i<build.size();i++)
					{
						float t = Math.abs(build.get(i).getProba()/probaGlobale);
						if(t > max_prob)
						{
							max_Litteral = build.get(i).getIndexLitteral();
							max_prob = t;
						}
					}
				}
				
				solution.add(max_Litteral);
				
				int cpt = 0;
				while( cpt < build.size()){
					if((build.get(cpt).getIndexLitteral() == max_Litteral) || (build.get(cpt).getIndexLitteral() == -max_Litteral)){
						build.remove(cpt);
					}else{
						cpt++;
					}
				}
		}
		
	/*	for (int i = 0; i < solution.size(); i++) {
			System.out.print(solution.get(i)+" ");
		}
		System.out.println("\n sat "+evaluate(fr,solution));*/
		//retourner la solution
	return solution;
	}
	
	
	
	
	public static void onlineUpdate(Vector<Ant> ants, Vector<Pheromone_Table> pt, float ro, int i) 
	{
		Vector<Integer> solution = new Vector<Integer>();

			solution = ants.get(i).getSolution(); //obtenir la solution d'une fourmi i
			for(int k = 0; k<solution.size();k++)//parcourir cette solution
			{
				//parcourir le vecteur de pheromone contenant l'indice de chaque litteral et sa qt de pheromone
				for(int j = 0; j < pt.size(); j++) 
				{
					if(pt.get(j).getIndexLitteral() == solution.get(k)) //si la fourmi est passé par là
					{ //cad la solution contient l'indice de ce litteral
						float t = pt.get(j).getPheromone(); //obtenir l'ancienne phéromone
						float t1 = (float) ((1-ro)*t + ro*0.1); //calcul de la nouvelle phéromone
						pt.get(j).setPheromone(t1); //mise à jour dans le vecteur des phéromones
					}
				}
			}
	}
	
	
	public static void offlineUpdate(Vector<Ant> ants, int best, Vector<Pheromone_Table> pt, LireFichier fr, float ro)
	{
		Vector<Integer> offline = new Vector<Integer>(); //vecteur qui contient la meilleure solution
		offline.addAll(ants.get(best).getSolution());
		for(int i = 0; i < offline.size();i++) 
		{
			for(int j = 0; j < pt.size();j++) //vecteur qui contient tous les indices des littéraux  et leurs phéromones
			{
				float t = pt.get(i).getPheromone(); //obtenir l'ancienne phéromone
				if(offline.get(i) == pt.get(j).getIndexLitteral()) //si la fourmis a pris ce chemin
				{					
					float cost = 1/evaluate(fr,offline);  //calculer le côut
					float update = (float) ((1-ro)*t + ro*cost ); //calculer la nouvelle quantité
					pt.get(j).setPheromone(update);
				}
				else { //si la fourmi n'est pas passé par là
					float update = (float) (1-ro)*t;
					pt.get(j).setPheromone(update);
				}
			}
		}
	}
	
	//determiner la  meilleure solution et obtenir son indice dans le vecteur de solutions
	public static int bestSolution(Vector<Ant> ants, Ant bestAnt)
	{
		int bestSol = ants.get(0).getEvaluation(); //initialiser l'evaluation par la 1ere solution
		int indiceBest = 0; //initialiser l'indice
		for(int i = 1; i < ants.size(); i++) //parcourir le vecteur des solutions
		{
			if(ants.get(i).getEvaluation() > bestSol) //s'il existe une meilleure solution dans le vecteur des solutions
			{ 
				bestSol = ants.get(i).getEvaluation(); //obtenir la fitness de cette solution
				indiceBest = i; //obtenir son indice dans le tableau des solutions des fourmis
			}
		}
		if(bestSol > bestAnt.getEvaluation())  //si la meilleure solution de l'iteration a une meilleure fitness
		{
			bestAnt.setSolution(ants.get(indiceBest).getSolution());  //modifier la solution de la meilleure fourmi
			bestAnt.setEvaluation(ants.get(indiceBest).getEvaluation()); //modifier sa fitness
			return indiceBest; //obtenir son indice dans le tableau des solution
		}
		return indiceBest;
	}
	
}
