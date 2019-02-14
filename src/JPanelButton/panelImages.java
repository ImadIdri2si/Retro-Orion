package JPanelButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelaffichage.ModeleAffichage;
/**
 * la classe qui s'occupe de du JPanel Images
 * @author XU WEI DUO
 *
 */
public class panelImages extends JPanel {
	private String type;
	private Image img = null;
	private int scaleFactor = 1;
	private boolean first = true;
	private double width,height;
	private ModeleAffichage modele;

	/**
	  * initilialise le Jpanel et les ecouteurs pour la souris
	 * @param type, le type de l'image
	 * @param entry, la sacle de l'image
	 */
	public panelImages(String type, int entry) {
			this.type = type;
			this.scaleFactor = entry;
			linkImage(type);
			setBackground(Color.DARK_GRAY);
	}
	
	/**
	 * cette methode permet a charger une image avec cette Jpanel image
	 * @param type, le nom du vehicule
	 */
	public void linkImage(String type) {
		try {
			System.out.println("choice: "+type);
			switch(type) {
				case "Singer": img = ImageIO.read(getClass().getClassLoader().getResource("Singer.PNG"));			 
				break;
				case "Genessis": img = ImageIO.read(getClass().getClassLoader().getResource("Genessis.PNG"));		
				break;
				case "Drope": img = ImageIO.read(getClass().getClassLoader().getResource("Drope.PNG"));
				break;
				case "Reaper": img = ImageIO.read(getClass().getClassLoader().getResource("Reaper.PNG"));
				break;
				case "Covenent": img = ImageIO.read(getClass().getClassLoader().getResource("Covenent.PNG"));
				break;
				case "Meditator": img = ImageIO.read(getClass().getClassLoader().getResource("Meditator.PNG"));
				break;
				case "Decoder": img = ImageIO.read(getClass().getClassLoader().getResource("Decoder.PNG"));
				break;
				case "Zeroinger": img = ImageIO.read(getClass().getClassLoader().getResource("Zeroinger.PNG"));
				break;
				case "Unknow": img = ImageIO.read(getClass().getClassLoader().getResource("Unknow.PNG"));
				break;
				case "BlackDomain": img = ImageIO.read(getClass().getClassLoader().getResource("BD.PNG"));
				break;
				case "Obstacle": img = ImageIO.read(getClass().getClassLoader().getResource("Obstacle.PNG"));
				break;
				case "ReinforceTp1": img = ImageIO.read(getClass().getClassLoader().getResource("ReinforceTp1.PNG"));
				break;
				case "ReinforceTp2": img = ImageIO.read(getClass().getClassLoader().getResource("ReinforceTp2.PNG"));
				break;
				case "Abandon": img = ImageIO.read(getClass().getClassLoader().getResource("Abandon.jpg"));
				break;
				case "Cloud": img = ImageIO.read(getClass().getClassLoader().getResource("Cloud.jpg"));
				break;
				case "Terra": img = ImageIO.read(getClass().getClassLoader().getResource("Terra.jpg"));
				break;
				case "Proton": img = ImageIO.read(getClass().getClassLoader().getResource("Proton.png"));
				break;
				case "Electron": img = ImageIO.read(getClass().getClassLoader().getResource("Electron.png"));
				break;
				case "Fenetre1": img = ImageIO.read(getClass().getClassLoader().getResource("Fenetre1.jpg"));
				break;
				
				case "InfoBar": img = ImageIO.read(getClass().getClassLoader().getResource("InfoBar.PNG"));
				break;
				case "MissileTp1": img = ImageIO.read(getClass().getClassLoader().getResource("MissileTp1.PNG"));
				break;
				case "MissileTp2": img = ImageIO.read(getClass().getClassLoader().getResource("MissileTp2.PNG"));
				break;
				
				case "ChoixMode": img = ImageIO.read(getClass().getClassLoader().getResource("ChoixMode.jpg"));
				break;
				case "Apropos": img = ImageIO.read(getClass().getClassLoader().getResource("Apropos.jpg"));
				break;
				case "CS14D": img = ImageIO.read(getClass().getClassLoader().getResource("CS14D.jpg"));
				break;
				case "CS1FE": img = ImageIO.read(getClass().getClassLoader().getResource("CS1FE.jpg"));
				break;
				case "CS1FM": img = ImageIO.read(getClass().getClassLoader().getResource("CS1FM.jpg"));
				break;
				case "GU1": img = ImageIO.read(getClass().getClassLoader().getResource("GU1.jpg"));
				break;
				case "GU2": img = ImageIO.read(getClass().getClassLoader().getResource("GU2.jpg"));
				break;
				case "GU3": img = ImageIO.read(getClass().getClassLoader().getResource("GU3.jpg"));
				break;
			}
			this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
			setSize(img.getWidth(null),img.getHeight(null));
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
		repaint();
	}
	
	/**
	 * dessiner et initialiser un objet dans la scene
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(first) {
			
			//setSize(img.getWidth(null),img.getHeight(null));
			System.out.println(img.getWidth(null)+""+img.getHeight(null));
			width = getWidth();
			height = getHeight();
			modele = new ModeleAffichage(width, height, 0, 0, width,height);
			//mat = new AffineTransform();
			first=false;
		}
		//g2d.drawImage(img, (int)(width/2-img.getWidth(null)/2), 0, null);
		g2d.drawImage(img, modele.getMatMC(), null);
	}

}
