

public class NoeudB {
	
	private char carac; //Correspond à la lettre
	private int freq; //Le nombre d'apparition
	private NoeudB leftChild, rightChild;
	
	//Nous utilisons deux constructeurs différents
	
	public NoeudB (int freq, NoeudB leftChild, NoeudB rightChild) { //Un pour les noeuds créés
		
		this.freq = freq;
		this.leftChild = leftChild ;
		this.rightChild = rightChild;
	}
	
	public NoeudB (char carac, int freq ) { //L'autre nous les noeuds feuilles correspondant aux caractères
		
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
