package explosionAnim;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthSpinnerUI;
/**
 * Cette classe permet de faire des animations d'explosion et de les gerer
 * @author Imad Idrissi 
 *
 */
public class Explosion implements Dessinable, Runnable{
	private Point position;
	//Image 
	private final int NB_IMAGES = 63;
	private static Image images[];
	private static URL url[] = null;
	private int numDImage;
	private boolean commencer = false, terminer = false;
	//List d'explosion
	private ArrayList<Explosion> xploList = new ArrayList<Explosion>();;
	/**
	 * Constructeur qui permet de creer une explosion selon une position
	 * @param point la position de la position
	 */
	public Explosion(Point point) {
		position = point;
		if (images == null) {
			lireLesImages();
		}
		numDImage = 0;
	}
	/*
	 * Construteur qui permet de creer l'objet et de lire les images 
	 */
	public Explosion() {
		if (images == null) {
			lireLesImages();
		}
		numDImage = 0;
	}
	/**
	 * Methode qui permet de dessiner l'explosion 
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.drawImage(images[numDImage],(int)position.getX()-(images[numDImage].getWidth(null)/2),(int) position.getY()-(images[numDImage].getHeight(null)/2), null);
	}
	/**
	 * Methode runnable qui permet d'avoir un compteur selon un decompte qui fait passer les images
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (commencer) {
			numDImage = (numDImage + 1);
			if(numDImage == images.length-1) {
				terminer = true;
				break;
			}
			try {
				Thread.sleep(50); // petit delai pour ralentir
			}
			catch (InterruptedException e) {
				System.out.println("Processus interrompu!");
			}

		}
	}

	/**
	 * Methode qui permet de demarrer l'animation 
	 */
	public void demarrer() {
		if (!commencer) { 
			Thread processusAnim = new Thread(this);
			processusAnim.start();
			commencer = true;
		}
	}
	/**
	 * Methode qui permet de lire image
	 */
	private void lireLesImages() {
		images = new Image[NB_IMAGES];
		url = new URL[NB_IMAGES];
		for (int k = 0; k < NB_IMAGES; k++) {
			String nomFichier =  "tile"+ (k+1) + ".png";
			url[k] = getClass().getClassLoader().getResource(nomFichier);
			if (url[k] == null) {
				System.out.println("lireLesImages: incapable de lire le fichier d'image " + nomFichier);
				return; //on quitte la m�thode
			}
		}
		for (int k = 0; k < NB_IMAGES; k++) {
			try {
				images[k] = ImageIO.read(url[k]);
			} 
			catch (IOException e) {
				System.out.println("IOException lors de la lecture avec ImageIO");
			}	
		}
	}//fin methode
	/**
	 * Methode qui permet de savoir si l'animation est demarrer
	 * @return retourne l'etat de l'animation 
	 */
	public boolean getDemarrer() {
		return commencer;
	}
	/**
	 * Methode qui permet de savoir si l'animation est terminer 
	 * @return retourne l'etat de l'animation 
	 */
	public boolean terminer() {
		return terminer;
	}
	/**
	 * Methode qui permet d'ajouter une explosion selon une position
	 * @param position la position de l'explosion 
	 */
	public void ajouterUneExplosion(Point position) {
		xploList.add(new Explosion(position));
		System.out.println(xploList.size());
	}
	/**
	 * Methode qui permet de dessiner la liste d'explosion
	 * @param g2d permet de dessiner
	 */
	public void dessinerList(Graphics2D g2d) {
		for(int i = 0; i < xploList.size(); i++){
			if(xploList.get(i).getDemarrer()) {
				xploList.get(i).dessiner(g2d);
			}
			if(xploList.get(i).terminer()) {
				xploList.remove(i);
			}
		}
	}
	/**
	 * Methode qui permet de demarrer la liste d'animation des explosions
	 */
	public void demarrerList() {
		for(int i = 0; i < xploList.size(); i++){
			xploList.get(i).demarrer();
		}
	}
	
	
	
	
	

}
