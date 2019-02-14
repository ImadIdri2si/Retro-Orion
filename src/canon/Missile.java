package canon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;

import mapListeners.MissileListener;
import vecteur.Vecteur;
import vecteur.VecteurGraphique;
/**
 * Cette classe missile speherique permet d'锚tre utiliser pour cree un canon afin de tirer des missile.
 * Un missile possede une vitesse, un rayon et une position;
 *
 * @author Imad Idrissi 
 *
 */

public class Missile implements Dessinable {
	private int rayon = 10; // Rayon du missile spherique
	//Les positions 
	private double positionX, positionY; // Position selon les axes X,Y;
	// les vitesses et acceleration
	private double vitesseX, vitesseY;
	private double accelerationX, accelerationY; // Vitesse selon les axes X,Y;
	// Les formes  
	private Ellipse2D missileSpherique; // La shape de notre missile 
	private Rectangle2D missileShape, missileZone;
	// Les vecteurs afficher
	private VecteurGraphique vecteurVitesse, vecteurAcceleration, vecteurForce;
	private double chargeQ = 500000;
	//Subit une force 
	private boolean subitForce; 
	private Vecteur forceEl = null;
	//Trail
	private ArrayList<Point> listPosition = null;
	private Point position  = null;
	private int nombreTrail = 20;
	private Ellipse2D[] trail = new Ellipse2D[nombreTrail];
	//Image missile
	private Image missile1 = null;
	//Temps deltaT;
	private double deltaT;
	//Color
	private int rbg=255, brg=0, grg=0;
	//Degat
	private final double DEGAT_POUCENTAGE = 1.0/3.0;
	// Nombre de collision limite
	private final int NOMBRE_COLLISION = 3;
	private int compteurCollision = 0;
	//
	private boolean selected = false;
	//Image
	Image img = null;
	//Mode Scientifique 
	private boolean modeSci = false;
	
	
	/**
	 * Constructeur de la classe missile, celle-ci permet des initialiser les propri茅t茅s de la classe
	 * @param positionX La position initiliale axe x
	 * @param positionY La position initiliale axe y
	 * @param vitesseX  La vitesse initiale axe x
	 * @param vitesseY  La vitesse initiale axe y
	 * @param rayon	Le rayon du missile
	 */
	public Missile(double positionX, double positionY, int angle , double vitesse) {
		this.positionX = positionX ;
		this.positionY = positionY ;
		vitesseX = vitesse * Math.cos(Math.toRadians(angle));
		vitesseY = vitesse* Math.sin(Math.toRadians(angle));
		accelerationX = 0;
		accelerationY = 0;
		subitForce = false;
		listPosition = new ArrayList<Point>();
		
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("MissileTp1.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.img = img.getScaledInstance(img.getWidth(null)/5, img.getHeight(null)/5, Image.SCALE_SMOOTH);
		rayon = img.getWidth(null)/2;
	}
	/**
	 * M茅thode permet dessiner un missile
	 * @param g2d La composante g2d permet de le dessiner sur le panel
	 */
	public void dessiner(Graphics2D g2d) {
		missileSpherique = new Ellipse2D.Double(positionX - rayon ,positionY - rayon, rayon*2, rayon*2 );
		missileShape = new Rectangle2D.Double(positionX - ((rayon*7)/2),positionY - ((rayon*7)/2), rayon*7, rayon*7 );
		missileZone = new Rectangle2D.Double(positionX - rayon ,positionY - rayon, rayon*2, rayon*2 );
		dessinerTrail(g2d);
		if(selected) {
			g2d.setColor(Color.red);
			g2d.fill(missileSpherique);
		}
		
		g2d.drawImage(img,(int)(positionX-rayon) ,(int)(positionY-rayon),null);
		
		if(modeSci) {
			dessinerVecteurDirection(g2d);
		}
	
	}
	/**
	 * Methode qui permet de selectionner chaque missile
	 * @param selected
	 */
	public void selected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * Methode qui retourne si un missile est selectionné
	 * @return selected, l'etat de la selection 
	 */
	public boolean getSelec() {
		return selected;
	}
	/**
	 * Methode permet de dessiner une trinaer du missile.
	 * @param g2d
	 */
	public void dessinerTrail(Graphics2D g2d) {
		if(listPosition.size() > nombreTrail) {
			listPosition.remove(0);
		}
		for (int i = 0; i < listPosition.size(); i++) {
			trail[i] = new Ellipse2D.Double(listPosition.get(i).getX()-i/2,listPosition.get(i).getY()-i/2, i, i);
			Color color = new Color(rbg, brg, grg,i*5) ;
			g2d.setColor(color);
			g2d.fill(trail[i]);
		}
	}
	/**
	 * Methode qui permet de changer la couleur de la trail
	 * @param rgb
	 * @param brg
	 * @param grg
	 */
	public void setColor(int rgb, int brg, int grg) {
		this.rbg = rgb;
		this.brg = brg;
		this.grg = grg;
	}
	public void setLongeurTrail(int longeur) {
		this.nombreTrail = longeur;
	}
	/**
	 * M茅thode permet de calculer la position selon la vitesse et le temps
	 * @param deltaT Le nombre d'iteration(temps)
	 */
	public void mouvement(double deltaT) {
		this.deltaT = this.deltaT + deltaT;
		vitesseX = vitesseX + accelerationX * deltaT;
		vitesseY = vitesseY + accelerationY * deltaT;
		positionX = (positionX + vitesseX*deltaT);
		positionY = (positionY + vitesseY*deltaT);
		position = new Point((int)positionX,(int) positionY);
		listPosition.add(position);
		if(getAcceleration().module() != 0) {
			subitForce = true;
		}else {
			subitForce = false;
		}
		//gestionVitesse();
	}
	/**
	 * M茅thode permet de dessiner le vecteur direction du missile 
	 * @param g2d La composante g2d permet de le dessiner sur le panel
	 */
	public void dessinerVecteurDirection(Graphics2D g2d) {
		if(vitesseX == 0 && vitesseY == 0) {
			vecteurVitesse = new VecteurGraphique(0,0,this.positionX, this.positionY );
		}else if(vitesseX == 0) {
			vecteurVitesse = new VecteurGraphique(0,vitesseY,this.positionX, this.positionY );
		}else if(vitesseY == 0) {
			vecteurVitesse = new VecteurGraphique(vitesseX,0,this.positionX, this.positionY );
		}else {
			vecteurVitesse = new VecteurGraphique((vitesseX),vitesseY,this.positionX, this.positionY );
		}
		vecteurAcceleration = new VecteurGraphique((accelerationX)*5,accelerationY*5,this.positionX, this.positionY );
		vecteurVitesse.setOrigineXY(positionX+rayon, positionY+rayon);
		vecteurAcceleration.setOrigineXY(positionX+rayon, positionY+rayon);
		vecteurVitesse.dessiner(g2d);
		Color back = g2d.getColor();
		g2d.setColor(Color.red);
		vecteurAcceleration.dessiner(g2d);
		if(vecteurForce != null) {
			vecteurForce = new VecteurGraphique((forceEl.getX()),forceEl.getY(), positionX, positionY );
			vecteurForce.dessiner(g2d);
		}
		g2d.setColor(Color.black);
		g2d.setColor(back);
	}
	/**
	 * M茅thode change la direction des missiles lors d'une collsions
	 * @param vitAccX La nouvelle direction des composants en X
	 * @param vitAccY La nouvelle direction des composants en Y
	 */
	public void vitAccCollision(boolean vitAccX, boolean vitAccY) {
		if(vitAccX) {
			vitesseX = vitesseX * -1;
			accelerationX = accelerationX * -1;
		}
		if(vitAccY) {
			vitesseY = vitesseY * -1;
			accelerationY = accelerationY * -1;
		}
	}
	/**
	 * M茅thode change la direction des missiles lors des collisions dans les coins du obstacles
	 * @param vitAccX La nouvelle direction des composants en X
	 * @param vitAccY La nouvelle direction des composants en Y
	 */
	public void vitAccColCercle(double vitAccX, double vitAccY) {
		vitesseX = vitesseX * vitAccX;
		accelerationX = accelerationX * vitAccX;
		vitesseY = vitesseY * vitAccY;
		accelerationY = accelerationY * vitAccY;
	}
	/**
	 * M茅thode permet de changer la vitesse axe X
	 * @param vitesseX La nouvelle vitesse axe X
	 */
	public void setVitesseX(double vitesseX) {
		this.vitesseX = vitesseX;
	}
	/**
	 * M茅thode permet de changer la vitesse axe Y
	 * @param vitesseY La nouvelle vitesse axe Y
	 */
	public void setVitesseY(double vitesseY) {
		this.vitesseY = vitesseY;
	}
	/**
	 * M茅thode permet de changer la position axe X
	 * @param positionX2 La nouvelle position axe X
	 */
	public void setPositionX(double positionX2) {
		this.positionX = positionX2 ;
	}
	/**
	 * M茅thode permet de changer la position axe Y
	 * @param positionY La nouvelle position axe X
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY ;
	}
	/**
	 * M茅thode permet de changer les positons X et Y ,selon un vecteur
	 * @param position La nouvelle position en axe x et y;
	 */
	public void setPosition(Point position){
		this.positionX = position.getX();
		this.positionY = position.getY();
	}
	/**
	 * M茅thode permet de changer les vitesses X et Y ,selon un vecteur
	 * @param vitesse La nouvelle vitesse en  axe x et y;
	 */
	public void setVitesse(Vecteur vitesse){
		this.vitesseX = vitesse.getX();
		this.vitesseY = vitesse.getY();
	}
	/**
	 * M茅thode permet de changer le rayon du missile
	 * @param rayon Le nouveau rayon 
	 */
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
	/**
	 * M茅thode permet de retourner la vitesse en X
	 * @return La vitesse en x
	 */
	public double getVitesseX() {
		return vitesseX;
	}
	/**
	 * M茅thode permet de retourner la vitesse en Y
	 * @return La vitesse en Y
	 */
	public double getVitesseY() {
		return vitesseY;
	}
	/**
	 * M茅thode permet de retourner la position en X
	 * @return La postion en X
	 */
	public double getPositionX() {
		return positionX-rayon;
	}
	/**
	 * M茅thode permet de retourner la position en Y
	 * @return La postion en X
	 */
	public double getPositionY() {
		return positionY-rayon;
	}
	/**
	 * M茅thode permet de retourner les positons sous forme de vecteur
	 * @return Un vecteur avec les postions en X,Y 
	 */
	public Vecteur getPosition() {
		return new Vecteur(positionX, positionY);
	}
	/**
	 * M茅thode permet de retourner les vitesses sous forme de vecteur
	 * @return Un vecteur avec les vitesses en X,Y
	 */
	public Vecteur getVitesse() {
		return new Vecteur(vitesseX, vitesseY);
	}
	/**

	 * Methode permet de retouner un vecteur acc閘eration 
	 * @return Vecteur acc閘eration

	 */
	public Vecteur getAcceleration() {
		return new Vecteur(accelerationX, accelerationY);
	}
	/**
	 * M茅thode permet de retourner le rayon du missile 
	 * @return La composante Rayon
	 */
	public int getRayon() {
		return rayon;
	}
	/**
	 * M茅thode permet de retourner le diametre du missile
	 * @return Le rayon multiplie par 2
	 */
	public int getDiametre() {
		return (rayon*2);
	}
	/**
	 * Obtient l'acceleration en X
	 * @return retourne l'acc en X
	 */
	public double getAccelerationX() {
		return accelerationX;
	}
	/**
	 * Modifier l'acc en X
	 * @param acc La nouvelle acc茅leration
	 */
	public void setAccelerationX(double acc) {
		this.accelerationX = acc;
	}
	/**
	 * Obtient l'acceleration en Y
	 * @return Retourne acceleration en Y
	 */
	public double getAccelerationY() {
		return accelerationY;
	}
	/**
	 * Modifie l'acceleration en Y
	 * @param acc La nouvelle acceleration Y
	 */
	public void setAccelerationY(double acc) {
		this.accelerationY = acc;
	}
	
	/**
	 * Obtient la charge du missiles
	 * @return Retourne la charge
	 */
	
	public double getChargeQ() {
		return chargeQ;
	}
	/**
	 * Modifier la charge du missile
	 * @param chargeQ la nouvelle charge du missile
	 */
	public void setChargeQ(double chargeQ) {
		this.chargeQ = chargeQ;
	}
	/**

	 * Methode permet de changer l'etat de la force, pour voir si le missile subit une force

	 * @param force 
	 */
	public void subitUneForce(boolean force) {
		subitForce = force;
	}
	/**

	 * Methode qui permet de retourner si le missile subit une force 
	 * @return subitForce
	 */
	public boolean getSubitForce() {
		return subitForce;
	}
	/**

	 * Methode permet de changer la position de la trail pour les classes d閞iv閑s

	 * @param La nouvelle position
	 */
	public void setTrailListPosiition(Point position) {
		listPosition.add(position);
	}
	/**
	 * Permet d'aller chercher une Image 
	 * @param Le nom de l'image
	 */
	public void lireUneImage(String url) {
		URL fich = getClass().getClassLoader().getResource(url);
		if (fich == null ) {
			JOptionPane.showMessageDialog(null,	"Fichier d'image introuvable!");
		} else {
			//terminer l'acquisition de l'image ici
			try {
				missile1 = ImageIO.read(fich);
				
			}
			catch (IOException e) {
				System.out.println("Erreur pendant la lecture du fichier d'image");
			}
		}
	}
	/**

	 * Methode qui retourne la shape du missile pour detecter les collisions
	 * @return  missileShape
	 */
	public Rectangle2D getColliderMissile() {
		return missileShape;
	}
	/**

	 * Methode qui retourne le deltaT, le temps ecouler depuis le tire du missile
	 * @return deltaT 
	 */
	public double getDeltaT() {
		return deltaT;
	}
	/**
	 * Methode qui revoit le degat maximale d'un missile
	 * @return DEGAT_POUCENTAGE
	 */
	public double degat() {
		return DEGAT_POUCENTAGE;
	}
	/**
	 * Methode qui revoit le nombre de collision maximale d'un missile
	 * @return NOMBRE_COLLISION
	 */
	public int nombreCollsion(){
		return NOMBRE_COLLISION;
	}
	/**
	 * Methode de compteur qui compte le nombre de collision sur chaque missile
	 */
	public void compteurCollision() {
		compteurCollision = compteurCollision + 1;
	}
	/**
	 * Methode qui retourne le nombre de collision actuel
	 * @return compteurCollision
	 */
	public int getCompteurCol() {
		return compteurCollision;
	}
	/**
	 * Methode qui permet d'avoir la force electrique 
	 * @param force
	 */
	public void setForce(Vecteur force) {
		forceEl = force;
	}
	/**
	 * Methode qui retourne la force electrique
	 * @return
	 */
	public Vecteur getForceEl() {
		return forceEl;
	}
	/**
	 * Methode qui permet d'obtenir la forme du missile pour cliquer dessus 
	 * @return la shape de la zone cliquable
	 */
	public Ellipse2D getShapeMissile() {
		return missileSpherique;
	}
	/**
	 * Methode qui permet d'avoir la forme collision, celle-ci prend la forme du missile
	 * @return la shape du missile en forme de rectangle
	 */
	public Rectangle2D getMissileZone() {
		return missileZone;
	}
	/**
	 * Methode qui permet de set les vecteurs vitesses 
	 * @param modeSci
	 */
	public void setModeSci(boolean modeSci) {
		this.modeSci = modeSci;
	}
	
	
		
}
