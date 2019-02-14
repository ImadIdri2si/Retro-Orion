package obstacle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

import canon.Dessinable;
import canon.Missile;
import vecteur.Vecteur;
/**
 * Classe obstacle permet de creer les obstacles afin de les affichers sur l'application
 * @author Imad Idrissi 
 *
 */

public class Obstacle implements Dessinable, Serializable  {
	private int positionX, positionY; //position de l'obstacle en X et Y
	// les mesures du rectangle hauteur, sa largeur et sa hauteur
	private int hauteurRecL = 40, largeurRecL = hauteurRecL-20; // les mesures du rectangle hauteur, sa largeur et sa hauteur
	private int hauteurRectH = largeurRecL,largeurRectH = hauteurRecL; // les mesures du rectangle largeur, sa largeur et sa hauteur
	private int diametre = Math.abs(hauteurRecL - largeurRecL);  // le rayon des cercles qui vont etre utilisés pour réaliser les coins de l'obstacle
	private Rectangle2D recHauteur, recLargeur; //Creation du rectancleHauteur, celui-ci possede une hauteur plus grande que la hauteur du rectangleLargeur, et ainsi de suite pour le rectangleLargeur
	private Ellipse2D coinHautGauche, coinHautDroite, coinBasGauche, coinBasDroite; // Creation des cercles pour les utiliser comme coin dans l'obstacle
	private Ellipse2D cercleDetection;
	private int rayonCercleDetection = 100;
	private boolean etat = false;
	private RoundRectangle2D rect;
	private ArrayList<Ellipse2D> list;
	
	/**
	 * Constructeur permet d'initialiser les positions en X et Y
	 * @param positionX La position en X
	 * @param positionY La position en Y
	 */
	public Obstacle(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		
	}
	
	/**
	 * Constructeur permet d'initialiser les positions en X et Y par un vecteur position
	 * @param position Vecteur position
	 */
	public Obstacle(Vecteur position) {
		this.positionX = (int)position.getX();
		this.positionY = (int)position.getY();
	}
	/**
	 * Méthode permet de creer un obstacle
	 * @param g2d La composante g2d permet de le dessiner sur le panel
	 */
	public void dessiner(Graphics2D g2d) {
		recHauteur =  new Rectangle2D.Double(positionX, positionY, largeurRecL, hauteurRecL);
		recLargeur = new Rectangle2D.Double(positionX-((largeurRectH/2)-(largeurRecL/2)), positionY+((hauteurRecL/2)-(hauteurRectH/2)), largeurRectH , hauteurRectH );
		coinHautGauche = new Ellipse2D.Double((positionX-(diametre/2)),positionY, diametre, diametre);
		coinHautDroite = new Ellipse2D.Double(positionX-(diametre/2),positionY+hauteurRectH, diametre, diametre);
		coinBasGauche = new Ellipse2D.Double(positionX-(diametre/2)+largeurRecL,positionY+hauteurRectH, diametre, diametre);
		coinBasDroite = new Ellipse2D.Double(positionX-(diametre/2)+largeurRecL,positionY, diametre, diametre);
		rect = new RoundRectangle2D.Double((positionX-(diametre/2)),positionY,largeurRectH,hauteurRecL,diametre,diametre);
		cercleDetection = new Ellipse2D.Double((positionX -(rayonCercleDetection/2))-((largeurRectH-largeurRecL)/2), (positionY -(rayonCercleDetection/2)), hauteurRecL+rayonCercleDetection, hauteurRecL + rayonCercleDetection);	
	}
	
	/**
	 * Méthode permet dessiner un obstacle
	 * @param g2d La composante g2d permet de le dessiner sur le panel
	 */
	public void dessinerContour(Graphics2D g2d) {
		g2d.draw(recHauteur);
		g2d.draw(recLargeur);
		g2d.draw(coinHautGauche);
		g2d.draw(coinHautDroite);
		g2d.draw(coinBasGauche);
		g2d.draw(coinBasDroite);
		g2d.draw(cercleDetection);
	}
	/**
	 * Méthode retourne le vecteur position du coin de l'obstacle 
	 * @return Vecteur position coin haut gauche
	 */
	public Vecteur getPositionCoinHautGauche() {
		return new Vecteur(positionX-(diametre/2),positionY);
	}
	/**
	 * Méthode retourne le vecteur position du coin de l'obstacle 
	 * @return Vecteur position coin haut droite
	 */
	public Vecteur getPositionCoinHautDroite() {
		return new Vecteur(positionX-(diametre/2),positionY+hauteurRectH);
	}
	/**
	 * Méthode retourne le vecteur position du coin de l'obstacle 
	 * @return Vecteur position coin bas gauche
	 */
	public Vecteur getPositionCoinBasGauche() {
		return new Vecteur(positionX-(diametre/2)+largeurRecL,positionY+hauteurRectH);
	}
	/**
	 * Méthode retourne le vecteur position du coin de l'obstacle 
	 * @return Vecteur position coin bas droite
	 */
	public Vecteur getPositionCoinBasDroite() {
		return new Vecteur(positionX-(diametre/2)+largeurRecL,positionY);
	}
	
	/**
	 * M�thode permet de changer la position X de l'obstacle
	 * @param positionX la nouvelle position en X
	 */
	public void setPositionX(int positionX) {
		this.positionX =  positionX;
	}
	/**
	 * M�thode permet de changer la position Y de l'obstacle
	 * @param positionY la nouvelle position en Y
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	/**
	 * M�thode permet de changer la position Y et X avec le vecteur position
	 * @param position Vecteur des nouvelles position
	 */
	public void setPosition(Vecteur position){
		this.positionX = (int)position.getX();
		this.positionY = (int)position.getY();
	}
	/**
	 * M�thodes qui permet de retourner la largeur du rectangleL
	 * @return largeurRecL
	 */
	public int getLargeurRecL() {
		return largeurRecL;
	}
	/**
	 * M�thodes qui permet de retourner la largeur du rectangleH
	 * @return largeurRectH

	 */
	public int getLargeurRecH() {
		return largeurRectH;
	}
	/**
	 *  M�thodes qui permet de retourner la hauteur du rectangleL
	 * @return hauteurRecL
	 */
	public int getHauteurRecL() {
		return hauteurRecL;
	}
	/**
	 * M�thodes qui permet de retourner la hauteur du rectangleH
	 * @return Hauteur rectangle hauteur 
	 */
	public int getHauteurRectH() {
		return hauteurRectH;
	}
	/**
	 * M�thodes qui permet de retourner la position  en Y
	 * @return positionY
	 */
	public int getPositionY() {
		return positionY;
	}
	/**
	 * M�thodes qui permet de retourner la position  en X
	 * @return positionX
	 */
	public int getPositionX() {
		return positionX;
	}
	/**
	 * M�thode qui retourne la position en largeur en X
	 * @return positionX
	 */
	public int getPositionLargeurX() {
		return positionX-((largeurRectH/2)-(largeurRecL/2));
	}
	/**
	 *  M�thode qui retourne la position en largeur en Y
	 * @return position Y
	 */
	public int getPositionLargeurY() {
		return positionY+((hauteurRecL/2)-(hauteurRectH/2));
	}
	/**
	 * M�thode qui permet d'avoir la position centrale de la zone 
	 * @return Un vecteur position 
	 */
	public Vecteur getPositionCercleZone() {
		return new Vecteur(((positionX -(rayonCercleDetection/2))-((largeurRectH-largeurRecL)/2)),(positionY -(rayonCercleDetection/2)));
	}
	/**
	 * M�thode qui permet de retrouner le rayon de la zone
	 * @return rayonDeLaZone
	 */
	public int getRayonCercleZone() {
		return (hauteurRecL+rayonCercleDetection)/2;
	}
	/**
	 * M�thode qui permet de savoir si un objet est dans la zone d'obstacle 
	 * @param etat
	 */
	public void setZoneEtat(boolean etat) {
		this.etat = etat;
	}
	/**
	 * M�thode qui permet d'avoir le rayon du cercle plac�s dans les coins de l'obstacle
	 * @return
	 */
	public int getRayon() {
		return diametre/2;
	}
	/**
	 * M�thode qui permet de modifier la taille de l'obstacle
	 * @param taille
	 */
	public void modifierTaille(int taille) {
		hauteurRecL = hauteurRecL * taille;
		largeurRecL = hauteurRecL-20;
		hauteurRectH = largeurRecL;
		largeurRectH = hauteurRecL;
	}
	/**
	 * M�thode qui permet de retourner la shape de l'obstacle
	 * @return
	 */
	public RoundRectangle2D getColider() {
		return rect;
	}
	/**
	 * Methode qui permet d'Avoir les 4 coins de l'obstacles
	 * @return retourne la liste des coins 
	 */
	public ArrayList<Ellipse2D> listCollider(){
		list = new ArrayList<Ellipse2D>();
		list.add(coinBasDroite);
		list.add(coinBasGauche);
		list.add(coinHautGauche);
		list.add(coinHautDroite);
		return list;
	}
	/**
	 * Methode qui permet d'Avoir la forme du rectangle largeur
	 * @return retourne forme 
	 */
	public Rectangle2D getRec1() {
		return recHauteur;
	}
	public Rectangle2D getRec2() {
		return recLargeur;
	}
	public Ellipse2D getZone() {
		return cercleDetection;
	}
}

