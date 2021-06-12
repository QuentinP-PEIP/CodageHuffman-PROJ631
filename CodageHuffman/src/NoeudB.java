

public class NoeudB {
	
	private char carac; //Correspond � la lettre
	private int freq; //Le nombre d'apparition
	private NoeudB leftChild, rightChild;
	
	//Nous utilisons deux constructeurs diff�rents
	
	public NoeudB (int freq, NoeudB leftChild, NoeudB rightChild) { //Un pour les noeuds cr��s
		
		this.freq = freq;
		this.leftChild = leftChild ;
		this.rightChild = rightChild;
	}
	
	public NoeudB (char carac, int freq ) { //L'autre nous les noeuds feuilles correspondant aux caract�res
		
		this.carac = carac;
		this.freq = freq;
	}
	

	public char getCarac() {
		return carac;
	}

	public int getFreq() {
		return freq;
	}

	public NoeudB getLeftChild() {
		return leftChild;
	}

	public NoeudB getRightChild() {
		return rightChild;
	}
	
	
}
