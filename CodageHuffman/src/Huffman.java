import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Huffman {
	
	public static double tauxCompression(File fichier, String codeF) { //Fonction qui calcul le taux de compression
		
		double volInit = fichier.length(); //Poids initial en octet du fichier texte
		double volFin = codeF.length() / 8; //Le nombre de bits de notre code divisé par 8 pour l'avoir en octet
		double txCompression = 1 - (volFin/volInit);
		
		return txCompression;
	}
	
	public static double  nbMoyenBit(Texte txt, String codeF, ArbreB arbre) { //Nombre moyen de bits de stockage d’un caractère du texte compressé
		
		double nbBit = calculCodeBinCarac(arbre); //Nombre de bits du code comprenant une fois le code binaire de chaque caractère
		double nbCarac = arbre.getListeNCarac().size(); //Nombre de caractères de l'alphabet du texte
		double nbMoyen = nbBit/nbCarac;
		
		return nbMoyen;
	}
	
	public static double calculCodeBinCarac(ArbreB arbre) {  //Fonction qui calcule la longueur du code comprenant une fois le code binaire de chaque caractère
		
		double codeBinCarac = 0; //Permet de comptabiliser la longueur du code comprenant une fois le code binaire de chaque caractère
		String[] codes = arbre.createCode(arbre.getRacine());
		
		for (int i = 0; i < codes.length; i++) { //Pour tous les codes des caractères
			
			if(codes[i] != null)
				codeBinCarac += codes[i].length();
		}
		
		return codeBinCarac;
	}
	
	public static String codeBinaire(Texte txt, ArbreB arbre) { //Calcul du code binaire
		
		
		String codeComplet = ""; //Le code binaire de notre fichier
		String texteMin = txt.getTexte().toLowerCase(); 
        char[] chars = texteMin.toCharArray(); 
        String[] codes = arbre.createCode(arbre.getRacine());
        
        for (int l = 0; l < texteMin.length(); l++) { //Pour chaque lettre du texte
        	for (int i = 0; i < codes.length; i++) { //Pour tous les codes des caractères
        		
        		if((char) (i) == chars[l] && codes[i] != null) { //On retrouve le code associé à la lettre
        			String code = codes[i];
        			codeComplet += code; //On l'ajoute au code complet
        		}
        	}
        	
        }
		return codeComplet;
	}

	 public static void main(String [] args) throws FileNotFoundException {
		 
		 Texte txt = new Texte("textesimple"); //Création d'un texte correspondant à un fichier texte
		 File fileTexte = new File("textesimple.txt"); //Création d'un File du même fichier pour avoir son poids
		 
		 HashMap<Character, Integer> caracNfreq = txt.analyse_text(); //HashMap permettant de lier un caractère avec son nombre d'apparition
		 
		 ArbreB arbre = new ArbreB(); //Création de l'arbre binaire
		 
		 for (char i : caracNfreq.keySet()) { //Pour tous les caractères dans notre HashMap
			 
			 NoeudB noeud = new NoeudB(i, caracNfreq.get(i)); //On crée les noeuds feuilles pour les caractères rescensés dans le texte
			 arbre.ajoutListeN(noeud); //On l'ajoute à la liste des noeuds

	       	}
		 
		 arbre.creationArbre();
	 
		 for(NoeudB n : arbre.getListeNATraiter()) {
			 
			 arbre.setRacine(n); //Lorsqu'il n'y a plus qu'un noeud, c'est forcément la racine de l'arbre
		 }
		 
		 String[] codes = arbre.createCode(arbre.getRacine()); //On crée la liste répertoriant les code binaire des caractères
		 
		 System.out.println("Code binaire : " + codeBinaire(txt, arbre));
		 
		 for (int i = 0; i < codes.length; i++) { //Pour tous les caractères
			 
			 if(codes[i] != null) //Si ce sont des caractères présents dans le texte
			   System.out.println((char) (i) + " : " + codes[i]); //On les affiche avec leur code binaire
		 }
		 
		 System.out.println("Taux de compression : " + tauxCompression(fileTexte, (codeBinaire(txt, arbre))));
		 System.out.println("Nombre moyen de bits de stockage d’un caractère : " + nbMoyenBit(txt, codeBinaire(txt, arbre), arbre));
		 
		 
		 //Création des fichiers
		 try{
		 PrintWriter writer1 = new PrintWriter("textesimple_comp.bin", "UTF-8"); //On crée un fichier dont le nom est "textesimple_comp.bin" enb UTF-8
		 writer1.println(codeBinaire(txt, arbre)); //La première ligne correspond au code binaire du texte comprimé
		 writer1.close(); //On ferme le PrintWriter
		 
		 PrintWriter writerFreq1 = new PrintWriter("textesimple_freq.txt", "UTF-8");
		 writerFreq1.println("Taille de l'alphabet : " + arbre.getListeNCarac().size()); //Nombre de caractères différents dans le texte
		 
		 for(NoeudB n : arbre.getListeNCarac()) { //Pour tous ces caractères
			 writerFreq1.println(n.getCarac()+ " "+n.getFreq()); //On écrit le caractère suivis de sa fréquence
		 }
		 
		 writerFreq1.close();
		 		 
		 }
		    catch (IOException e){
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	 }
}
