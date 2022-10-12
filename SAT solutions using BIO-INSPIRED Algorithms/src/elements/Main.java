package elements;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import ACS.ACO;


public class Main {

	public static void main(String[] args) {
		
		File f = new File("C:\\Users\\Dell\\Desktop\\Bioinspiré projects\\UF75.325.100\\uf75-01.cnf");
		LireFichier fr = new LireFichier(f);
		fr.readFile();
		System.out.println(fr.getNombreTotalClauses());
		System.out.println(fr.getNombreTotalLitteraux());
		System.out.println(fr.getClauses().size());
		System.out.println(fr.getLitteraux().size());
		
/*		AStar.chercheSolution(fr);
		System.out.println("Solution 1: "+ AStar.solution.size());
		Vector<Integer> solution0 = new Vector<Integer>();
		for(int i =0 ; i < AStar.solution.size(); i++)
		{
			solution0.add(AStar.solution.get(i).getLitteralNum());
		}
		Map<Integer,Integer> map = new TreeMap<Integer, Integer>();
		
		map = ObtenirResultat.obtenir(AStar.solution);
		System.out.println("Map size: "+map.size());
		System.out.println("Evaluer avec map: "+Methodes.evaluate(fr,solution0));
		System.out.println("Map size: "+map.size());*/
	
	/*	for(int i=0; i<fr.getNombreTotalClauses();i++)
		{
			for(int j=0; j<3;j++)
			{
				System.out.print(fr.getClauses().get(i).getLitteraux().get(j).getLitteralNum()+" ");
				
			}
			System.out.print("\n");
		}
		System.out.println("\n");
		for(int i =0;i<fr.getLitteraux().size();i++) {
				System.out.println(fr.getLitteraux().get(i).getLitteralNum());
			}*/
		
		/*Vector<Pheromone_Table> pt = new Vector<Pheromone_Table>();
		for(int i = -75; i < 76; i++)
		{
			if(i!=0) {
				Pheromone_Table p = new Pheromone_Table(i, 0.1f);
				pt.add(p);
			}
			
		}
		
		Vector<Integer> solution = new Vector<Integer>();
		
		solution = Methodes.build_solution(pt, 0.3f, 2, 2, fr);
		
		System.out.println(Methodes.evaluate(fr,solution));
		String[] a = "-50 34 -54 -37 31 -53 4 -69 -22 39 47 62 68 -71 -67 -48 -35 -15 17 26 27 43 70 72 -58 -45 -44 -42 -32 -24 -16 -12 -7 -1 38 51 57 64 66 74 -52 -46 -28 -23 -20 -18 -9 8 14 25 30 -75 -61 -60 -55 -33 -19 -13 -6 -3 40 59 65 73 -63 -49 -41 -36 -29 -21 -11 2 5 10 56".split(" ");
		Vector<Integer> v = new Vector <Integer>();
		for(int i = 0; i < a.length;i++)
		{
			v.add(Integer.parseInt(a[i]));
		}

		
	/*	for(int i =0; i<pt.size(); i++)
		{
			System.out.print("L'index est: "+pt.get(i).getIndexLitteral()+" Sa phéromone est: "+pt.get(i).getPheromone() );
			System.out.print("\n");
		}*/
	/*	int evaluation = Methodes.evaluate(fr, solution);
		Vector<Ant> ants = new Vector<Ant>();
		 ants.add(new Ant(solution, evaluation));
		Methodes.onlineUpdate(ants, pt, 0.1f,0);
		
		for(int i =0; i<pt.size(); i++)
		{
			System.out.print("L'index est: "+pt.get(i).getIndexLitteral()+" Sa phéromone est: "+pt.get(i).getPheromone() );
			System.out.print("\n");
		}
		*/
		
		//--------------------- ACS-------------------
		ACO.algorithmeACO(fr, 2, 2, 0.1f, 0.2f, 5, 10);
		for(int i = 0; i < ACO.bestAnt.getSolution().size(); i++)
		{
			System.out.print(ACO.bestAnt.getSolution().get(i)+" ");
		}
		System.out.println("\n"+ACO.bestAnt.getEvaluation()); 
	}
	
}
