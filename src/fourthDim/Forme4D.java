package fourthDim;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * cette classe cree une forme en 4D, en 3D et en 2D
 * @author XU WEI DUO
 *
 */
public class Forme4D implements Serializable{
	Point4D[] forme;
	/**
	 * constructeur qui prend en paramettre une liste de Point en R4
	 * @param entry, entree de point R4
	 */
	public Forme4D(Point4D[] entry) {
		this.forme = entry;
	}
	/**
	 * constructeur qui prend en paramettre un point en 4D
	 * @param x, coordonne en x du point 4D
	 * @param y, coordonne en y du point 4D
	 * @param z, coordonne en z du point 4D
	 * @param w, coordonne en w du point 4D
	 */
	public Forme4D(double x, double y, double z, double w) {
		this.forme = new Point4D[] {new Point4D(x,y,z,w)};;
	}
	/**
	 * constructeur qui prend en parametre le choix de la forme 4D predefini
	 * @param choice,le choix de la forme 4D predefini
	 */
	public Forme4D(int choice) {
		switch(choice) {
		
			case 1: 
				this.forme = new Point4D[] {new Point4D(0,0,0,0), new Point4D(0,1,0,0), new Point4D(1,1,0,0), new Point4D(1,0,0,0),new Point4D(0,0,1,0), new Point4D(0,1,1,0), new Point4D(1,1,1,0), new Point4D(1,0,1,0)};
				this.forme[0].setLink(1);
				this.forme[1].setLink(2);
				this.forme[2].setLink(3);
				this.forme[3].setLink(0);
				
				this.forme[4].setLink(5);
				this.forme[5].setLink(6);
				this.forme[6].setLink(7);
				this.forme[7].setLink(4);
				
				this.forme[0].setLink(4);
				//System.out.println(forme[0].toString() +" linked with "+ forme[4].toString());
				this.forme[1].setLink(5);
				this.forme[2].setLink(6);
				this.forme[3].setLink(7);
				/**
				for(int a = 0; a<forme.length; a++) {
					int scale = 1;
					forme[a].setX(forme[a].getX()+scale);
					forme[a].setY(forme[a].getY()+scale);
					forme[a].setZ(forme[a].getZ()+scale);
					forme[a].setW(forme[a].getW()+scale);
				}
				**/
				break;
			case 2:
				this.forme = new Point4D[] {new Point4D(0,0,0,0), new Point4D(0,1,0,0), new Point4D(1,1,0,0), new Point4D(1,0,0,0),new Point4D(0.5,0.5,1,0)};
				this.forme[0].setLink(1);
				this.forme[1].setLink(2);
				this.forme[2].setLink(3);
				this.forme[3].setLink(0);
				
				this.forme[0].setLink(4);
				this.forme[1].setLink(4);
				this.forme[2].setLink(4);
				this.forme[3].setLink(4);
				break;
			case 3:
				this.forme = new Point4D[] {new Point4D(0,0,0,0), new Point4D(0,1,0,0), new Point4D(1,1,0,0), new Point4D(1,0,0,0),new Point4D(0,0,1,0), new Point4D(0,1,1,0), new Point4D(1,1,1,0), new Point4D(1,0,1,0), new Point4D(0.5,0.5,1.5,0)};
				this.forme[0].setLink(1);
				this.forme[1].setLink(2);
				this.forme[2].setLink(3);
				this.forme[3].setLink(0);
				
				this.forme[4].setLink(5);
				this.forme[5].setLink(6);
				this.forme[6].setLink(7);
				this.forme[7].setLink(4);
				
				this.forme[0].setLink(4);
				//System.out.println(forme[0].toString() +" linked with "+ forme[4].toString());
				this.forme[1].setLink(5);
				this.forme[2].setLink(6);
				this.forme[3].setLink(7);
				
				this.forme[8].setLink(4);
				this.forme[8].setLink(5);
				this.forme[8].setLink(6);
				this.forme[8].setLink(7);
				break;
			case 99: this.forme =  new Point4D[] {new Point4D(0,0,0,0),new Point4D(1,0,0,0),new Point4D(1,1,0,0),new Point4D(0,1,0,0),new Point4D(0,0,0,0),new Point4D(0,0,0,1),new Point4D(1,0,0,1),new Point4D(1,1,0,1),new Point4D(0,1,0,1),new Point4D(0,0,0,1),new Point4D(0,0,1,1),new Point4D(0,1,1,1),new Point4D(0,1,0,1),new Point4D(0,1,0,0),new Point4D(0,1,1,0),new Point4D(1,1,1,0),new Point4D(1,0,1,0),new Point4D(0,0,1,0),new Point4D(0,1,1,0),new Point4D(0,1,1,1),new Point4D(1,1,1,1),new Point4D(1,0,1,1),new Point4D(1,0,1,0),new Point4D(1,0,0,0),new Point4D(1,0,0,1),new Point4D(1,0,1,1),new Point4D(0,0,1,1),new Point4D(0,0,1,0),new Point4D(0,0,0,0),new Point4D(1,0,0,0),new Point4D(1,1,0,0),new Point4D(1,1,1,0),new Point4D(1,1,1,1),new Point4D(1,1,0,1),new Point4D(1,1,0,0)};
				break;
			
			case 98: this.forme =  new Point4D[] {new Point4D(0,2,0,0),new Point4D(2,0,0,0),new Point4D(0,0,0,0),new Point4D(0,2,0,0),new Point4D(1,1,1,0),new Point4D(1,1,1,1),new Point4D(0,0,0,1),new Point4D(0,0,0,0),new Point4D(1,1,1,0),new Point4D(2,0,0,0),new Point4D(2,0,0,1),new Point4D(1,1,1,1),new Point4D(0,2,0,1),new Point4D(2,0,0,1),new Point4D(0,0,0,1),new Point4D(0,2,0,1),new Point4D(0,2,0,0)};
				break;
		}
	}
	
	/**
	 * cette methode permet `a enter un nouveua point dans la forme
	 * @param newPoint, un nouveua point
	 */
	public void setPoint(Point4D newPoint) {
		int dim = forme.length;
		Point4D[] formeTemp = new Point4D[dim+1];
		for(int count = 0; count<dim; count++) {
			formeTemp[count] = forme[count];
		}
		formeTemp[dim] = newPoint;
		forme = formeTemp;
	}
	
	/**
	 * cette methode permet `a ajouter un arrete `a un point avec un autre point
	 * @param p1, le point de depart
	 * @param p2, le point d'arrive
	 */
	public void setLink(int p1, int p2) {
		forme[p1].setLink(p2);
		//System.out.println("linked " + p1 + " by " + p2);
	}
	
	/**
	 * cette methode renvoie tous les arretes de la forme4D
	 * @return, retournne tous les arretes de la forme4D
	 */
	public int[][] getAllLinks(){
		int[][] extLink = new int[forme.length][];
		int[] links;
		for(int alpha = 0; alpha<forme.length; alpha++) {
			if(forme[alpha].getLink()!=null) {
				extLink[alpha] = new int[forme[alpha].getLinkDim()];
				links = forme[alpha].getLink();
				for(int beta = 0; beta < forme[alpha].getLinkDim(); beta++) {
					extLink[alpha][beta] = links[beta];
				}
			}
			
		}
		return extLink;
	}
	
	/**
	 * cette methode permet a redefinir tous les arretes de la forme4D
	 * @param links, les arretes de la forme4D
	 */
	public void setAllLinks(int[][] links) {
		for(int alpha = 0; alpha<forme.length; alpha++) {
			if(links[alpha]!=null) {
				for(int beta = 0; beta < links[alpha].length; beta++) {
					forme[alpha].setLink(links[alpha][beta]);
				}
			}
			
		}
	}
	
	/**
	 * cette methode permet `a diminuter la dimension de la forme de 4D `a 3D en eliminant les points en 4D
	 */
	public void dimentionDown() {
		int dim = forme.length/2;
		Point4D[] formeTemp = new Point4D[dim];
		for(int count = 0; count<dim; count++) {
			Point4D point = new Point4D(forme[count]);
			int[] links = forme[count].getLink();
			point.setLink(links);
			formeTemp[count] = point;
		}
		this.forme = formeTemp;
		
		
	}
	
	/**
	 * cette methode permet `a augmenter la dimension de la forme de 3D `a 4D en aoutant des points en 4D
	 * @param entry, le coordonne de point w dans R4
	 */
	public void dimentionUp(double entry) {
		int dim = forme.length;
		Point4D[] formeTemp = new Point4D[dim];
		for(int count = 0; count<dim; count++) {
			Point4D point = new Point4D(forme[count]);
			int[] links = forme[count].getLink();
			point.setLink(links);
			formeTemp[count] = point;
		}
		//System.out.println(this.toString());
		for(int count = 0; count<formeTemp.length; count++) {
			formeTemp[count].setW(entry);
			//System.out.print(forme[count].getW()+" ");
		}
		//System.out.println(toString());
		this.formeFusion(formeTemp);	
		//System.out.println(toString());
	}
	
	/**
	 * cette methode permet `a fusionner la partie de la forme ayant R3 et la partie de la forme ayant R4
	 * @param formeFus, la forme `a fusionner
	 */
	public void formeFusion(Point4D[] formeFus) {
		int dim = forme.length;
		
		Point4D[] formeTemp = new Point4D[2*dim];
		for(int count = 0; count < dim; count++) {
			formeTemp[count] = forme[count];
			formeTemp[dim+count] = formeFus[count];
			
		}
		this.forme = formeTemp;
		/**
		for(int count = 0; count < 2*dim; count++) {
			System.out.println(forme[count].getW());
		}
		**/
	}
	
	
	/**
	 * cette methode retourne la matrix de la forme 4D
	 * @return,la matrix de la forme 4D
	 */
	public Point4D[] get4DMatrix(){
		return forme;
	}
	
	/**
	 * cette methode permet `a deplacer la forme complet selon un indice de deplacement
	 * @param dp, indice de deplacement
	 */
	public void deplacement(double dp) {
		for(int count = 0; count<forme.length; count++) {
			for(int beta = 0; beta<forme[0].getDim(); beta++) {
				forme[count].setByNb(forme[count].getByNb(beta)+dp,beta);
			}
		}
		
	}
	
	/**
	 * cette methode permet `a cloner une forme en 4D
	 * @return,clone de la forme en 4D
	 */
	public Point4D[] clone() {
		Point4D[] formeTemp = new Point4D[forme.length];
		//int dim = forme.length;
		for(int count = 0; count<forme.length; count++) {
		
			Point4D point = new Point4D(forme[count]);
			int[] links = forme[count].getLink();
			point.setLink(links);
			formeTemp[count] = point;	
			
		}
		return formeTemp;
		
	}
	
	/**
	 * cette methode permet `a enter tous informations de la matrix de la forme 4D dans une matrix d'information
	 * @return, la matrix d'information
	 */
	public String toString() {
		double dim = forme.length;
		String matrix = "";
		for(int alpha = 0; alpha<forme[0].getDim(); alpha++) {
			matrix += "\n";
			for(int beta = 0; beta<dim; beta++) {
				//if(!Double.isNaN(forme[beta].getByNb(alpha))) {
					matrix += "["+forme[beta].getByNb(alpha)+"]";
				//}
			}
		}
		return matrix;
	}
}
