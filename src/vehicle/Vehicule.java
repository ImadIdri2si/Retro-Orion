package vehicle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import canon.Canon;
import modelaffichage.ModeleAffichage;
import objects.WDwarf;
import vecteur.Vecteur;
import vecteur.VecteurGraphique;
/**
 * cette classe s'occupe d'une véhicule entière, avec tous les physiques et les évènement reliés. 
 * @author XU WEI DUO
 *
 */
public class Vehicule {
	
	private Ellipse2D.Double[] tail;
	private int tailDim, tailLimit;
	private int tailHead;
	private double radiusX, radiusY;
	private Color headColor;
	private Color tailColor;
	private int scaleFactor = 4;
	
	private char u,d,l,r,s;
	
	private boolean up, down, left, right;
	double x,y;
	double angle;
	int angleBound=5;
	
	double width, height;
	
	Image img = null;
	Rectangle2D.Double headCollider, collider;
	
	private AffineTransform modele;
	private AffineTransform mat;
	private double scaleX, scaleY;
	
	private double speed = 0, acc = 2, speedMin=0,speedMax = 10,accTime=0, mass;
	private double dx=0, dt=0;
	private double speedFctor=1;
	
	private Vecteur vecImg, vecMov;//, vecCap;
	
	private boolean captation = false;
	
	private boolean sciMode = false;
	
	private boolean suddenD = false;
	
	private String vehiculeName;
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour le vehicule et sa queue 
	 * @param xHead, la position X de la tete du queue
	 * @param yHead, la position Y de la tete du queue
	 * @param radiusEntry, le rayon des ellipses qui forment le queue
	 * @param tailLimit, la limit du queue
	 * @param modEntry, matrice de tranformation
	 * @param pos, quelle partie du clavier appartenait ce vehicule
	 * @param type, qulle est le type du vehicule
	 * @param widthLimit, la limit en X
	 * @param heightLimit, la limit en Y
	 */
	public Vehicule(double xHead, double yHead, int radiusEntry, int tailLimit,AffineTransform modEntry, String pos, String type, double widthLimit, double heightLimit) {
		this.tailDim = 1;
		x=xHead;
		y=yHead;
		width = widthLimit;
		height = heightLimit;
		this.modele = modEntry;
		scaleX = modele.getScaleX();
		scaleY = modele.getScaleY();
		scaleFactor = (int)(scaleFactor);
		//System.out.println("image scale: "+scaleFactor);
		System.out.println("sX "+scaleX+" and sY "+ scaleY);
		this.tailLimit = tailLimit;
		this.tail = new Ellipse2D.Double[tailLimit]; 
		
		this.radiusX = scaleX*radiusEntry;
		this.radiusY = scaleY*radiusEntry;
		tail[0]= new Ellipse2D.Double(scaleX*xHead-radiusX/2.0,scaleY*yHead-radiusY/2.0, radiusX, radiusY);
		this.tailHead = 0;
		
		vehiculeName = type;
		linkImage(vehiculeName);
		
		if(pos.equals("l")) {
			u='w';
			d='s';
			l='a';
			r='d';
			s='q';
		}else if(pos.equals("r")) {
			u='i';
			d='k';
			l='j';
			r='l';
			s='o';
		}
		
	}
	
	/**
	 * cette methode permet a renvoyer le nom du vehicule choisi
	 * @return, le nom du vehicule choisi
	 */
	public String getVehiculeName() {
		return vehiculeName;
	}
	
	/**
	 * cette classe permet d'accorder une imgae et deux colliders à un véhicule choisi
	 * @param type, préciser le type du véhicule parmi les choix offerts 
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
			case "Unknow": choice = new VehiculesInfo(type);
			break;	
		}
		infos = choice.getInfo();
		img =choice.getImg();
		headColor = choice.getColor();
		this.speed = infos[0];
		this.acc = infos[1];
		this.speedMin = speed;
		this.speedMax = infos[2];
		mass = infos[3];
		this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
		headCollider = new Rectangle2D.Double(img.getWidth(null)/7,0, 5*img.getWidth(null)/7, 2);//img.getHeight(null));
		collider = new Rectangle2D.Double(0,0, img.getWidth(null), img.getHeight(null));
	}
	
	/**
	private void linkImage(String type) {
		try {
			System.out.println("choice: "+type);
			switch(type) {
			
				case "Singer": img = ImageIO.read(getClass().getClassLoader().getResource("Singer.png"));
								headColor = new Color(245,153,153);
								this.speed = 3.6; //km/s
								this.acc = 2.1;
								this.speedMin = speed;
								this.speedMax = 9;
								mass = 32000;
							 
				break;
				case "Genessis": img = ImageIO.read(getClass().getClassLoader().getResource("Genessis.png"));
								headColor = new Color(251,197,235);
								this.speed = 2.2; //km/s
								this.acc = 3.4;
								this.speedMin = speed;
								this.speedMax = 10;
								mass = 40430;
							
				break;
				case "Drope": img = ImageIO.read(getClass().getClassLoader().getResource("Drope.png"));
								headColor = new Color(204,204,204);
								this.speed = 4.4; //km/s
								this.acc = 0.6;
								this.speedMin = speed;
								this.speedMax = 6;
								mass = 10000;
			
				break;
				case "Reaper": img = ImageIO.read(getClass().getClassLoader().getResource("Reaper.png"));
								headColor = new Color(22,154,14);
								this.speed = 3.3; //km/s
								this.acc = 1.8;
								this.speedMin = speed;
								this.speedMax = 8;
								mass = 30300;
			
				break;
				case "Covenent": img = ImageIO.read(getClass().getClassLoader().getResource("Covenent.png"));
								headColor = new Color(92,0,173);
								this.speed = 2.6; //km/s
								this.acc = 1.4;
								this.speedMin = speed;
								this.speedMax = 9;
								mass = 30300;
			
				break;
				case "Meditator": img = ImageIO.read(getClass().getClassLoader().getResource("Meditator.png"));
								headColor = new Color(203,35,87);
								this.speed = 1.4; //km/s
								this.acc = 3.2;
								this.speedMin = speed;
								this.speedMax = 13;
								mass = 66666;
			
				break;
				case "Decoder": img = ImageIO.read(getClass().getClassLoader().getResource("Decoder.png"));
								headColor = new Color(0,72,203);
								this.speed = 3.4; //km/s
								this.acc = 2.1;
								this.speedMin = speed;
								this.speedMax = 7;
								mass = 40000;
			
				break;
				
			}
			this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
			headCollider = new Rectangle2D.Double(img.getWidth(null)/7,0, 5*img.getWidth(null)/7, 2);//img.getHeight(null));
			collider = new Rectangle2D.Double(0,0, img.getWidth(null), img.getHeight(null));
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
	}
	**/
	
	/**
	 * cette méthode retourne l'image qui a été accordée au véhicule
	 * @return, retourne l'image du véhicule
	 */
	public Image getImg() {
		return img;
	}
	
	/**
	 * cette méthode permet de spécifier les actions lorsqu'il y a eu un combo au clavier
	 * @param smooth, si le véhicule tourne de façon smooth (degré par degré) ou non
	 * @param limit, l'angle d'arrivé de la rotation pour le véhicule
	 * @param fct, le véhicule tourne par quel degré à chaque instance
	 * @param rot, l'angle actuel du véhicule
	 */
	private void combo(boolean smooth, int limit, int fct, int rot) {
		this.setMove(speedFctor,false);
		if(smooth) {
			this.setAngle(this.rotate(this.getAngle(),limit,fct));
		}else {
			this.setAngle(rot);
		}
	}
	
	/**
	 * cette méthode permet à itentifier les évènements du clavier et faire quoi si'il y a une collision
	 * @param keys, la chaine boolean de tous les keys du claiver qui sont convertible en char
	 * @param collision, si ou on non il y a eu une collision
	 * @param smooth, si le véhicule tourne de façon smooth (degré par degré) ou non
	 * @param dwarf, les DWarfs
	 */
	public void keysUpdate(boolean[] keys, boolean collision, boolean smooth, WDwarf dwarf) {
		
		if(collision) {
			speedFctor = -8;
		}else {
			if(speedFctor<1) {
				speedFctor++;
			}
		}
		
		if((keys[u] || keys[u]) && (keys[l] || keys[l])) {
			this.setKeys(true, false, true, false);
			combo(smooth,315,angleBound,-45);
			vecMov = new Vecteur(Math.sin(Math.toRadians(-45)),Math.cos(Math.toRadians(135)));
			//System.out.println(Math.sqrt((vecMov.getX()*vecMov.getX())+(vecMov.getY()*vecMov.getY())));
			return;
		}
		if((keys[u] || keys[u]) && (keys[r] || keys[r])) {
			this.setKeys(true, false, false, true);
			combo(smooth,45,angleBound,45);
			vecMov = new Vecteur(Math.sin(Math.toRadians(45)),Math.cos(Math.toRadians(-135)));
			//System.out.println(Math.sqrt((vecMov.getX()*vecMov.getX())+(vecMov.getY()*vecMov.getY())));
			return;
		}
		if((keys[d] || keys[d]) && (keys[l] || keys[l])) {
			this.setKeys(false, true, true, false);
			combo(smooth,225,angleBound,-45-90);
			vecMov = new Vecteur(Math.sin(Math.toRadians(-135)),Math.cos(Math.toRadians(45)));
			//System.out.println(Math.sqrt((vecMov.getX()*vecMov.getX())+(vecMov.getY()*vecMov.getY())));
			return;
		}
		if((keys[d] || keys[d]) && (keys[r] || keys[r])) {
			this.setKeys(false, true, false, true);
			combo(smooth,135,5,45+90);
			vecMov = new Vecteur(Math.sin(Math.toRadians(135)),Math.cos(Math.toRadians(-45)));
			//System.out.println(Math.sqrt((vecMov.getX()*vecMov.getX())+(vecMov.getY()*vecMov.getY())));
			return;
		}
		
		if(keys[u] || keys[u]) {
			this.setKeys(true, false, false, false);
			if(!smooth)this.setAngle(0);
			vecMov = new Vecteur(0,-1);
		}
		if(keys[d] || keys[d]) {	
			this.setKeys(false, true, false, false);
			if(!smooth)this.setAngle(180);
			vecMov = new Vecteur(0,1);
			//betaAngle = 180;
		}
		if(keys[l] || keys[l]) {	
			this.setKeys(false, false, true, false);
			if(!smooth)this.setAngle(270);
			vecMov = new Vecteur(-1,0);
			//betaAngle = 270;
		}
		if(keys[r] || keys[r]) {
			this.setKeys(false, false, false, true);
			if(!smooth)this.setAngle(90);
			vecMov = new Vecteur(1,0);
			//betaAngle = 90;
		}
		
		if(tail[tailDim-1]!=null && speed+0.1>speedMax && tailDim>1) {
			//if(tailDim-1!=tailHead)
			dwarf.spray(tail[tailDim-1].getCenterX(), tail[tailDim-1].getCenterY(), tail[tailDim-1].getWidth()*12);
			tailDestroy();
		}
		

		if((keys[s])&& accTime<=1 && speed<speedMax && tailDim>1) {
			accTime=accTime+0.001;
			speed = speed + acc*accTime*tailDimByLimitRatio();
			//System.out.println("acceleration");
		
		}else {
			if(speed>speedMin ) {
				if(accTime>0) {
					speed = speed - acc*accTime*tailDimByLimitRatio();
					accTime = accTime-0.001;
					///accReady = false;
				}else if(speed>speedMin) {
					speed = speed - 1;	
				}
				if(this.equalDouble(speedMin, speed, 0.001)) {
					speed = speedMin;
					accTime = 0;
				}
			}else if(speed<speedMin){
				speed = speed + 0.1;	
			
				if(this.equalDouble(speedMin, speed, 0.1)) {
					speed = speedMin;
					accTime = 0;
				}
			}else {
				accTime = 0;
			}
			
			
		}
		
		
		if(smooth) {
			this.setMove(speedFctor,true);
		}else {
			this.setMove(speedFctor,false);
		}
	}
	
	/**
	 * cette méthode permet d'itentifier quelle(s) est(sont) le(s) dernier(s) touche(s) qui a(ont) été appuyé(es) sur le clavuer
	 * @param up, la touche qui représente le haut 
	 * @param down, la touche qui représente le bas
	 * @param left, la touche qui représente la gauche 
	 * @param right, la touche qui représente la droite
	 */
	private void setKeys(boolean up, boolean down, boolean left, boolean right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * cette méthode permet de recevoir un vecteur de mouvement pour déplacer le véhicule 
	 * @param vecEnt, le vecteur de mouvement 
	 */
	public void setMove(Vecteur vecEnt) {
		
		y = y + speedFctor*vecEnt.getY()*speed;
		x = x + speedFctor*vecEnt.getX()*speed;
		//System.out.println("--in cap moving:" + vecEnt.getX()*speed +" and "+ vecEnt.getY()*speed+ "per sec-- vecMov: " + this.vecMov.module());
		this.setTail(x,y);
	}
	
	/**
	 * cette méthode permet de faire déplacer le véhicule
	 * @param factor, si la véhicule recule ou non
	 * @param rotate, de quel angle troune le véhicule
	 */
	private void setMove(double factor, boolean rotate) {
		if(up ) {
			y = y + factor*vecMov.getY()*speed;
			if(rotate)this.setAngle(this.rotate(angle,0,angleBound));
			//System.out.println("position: "+ factor*vecMov.getY()*speed +" with:" + factor+" vecMovY: "+ vecMov.getY()+" speed: "+ speed);
		}
		
		if(down) {
			y = y +  factor*vecMov.getY()*speed;
			if(rotate)this.setAngle(this.rotate(angle,180,angleBound));
			//System.out.println("position: "+ factor*vecMov.getY()*speed +" with:" + factor+" vecMovY: "+ vecMov.getY()+" speed: "+ speed);
		}
		
		if(left ) {
			x = x +  factor*vecMov.getX()*speed;
			if(rotate)this.setAngle(this.rotate(angle,270,angleBound));
			//System.out.println("position: "+ factor*vecMov.getX()*speed +" with:" + factor+" vecMovX: "+ vecMov.getX()+" speed: "+ speed);
		}
		
		if(right ) {
			x = x +  factor*vecMov.getX()*speed;
			if(rotate)this.setAngle(this.rotate(angle,90,angleBound));
			//System.out.println("position: "+ factor*vecMov.getX()*speed +" with:" + factor+" vecMovX: "+ vecMov.getX()+" speed: "+ speed);
		}	
		this.setTail(x,y);
	}
	
	/**
	 * cette méthode permet de recevoir un nouveau angle de rotation du véhicule
	 * @param entry, l'angle  de rotation en degré
	 */
	public void setAngle(double entry) {
		this.angle = entry;
	}
	
	/**
	 * cette méthode envoie l'angle de rotation du véhicule
	 * @return, l'angle de rotaiton en degré
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * cette méthode envoie la vitesse du véhicule
	 * @return, la vitesse du véhicule
	 */
	public double getSpeed() {
		return speed*1000;
	}
	
	/**
	 * cette methode permet a envoyer la vitesse minimale du vehicule
	 * @return, la vitesse minimale du vehicule
	 */
	public double getSpeedMin() {
		return speedMin*1000;
	}
	
	/**
	 * cette éthode permet de recevoir une nouvelle vitesse pour le véhicule
	 * @param entry, la nouvelle vitesse pour le véhicule
	 */
	public void setSpeed(double entry) {
		speed = entry/1000;
		//System.out.println(speed);
	}
	
	/**
	 * cette méthode envoie la position en X de la tête de la queue 
	 * @return, la posiiton en x de la tête de la queue
	 */
	public double getX() {
		return x*1000;
	}
	
	/**
	 * cette éthode permet de recevoir une nouvelle position en X pour la tête de la queue  
	 * @param entry, la nouvelle posiiton en x pour la tête de la queue
	 */
	public void setX(double entry) {
		x = entry/1000;
	}
	
	/**
	 * cette méthode envoie la position en Y de la tête de la queue 
	 * @return, la posiiton en y de la tête de la queue
	 */
	public double getY() {
		return y*1000;
	}

	/**
	 * cette éthode permet de recevoir une nouvelle position en Y pour la tête de la queue  
	 * @param entry, la nouvelle posiiton en y pour la tête de la queue
	 */
	public void setY(double entry) {
		y = entry/1000;
	}
	
	/**
	 * cete méthode permet à dessiner l'image du véhicule ainsi ses colliders
	 * @param g2d, contexte graphique
	 */
	public void drawLmg(Graphics2D g2d) {
		mat = new AffineTransform(modele);
		Color colorBack = g2d.getColor();
		//this.angle = angleEntry;
		vecImg = new Vecteur();
		double rotAngleCapt=0;
		if(captation) {
			
			//System.out.println(Math.atan(vecCap.getY()/vecCap.getX()));
			if(vecMov.getX()>0) {
				rotAngleCapt = Math.atan(vecMov.getY()/vecMov.getX())+Math.PI/2;
			}else {
				rotAngleCapt = Math.atan(vecMov.getY()/vecMov.getX())-Math.PI/2;
			}
			mat.rotate(rotAngleCapt,x,y);
			
		}else {
		
			mat.rotate(Math.toRadians(angle),x,y);
		}
		vecImg.setXY(Math.sin(Math.toRadians(angle))*acc,Math.cos(Math.toRadians(angle))*-acc);
		

		mat.translate((x-(img.getWidth(null)/2.0)),(y-9*img.getHeight(null)/10.0));
		
		g2d.drawImage(img,mat,null);
		
		
		if(sciMode && vecMov!=null) {
			g2d.setColor(Color.RED);
			g2d.draw(mat.createTransformedShape(collider));
			g2d.setColor(Color.GREEN);
			g2d.fill(mat.createTransformedShape(headCollider));
			AffineTransform temp = g2d.getTransform();
			int strBound = 20;
			g2d.setColor(Color.BLACK);
			if(captation) {
				//g2d.rotate(rotAngleCapt,x,y);
				g2d.drawString("---Orientation: [" + String.format("%.3f",Math.toDegrees(rotAngleCapt))+"]deg ", (int)x+20,(int)y+2*strBound);
			}else {
				//g2d.rotate(Math.toRadians(angle),x,y);
				g2d.drawString("---Orientation: [" + String.format("%.3f",angle)+"]deg ", (int)x+20,(int)y+2*strBound);
			}
			g2d.drawString("---SPEED: [" + String.format("%.3f",speed)+"]km/s ", (int)x+20,(int)y+3*strBound);
			g2d.drawString("---ACC: [" + String.format("%.3f",acc*tailDimByLimitRatio())+"]km/s^2 ", (int)x+20,(int)y+4*strBound);
			g2d.drawString("---tail Length: [" + tailDim+"]/"+tailLimit, (int)x+20,(int)y+5*strBound);
			g2d.drawString("---vecMov Length: [" + String.format("%.3f",vecMov.module())+"]u ", (int)x+20,(int)y+6*strBound);
			g2d.drawString("--- SPEEDFactor: [" + speedFctor+"] ", (int)x+20,(int)y+7*strBound);
			
			g2d.setTransform(temp);
		}
		g2d.setColor(colorBack);
		//VecteurGraphique vecGraph = new VecteurGraphique(vecImg);
		
		//System.out.println(vector.getX()+" and "+vector.getY());
		
		//System.out.println(matBeta.getTranslateX() + ", " + matBeta.getTranslateY() +" | "+ bx+", "+by);
	}
	
	/**
	 * cette methode permet a calculer un facteur pour l'acceleration qui depend de la longeur de la queue
	 * @return, un facteur pour l'acceleration qui depend de la longeur de la queue
	 */
	public double tailDimByLimitRatio() {
		if(tailDim>1) {
			return (1-((double)tailDim/(double)tailLimit));
		}else {
			return 1;
		}
		
	}
	
	/**
	 * cette methode permet a renvoyer l'acceleration avec son facteur 
	 * @return, l'acceleration avec son facteur 
	 */
	public double getAcc() {
		return acc*tailDimByLimitRatio();
	}
	
	/**
	 * cette methode permet `a activer le mode scientifique
	 * @param entry, si oui alors active le mode scientifique
	 */
	public void setScfMode(boolean entry) {
		sciMode = entry;
	}
	
	/**
	 * cette methode permet `a savoir si le mode scientifique est active ou non
	 * @return, retourne si le mode scientifique est active ou non
	 */
	public boolean getScfMode() {
		return sciMode;
	}
	
	/**
	 * cette méthode envoie le vecteur associé à l'image
	 * @return, le vécteur associé à l'image du véhicule
	 */
	public Vecteur getVecImg() {
		return vecImg;
	}
	
	/**
	 * cette méthode envoie le vecteur associé au déplacement et à l'orientation du véhicule
	 * @return, le vecteur associé au déplacement et à l'orientation du véhicule
	 */
	public Vecteur getVecMov() {
		return vecMov;
	}
	
	/**
	 * cette méthode permet à recevoir un nouveau vecteur associé au déplacement et à l'orientation du véhicule
	 * @param vec, le nouveau vecteur associé au déplacement et à l'orientation du véhicule
	 */
	public void setVecMov(Vecteur vec) {
		vecMov = vec;
	}
	
	/**
	 * cette méthode permet à recevoir une confirmation pour savoir si le véhicule a été capturé par une force externe ou non
	 * @param cap, permet à confirmer si le véhicule a été capturé par une force externe ou non
	 */
	public void setCaptation(boolean cap) {
		captation = cap;
		if(!captation) {
			if(up) {
				vecMov = new Vecteur(0,-1);
			}else if(down) {
				vecMov = new Vecteur(0,1);
			}else if(left) {
				vecMov = new Vecteur(-1,0);
			}else if(right) {
				vecMov = new Vecteur(1,0);
			}
		}
		
	}
	
	/**
	 * cette methode retorune vrai si le vehicule a ete capturer
	 * @return, retorune vrai si le vehicule a ete capturer
	 */
	public boolean getCaptation() {
		return captation;
	}
	
	/**
	 * cette méthode permet à agrandir la queue
	 * @param xEntry, la position en x pour une nouvelle ellipse de la queue
	 * @param yEntry, la position en y pour une nouvelle ellipse de la queue
	 */
	private void setTail(double xEntry, double yEntry) {
		//dx = dx+speed;
		//dt++;
		//System.out.println("speed :"+speed+"km/s" + " and total length: "+dx +"km with total time: "+dt+"s");
		//if(x<0+collider.getWidth()||x>width-collider.getWidth() || y<0+collider.getHeight()||y>height-collider.getHeight()) {
		/**
		if(x<0||x>width|| y<0||y>height) {
			speedFctor = -8;
		}else {
			if(speedFctor<1) {
				speedFctor++;
			}
		}
		**/
		double side = collider.getWidth()/4;
		if(x<0-side) {
			x = width+side;
			if(captation) {
				suddenD = true;
			}
		}else if(x>width+side) {
			x = 0-side;
			if(captation) {
				suddenD = true;
			}
		}else if(y<0-side) {
			y = height+side;
			if(captation) {
				suddenD = true;
			}
		}else if(y>height+side) {
			y=0-side;
			if(captation) {
				suddenD = true;
			}
		}else {
			suddenD = false;
		}
		tailHead = (tailHead+1) % tailDim;
		tail[tailHead] = new Ellipse2D.Double(scaleX*xEntry-radiusX/2.0,scaleY*yEntry-radiusY/2.0, radiusX, radiusY);
		
		
		//System.out.println(tailHead+1 +" with "+ tailDim);
	}
	
	/**
	 * cette methode retourne vrai s`il y a eu un accident quelconque avec un objet dans la scene
	 * @return,retourne vrai s`il y a eu un accident quelconque avec un objet dans la scene
	 */
	public boolean getSuddenD() {
		return suddenD;
	}
	
	/**
	 * cette méthode permet à dessiner la queue
	 * @param g2d, contexte graphique
	 */
	public void drawTail(Graphics2D g2d) {
		Color colorBack = g2d.getColor();
		int head = tailHead;
		int counter = head;
		for(int count = 0; count<tailDim; count++) {
			if(tail[counter] != null) {
				if(counter == head) {
					tailColor = headColor;
				}
				int R = tailColor.getRed();
				int G = tailColor.getGreen();
				int B = tailColor.getBlue();
				if((R>0&&R<255)&&(G>0&&G<255)&&(B>0&&B<255)) {
					tailColor = new Color(R-1,G-1,B-1);
				}
				g2d.setColor(tailColor);
				//g2d.fill(modele.createTransformedShape(tail[counter]));
				g2d.fill((tail[counter]));
			}

			if(counter==0) {
				counter = (tailDim-1);
			}else {
				counter = (counter-1);
			}
		}
		g2d.setColor(colorBack);
	}
	
	
	/**
	 * cette méthode permet à recevoir une nouvelle ellipse pour le queue
	 * @param entry, la nouvelle ellipse pour le queue
	 */
	public void setTail(Ellipse2D.Double[] entry) {
		this.tail = entry;
	}
	
	/**
	 * cette méthode permet à envoyer la dimention de la queue
	 * @return, a dimention de la queue
	 */
	public int getTailDim() {
		return tailDim;
	}
	
	/**
	 * cette methode renvoit la limit de la queue que cette vehicule peut avoir
	 * @return, la limit de la queue que cette vehicule peut avoir
	 */
	public int getTailLimit() {
		return tailLimit;
	}
	
	/**
	 * cette méthode permet à envoyer la liste de la queue
	 * @return, la liste de la queue
	 */
	public Ellipse2D.Double[] getTail() {
		return tail;
	}
	
	/**
	 * cette méthode permet à envoyer la position de la tête de la queue dans la liste de la queue
	 * @return,  la position de la tête de la queue dans la liste de la queue
	 */
	public int getTailHead() {
		return tailHead;
	}
	
	/**
	 * cette méthode permet à savoir une égalité entre les nombres réels selon la norme
	 * @param value, le nombre en réels pour confirmer l'égalité
	 * @param suspect, le nombre en réels à vérifier
	 * @param range, la déviation maximale de l'égalité
	 * @return, retourne si il y a une égalité ou non selon la norme
	 */
	private boolean equalDouble(double value,double suspect, double range) {
		return(suspect+range>value && suspect-range<value);
	}
	
	/**
	 * cette méthode permet de tourner de façon smooth un véhicule, de degré par degré
	 * @param entry,  l'angle actuel du véhicule
	 * @param limit, l'angle d'arrivé de la rotation pour le véhicule
	 * @param bound, le véhicule tourne par quel degré à chaque instance
	 * @return, l'angle actuel du véhicule
	 */
	private double rotate(double entry,int limit, int bound) {
		//System.out.println(entry +" and "+limit);
		if(entry!=limit) {
			
			if( equalDouble(entry,270,0.1) && limit<90) {
				entry = entry-360;
				//System.out.println(entry +" and "+limit);
			}
			
			if( equalDouble(entry,90,0.1) && limit>270) {
				entry = entry+360;
				//System.out.println(entry +" and "+limit);
			}
			
			if( equalDouble(entry,0,0.1) && limit>180) {
				entry = entry+360;
			}
			
			if(entry>180 && equalDouble(limit,0,0.1)) {
				entry = entry-360;
				//System.out.println(entry +" and "+limit);
			}
			
			if(Math.abs((entry-limit))==180) {
				if(Math.random()>0.5) {
					if(entry>=180) {
						entry = entry - 360;
					}else {
						entry = entry + 360;
					}
				}			
			}
			
			if(entry<limit){
				return entry = entry+bound;
			}else if(entry>limit){
				return entry = entry-bound;
			}	
			
		}
		
		return entry;
	}
	
	/**
	 * cette méthode renvoie la position x et y de la tête de la queue du véhicule
	 * @return, la position x et y de la tête de la queue du véhicule
	 */
	public Double getTailHeadCoordinate() {
		if(tail[tailHead]!=null) {
			return new Point.Double(tail[tailHead].getX()+radiusX/2, tail[tailHead].getY()+radiusY/2);
		}else {
			return null;
		}
		
	}
	
	/**
	 * cette méthode renvoie la couleur de la queue
	 * @return, la couleur de la queue
	 */
	public Color getTailColor() {
		return tailColor;
	}
	
	/**
	 * cette méthode renvoie la couleur de la tête de la queue
	 * @return, la couleur de la tête de la queue
	 */
	public Color getHeadColor() {
		return headColor;
	}
	
	/**
	 * cette méthode retourne la masse du véhicule
	 * @return, la masse du véhicule
	 */
	public double getMass() {
		return mass;
	}
	
	/**
	 * cette méthode permet à détecter une collision simple entre deux airs avec intersect()
	 * @param opp, le véhicule opposé
	 * @return, retourne si il y a eu une collision ou non 
	 */
	public boolean simpleCollision(Vehicule opp) {
		Area colOpp = new Area(opp.getCollider());
		Area colHeadThis = new Area(mat.createTransformedShape(headCollider));
		colHeadThis.intersect(colOpp);
		if(!colHeadThis.isEmpty()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * pour détcter si la queue a été touchée ou avalé par un véhicule opposé
	 * @param opp, le véhicule opposé
	 */
	public void tailCollision(Vehicule opp) {
		if(tailDim<tailLimit) {
			Ellipse2D.Double[] oppTail = opp.getTail();
			for(int count = 0; count < oppTail.length; count++) {
				if(oppTail[count]!=null) {
					//Area opptail = new Area(oppTail[count]);
						//opptail.intersect(new Area(mat.createTransformedShape(collider)));
					if(new Area(mat.createTransformedShape(collider)).contains(oppTail[count].getCenterX(),oppTail[count].getCenterY())) {
					//if(!opptail.isEmpty()) {
						opp.tailDestroy(count);
						this.tailExtend();
						//setBackground(beta.getHeadColor());
					}
				}
			}
		}
		
	}
	
	/**
	 * détruit la partie de la queue qui a été touché ou avalé par le véhicule opposé
	 * @param entry, l'indice de la partie de la queue
	 */
	public void tailDestroy(int entry) {
		tail[entry] = null;
		
		if(tailDim>1) {
			tail[tailDim-1] = null;
			tailDim --;
		}
		//System.out.println("-"+tailDim);
	}
	
	/**
	 * cette methode permet a detruire un point de la queue du vehicule
	 */
	public void tailDestroy() {
		if(tailDim>1) {
			if(tailDim!=tailHead && tailDim!=tail.length) {
				tail[tailDim] = null;
			}else {
				tail[tailDim-1] = null;	
			}
			
			tailDim --;
		}
		//System.out.println("-"+tailDim);
	}
	/**
	public void tailDestroy() {
		if(tailDim>1) {
			if(tailHead==0) {
				tail[tailDim]=null;
			}else {
				tail[tailHead-1]=null;
			}
			tailDim --;
		}
		
	}
	**/
	
	/**
	 * cette methode permet a detruire un nombre defini de queue 
	 * @param nb, le nombre defini de queue 
	 */
	public void tailDestroyNb(int nb) {
		for(int count = 0; count<nb; count++) {
			tailDestroy();
		}
	}
	
	public void tailDestroyAndSpray(int nb, WDwarf dwarf, int px, int py, int radius) {
		for(int count = 0; count<nb; count++) {
			if(tailDim>1) {
				if(tailDim!=tailHead && tailDim!=tail.length) {
					tail[tailDim] = null;
				}else {
					tail[tailDim-1] = null;	
				}
				tailDim --;
				dwarf.spray(px, py, radius);
			}
		}
	}
	
	/**
	 * prolongé le tail de la  queue
	 */
	public void tailExtend() {
		
		if(tailDim<tail.length) {
			tailDim ++;
		}
		
		//System.out.println("+"+tailDim);
	}
	
	/**
	 * cette methode permet a alonger la queue d'un nombre defini
	 * @param amount, alonger la queue d'un nombre defini
	 */
	public void tailExtend(int amount) {
		
		if(tailDim+amount<tail.length) {
			tailDim=tailDim+amount;
		}else {
			int max = amount+tailDim-tail.length;
			tailDim = tailDim+max;
		}
		
		//System.out.println("+"+tailDim);
	}
	
	/**
	 * permet a savoir si la longeur de la queue est rendu au max
	 * @return, si la longeur de la queue est rendu au max
	 */
	public boolean ifTailFull() {
		if(tailDim==tailLimit) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * cette méthode renvoie le collider pour l'ensemble du véhicule
	 * @return, le collider pour l'ensemble du véhicule
	 */
	public Shape getCollider(){
		return mat.createTransformedShape(collider);
	}
	
	/**
	 * cette méthode renvoie le collider pour la tête du véhicule
	 * @return, le collider pour la tête du véhicule
	 */
	public Shape getHeadCollider(){
		return mat.createTransformedShape(headCollider);
	}
}
