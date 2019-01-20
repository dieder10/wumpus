package tipos;

public class InitialOrder {

	private int size;
	private int nHoles;
	private int nArrows;
	
	public InitialOrder(int size, int nHoles, int nArrows) {
		this.size = size;
		this.nHoles = nHoles;
		this.nArrows = nArrows;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getnHoles() {
		return nHoles;
	}

	public void setnHoles(int nHoles) {
		this.nHoles = nHoles;
	}

	public int getnArrows() {
		return nArrows;
	}

	public void setnArrows(int nArrows) {
		this.nArrows = nArrows;
	}
	
	
}
