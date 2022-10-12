package ACS;
//classe qui contient l'indice d'un litterala et sa pheromone
public class Pheromone_Table {
	private int indexLitteral;
	private float pheromone;
	
	//Constructor 
	public Pheromone_Table(int indexLitteral, float pheromone) {
		this.indexLitteral = indexLitteral;
		this.pheromone = pheromone;
	}

	//getters and setters 
	public int getIndexLitteral() {
		return indexLitteral;
	}

	public void setIndexLitteral(int indexLitteral) {
		this.indexLitteral = indexLitteral;
	}

	public float getPheromone() {
		return pheromone;
	}

	public void setPheromone(float pheromone) {
		this.pheromone = pheromone;
	}
	
	
	
	
}
