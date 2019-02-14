package AnimationPresentation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import obstacle.GererObstacle;
import obstacle.Obstacle;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Obsatcles extends JPanel {
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private GererObstacle obs;
	private Obstacle obsForme;
	private String etat;
	private String cote ;
	private String coin ;
	/**
	 * Create the panel.
	 */
	public Obsatcles() {
		setBackground(Color.WHITE);
		obsForme = new Obstacle(300, 90);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(obsForme.getZone().contains(new Point(e.getX(),e.getY()))) {
					etat = "Dans la zone";
				}else {
					etat = "Hors Zone";
				}
				if(obsForme.getRec1().contains(new Point(e.getX(),e.getY()))) {
					cote = "haut et bas";
				}else if(obsForme.getRec2().contains(new Point(e.getX(),e.getY()))) {
					cote = "gauche et droit";
				}else {
					cote = "Aucun coté touché";
				}
				if(obsForme.listCollider().get(0).contains(new Point(e.getX(),e.getY()))) {
					coin = "Coin haut droite";
					
				} else if(obsForme.listCollider().get(1).contains(new Point(e.getX(),e.getY()))) {
					coin = "Coin bas droite";
				} else if(obsForme.listCollider().get(2).contains(new Point(e.getX(),e.getY()))) {
					coin = "Coin haut gauche";
				} else if(obsForme.listCollider().get(3).contains(new Point(e.getX(),e.getY()))) {
					coin = "Coin bas gauche";
				}else {
					coin = "Aucun coin";
				}
				leverEvenCoin();
				leverEvenAnimCote();
				leverEvenDistanceZone();
			}
		});
		 obs = new GererObstacle();
		 obs.ajouterObs(100, 130);
		 obsForme.modifierTaille(2);
		 obs.setScaleAll(2);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING ,RenderingHints.VALUE_ANTIALIAS_ON);
		obs.dessiner(g2d);
		obsForme.dessiner(g2d);
		obsForme.dessinerContour(g2d);
		}
	
	public void addResultatsListener(ResultatsListener objEcouteur) {
		OBJETS_ENREGISTRES.add(ResultatsListener.class, objEcouteur);
	} 
	/**
	 * ecouteur de l'animation
	 */
	private void leverEvenCoin() {
		for(ResultatsListener ecout : OBJETS_ENREGISTRES.getListeners(ResultatsListener.class) ) {
			ecout.coin(coin);
			
		}
	}
	/**
	 * ecouteur de animation final
	 */
	private void leverEvenAnimCote() {
		for(ResultatsListener ecout : OBJETS_ENREGISTRES.getListeners(ResultatsListener.class) ) {
			ecout.cote(cote);
			
		}
	}
	/**
	 * ecouteur de la distanceA
	 */
	private void leverEvenDistanceZone() {
		for(ResultatsListener ecout : OBJETS_ENREGISTRES.getListeners(ResultatsListener.class) ) {
			ecout.zone(etat);
		}
	
	}
	

}
