
public class Arc {

		private int code; //0 ou 1 en fonction de si c'est le fils gauche ou droit
		private NoeudB Ndepart, Narrivee; //Le noeud de départ est le noeud "en bas" dans l'arbre
		
		public Arc(int code, NoeudB ndepart, NoeudB narrivee) {
			super();
			this.code = code;
			this.Ndepart = ndepart;
			this.Narrivee = narrivee;
		}

		public int getCode() {
			return code;
		}

		public NoeudB getNdepart() {
			return Ndepart;
		}

		public NoeudB getNarrivee() {
			return Narrivee;
		}
		
		
		
}
