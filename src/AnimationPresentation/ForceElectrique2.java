package AnimationPresentation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import canon.Canon;
import explosionAnim.Explosion;
import gestion.GestionCollision;
import gestion.GestionPhysique;
import physique.ForceElectrique;
import vecteur.Vecteur;

public class ForceElectrique2 extends JPanel implements Runnable {
	private GestionPhysique force;
	private ForceElectrique forceEl;
	private Canon canon1;
	private boolean tirer = true;
	private boolean uneBalle = false;
	private GestionCollision col;
	private Explosion expolision;




	/**
	 * Create the panel.
	 */
	public ForceElectrique2() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTirer(true);
				
			
			}
		});
		forceEl = new ForceElectrique();
		canon1 = new Canon(300,30);
		forceEl.ajouterChargeFixe(new Vecteur(100,150),- 50);
		forceEl.ajouterChargeFixe(new Vecteur(400,150),  30);
		expolision = new Explosion();
		force = new GestionPhysique(canon1, forceEl);
		col = new GestionCollision(canon1,null,expolision  );

	}
	
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING ,RenderingHints.VALUE_ANTIALIAS_ON);
		
		//obs.dessiner(g2d);
		col.setTaille(new Vecteur(getWidth(), getHeight()));
		col.collisionDetected(null);	
		force.appliquerForceElectrique(g2d);
		force.dessiner(g2d, getWidth(),getHeight());	
		canon1.dessiner(g2d);
		expolision.dessinerList(g2d);
		//System.out.println(canon1.getVitesseVec().toString());
		}
	public void run() {
		while(tirer) {
			if(uneBalle) {
				canon1.setDirection(new Vecteur(Math.random()*1,Math.random()*1));
				canon1.tirer();
				//canon1.ajouterTarget(500, 500);
				System.out.println("actionner");
				uneBalle = false;
			}
			
			repaint();
			try {
				Thread.sleep(10);
			}catch (InterruptedException e) {
				System.out.println("Exceptions dans run");
			}
		}
		
	}
	public void demarrer() {
		Thread animCanon = new Thread(this);
		animCanon.start();
		System.out.println("Dans le demarrage");
	}
	public void setTirer(Boolean etat) {
		uneBalle = etat;
	}

}
