package fourthDim;

import java.io.Serializable;
/**
 * cette classe cree un point en 4D, en 3D et en 2D
 * @author XU WEI DUO
 *
 */
public class Point4D implements Serializable{
	
	private double x;
	private double y;
	private double z;
	private double w;
	private int[] links ;
	/**
	 * constructeur qui prend en parametre les coordonnes du point en R4
	 * @param x, coordonne du point en x
	 * @param y, coordonne du point en y
	 * @param z, coordonne du point en z
	 * @param w, coordonne du point en w
	 */
	public Point4D(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/**
	 * constructeur qui prend en parametre les coordonnes du point en R3
	 * @param x, coordonne du point en x
	 * @param y, coordonne du point en y
	 * @param z, coordonne du point en z
	 */
	public Point4D(double x, double y, double z) {
		this(x,y,z,Double.NaN);
	}
	
	/**
	 * constructeur qui prend en parametre les coordonnes du point
	 * @param point, le point
	 */
	public Point4D(Point4D point) {
		this(point.getX(),point.getY(),point.getZ(),point.getW());
	}
	
	/**
	 * constructeur qui prend en parametre les coordonnes du point en R2
	 * @param x, coordonne du point en x
	 * @param y, coordonne du point en y
	 */
	public Point4D(double x, double y) {
		this(x,y,Double.NaN,Double.NaN);
	}
	
	/**
	 * constructeur qui permet `a creer un point null
	 */
	public Point4D() {
		this(Double.NaN,Double.NaN);
	}
	
	/**
	 * cette methode permet `a prendre indice d'un autre point qui doit etre relie `a celui-ci
	 * @param toLink, indice d'un autre point qui doit etre relie `a celui-ci
	 */
	public void setLink(int toLink) {
		if(links == null) {
			//System.out.println("first");
			links = new int[1];
			links[0] = toLink;
			//System.out.println(toLink+" in 0");
		}else {
			int dim = links.length;
			//System.out.println("dim is: " + dim);
			int[] linkTemp = new int[dim+1];
			for(int count=0; count<dim; count++) {
				linkTemp[count] = links[count];
			}
			linkTemp[dim] = toLink;
			links = linkTemp;
		}
	}
	
	/**
	 *  cette methode permet `a prendre une liste d'indices des points qui doivent etre relies `a celui-ci
	 * @param linksEntry,une liste d'indices des points qui doivent etre relies `a celui-ci
	 */
	public void setLink(int[] linksEntry) {
		this.links = linksEntry;
	}
	
	/**
	 * cette methode renvoit la liste d'indices des points qui doivent etre relies `a celui-ci
	 * @return, une liste d'indices des points qui doivent etre relies `a celui-ci
	 */
	public int[] getLink() {
		return links;
	}
	
	/**
	 * cette methode permet `a savoir la dimention de la liste des indices des points qui doivent etre relies `a celui-ci
	 * @return,la dimention de la liste des indices des points qui doivent etre relies `a celui-ci
	 */
	public int getLinkDim() {
		return links.length;
	}
	
	/**
	 * cette methode renvoie dans un String les informations de la liste des indices des points qui doivent etre relies `a celui-ci
	 * @return,les informations de la liste des indices des points qui doivent etre relies `a celui-ci
	 */
	public String linkToString() {
		String linksToString = "| ";
		for(int count = 0; count<links.length; count++) {
			linksToString += links[count]+" | ";
		}
		return linksToString;
	}
	
	/**
	 * cette methode renvoit la dimention non null du point
	 * @return, la dimention non null du point
	 */
	public int getDim() {
		int counter = 0;
		for(int count = 0; count<4; count++) {
			if(!Double.isNaN(this.getByNb(count))) {
				//System.out.println(this.getByNb(count) + " white " + counter);
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * cette methode renvoit la coordonne en x du point
	 * @return, la coordonne en x du point
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * cette methode renvoit la coordonne en y du point
	 * @return, la coordonne en y du point
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * cette methode renvoit la coordonne en z du point
	 * @return, la coordonne en z du point
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * cette methode renvoit la coordonne en w du point
	 * @return, la coordonne en w du point
	 */
	public double getW() {
		return w;
	}
	
	/**
	 * cette methode pemet `a avoir le coordonne du point selon une indice 
	 * @param count, indice deu coordonne du point
	 * @return, le coordonne du point selon indice
	 */
	public double getByNb(int count) {
		double value = 0;
		switch (count) {
			case 0: value= x;
				break;
			case 1: value= y;
				break;
			case 2: value= z;
				break;
			case 3: value= w;
				break;
		}
		return value;
	}
	
	/**
	 * cette methode permet `a faire redefinir le coordonne en x du point
	 * @param entry, le nouveau coordonne en x du point
	 */
	public void setX(double entry) {
		this.x = entry;
	}
	
	/**
	 * cette methode permet `a faire redefinir le coordonne en y du point
	 * @param entry, le nouveau coordonne en y du point
	 */
	public void setY(double entry) {
		this.y = entry;
	}
	
	/**
	 * cette methode permet `a faire redefinir le coordonne en z du point
	 * @param entry, le nouveau coordonne en z du point
	 */
	public void setZ(double entry) {
		this.z = entry;
	}
	
	/**
	 * cette methode permet `a faire redefinir le coordonne en w du point
	 * @param entry, le nouveau coordonne en w du point
	 */
	public void setW(double entry) {
		this.w = entry;
	}
	/**
	 * cette methode pemet `a redefinir le coordonne du point selon une indice 
	 * @param value, le nouveua coordonne du point selon une indice
	 * @param count, indice deu coordonne du point
	 */
	public void setByNb(double value, int count) {
		switch (count) {
			case 0: this.x = value;
				break;
			case 1: this.y = value;
				break;
			case 2: this.z = value;
				break;
			case 3: this.w = value;
				break;
		}
		
	}
	
	/**
	 * cette methode permet `a entrer dans un String les coordonnes du point 
	 */
	public String toString() {
		return "["+this.x +"|"+ this.y +"|"+ this.z +"|"+ this.w+"]";
	}
}
