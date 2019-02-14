package AnimationPresentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import canon.Canon;
import explosionAnim.Explosion;
import gestion.GestionCollision;
import obstacle.GererObstacle;
import vecteur.Vecteur;
import java.awt.event.MouseMotionAdapter;

public class Missile extends JPanel implements Runnable {
	private Canon canon1;
	private boolean tirer = true;
	private boolean uneBalle = false;
	private GestionCollision col;
	private Explosion expolision;
	private GererObstacle obs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Missile() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				canon1.target(e.getX(), e.getY());
			}
		});
		canon1 = new Canon(100,100);
		expolision = new Explosion();
		

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTirer(true);
				
			
			}
		});
		setBackground(Color.blue);
	    obs = null;
	   
		col = new GestionCollision(canon1,obs, expolision );
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING ,RenderingHints.VALUE_ANTIALIAS_ON);
		canon1.dessiner(g2d);
		col.setTaille(new Vecteur(getWidth(), getHeight()));
		col.collisionDetected(null);	
		expolision.dessinerList(g2d);
		//System.out.println(canon1.getVitesseVec().toString());
		}
	
	public void run() {
		while(tirer) {
			if(uneBalle) {
				canon1.setDirection(new Vecteur(1*Math.random(),1*Math.random()));
				canon1.tirer();
				canon1.tirerMissileChercheur(50);
				uneBalle = false;
				
			}
			repaint();
			try {
				Thread.sleep(50);
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
