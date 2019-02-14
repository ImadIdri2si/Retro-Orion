package obstacle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import canon.Missile;
/**
 * Cette classe permet de gerer les obstacles, soit pour les ajouter ou les supprimer
 * @author Imad Idrissi
 * @author XU WEI DUO
 */
public class GererObstacle implements Serializable {
	private ArrayList<Obstacle> obsList;
	private boolean first = true;
	private Image img;
	/**
	 * @author Imad Idrissi 
	 * Constructeur qui permet d'initialiser la array list
	 */
	public GererObstacle() {
		obsList = new ArrayList<Obstacle>();
	}
	/**
	 * @author Imad Idrissi 
	 * Constructeur qui recoit une liste d'objet
	 * @param obsList La liste d'obstacle
	 */
	public GererObstacle( ArrayList<Obstacle> obsList) {
		this.obsList = obsList;
	}
	/**
	 * @author Imad Idrissi 
	 * Mehode qui permet d'ajouter un obstacle selon la position
	 * @param positionX la position en X
	 * @param positionY la position en Y
	 */
	public void ajouterObs(int positionX, int positionY) {
		obsList.add(new Obstacle(positionX, positionY));
		
	}
	/**
	 * @author Imad Idrissi 
	 * Mehode qui permet de supprimer un obstacle selon un index
	 * @param index l'index de la liste
	 */
	public void supprimerObs(int index) {
		obsList.remove(index);
	}
	/**
	 * @author Imad Idrissi 
	 * Mehode qui permet de retourner une Array d'obstacle 
	 * @return obsList retourne la liste d'obstacle 
	 */
	public ArrayList<Obstacle> getObsList(){
		return obsList;
	}
	/**
	 * @author Imad Idrissi 
	 * Mehode qui permet de prendre une liste d'obstacle 
	 * @param obsList la liste d'obtacle 
	 */
	public void setObsList( ArrayList<Obstacle> obsList) {
		this.obsList = obsList;
	}
	/**
	 * @author Imad Idrissi 
	 * Mehode qui permet de dessiner les obstacles
	 * @param g2d permet de dessiner 
	 */
	public void dessiner(Graphics2D g2d) {
		if(first) {
			try {
				img = ImageIO.read(getClass().getClassLoader().getResource("Obstacle.PNG"));

				this.img = img.getScaledInstance(obsList.get(0).getLargeurRecH(), obsList.get(0).getLargeurRecH(), Image.SCALE_SMOOTH);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			first = false;
		}
		for(int i = 0 ; i < obsList.size(); i++) {
			obsList.get(i).dessiner(g2d);
			drawImage(g2d,i);			
		}
		
	}
	
	
	/**
	 * @author Imad Idrissi 
	 * Mehode qui retourne la taille d'array list
	 * @return la taille d'array list
	 */
	public int getSize() {
		return obsList.size();
	}
	
	/**
	 * by XU WEI DUO
	 * cette methode permet a dessiner image pour les obstacles
	 * @param g2d, contexe graphique
	 * @param i, indice de l'obstacle
	 */
	public void drawImage(Graphics2D g2d, int i) {
		int dp = (obsList.get(0).getLargeurRecH()-obsList.get(0).getLargeurRecL())/2;
		g2d.drawImage(img,obsList.get(i).getPositionX()-dp,obsList.get(i).getPositionY(),null);
	}
	
	
	/**
	 * by XU WEI DUO
	 * cette methode permet a savoir la dimention de des obstacles
	 * @param choice, indice des obstacles
	 * @return, le point qui contient les informations de la dimention
	 */
	public Point GetDim(int choice) {
		return new Point(obsList.get(choice).getHauteurRectH(),obsList.get(choice).getHauteurRecL());
	}
	
	/**
	 *  by XU WEI DUO
	 *  retourne les coordonnees du centre d'un obstacle 
	 * @param, choix d'un obstacle dans la liste 
	 * @return,retourne les coordonnees du centre
	 */
	public Point getCenter(int choice) {
		return new Point(obsList.get(choice).getPositionX()+(int)(obsList.get(choice).getHauteurRectH())/2,obsList.get(choice).getPositionY()+(int)(obsList.get(choice).getHauteurRecL())/2);
	}
	
	/**
	 *  by XU WEI DUO
	 *  permet a deplacer un obstacle a partir de son centre
	 * @param choice, choix d'un obstacle dans la liste 
	 * @param dx,le deplacement en x
	 * @param dy,le deplacement en y
	 */
	public void deplace(int choice, int dx, int dy) {
		obsList.get(choice).setPositionX(-(int)(obsList.get(choice).getHauteurRectH())/2+dx);
		obsList.get(choice).setPositionY(-(int)(obsList.get(choice).getHauteurRecL())/2+dy);
		
	}
	
	/**
	 * by XU WEI DUO
	 * cette methode permet a dessiner les point centrales des obstalces
	 * @param g2d, le contexe graphique
	 */
	public void drawCenter(Graphics2D g2d) {
		for(int count = 0; count<obsList.size();count++) {
			//obsList.get(count).setPositionX((int)getCenter(count).getX());
			//obsList.get(count).setPositionY((int)getCenter(count).getY());
			int radius = 10;
			g2d.setColor(Color.RED);
			g2d.fill(new Ellipse2D.Double((int)getCenter(count).getX()-radius/2,(int)getCenter(count).getY()-radius/2,radius,radius));
			g2d.setColor(Color.GREEN);
			g2d.fill(new Ellipse2D.Double(obsList.get(count).getPositionX()-radius/2,obsList.get(count).getPositionY()-radius/2,radius,radius));
			obsList.get(count).dessinerContour(g2d);
		}
	}
	
	/**
	 * by XU WEI DUO
	 * cette methode permet a refait la transformation a tous les obstalces
	 * @param MapFactor, la grandeur du map
	 * @param scaleFactor, le facteur de scale
	 */
	public void refactorAll(int MapFactor, int scaleFactor) {
		for(int count = 0; count<obsList.size();count++) {
			obsList.get(count).setPositionX(MapFactor*(int)getCenter(count).getX());//-(int)(obsList.get(count).getHauteurRectH())/2);
			obsList.get(count).setPositionY(MapFactor*(int)getCenter(count).getY());//-(int)(obsList.get(count).getHauteurRecL())/2);
			//setscaleAll
			obsList.get(count).modifierTaille(scaleFactor);
			//recenter
			obsList.get(count).setPositionX(obsList.get(count).getPositionX()-(int)(obsList.get(count).getHauteurRectH())/2);
			obsList.get(count).setPositionY(obsList.get(count).getPositionY()-(int)(obsList.get(count).getHauteurRecL())/2);
			
			//obsList.get(count).setPositionX(factor*obsList.get(count).getPositionX());
			//obsList.get(count).setPositionY(factor*obsList.get(count).getPositionY());
		}
		System.out.println("--------------scaling: "+obsList.get(0).getLargeurRecH());
	}
	
	/**
	public void recenter() {
		for(int count = 0; count<obsList.size();count++) {
			obsList.get(count).setPositionX(obsList.get(count).getPositionX()-(int)(obsList.get(count).getHauteurRectH())/2);
			obsList.get(count).setPositionY(obsList.get(count).getPositionY()-(int)(obsList.get(count).getHauteurRecL())/2);
		}
	}
	**/
	
	/**
	 * by XU WEI DUO
	 * permet a modifier l'echelle des obstacles 
	 * @param factor, le facteur de l'echelle
	 */
	public void setScaleAll(int factor) {
		for(int count = 0; count<obsList.size();count++) {
			//obsList.get(count).setPositionX(obsList.get(count).getHauteurRectH()/2);
			obsList.get(count).modifierTaille(factor);
			obsList.get(count).setPositionX(obsList.get(count).getPositionX()-(int)(obsList.get(count).getHauteurRectH())/2);
			obsList.get(count).setPositionY(obsList.get(count).getPositionY()-(int)(obsList.get(count).getHauteurRecL())/2);
			//obsList.get(count).setScale(factor);
		}
	}


}
