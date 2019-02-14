package canon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import vecteur.Vecteur;
import vecteur.VecteurGraphique;
/**
 * Cette classe est derive de la classe missile, afin de posseder ses fonctionnalite. Le missile chercheur a pour but de poursuivre des veicules adverses
 * @author Imad Idrissi 
 */
public class MissileChercheur extends Missile implements Dessinable{
	//Position du target
	private double targetX;
	private double targetY;
	//Angle de tire
	private double angle;
	//Vitesse
	private double vitesseX, vitesseY;
	//La forme
	private Rectangle2D missileRec;
	//Les positions
	private double positionX, positionY;
	//Les informations sur la forme
	private double largeurMissile = 30, hauteurMissile = 10;
	//Module de la vitesse
	private double vitesse;
	//Vecteur 
	private VecteurGraphique vecteurVitesse;
	//Temps ecoule
	private double detlaT;
	//Degat
	private final double DEGAT_POUCENTAGE = 1;
	//Vecteur 
	private VecteurGraphique vect ;
	//
	private boolean modeSci;

	/**
	 * Constructeur qui permet d'initialiser les composantes
	 * @param positionX La position en X initiale du missil e
	 * @param positionY La position en Y initiale du missil e
	 * @param angle L'angle initiale de tir du missile 
	 * @param vitesse La vitesse du missile
	 */
	public MissileChercheur(double positionX, double positionY, int angle, double vitesse) {
		super(positionX, positionY, angle, vitesse);
		// TODO Auto-generated constructor stub
		this.positionY = positionY - (hauteurMissile/2); 
		this.positionX = positionX - (largeurMissile/2);
		this.vitesse = vitesse;
		vitesseX = vitesse * Math.cos(Math.toRadians(angle));
		vitesseY = vitesse* Math.sin(Math.toRadians(angle));
		
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("MissileTp2.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.img = img.getScaledInstance(img.getWidth(null)/5, img.getHeight(null)/5, Image.SCALE_SMOOTH);
		largeurMissile = img.getWidth(null);
		hauteurMissile = img.getHeight(null);
	}
	/**

	 * Mehode permet de calculer l'angle entre le missileChercheur et le target
	 * @param positionXTarget La positionX du target 
	 * @param positionYTarget La positionY du target  
	 */
	public void tracking(double positionXTarget, double positionYTarget) {
		targetX =  getPositionX()-positionXTarget;
		targetY =  getPositionY()-positionYTarget;
        angle =  Math.atan2(targetY, targetX) * 180 / Math.PI;
	}
	/**
	 * Mehode permet de dessiner
	 * @param g2d permet de dessiner
	 */
	public void dessiner(Graphics2D g2d) {
		missileRec = new Rectangle2D.Double(positionX-(largeurMissile/2), positionY-(hauteurMissile/2), largeurMissile, hauteurMissile);
		AffineTransform old = g2d.getTransform();
		setColor(255, 0, 233);
		dessinerTrail(g2d);
		//draw shape/image (will be rotated)
		g2d.rotate(Math.toRadians(angle), positionX, positionY);
		g2d.drawImage(img,(int)(positionX-(largeurMissile/2)) ,(int)(positionY-(hauteurMissile/2)),null);
		//g2d.fill(missileRec);
        g2d.setTransform(old);
        if(modeSci) {
            dessinerVecteurDirection( g2d);

        }
	}
	/**
	 * Methode qui calcule le mouvement du missile chercheur
	 * @param deltaT Le temps du deltaT
	 */
	public void lancement(double deltaT) {
		this.detlaT = this.detlaT + deltaT;
		positionX = (positionX - vitesseX*deltaT);
		positionY = (positionY - vitesseY*deltaT);
		vitesseX = vitesse * (90 - Math.abs(angle)) / 90;
        if (angle < 0) {
            vitesseY = - vitesse + Math.abs(vitesseX);
        }else {
            vitesseY = vitesse - Math.abs(vitesseX);
        }
		setPosition(new Point((int)positionX,(int)positionY));
		setTrailListPosiition(new Point((int)(positionX),(int)(positionY)));
		setVitesse(new Vecteur(vitesseX,vitesseY));
	}
	
	/**
	 * M�thode qui retourne la shape du missile, pour la detection de collision 
	 * @return La forme du missile
	 */
	public Rectangle2D coliderMissileChercheur() {
		return missileRec;
	}
	/**

	 * Methode qui retourne le deltaT
	 * @return deltaT le temps 
	 */
	public double getDeltaT() {
		return detlaT;
	}
	/**
	 * methode qui permet d'avoir le pourcentage de degat
	 * @return retourne le nombre de degat
	 */
	public double degat() {
		return DEGAT_POUCENTAGE;
	}
	/**
	 * methode qui permet de dessinerles vecteurs missile chercheur
	 */
	public void dessinerVecteurDirection(Graphics2D g2d) {
		Color colorSav = g2d.getColor();
		g2d.setColor(Color.RED);
		vect = new VecteurGraphique(-1*targetX, -1*targetY, positionX, positionY );
		vect.dessiner(g2d);
		g2d.setColor(colorSav);
	}
	/**
	 * Methode qui permet d'afficher les vecteurs
	 */
	public void setModeSci(boolean etat) {
		modeSci = etat;
	}
	

}
