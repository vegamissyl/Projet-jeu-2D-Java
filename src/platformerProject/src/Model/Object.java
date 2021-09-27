package Model;

// Classe m�re de tous les objets de l'environnement du jeu
public class Object {
		//dimension de l'objet (largeur et haiteur)
		private int width;
		private int height;
		
		//position de l'objet
		private int x;
		private int y;
		
		//Constructeur 
		public Object(int _x, int _y, int _width, int _height) {
			
			this.x = _x;
			this.y = _y;
			this.width = _width;
			this.height = _height;
		}
		
		///getters///
		public int getWidth() {return width;}

		public int getHeight() {return height;}

		public int getX() {return x;}

		public int getY() {return y;}
		
		///setters///
		public void setWidth(int width) {this.width = width;}
		
		public void setHeight(int height) {this.height = height;}
		
		public void setX(int x) {this.x = x;}
		
		public void setY(int y) {this.y = y;}

		// autres methodes
		
		// Mouvement relatif au d�placement du personnage
		public void mouvement() {
			if (View.PanelGame.getxPos() >= 0) {
				this.x = this.x - View.PanelGame.getDx();
			}
		}
}


