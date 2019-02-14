package canon;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import vecteur.Vecteur;
/**
 * Cette classe permet de creer un canon qui permet de tirer un missile sur une trajectoire de l'angle du canon
 * @author Imad Idrissi
 *
 */

public class Canon implements Dessinable {
	private double positionX, positionY; // Positions du canon 
	private Missile missile;// La composante missile permet de creer plutart un missile
	private MissileChercheur missileChercheur;
	private ArrayList<Missile> missileList;
	private ArrayList<MissileChercheur> missileChercheurList;
	private double vitesse = 40;// les vitesses du missiles
	private double deltaT = 0.1;
	private int angleVecteurVoiture, angleDirectionMissile = 45;
	private boolean tireurA = false, tireurB = false;
	private boolean trouver = false;
	private int indexRechercher, compteur, indexPrecedent;
	private boolean missileFind = false, missileCherhceurFind = false;
	private boolean aucunElement = false;
	
	
	/**
	 * Initialise la position du canon
	 * @param positionX La position en X du canon 
	 * @param positionY La position en Y du canon 
	 */
	public Canon(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		missileList = new ArrayList<Missile>();
		missileChercheurList = new ArrayList<MissileChercheur>();
	}
	/**

	 * Cette methode permet de dessiner les missiles

	 * @param g2d La composante qui permet d'afficher sur Panel
	 */
	public void dessiner(Graphics2D g2d) {
		for( int i = 0; i < missileList.size(); i++) {
			missile = missileList.get(i);
			missile.mouvement(deltaT);
			missile.dessiner(g2d);	
			//System.out.println(i+" drawn");
		}
		for( int i = 0; i < missileChercheurList.size(); i++) {
			missileChercheur = missileChercheurList.get(i);
			missileChercheur.lancement(deltaT);
			missileChercheur.dessiner(g2d);	
			//System.out.println(i+" drawn");
		}
	}
	/**

	 * methode permet de tirer 3 missiles sur trajectoire differente

	 */
	public void tirer() {
		missileList.add(new Missile(positionX, positionY,angleDirectionMissile + angleVecteurVoiture, vitesse ));
		missileList.add(new Missile(positionX, positionY,-angleDirectionMissile + angleVecteurVoiture, vitesse));
		missileList.add(new Missile(positionX, positionY,0 + angleVecteurVoiture, vitesse));
	}
	public void tirer(int positionX, int positionY) {
		missileList.add(new Missile(positionX, positionY,angleDirectionMissile + angleVecteurVoiture, vitesse ));
	}
	/**

	 * M�thode permet de tirer 3 missiles sur trajectoire differente

	 * @param vitEntry la nouvelle vitesse
	 */
	public void tirer(double vitEntry) {
		missileList.add(new Missile(positionX, positionY,angleDirectionMissile + angleVecteurVoiture, (int)vitEntry/1000));
		missileList.add(new Missile(positionX, positionY,-angleDirectionMissile + angleVecteurVoiture, (int)vitEntry/1000));
		missileList.add(new Missile(positionX, positionY,0 + angleVecteurVoiture, (int)vitEntry/1000));
	}
	/**

	 * methode permet de tirer un missile chercheur en lui donnant une vitesse

	 * @param vitEntry La vitesse du missile
	 */
	public void tirerMissileChercheur(double vitEntry) {
		missileChercheurList.add(new MissileChercheur(positionX, positionY,angleDirectionMissile + angleVecteurVoiture, (int)vitEntry));
	}
	/**

	 * methode permet d'avoir la position de l'enemy 

	 * @param x Sa position en X
	 * @param y Sa position en Y
	 */
	public void target(double x, double y) {
		for( int i = 0; i < missileChercheurList.size(); i++) {
			missileChercheur = missileChercheurList.get(i);
			missileChercheur.tracking(x,y);
			//System.out.println(i+" drawn");
		}
	}
	/**

	 * M�thode qui place le canon en X
	 * @param entry La position du canon
	 */
	public void setX(double entry) {
		this.positionX = entry;
	}
	/**

	 * methode qui place le canon en y
	 * @param entry La position du canon
	 */
	public void setY(double entry) {
		this.positionY = entry;
	}
	/**

	 *Cette methode permet d'avoir la liste des missiles tires 

	 * @return missileList
	 */
	public ArrayList<Missile> getMissileList(){
		return missileList;
	}
	/**

	 * methode permet d'avoir la listes des missiles chercheur

	 * @return missileChercheurList
	 */
	public ArrayList<MissileChercheur> getMissileChercheurList(){
		return missileChercheurList;
	}
	/**
	 * M茅thode qui donne la direction du vehicules
	 * @param vecteurDirection Direction du v茅hicule
	 */
	public void setDirection(Vecteur vecteurDirection) {
		if(vecteurDirection.getX() < 0) {
			angleVecteurVoiture =(int) Math.toDegrees((Math.atan(vecteurDirection.getY()/vecteurDirection.getX()))+Math.PI) ;	
		}else {
			angleVecteurVoiture =(int) Math.toDegrees((Math.atan(vecteurDirection.getY()/vecteurDirection.getX())));	
		}
		
	}
	/**

	 * methode permet de savoir si le joueur a tirer

	 * @param tireur L'etat du tireur
	 */
	public void tireurA(boolean tireur) {
		tireurA = tireur;
	}
	/**

	 *  Methode permet de savoir si le joueur 1 a  tiree


	 * @param tireur
	 */
	public void tireurB(boolean tireur) {
		tireurB = tireur;
	}
	/**

	 *  methode permet de retourner l'etat de tire du joueur 1

	 * @return tireurA
	 */
	public Boolean getEtatTireur1() {
		return tireurA;
	}
	/**

	 * methode permet de retourner l'etat de tire du joueur 2

	 * @return tireurB
	 */
	public Boolean getEtatTireur2() {
		return tireurB;
	}
	/**
<
	 * methode permet d'avoir l'index d'un missile lorsqu'on click dessus

	 * @param e Prend les positions de la sourie
	 */
	public void clickOnMissile(MouseEvent e) {
		trouver = false;
		missileFind = false;
		missileCherhceurFind = false;
		compteur = 0;
		while(!trouver){
			if(!getMissileList().isEmpty()) {
				if(indexRechercher < getMissileList().size()) {
					getMissileList().get(indexRechercher).selected(false);
				}
				
				if(getMissileList().get(compteur).getColliderMissile().contains(e.getX(), e.getY())){
					//trouver = true;
					missileFind = true;
					indexRechercher = compteur;
					getMissileList().get(compteur).selected(true);
					break;
				}else {
					missileFind = false;
					trouver = false;
				}
				
			}
			if(!getMissileChercheurList().isEmpty()) {
				if(getMissileChercheurList().get(compteur).coliderMissileChercheur().contains(e.getX(), e.getY())){
					trouver = true;
					indexRechercher = compteur;
					missileCherhceurFind = true;
					break;
				}else {
					missileCherhceurFind = false;
				}
			}
			compteur = compteur + 1;
			if((compteur == getMissileChercheurList().size()+getMissileList().size()) || (getMissileChercheurList().size()+getMissileList().size() == 0)) {
				aucunElement = true;
				break;
			}
			
		}
	}
	/**

	 * methode permet de retourner le modules de la vitesse selon l'index

	 * @return Le module de la vitesse
	 */
	public double getVitesseModule() {
		double vitesseModule = 0;
		if(missileFind) {
			vitesseModule = getMissileList().get(indexRechercher).getVitesse().module();
		}else if(missileCherhceurFind) {
			vitesseModule = getMissileChercheurList().get(indexRechercher).getVitesse().module();
		}
		return vitesseModule;
	}
	/**

	 * methode permet d'avoir le vecteur vitesse selon l'index

	 * @return Le vecteur vitesse
	 */
	 public Vecteur getVitesseVec() {
		 Vecteur vitesseVecteur = new Vecteur();
			if(missileFind) {
				vitesseVecteur = getMissileList().get(indexRechercher).getVitesse();
			}else if(missileCherhceurFind) {
				vitesseVecteur = getMissileChercheurList().get(indexRechercher).getVitesse();
			}else if(aucunElement) {
				vitesseVecteur = new Vecteur(0,0);
			}
			return vitesseVecteur;
	 }
	 /**

	  * methode permet d'avoir le module d'acc閘eration selon l'index
	  * @return Le module d'acc閘eration

	  */
	 
	 public double getAcc() {
		 double accModule = 0;
			if(missileFind) {
				if(indexRechercher < getMissileList().size()) {
					accModule = getMissileList().get(indexRechercher).getAcceleration().module();
					
				}else {
					missileFind = false;	
				}
			}else if(missileCherhceurFind) {
				if(indexRechercher < getMissileChercheurList().size()) {
					accModule = getMissileChercheurList().get(indexRechercher).getAcceleration().module();
				}else {
					missileCherhceurFind = false;
				}
				
			}else if(aucunElement) {
				accModule = 0;
			}
			return accModule;
	 }
	 /**

	  * methode permet d'avoir le vecteur d'acc閘eration selon l'index
	  * @return Le vecteur acc閘eration

	  */
	 public Vecteur getAccVec() {
		 Vecteur accVecteur = new Vecteur();
			if(missileFind) {
				accVecteur = getMissileList().get(indexRechercher).getAcceleration();
			}else if(missileCherhceurFind) {
				accVecteur = getMissileChercheurList().get(indexRechercher).getAcceleration();
			}else if(aucunElement) {
				accVecteur = new Vecteur(0,0);
			}
			return accVecteur;
	 }
	 /**

	  * methode permet de savoir si le missile selectionner subit une force

	  * @return Vrai ou faux 
	  */
	 public boolean getSubitUneForceElectrique() {
		boolean force = false;	
		 if(missileFind) {
				force = getMissileList().get(indexRechercher).getSubitForce();
			}else if(missileCherhceurFind) {
				force = getMissileChercheurList().get(indexRechercher).getSubitForce();
			}else if(aucunElement) {
				force = false;
			}
			return force;
	 }
	 /**

	  * methode permet d'avoir le deltaT des missiles selon l'index
	  * @return Le deltaT
	  */
	 public double getDeltaT() {
		 double deltaT = 0;
			if(missileFind) {
				deltaT = getMissileList().get(indexRechercher).getDeltaT();
			}else if(missileCherhceurFind) {
				deltaT = getMissileChercheurList().get(indexRechercher).getDeltaT();
			}else if(aucunElement) {
				deltaT = 0;
			}
			return deltaT;
	 }
	 /**
	  * Methode qui retourne la force electrique que chaque missile subit
	  * @return vecteur force 
	  */
	 public Vecteur getForce() {
		 Vecteur vecForce = new Vecteur();
		 if(missileFind) {
			 vecForce = getMissileList().get(indexRechercher).getForceEl();
		 }else {
			 vecForce = new Vecteur(0,0);
		 }
		 return vecForce;
	 }
	 /**
	  * Methode qui retourne la charge du missile selon son index 
	  * @return La charge
	  */
	 public double getCharge() {
		 double charge = 0;
		 if(missileFind) {
			 charge = getMissileList().get(indexRechercher).getChargeQ();
		 }else {
			charge = 0;
		 }
		 return charge;
	 }
	 /**
	  * Methode qui permet d'afficher tout les vecteurs des missiles sur la map
	  * @param etat pour activer le mode sci
	  */
	 public void setModeSci(boolean etat) {
		 for( int i = 0; i < missileList.size(); i++) {
				missile = missileList.get(i);
				missile.setModeSci(etat);
				//System.out.println(i+" drawn");
			}
		 for( int i = 0; i < missileChercheurList.size(); i++) {
				missileChercheur = missileChercheurList.get(i);
				missileChercheur.setModeSci(etat);
				//System.out.println(i+" drawn");
			}
	 }
	 
	
 
	
}
