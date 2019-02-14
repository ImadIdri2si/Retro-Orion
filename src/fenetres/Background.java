package fenetres;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 
 * @author Imad Imad
 * Affiche le background
 */
public class Background extends JPanel {
	private Image backgroud = null;

	/**
	 * Create the panel.
	 */
	public Background() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroud, 0, 0, getWidth(), getHeight(), null);
	}
	public void ImageUrl(String url) {
			URL fich = getClass().getClassLoader().getResource(url);
			if (fich == null ) {
				JOptionPane.showMessageDialog(null,	"Fichier d'image introuvable!");
			} else {
				//terminer l'acquisition de l'image ici
				try {
					backgroud = ImageIO.read(fich);
					
				}
				catch (IOException e) {
					System.out.println("Erreur pendant la lecture du fichier d'image");
				}
			}
		}
	

}
