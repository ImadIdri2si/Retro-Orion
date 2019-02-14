package gestion;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import vecteur.Vecteur;
import vehicle.Vehicule;
import canon.Canon;
import canon.Missile;
import canon.MissileChercheur;
import explosionAnim.Explosion;
import objects.WDwarf;
import obstacle.GererObstacle;
import obstacle.Obstacle;
/**
 * Cette classe permet de gerer et détecter les collisions entre les obstacles et les projectiles du canon
 * @author Imad Idrissi 
 *
 */
public class GestionCollision {
	//objet
	private Canon canon; //Le canon qui va etre inialisé dans le constructeur
	private GererObstacle obs; //L'ostacle qui va etre inialisé dans le constructeur
	private Vehicule voitureA = null;
	private Vehicule voitureB = null;
	//etat
	private Vecteur tailleScene;
	private boolean collisionMurs;
	private Ellipse2D ellipse2d;
	private Rectangle2D rectMissileChercheur;
	private RoundRectangle2D obstacle;
	//Explosion
	private Explosion ex;
	

	/**
	 * Constructeur prend en parametre le canon et le obstacle afin de verifier les collsions selon les positions
	 * @param canon Canon initiale
	 * @param obs Obstcale initiale
	 */
	public GestionCollision(Canon canon,GererObstacle obs, Explosion ex ) {
		this.canon = canon;
		this.obs = obs;
		this.ex = ex;
	}
	public GestionCollision(Canon canon, GererObstacle obs, Vehicule voitureA, Vehicule voitureB, Explosion ex ) {
		this.canon = canon;
		this.obs = obs;
		this.voitureA = voitureA;
		this.voitureB = voitureB;
		this.ex = ex;
	}
	/**
	 * Cette méthode permet de savoir verifier s'il ya une collision
	 * @param dwarf,des particules que les v��hicules pourrons avaler pour allonger leures queues
	 */
	public void collisionDetected(WDwarf dwarf) {
		ex.demarrerList();
		if(canon!=null && canon.getMissileChercheurList().size() + canon.getMissileList().size()!=0) {
			for(int i = 0; i < (canon.getMissileChercheurList().size()+canon.getMissileList().size()) ;i++) {
				if(!canon.getMissileList().isEmpty()) {
					if(voitureA != null && voitureB != null) {
						missileCollisionVoiture(i,dwarf);
					}
					collisionMurs(i);
					if(collisionMurs && i < canon.getMissileList().size() ) {
						collisionCoins(i);
					}
				}
				if(!canon.getMissileChercheurList().isEmpty() ) {
					if(voitureA != null && voitureB != null) {
						missileChercheurCollisionVoiture(i,dwarf);
					}
				}
				if(obs!=null && obs.getObsList().size()!=0) {
					for(int k = 0; k < obs.getObsList().size(); k++) {
						if(!canon.getMissileChercheurList().isEmpty()) {
							missileChercheurCollisionObstacle(i, k);
						}
						if(detectionZoneObstacle(i,k)) {
							collisionObstcale(i,k);
						}
						
					}

				}
			}				
		}			


	}
	/**
	 * Cette méthode permet de detecter les collisions avec les murs
	 * @param index de la liste de missile
	 */
	public void collisionMurs(int index) {	//WORKS
		if(index<canon.getMissileList().size()) {
			if(((canon.getMissileList().get(index).getPositionX() + canon.getMissileList().get(index).getDiametre()) >= tailleScene.getX()) 
					|| ((canon.getMissileList().get(index).getPositionX()) <= 0)) {

				canon.getMissileList().get(index).vitAccCollision(true, false);
				nombreColMax(index);
				collisionMurs = true;
			}else if(((canon.getMissileList().get(index).getPositionY() + canon.getMissileList().get(index).getDiametre()) >= tailleScene.getY()) 
					|| ((canon.getMissileList().get(index).getPositionY()) <= 0)) {
				canon.getMissileList().get(index).vitAccCollision(false, true);
				nombreColMax(index);
				collisionMurs = true;
			}else {
				collisionMurs = false;
			}
		}

	}
	/**
	 * Cette méthode permet de detecter les collisions dans les coins des murs
	 * @param index de la liste d'obstacle 
	 */
	public void collisionCoins(int index) { //Works
		if(((canon.getMissileList().get(index).getPositionX() + canon.getMissileList().get(index).getDiametre()) >= tailleScene.getX() && canon.getMissileList().get(index).getPositionY() <= 0) 
				|| ((canon.getMissileList().get(index).getPositionX()) <= 0 && canon.getMissileList().get(index).getPositionY() <= 0) 
				|| ((canon.getMissileList().get(index).getPositionX() + canon.getMissileList().get(index).getDiametre()) >= tailleScene.getX() && canon.getMissileList().get(index).getPositionY() + canon.getMissileList().get(index).getDiametre() >= tailleScene.getY()) 
				|| ((canon.getMissileList().get(index).getPositionX() <= 0 && canon.getMissileList().get(index).getPositionY() + canon.getMissileList().get(index).getDiametre() >= tailleScene.getY()))) {
			canon.getMissileList().get(index).vitAccCollision(false, true);
		}

	}
	/**
	 * Cette méthode permet de detecter des collisions avec les obstacles 
	 * @param index de la liste d'obstacle
	 */
	public void collisionObstcale(int index, int indexObs) { //WORKS
		if(collisionGaucheDroiteObs(index,indexObs)) {
			canon.getMissileList().get(index).vitAccCollision(true, false);
			nombreColMax(index);
		}else if(collisionHautBasObs(index, indexObs)){
			canon.getMissileList().get(index).vitAccCollision(false, true);
			nombreColMax(index);
		}else if(coinObstacle(index,indexObs)) {
			canon.getMissileList().get(index).vitAccColCercle(-1, -1);
			nombreColMax(index);
		}

	}
	/**
	 * methode qui permet de savoir qu'elle cote le missile a frapper 
	  * @param index le index de missile
	 * @param indexObs les index des obstacles
	 * @return vrai ou faux s'il ya une collision 
	 */
	public Boolean collisionHautBasObs(int  index, int indexObs) { //WORKS
		if(((canon.getMissileList().get(index).getPositionY() ) >= obs.getObsList().get(indexObs).getPositionY() 
				&& (canon.getMissileList().get(index).getPositionY() - (canon.getMissileList().get(index).getDiametre()) <= obs.getObsList().get(indexObs).getPositionY() + obs.getObsList().get(indexObs).getHauteurRectH()) 
				&& (canon.getMissileList().get(index).getPositionX() + canon.getMissileList().get(index).getDiametre() >= obs.getObsList().get(indexObs).getPositionX() 
				&& (canon.getMissileList().get(index).getPositionX() + canon.getMissileList().get(index).getDiametre()) <= obs.getObsList().get(indexObs).getPositionX() + obs.getObsList().get(indexObs).getHauteurRecL()))) {
			return true;
			
		}else {
			return false;
		}
	}
	/**
	 * methode qui permet de savoir qu'elle cote le missile a frapper 
	 * @param index le index de missile
	 * @param indexObs les index des obstacles
	 * @return vrai ou faux s'il ya une collision 
	 */
	public Boolean collisionGaucheDroiteObs(int index, int indexObs) { //WORKS
		if(((canon.getMissileList().get(index).getPositionX() + canon.getMissileList().get(index).getDiametre()) >= obs.getObsList().get(indexObs).getPositionLargeurX() 
				&& (canon.getMissileList().get(index).getPositionX() - canon.getMissileList().get(index).getDiametre()) <= obs.getObsList().get(indexObs).getPositionLargeurX() + obs.getObsList().get(indexObs).getLargeurRecL()) 
				&& (canon.getMissileList().get(index).getPositionY() + canon.getMissileList().get(index).getDiametre() >= obs.getObsList().get(indexObs).getPositionLargeurY() 
				&& (canon.getMissileList().get(index).getPositionY() + canon.getMissileList().get(index).getDiametre()) <= obs.getObsList().get(indexObs).getPositionLargeurY() + obs.getObsList().get(indexObs).getLargeurRecH())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Methode qui permet d'optimiser la collision entre le missile et l'obstcale, pour que la detection entre missile et obstacles seulement si le missile est dans la zone 
	 * @param index le index de missile
	 * @param indexObs les index des obstacles
	 * @return soit vrai ou faux si celui-ci est dans la zone 
	 */
	public boolean detectionZoneObstacle(int index, int indexObs) { 
		if(indexObs < obs.getObsList().size() && index < canon.getMissileList().size()) {
			double dx =  ((obs.getObsList().get(indexObs).getPositionCercleZone().getX()+obs.getObsList().get(indexObs).getRayonCercleZone()))-canon.getMissileList().get(index).getPositionX();
			double dy =  ((obs.getObsList().get(indexObs).getPositionCercleZone().getY()+obs.getObsList().get(indexObs).getRayonCercleZone()))-canon.getMissileList().get(index).getPositionY();
			double distance = Math.sqrt((Math.pow(dx, 2)+(Math.pow(dy, 2))));
			if(distance <= (canon.getMissileList().get(index).getRayon() + obs.getObsList().get(indexObs).getRayonCercleZone())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}

	}
	
	/**
	 * 
	 * Méthode qui permet de detecter les collisions avec les coins de l'obstacles
	 * @param index index de la liste de missiles 
	 * @param indexObs index de la liste d'obstacle
	 * @return etat, s'il y'a une collision 
	 */
	public boolean coinObstacle(int index, int indexObs) {
		Rectangle2D rectMissile = canon.getMissileList().get(index).getMissileZone(); 
		boolean etat = false;
		ArrayList<Ellipse2D> obstacle = obs.getObsList().get(indexObs).listCollider();
		int compteur = 0;
		while(compteur < obstacle.size()) {
			if(obstacle.get(compteur).intersects(rectMissile)) {
				setCompteur(obstacle.get(compteur));
				etat = true;
				System.out.println();
				break;
			}else {
				etat = false;
			}
			compteur +=1;	
		}
		return etat;
	}
	/**
	 * Méthode qui permet de set le compteur de coin
	 * @param ellipse2d
	 */
	public void setCompteur(Ellipse2D ellipse2d) {
		this.ellipse2d = ellipse2d;
	}
	/**
	 * Méthode qui retourne le coin de chaque obstacle 
	 * @return Un cercle
	 */
	public Ellipse2D getObs() {
		return ellipse2d;
	}
	/**
	 * Cette méthode permet d'Avoir la taille du panel 
	 * @param taille La taille de l'écran est donner par un vecteur qui offre la taille en X et en la taille Y
	 */
	public void setTaille(Vecteur taille){
		this.tailleScene = taille;

	}
	/**
	 * Détection de collision entre missile chercheur et les obstacles
	 * @param index de Liste de missile 
	 * @param indexObs de Liste d'obstacle
	 */
	public void missileChercheurCollisionObstacle(int index, int indexObs) {
		rectMissileChercheur = canon.getMissileChercheurList().get(index).coliderMissileChercheur();
		obstacle = obs.getObsList().get(indexObs).getColider();
		if(obstacle.intersects(rectMissileChercheur)) {
			ex.ajouterUneExplosion(new Point((int)canon.getMissileChercheurList().get(index).getPositionX(),(int)canon.getMissileChercheurList().get(index).getPositionY()));
			canon.getMissileChercheurList().remove(index);
		}
	}
	/**
	 * Méthode qui permet de detecter la collision entre missileChercheur et un véhicule 
	 * @param index Liste de missile
	 * @param dwarf,des particules que les v��hicules pourrons avaler pour allonger leures queues
	 */
	public void missileChercheurCollisionVoiture(int index, WDwarf dwarf) {
		Rectangle2D rectMissileChercheur = canon.getMissileChercheurList().get(index).coliderMissileChercheur();
		Shape voiture = null;
		Vehicule vic = null;
		if (canon.getEtatTireur1()) {
			voiture = voitureB.getCollider();
			vic = voitureB;
		}else {
			voiture = voitureA.getCollider();
			vic = voitureA;
		}
		if(voiture.intersects(rectMissileChercheur)) {
			ex.ajouterUneExplosion(new Point((int)canon.getMissileChercheurList().get(index).getPositionX(),(int)canon.getMissileChercheurList().get(index).getPositionY()));
			
			//int disNb=(int)(vic.getTailDim()*canon.getMissileChercheurList().get(index).degat());
			int disNb=((int)(vic.getTailDim()*canon.getMissileChercheurList().get(index).degat()));
			int expoRadius = 3000;
			//System.out.print("des nb:_______"+disNb);
			if(dwarf!=null)vic.tailDestroyAndSpray(disNb, dwarf, (int)canon.getMissileChercheurList().get(index).getPositionX(),(int)canon.getMissileChercheurList().get(index).getPositionY(), expoRadius);
			
			canon.getMissileChercheurList().remove(index);
		}
	}

	/**
	 * Méthode qui permet de detecter la collision entre missile et un véhicule
	 * @param index Liste de missile
	 * @param dwarf,des particules que les v��hicules pourrons avaler pour allonger leures queues
	 */
	public void missileCollisionVoiture(int index, WDwarf dwarf) {
		Rectangle2D rectMissile = canon.getMissileList().get(index).getColliderMissile();
		Shape voiture = null;
		Vehicule vic = null;
		if (canon.getEtatTireur1()) {
			voiture = voitureB.getCollider();
			vic = voitureB;
		}else {
			voiture = voitureA.getCollider();
			vic = voitureA;
		}
		if(voiture.intersects(rectMissile)) {
			ex.ajouterUneExplosion(new Point((int)canon.getMissileList().get(index).getPositionX(),(int)canon.getMissileList().get(index).getPositionY()));
			
			
			//int disNb=(int)(vic.getTailDim()*canon.getMissileList().get(index).degat());
			int disNb=((int)((double)vic.getTailDim()*canon.getMissileList().get(index).degat()));
			int expoRadius = 1000;
			//System.out.print("des nb:_______"+disNb);
			if(dwarf!=null)vic.tailDestroyAndSpray(disNb,dwarf,(int)canon.getMissileList().get(index).getPositionX(),(int)canon.getMissileList().get(index).getPositionY(),expoRadius);
			canon.getMissileList().remove(index);
		}
	}
	/**
	 * Méthode qui permet de verifier le nombre maximal de collision
	 * @param index liste de missile
	 */
	public void nombreColMax(int index){
		if(!canon.getMissileList().isEmpty()) {
			canon.getMissileList().get(index).compteurCollision();
			if(canon.getMissileList().get(index).getCompteurCol() == canon.getMissileList().get(index).nombreCollsion()) {
				ex.ajouterUneExplosion(new Point((int)canon.getMissileList().get(index).getPositionX(),(int)canon.getMissileList().get(index).getPositionY()));
				canon.getMissileList().remove(index);
			}
		}
	}
}
