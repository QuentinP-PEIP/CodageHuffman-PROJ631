import java.util.ArrayList;

public class ArbreB {
	
	private NoeudB racine; //Racine de l'arbre
	private ArrayList<NoeudB> listeNATraiter = new ArrayList<NoeudB>(); //Liste des noeuds à traiter, on ajoute ceux créés et on enlèves ceux traités
	private ArrayList<NoeudB> listeN = new ArrayList<NoeudB>(); //Liste des noeuds
	private ArrayList<NoeudB> listeNCarac = new ArrayList<NoeudB>(); //Liste des noeuds feuilles
	private ArrayList<Arc> listeA = new ArrayList<Arc>(); //Liste des arcs
	
	public ArrayList<NoeudB> getListeNCarac() {
		return listeNCarac;
	}

	public ArrayList<NoeudB> getListeN() {
		return listeN;
	}
	

	public ArrayList<Arc> getListeA() {
		return listeA;
	}

	public ArrayList<NoeudB> getListeNATraiter() {
		return listeNATraiter;
	}

	public void setRacine(NoeudB racine) {
		this.racine = racine;
	}
	
	public NoeudB getRacine() {
		return racine;
	}
	
	public void ajoutListeN(NoeudB noeud) { //Ajoute un noeuds aux différentes listes
		
		listeNATraiter.add(noeud);
		listeN.add(noeud);
		listeNCarac.add(noeud);		
	}
	
	public void enleverNoeud(NoeudB[] couple) { //Permet d'enlever les noeuds traités
		
		listeNATraiter.remove(couple[0]);
		listeNATraiter.remove(couple[1]);
		
	}
	

	public NoeudB[] calculFreqMin() { //Calcul de la fréquence minimale (somme de deux noeuds)
		 
		int freqMin = (listeNATraiter.get(0).getFreq()+listeNATraiter.get(1).getFreq()); //On l'initialise avec les deux premiers noeuds
		NoeudB nMin1 = listeNATraiter.get(0);
		NoeudB nMin2 = listeNATraiter.get(1);
		
		for(NoeudB n1 : this.getListeNATraiter()) { //On test toutes les sommes de fréquences
			 
			for(NoeudB n2 : this.getListeNATraiter()) {
				 
				if((n1.getFreq()+n2.getFreq())<freqMin && n1 != n2) { //Si la fréquence calculé est inférieure à l'actuelle et qu'on fait bien la somme de deux noeuds différents
					
					freqMin = (n1.getFreq()+n2.getFreq()); //La fréqunce min correspond à la somme des deux noeuds
					nMin1 = n1;
					nMin2 = n2;
				}
				
			 }
		 }
		
		NoeudB[] coupleN = new NoeudB[2]; //On créé un couple de noeuds afin d'identifier le fils gauche et le droit
		coupleN[0] = nMin1;
		coupleN[1] = nMin2;
		
		return coupleN;
		
	}
	

	public void ajouterNoeud(NoeudB[] couple) { //Permet d'ajouter un noeud à l'arbre à parmir des deux noeuds "de base" 
		
		NoeudB noeud = new NoeudB(couple[0].getFreq()+couple[1].getFreq(), couple[0], couple[1]); //La fréquence du nouveau noeud est la somme de ses deux fils
		Arc arcG = new Arc(0, couple[0], noeud); //L'arc entre le noeud et son fils gauche correspond au code 0
		Arc arcD = new Arc(1, couple[1], noeud); //celui du fils droit au code 1
		listeA.add(arcG); //On ajoute les arcs à la liste
		listeA.add(arcD);
		listeNATraiter.add(noeud); //Le nouveau créé doit être traitable
		listeN.add(noeud);
		
		
	}
	
	public void creationArbre() { //Fonction qui va créer l'arbre
		
		while(listeNATraiter.size() > 1) { //Tant que qu'il y a plus d'un noeud à traiter
			
			NoeudB[] coupleN = this.calculFreqMin(); //On détermine le couple de noeuds avec la fréquence la plus faible
			this.ajouterNoeud(coupleN); //On ajoute le noeud
			this.enleverNoeud(coupleN); //On l'enleve les autres
		}
		
	}
	
	
	public void parcoursArbre(NoeudB racine, String code, String [] codes) { //Fonction qui va parcourir l'arbre, fonction récursive qui s'appelle sur un noeud, le but est de modifier le code et de l'attribuer au caractère correspondant dans les codes.
		
			if (racine.getLeftChild() == null && racine.getRightChild() == null) { //Si le noeud en question est une feuille
				
				codes[racine.getCarac()] = code; //Le code trouvé est assigné au caractère
				
			}
			
			else {

			this.parcoursArbre(racine.getLeftChild(), code +"0", codes); //Si on fait l'appel récursif sur le fils gauche, on ajout 0 au code
			this.parcoursArbre(racine.getRightChild(), code +"1", codes); //si c'est sur le fils droit, on ajoute 1
			
			}
		
	}
	
	public String[] createCode(NoeudB racine) { //Fonction qui crée la liste répertoriant les code binaire des caractères
		
		 String[] codes = new String[255]; //Une liste de String correspondant aux différents caractères ASCII
		 parcoursArbre(racine, "", codes); //On commence le parcours de l'arbre par la racine
		 return codes; //On retourne la liste des caractères avec leur code
		}
	
}
