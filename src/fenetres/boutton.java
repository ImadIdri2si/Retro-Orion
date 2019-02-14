package fenetres;

import java.awt.AlphaComposite;
import java.awt.Color;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.UIManager;
/**
 * Classe qui permet de creer un bouton de forme rectangulaire et de lui ajouter une animation dessus lorsque la souris et dessus
 * @author Imad Idrissi
 *
 */

public class boutton extends JPanel {
	private Rectangle2D boutton; 
	//Position
	private int x,y;
	//etat du bouton
	private boolean etatSurBoutton = false;
	//Gestion du niveau de couleur
	private int delta = 10;
	private int max = 255, min =0;
	private int niveau;
	//Couleur
	private Color color2 = new Color(255,0,0);
	private Color color1 = new Color(123,104,238);
	private GradientPaint objet;
	// Timer
	private Timer timer;
	

	/**
	 * Creation du panel
	 */
	public boutton() {
		//écouteur qui permet de savoir si la sourie est sur le rectangle
		//setBackground(new Color(0,0,0,0));
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(boutton.contains(e.getX(), e.getY())) {
					
					etatSurBoutton = true;
				}else {
					etatSurBoutton = false;
				}
				repaint();
				
			}
		});
		// Un écouteur pour joué avec le niveau de couleur
		 ActionListener action = new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                if(etatSurBoutton) {
                	niveau += delta;
                    if (niveau > max) {
                        niveau =  max;
                    }
                    color2 = new Color(255,0,0,niveau);
                    System.out.println(etatSurBoutton);
                    repaint();
                }else if(!etatSurBoutton){
                	niveau -= delta;
                    if (niveau < min) {
                        niveau =  min;
                    }
                    color2 = new Color(255,0,0,niveau);
                    //System.out.println(hue);
                    System.out.println(etatSurBoutton);
                    repaint();
                	

                }
               }

         };
         // Le temps pour faire une itération 
         timer = new Timer(10, action);
         timer.start();
         
     }
		

	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		boutton = new Rectangle2D.Double(2, 2, getWidth()-1, getHeight()-1);
		//toujours de la meme couleur
		objet = new GradientPaint(0,0, Color.red, 150, 100, color2);
		g2d.setPaint(objet);
		g2d.fill(boutton);
		g2d.setColor(Color.red);
		g2d.fill(boutton);
		//System.out.println(etatSurBoutton);
		
	}//fin
	
	
	

}
