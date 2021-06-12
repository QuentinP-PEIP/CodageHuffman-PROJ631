import java.util.ArrayList;

public class ArbreB {
	
	private NoeudB racine; //Racine de l'arbre
	private ArrayList<NoeudB> listeNATraiter = new ArrayList<NoeudB>(); //Liste des noeuds � traiter, on ajoute ceux cr��s et on enl�ves ceux trait�s
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
	
	public void ajoutListeN(NoeudB noeud) { //Ajoute un noeuds aux diff�rentes listes
		
		listeNATraiter.add(noeud);
		listeN.add(noeud);
		listeNCarac.add(noeud);		
	}
	
	public void enleverNoeud(NoeudB[] couple) { //Permet d'enlever les noeuds trait�s
		
		listeNATraiter.remove(couple[0]);
		listeNATraiter.remove(couple[1]);
		
	}
	

	public NoeudB[] calculFreqMin() { //Calcul de la fr�quence minimale (somme de deux noeuds)
		 
		int freqMin = (listeNATraiter.get(0).getFreq()+listeNATraiter.get(1).getFreq()); //On l'initialise avec les deux premiers noeuds
		NoeudB nMin1 = listeNATraiter.get(0);
		NoeudB nMin2 = listeNATraiter.get(1);
		
		for(NoeudB n1 : this.getListeNATraiter()) { //On test toutes les sommes de fr�quences
			 
			for(NoeudB n2 : this.getListeNATraiter()) {
				 
				if((n1.getFreq()+n2.getFreq())<freqMin && n1 != n2) { //Si la fr�quence calcul� est inf�rieure � l'actuelle et qu'on fait bien la somme de deux noeuds diff�rents
					
					freqMin = (n1.getFreq()+n2.getFreq()); //La fr�qunce min correspond � la somme des deux noeuds
					nMin1 = n1;
					nMin2 = n2;
				}
				
			 }
		 }
		
		NoeudB[] coupleN = new NoeudB[2]; //On cr�� un couple de noeuds afin d'identifier le fils gauche et le droit
		coupleN[0] = nMin1;
		coupleN[1] = nMin2;
		
		return coupleN;
		
	}
	

	public void ajouterNoeud(NoeudB[] couple) { //Permet d'ajouter un noeud � l'arbre � parmir des deux noeuds "de base" 
		
		NoeudB noeud = new NoeudB(couple[0].getFreq()+couple[1].getFreq(), couple[0], couple[1]); //La fr�quence du nouveau noeud est la somme de ses deux fils
		Arc arcG = new Arc(0, couple[0], noeud); //L'arc entre le noeud et son fils gauche correspond au code 0
		Arc arcD = new Arc(1, couple[1], noeud); //celui du fils droit au code 1
		listeA.add(arcG); //On ajoute les arcs � la liste
		listeA.add(arcD);
		listeNATraiter.add(noeud); //Le nouveau cr�� doit �tre traitable
		listeN.add(noeud);
		
		
	}
	
	public void creationArbre() { //Fonction qui va cr�er l'arbre
		
		while(listeNATraiter.size() > 1) { //Tant que qu'il y a plus d'un noeud � traiter
			
			NoeudB[] coupleN = this.calculFreqMin(); //On d�termine le couple de noeuds avec la fr�quence la plus faible
			this.ajouterNoeud(coupleN); //On ajoute le noeud
			this.enleverNoeud(coupleN); //On l'enleve les autres
		}
		
	}
	
	
	public void parcoursArbre(NoeudB racine, String code, String [] codes) { //Fonction qui va parcourir l'arbre, fonction r�cursive qui s'appelle sur un noeud, le but est de modifier le code et de l'attribuer au caract�re correspondant dans les codes.
		
			if (racine.getLeftChild() == null && racine.getRightChild() == null) { //Si le noeud en question est une feuille
				
				codes[racine.getCarac()] = code; //Le code trouv� est assign� au caract�re
				
			}
			
			else {

			this.parcoursArbre(racine.getLeftChild(), code +"0", codes); //Si on fait l'appel r�cursif sur le fils gauche, on ajout 0 au code
			this.parcoursArbre(racine.getRightChild(), code +"1", codes); //si c'est sur le fils droit, on ajoute 1
			
			}
		
	}
	
	public String[] createCode(NoeudB racine) { //Fonction qui cr�e la liste r�pertoriant les code binaire des caract�res
		
		 String[] codes = new String[255]; //Une liste de String correspondant aux diff�rents caract�res ASCII
		 parcoursArbre(racine, "", codes); //On commence le parcours de l'arbre par la racine
		 return codes; //On retourne la liste des caract�res avec leur code
		}
	
}
