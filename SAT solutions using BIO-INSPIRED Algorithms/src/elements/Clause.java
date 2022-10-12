package elements;

import java.util.Vector;

public class Clause{
	
	//toutes les clauses du fichier
	private Vector<Litteral> Litteraux=new Vector<Litteral>();

	//getter
	public Vector<Litteral> getLitteraux() {
		return Litteraux;
	}
	
	
	// setter
	public void setLitteraux(Litteral l) {
		Litteraux.add(l);
	}

}
