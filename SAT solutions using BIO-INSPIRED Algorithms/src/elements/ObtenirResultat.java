package elements;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class ObtenirResultat
{
		public static Map<Integer, Integer> obtenir(Vector<Litteral> sol)
		{

			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
			
			for(int i=0;i<sol.size();i++) 
			{
				
			
					if(sol.get(i).getLitteralNum()<0)
					{
						map.put(-sol.get(i).getLitteralNum(),0);
					}
					else if(sol.get(i).getLitteralNum()>=1) {
						map.put(sol.get(i).getLitteralNum(),1);
						}
						
			}
		

			for (int i = 1;i<sol.size();i++)
			{
				if(!map.containsKey(i)) map.put(i,0);
			}
		 return map;
		}
}
