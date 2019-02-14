package vehicle;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 * cette classe s'occupe des informations des vehciules et leurs images
 * @author XU WEI DUO
 *
 */
public class VehiculesInfo {

	private double[] infos;
	private Image img;
	private Color headColor;
	private double acc, speedMin,speedMax, mass;
	
	/**
	 * le constructeur qui precise le nom du vehicule choisi
	 * @param type, le nom du vehicule choisi
	 */
	public VehiculesInfo(String type) {
		
		linkImage(type);
	}
	
	/**
	 * cette methode renvoie tous les informations numeriques du vehicule choisi
	 * @return, les informations numeriques du vehicule choisi
	 */
	public double[] getInfo() {
		return infos = new double[] {speedMin,acc,speedMax,mass};
	}
	
	/**
	 * cette methode renvoie l'image du vehicule choisi
	 * @return, l'image du vehicule choisi
	 */
	public Image getImg() {
		return img;
	}
	
	/**
	 * cette methode renvoie la couleur du vehicule choisi
	 * @return, la couleur du vehicule choisi
	 */
	public Color getColor() {
		return headColor;
	}
	
	/**
	 * cette methode permet a associer une image d'un vehicule choisi et ainsi tous ses informations 
	 * @param type, le nom du vehicule choisi
	 */
	private void linkImage(String type) {
		try {
			System.out.println("choice: "+type);
			switch(type) {
			
				case "Singer": img = ImageIO.read(getClass().getClassLoader().getResource("Singer.PNG"));
								headColor = new Color(245,153,153);
								speedMin = 3.6; 
								acc = 2.1;
								speedMax = 9;
								mass = 32000;
							 
				break;
				case "Genessis": img = ImageIO.read(getClass().getClassLoader().getResource("Genessis.PNG"));
								headColor = new Color(251,197,235);
								speedMin = 2.2; //km/s
								acc = 3.4;
								speedMax = 10;
								mass = 40430;
							
				break;
				case "Drope": img = ImageIO.read(getClass().getClassLoader().getResource("Drope.PNG"));
								headColor = new Color(204,204,204);
								speedMin = 4.4; //km/s
								acc = 0.6;
								speedMax = 6;
								mass = 10000;
			
				break;
				case "Zeroinger": img = ImageIO.read(getClass().getClassLoader().getResource("Zeroinger.PNG"));
								headColor = new Color(252,189,255);
								speedMin = 3.4; //km/s
								acc = 4.3;
								speedMax = 18;
								mass = 44333;

				break;
				case "Reaper": img = ImageIO.read(getClass().getClassLoader().getResource("Reaper.PNG"));
								headColor = new Color(22,154,14);
								speedMin = 3.3; //km/s
								acc = 1.8;
								speedMax = 8;
								mass = 20200;
			
				break;
				case "Covenent": img = ImageIO.read(getClass().getClassLoader().getResource("Covenent.PNG"));
								headColor = new Color(92,0,173);
								speedMin = 2.6; //km/s
								acc = 1.4;
								speedMax = 9;
								mass = 30300;
			
				break;
				case "Meditator": img = ImageIO.read(getClass().getClassLoader().getResource("Meditator.PNG"));
								headColor = new Color(203,35,87);
								speedMin = 1.4; //km/s
								acc = 3.9;
								speedMax = 16;
								mass = 66666;
			
				break;
				case "Decoder": img = ImageIO.read(getClass().getClassLoader().getResource("Decoder.PNG"));
								headColor = new Color(0,72,203);
								speedMin = 3.4; //km/s
								acc = 2.1;
								speedMax = 7;
								mass = 40000;
			
				break;
				case "Unknow": img = ImageIO.read(getClass().getClassLoader().getResource("Unknow.PNG"));
							headColor = new Color(80,80,80);
							speedMin = 0.43; //km/s
							acc = 0.34;
							speedMax = 1;
							mass = 4444333;
				break;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
	}
}
