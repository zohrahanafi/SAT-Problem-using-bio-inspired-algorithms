package ACS;


public class BuildSolution 
{
	private int indexLitteral;
	private float proba;
	
	public BuildSolution(int indexLitteral,float proba)
	{
		this.indexLitteral = indexLitteral;
		this.proba = proba;
	}

	public int getIndexLitteral() {
		return indexLitteral;
	}

	public void setIndexLitteral(int indexLitteral) {
		this.indexLitteral = indexLitteral;
	}

	public float getProba() {
		return proba;
	}

	public void setProba(int proba) {
		this.proba = proba;
	}
	
	
}
