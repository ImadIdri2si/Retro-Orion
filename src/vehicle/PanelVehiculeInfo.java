package vehicle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
/**
 * la classe qui s'occupe de du panel qui affiche tous les informations d'un vehicule
 * @author XU WEI DUO
 *
 */
public class PanelVehiculeInfo extends JPanel {

	private String type, player;
	private double width,height;
	private double acc = 2, speedMin=0,speedMax = 10, mass;
	private int scaleFactor = 2;
	private int originY = 240, dy=20;
	Image img = null;
	private JLabel lblName,lblSpeedmin,lblSpeedmax,lblAccel,lblWeight;
	
	/**\
	 * initilialise le Jpanel et les ecouteurs pour la souris
	 * @param player, le nom du joueur
	 * @param scaleFactor, le facteur de scale
	 * @param originY, le point origin Y
	 * @param dy, la distance en y entre chaque line
	 * @param fontSize, la gradeur des lettres
	 * @param dimX, la dimension en x
	 * @param dimY, la dimension en y
	 */
	public PanelVehiculeInfo(String player, int scaleFactor,int originY,  int dy, int fontSize, int dimX, int dimY) {
		setBackground(Color.DARK_GRAY);
		Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 2);
		setBorder(lineborder);
		setLayout(null);
		setSize(dimX,dimY);
		this.player = player;
		this.scaleFactor = scaleFactor;
		this.originY = originY;
		this.dy = dy;
		
		lblName = new JLabel(player);
		lblName.setForeground(Color.WHITE);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("OCR A Extended", Font.PLAIN, fontSize));
		lblName.setBounds(0, originY, getWidth(), 15);
		//lblName.setBorder(new LineBorder(Color.PINK, 5));
		add(lblName);
		
		//lblSpeedmin = new JLabel("SpeedMin");
		lblSpeedmin = new JLabel("Vitesse Min");
		lblSpeedmin.setForeground(Color.WHITE);
		lblSpeedmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedmin.setFont(new Font("OCR A Extended", Font.PLAIN, fontSize));
		lblSpeedmin.setBounds(0, originY+dy, getWidth(), 15);
		add(lblSpeedmin);
		
		//lblSpeedmax = new JLabel("SpeedMax");
		lblSpeedmax = new JLabel("Vitesse Max");
		lblSpeedmax.setForeground(Color.WHITE);
		lblSpeedmax.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedmax.setFont(new Font("OCR A Extended", Font.PLAIN, fontSize));
		lblSpeedmax.setBounds(0, originY+dy*2, getWidth(), 15);
		add(lblSpeedmax);
		
		//lblAccel = new JLabel("Accel");
		lblAccel = new JLabel("Acc\u00E9l\u00E9ration");
		lblAccel.setForeground(Color.WHITE);
		lblAccel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccel.setFont(new Font("OCR A Extended", Font.PLAIN, fontSize));
		lblAccel.setBounds(0, originY+dy*3, getWidth(), 15);
		add(lblAccel);
		
		//lblWeight = new JLabel("Weight");
		lblWeight = new JLabel("Masse");
		lblWeight.setForeground(Color.WHITE);
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setFont(new Font("OCR A Extended", Font.PLAIN, fontSize));
		lblWeight.setBounds(0, originY+dy*4, getWidth(), 15);
		add(lblWeight);

	}

	/**
	 * initilialise le Jpanel et les ecouteurs pour la souris
	 * @param player, le nom du player
	 */
	public PanelVehiculeInfo(String player) {
		this(player,2,240,20,10,160,245);
	}
	/**
	 * choisir le type de vehicule
	 * @param entry, le nom du vehicule
	 */
	public void setType(String entry) {
		if(entry==null || entry=="" || entry.length()==0) {
			this.type = "Unknow";
		}else {
			this.type = entry;
		}
		
		
		
		linkImage(type);
		//lblName.setText(player+": [" + type+"]");
		//lblSpeedmin.setText("speedMin: "+speedMin+"km/s");
		//lblSpeedmax.setText("speedMax: "+speedMax+"km/s");
		//lblAccel.setText("Accel: "+acc+"km/m^2");
		//lblWeight.setText("Weight: "+mass+"kg");
		lblName.setText(player+": [" + type+"]");
		lblSpeedmin.setText("Vitesse Min: "+speedMin+"km/s");
		lblSpeedmax.setText("Vitesse Max: "+speedMax+"km/s");
		lblAccel.setText("Acc\u00E9l\u00E9ration: "+acc+"km/m^2");
		lblWeight.setText("Masse: "+mass+"kg");
		repaint();
	}
	
	/**
	 * cette classe permet d'accorder une imgae a un vehicule choisi et tous ses informations
	 * @param type, pr¨¦ciser le type du v¨¦hicule parmi les choix offerts 
	 */
	private void linkImage(String type) {
		VehiculesInfo choice = null;
		 double[] infos;
		System.out.println("choice: "+type);
		switch(type) {
			case "Singer": choice = new VehiculesInfo(type);	 
			break;
			case "Genessis":choice = new VehiculesInfo(type);	
			break;
			case "Drope": choice = new VehiculesInfo(type);
			break;
			case "Reaper": choice = new VehiculesInfo(type);
			break;
			case "Covenent": choice = new VehiculesInfo(type);
			break;
			case "Meditator": choice = new VehiculesInfo(type);
			break;
			case "Decoder": choice = new VehiculesInfo(type);
			break;	
			case "Zeroinger": choice = new VehiculesInfo(type);
			break;	
			case "Unknow":  choice = new VehiculesInfo("Unknow");
			break;
		}
		//if(choice!=null) {
			infos = choice.getInfo();
			img =choice.getImg();
			this.acc = infos[1];
			this.speedMin = infos[0];
			this.speedMax = infos[2];
			mass = infos[3];
			this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
			setBackground(choice.getColor());
		/**
		}else {
			img = null;
			this.acc = 0;
			this.speedMin = 0;
			this.speedMax = 0;
			mass = 0;
			this.type = "UNKNOW";
			setBackground(Color.DARK_GRAY);
		}
		**/
	
	}
	
	/**
	 * dessiner et initialiser un objet dans la scene
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		width = getWidth();
		height = getHeight();
		if(img!=null)g2d.drawImage(img,(int)(width/2-img.getWidth(null)/2),dy,null);
		
		//textToCenter(lblName,g2d);
		//textToCenter(lblSpeedmin,g2d);
		//textToCenter(lblSpeedmax,g2d);
		//textToCenter(lblAccel,g2d);
		//textToCenter(lblWeight,g2d);
		
	}
	
	/**
	public void textToCenter(JLabel entry, Graphics2D g2d) {
		int reminderLength = g2d.getFontMetrics().stringWidth(entry.getText());
		entry.setBounds((int)(width/2-reminderLength/2), entry.getY(), entry.getWidth(), entry.getHeight());
	}
	**/
}
