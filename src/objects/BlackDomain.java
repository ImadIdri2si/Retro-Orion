package objects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import vecteur.Vecteur;
import vecteur.VecteurGraphique;
import vehicle.Vehicule;

/**
 * cette classe s'occupe d'un champ m¨¦gn¨¦tique, avec tous les physiques reli¨¦s. 
 * @author XU WEI DUO
 * source 1: http://webprofs.cmaisonneuve.qc.ca/svezina/nyb/note_nyb/NYB_XXI_Chap%204.2a.pdf
 * source 2: http://physik.diekirch.org/1er/A05%20Mouvement%20dans%20B.pdf
 */
public class BlackDomain {
	//basic 
	private Ellipse2D.Double core;
	private double coreX, coreY, coreRadius,coreRadiusOrigin;
	private double scaleX,scaleY, limitX, limitY;
	private Vecteur vecMeasure, prd;
	private float radA=0.0f, radB=0.2f, radC=0.4f;
	private boolean down = false;
	private double q=10,v,B=0;
	//image
	Image img = null;
	private int scaleFactor = 4;
	double imgScale;
	double imgRot;
	//matrix
	private AffineTransform modele;
	//rotation & object in
	private boolean inRot = true;
	private boolean inObj = false, suddenD = false, selfDes = false;
	//spray & consume WDwarfs
	private int consume=0;
	private int sprayCounter = 0;
	//science mode
	private boolean sciMode = false;
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour cr¨¦ un champ m¨¦gn¨¦tique
	 * @param mat, matrix de transformation
	 * @param width, la position centrale X du champ
	 * @param height, la position centrale Y du champ
	 * @param radius, le rayon du champ
	 * @param first, indique si c'est la premiere fois ou non
	 */
	public BlackDomain(AffineTransform mat, double width, double height, double radius, boolean first) {
		modele = new AffineTransform(mat);
		scaleX = modele.getScaleX();
		scaleY = modele.getScaleY();
		limitX = width;
		limitY = height;
		
		if(first) {
			this.coreX = width/2;
		}else {
			this.coreX = scaleX*this.generateDomain(limitX, limitY).getX();
		}
		
		this.coreY = scaleY*this.generateDomain(limitX, limitY).getY();
		this.coreRadius = 0;
		this.coreRadiusOrigin = scaleX*radius;
		core = new Ellipse2D.Double(coreX-coreRadius/2.0,coreY-coreRadius/2.0,coreRadius,coreRadius);
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("BD.PNG"));
			this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("image of blackDomain is missing");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * cette methode permet `a deplacer cet objet
	 * @param dx, le deplacement en x
	 * @param dy, le deplacement en y
	 */
	public void deplace(double dx, double dy) {
		coreX = dx;
		coreY = dy;
		core = new Ellipse2D.Double(coreX-coreRadius/2.0,coreY-coreRadius/2.0,coreRadius,coreRadius);
	}
	
	/**
	 * cette methode renvoit les coordonne centrales de l'objet
	 * @return, les coordonne centrales de l'objet
	 */
	public Point getCore() {
		return new Point((int)coreX,(int)coreY);
	}
	
	/**
	 *  cette methode permet l'interaction entre un vehicule et ce objet
	 * @param opp, un vehicule
	 * @param oppCaptat, si le vehicule a ete capturer 
	 * @param g2d, contexe graphique
	 * @return, retourne vrai si le vehicule aete capturer
	 */
	public boolean vehInteract(Vehicule opp, boolean oppCaptat, Graphics2D g2d) {
		//if( opp.getTailHeadCoordinate()!=null )
		if(opp.getVecMov()!=null && this.getInRot()  && (oppCaptat||(!this.getInObj()&& opp.getTailHeadCoordinate()!=null &&(this.measure(opp.getTailHeadCoordinate().getX(),opp.getTailHeadCoordinate().getY()))))) {
			//domain.drawline(g2d, zeta.getTailHeadCoordinate().getX(),zeta.getTailHeadCoordinate().getY());
			this.rotInFiled(opp , g2d);
			this.setInObj(true);
			oppCaptat = true;
			if((this.getSuddenD() || opp.getSuddenD())) {
				opp.setCaptation(false);
				oppCaptat = false;
				this.setInRot(false);
				this.setInObj(false);
			}
		}else {
			if(sciMode && opp.getVecMov()!=null && !this.getInObj() && opp.getTailHeadCoordinate()!=null) this.drawMeasure(opp.getTailHeadCoordinate().getX(),opp.getTailHeadCoordinate().getY(),g2d);
		}
		
		return oppCaptat;
	}
	
	/**
	 * pour savoir si un vehicule est entrer `a l'interieur de cet objet
	 * @param opp, le vehicule
	 * @return, retourne vrai si le vehicule est `a interieur de cet objet
	 */
	public boolean contain(Vehicule opp) {
		if(core!=null && opp!=null && opp.getTailHeadCoordinate()!=null) {
			return core.contains(opp.getTailHeadCoordinate());
		}else {
			return false;
		}
		
	}
	
	/**
	 * cette methode permet a creer des nouveaux coordonnes pour creer un nouveua blackDomain
	 * @param width, la limit en x 
	 * @param height, la limit en y 
	 * @return, retourne les coordonnes o`u cet objet doit etre creer
	 */
	private Point generateDomain(double width, double height) {
		int dx,dy,factorX=1,factorY=1;
		
		if((Math.random()*10)>5) {
			factorX = -1;
		}else {
			factorX = 1;
		}
		
		if((Math.random()*10)>5) {
			factorY = -1;
		}else {
			factorY = 1;
		}
		dx = (int)(width/2+factorX*(Math.random()*100));
		dy = (int)(height/2+factorY*(Math.random()*100));
		
		while(dx<width/4 || dx>3*width/4) {
			dx = dx-(int)(Math.random()*100);//(int)(Math.random()*10+1);
			if(dx<=0 ) {
				dx = (int)((Math.random()*100)*(Math.random()*100)*(Math.random()*10));
			}
		}	
		
		while(dy<height/4 || dy>3*height/4) {
			dy = dy-(int)(Math.random()*100);//(int)(Math.random()*10+1);
			if(dy<=0 ) {
				dy = (int)((Math.random()*100)*(Math.random()*100)*(Math.random()*10));
			}
		}
		//System.out.print("["+dx+","+dy+"] ");
		return new Point(dx,dy);
	}
	
	/**
	 * cette methode permet a mesurer la distance entre un vehicule et le champ,
	 * pour savoir si le v¨¦hicule en question est dans le rayon du champ
	 * @param centerX, la position en X de la t¨ºte de la queue d'un v¨¦hicule
	 * @param centerY, la position en Y de la t¨ºte de la queue d'un v¨¦hicule
	 * @return, retourne vrai si la distance est inf¨¦rieur au rayon
	 */
	private boolean measure(double centerX, double centerY){
		vecMeasure = new Vecteur(coreX-centerX,coreY-centerY);
		return (vecMeasure.module() < coreRadius*2);
	}
	
	/**
	 * cette methode permet a dessiner la distance entre un vehicule et le champ avec un vecteur
	 * @param centerX, la position en X de la t¨ºte de la queue d'un v¨¦hicule
	 * @param centerY, la position en Y de la t¨ºte de la queue d'un v¨¦hicule
	 * @param g2d, contexte graphique
	 */
	private void drawMeasure(double centerX, double centerY,Graphics2D g2d) {
		
		VecteurGraphique vecGraph = new VecteurGraphique(vecMeasure);
		vecGraph.setOrigineXY(centerX, centerY);
		g2d.setColor(Color.RED);
		vecGraph.dessiner(g2d);
		
		
	}
	
	/**
	 * cette methide permet a savoir si ce domain est destruit
	 * @return, retourne vrai si ce domain est destruit
	 */
	public boolean getDestroyed() {
		
		return selfDes;
	}
	
	/**
	 * cette methode permet `a consummer les WDwarfs des vehicules
	 */
	public void consume() {
		consume++;
	}
	
	/**
	 * quelle est la quantite de l'eruption des WDwarfs consumme
	 * @return, la quantite de l'eruption des WDwarfs consumme
	 */
	public int getSprayCounter() {
		return sprayCounter;
	}
	
	/**
	 * cette methode gere l'eruption des WDwarfs consumme
	 * @param dwarf, la classe qui gere les White Dwarfs
	 */
	public void consumedSpray(WDwarf dwarf) {
		if(consume>0) {//&& ((int)(Math.random()*10))%2==0) {
			dwarf.spray(coreX, coreY,coreRadius);
			//sprayCounter++;
			consume--;
		}
		
	}
	
	/**
	 * cette methode dessine un domain de facon simple
	 * @param g2d, le contexe graphique
	 * @param dimUp, si le domain agrandi ou non
	 */
	public void DrawDomainSimple(Graphics2D g2d, boolean dimUp) {
		if(coreRadius<coreRadiusOrigin) {
			coreRadius=coreRadius+1;
			core = new Ellipse2D.Double(coreX-coreRadius/2,coreY-coreRadius/2,coreRadius,coreRadius);
		}else if(coreRadius<coreRadiusOrigin*4 && dimUp){
			coreRadius=coreRadius+1;
			core = new Ellipse2D.Double(coreX-coreRadius/2,coreY-coreRadius/2,coreRadius,coreRadius);
		}else if(coreRadius>coreRadiusOrigin && !dimUp) {
			coreRadius=coreRadius-1;
			if(coreRadius==20) {
				coreRadius=coreRadius-1;
			}
			core = new Ellipse2D.Double(coreX-coreRadius/2,coreY-coreRadius/2,coreRadius,coreRadius);
		}
		
		Color colorBack = g2d.getColor();
		if(!down) {
			radA = (radA+0.002f);
			radB = (radB+0.002f);
			radC = (radC+0.002f);
		}else {
			radA = (radA-0.002f);
			radB = (radB-0.002f);
			radC = (radC-0.002f);
		}
		if(radA >= 0.4f) {
			down = true;
		}else if(radA <= 0.1f) {
			down = false;
		}
		//System.out.println(radA+" and "+radB+" and "+radC);
		
		if(coreRadius>0) {
			RadialGradientPaint gp = new RadialGradientPaint((int)coreX, (int)coreY,Math.abs((int)coreRadius+1),new float[] {radA,radB,radC},new Color[] {new Color(0,0,0,150),new Color(100,0,0,100),new Color(0,100,0,50)});
			g2d.setPaint(gp);
		}

		g2d.fill(core);
		g2d.setColor(Color.RED);
		g2d.draw(core);
		
		AffineTransform mat = new AffineTransform(modele);
		if(coreRadius/coreRadiusOrigin<1) {
			imgScale = coreRadius/coreRadiusOrigin;
		}
		mat.translate(coreX-img.getWidth(null)/2, coreY-img.getHeight(null)/2);
		//mat.translate((img.getWidth(null)/2) - (img.getWidth(null)*(imgScale))/2,(img.getHeight(null)/2) - (img.getHeight(null)*(imgScale))/2);
		//AffineTransform back = new AffineTransform(mat);
		mat.translate((img.getWidth(null)*(1-imgScale))/2,(img.getHeight(null)*(1-imgScale))/2);
		mat.scale(imgScale,imgScale);
		//mat.setTransform(back);
		imgRot = (imgRot+5)%360;
		mat.rotate(Math.toRadians(imgRot),img.getWidth(null)/2,img.getHeight(null)/2);
		
		g2d.drawImage(img, mat, null);	
		g2d.setColor(colorBack);
	}
	
	/**
	 * cette methode permet a dessiner le champ magnetique
	 * @param g2d, contexte graphique
	 */
	public void drawDomain(Graphics2D g2d) {
		
		if(!inRot && !inObj && (coreRadius+0.1>coreRadiusOrigin&&coreRadius-0.1<coreRadiusOrigin)) {
			coreRadius=coreRadius-1;
		}
		
		if(coreRadius<coreRadiusOrigin && inRot) {
			coreRadius=coreRadius+1;
			core = new Ellipse2D.Double(coreX-coreRadius/2,coreY-coreRadius/2,coreRadius,coreRadius);
		}else if(coreRadius!=coreRadiusOrigin && !inRot) {
			if(coreRadius>3000) {
				coreRadius=coreRadius-6;
			}else if(coreRadius>2000) {
				coreRadius=coreRadius-4;
			}else if(coreRadius>1000) {
				coreRadius=coreRadius-2;
			}else {
				coreRadius=coreRadius-1;
			}
			
			if(coreRadius==20) {
				coreRadius=coreRadius-1;
			}
			//int bias = 1;
			if(!inObj && B>0) {
				B=B-B/coreRadius;
			}
			if(coreRadius<=0) {
				//coreRadius=coreRadiusOrigin;
				inRot = true;
				B = 0;
				if(suddenD) {
					suddenD = false;
				}
				selfDes = true;
				return;
			}
			/**
			if((coreRadius+bias > coreRadiusOrigin)&&(coreRadius-bias < coreRadiusOrigin)) {
				coreRadius=coreRadiusOrigin;
				inRot = true;
				B = 0;
				if(suddenD) {
					suddenD = false;
				}
			}
			**/
			core = new Ellipse2D.Double(coreX-coreRadius/2,coreY-coreRadius/2,coreRadius,coreRadius);
		}
		
		Color colorBack = g2d.getColor();
		if(!down) {
			radA = (radA+0.002f);
			radB = (radB+0.002f);
			radC = (radC+0.002f);
		}else {
			radA = (radA-0.002f);
			radB = (radB-0.002f);
			radC = (radC-0.002f);
		}
		if(radA >= 0.4f) {
			down = true;
		}else if(radA <= 0.1f) {
			down = false;
		}
		//System.out.println(radA+" and "+radB+" and "+radC);
		
		if(coreRadius>0) {
			RadialGradientPaint gp = new RadialGradientPaint((int)coreX, (int)coreY,Math.abs((int)coreRadius+1),new float[] {radA,radB,radC},new Color[] {new Color(0,0,0,150),new Color(100,0,0,100),new Color(0,100,0,50)});
			g2d.setPaint(gp);
		}

		g2d.fill(core);
		g2d.setColor(Color.RED);
		g2d.draw(core);
		
		AffineTransform mat = new AffineTransform(modele);
		if(coreRadius/coreRadiusOrigin<1) {
			imgScale = coreRadius/coreRadiusOrigin;
		}
		mat.translate(coreX-img.getWidth(null)/2, coreY-img.getHeight(null)/2);
		//mat.translate((img.getWidth(null)/2) - (img.getWidth(null)*(imgScale))/2,(img.getHeight(null)/2) - (img.getHeight(null)*(imgScale))/2);
		//AffineTransform back = new AffineTransform(mat);
		mat.translate((img.getWidth(null)*(1-imgScale))/2,(img.getHeight(null)*(1-imgScale))/2);
		mat.scale(imgScale,imgScale);
		//mat.setTransform(back);
		imgRot = (imgRot+5)%360;
		mat.rotate(Math.toRadians(imgRot),img.getWidth(null)/2,img.getHeight(null)/2);
		
		g2d.drawImage(img, mat, null);	
		
		
		if(sciMode) {
			int strBound = 20, cX=0,cY=0;
			cX = (int)coreX;
			cY = (int)coreY;
			if(coreX<0 || coreX>limitX || coreY<0 || coreY>limitY) {
				if(coreX<0) cX = 10;
				else if(coreX>limitX) cX = (int)limitX-220;
				else if(coreY<0) cY = 10;
				else if(coreY>limitY) cY = (int)limitY-120;
			}
			g2d.setColor(Color.BLACK);
			g2d.drawString("core radius: ["+coreRadius+"]km by ["+coreRadiusOrigin+"]km", cX, cY+strBound);
			g2d.drawString("Field strength: ["+String.format("%.3f",B)+"]T", cX, cY+2*strBound);
			g2d.drawString("ready for/in rotation: ["+inRot+"]", cX, cY+3*strBound);
			g2d.drawString("object inside: ["+inObj+"]", cX, cY+4*strBound);
			g2d.drawString("consumed: ["+consume+"]u", cX, cY+5*strBound);
		}
		g2d.setColor(colorBack);
		
	}
	

	/**
	 * cette methode permet `a activer le mode scientifique
	 * @param entry, si oui alors active le mode scientifique `a  
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
	 * cette m¨¦thode retourne le rayon du champ magn¨¦tique
	 * @return, le rayon du champ magn¨¦tique
	 */
	public double getRadius() {
		return coreRadius;
	}
	
	/**
	 * cette methode renvoie l'intentie du champ B
	 * @return, B intensite du champ
	 */
	public double getB() {
		return B;
	}
	
	/**
	 * cette methode renvoie le vecteur de la force du champ
	 * @return, le vecteur de la force du champ
	 */
	public Vecteur getVecForce() {
		return prd;
	}
	
	/**
	 * cette classe s<occupe du physique pour le champ magn¨¦tique en capturant un v¨¦hicule
	 * @param opp, le v¨¦hicule captur¨¦
	 * @param g2d, contexte graphique
	 */
	private void rotInFiled(Vehicule opp, Graphics2D g2d) {
		Color colorBack = g2d.getColor();
		if(inRot && this.calculateForcePerp(opp.getVecMov(), q, opp.getSpeed(),-B).module()>0 ) {
			if( opp.getTailHeadCoordinate()==null) {
				suddenD = true;
				return;
			}
			prd = this.calculateForcePerp(opp.getVecMov(), q, opp.getSpeed(),-B);
			Vecteur add=new Vecteur();
			try {
				add = (opp.getVecMov().multiplie(opp.getSpeed()/1000)).additionne(prd.multiplie(1/opp.getMass()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(this.calRadius(opp.getVecMov()));
			
			Vecteur vecRad = new Vecteur();
			try {
				vecRad = prd.normalise().multiplie(this.calRadius(opp, opp.getMass()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			double dx = opp.getTailHeadCoordinate().getX();
			double dy = opp.getTailHeadCoordinate().getY();
			if(sciMode) {
				VecteurGraphique vecGraph = new VecteurGraphique(opp.getVecMov().multiplie(10));
				vecGraph.setOrigineXY(dx,dy);
				g2d.setColor(Color.BLUE);
				vecGraph.dessiner(g2d);
				
				vecGraph = new VecteurGraphique(add.multiplie(10));
				vecGraph.setOrigineXY(dx,dy);
				g2d.setColor(Color.RED);
				vecGraph.dessiner(g2d);
				
				Ellipse2D.Double test = new Ellipse2D.Double((dx+vecRad.getX())-4/2,(dy+vecRad.getY())-4/2,4,4);
				g2d.setColor(Color.GREEN);
				g2d.fill(test);
				
				vecGraph = new VecteurGraphique(vecRad);
				vecGraph.setOrigineXY(dx, dy);
				g2d.setColor(Color.GREEN);
				vecGraph.dessiner(g2d);
			}
			coreX = (dx+vecRad.getX());
			coreY = (dy+vecRad.getY());
			//System.out.println(coreRadius);
			coreRadius = this.calRadius(opp, opp.getMass())*2;
			core = new Ellipse2D.Double(coreX-coreRadius/2,coreY-coreRadius/2,coreRadius,coreRadius);
			//System.out.println("v: "+zeta.getVecMov().toString());
			//System.out.println("f: "+add.toString());
			//domain.drawline(g2d, zeta.gettailHeadCoordinate().getX(),zeta.gettailHeadCoordinate().getY());
			//System.out.println("add: "+add.toString());
			try {
				opp.setVecMov(add.normalise());
				opp.setMove(add.normalise());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			opp.setCaptation(true);
		}
		
		
		if(B<100) {
			B=B+1;
		}
		g2d.setColor(colorBack);
		
	}
	
	/**
	 * cette m¨¦thode retourne vrai si le queue du v¨¦hicule captur¨¦ a ¨¦t¨¦ mang¨¦ en majeurit¨¦
	 * @return, si le queue du v¨¦hicule captur¨¦ a ¨¦t¨¦ mang¨¦ en majeurit¨¦
	 */
	public boolean getSuddenD() {
		return suddenD;
	}
	
	
	/**
	 * cete m¨¦thode retourne vrai si le champ a d¨¦j¨¤ captur¨¦ un v¨¦hicule ou non
	 * @return, si le champ a d¨¦j¨¤ captur¨¦ un v¨¦hicule ou non
	 */
	public boolean getInObj() {
		return inObj;
	}
	
	/**
	 * cete m¨¦thode prend en param¨¨tre un boolean pour dire si le champ va capturer un v¨¦hicule ou non
	 * @param entry, boolean pour dire si le champ a captur¨¦ un v¨¦hicule ou non
	 */
	public void setInObj(boolean entry) {
		inObj = entry;
	}
	
	/**
	 * cette m¨¦thode retourne vrai si le champ est en train de faire tourner un v¨¦hicule avec une force du champ magn¨¦tique
	 * @return, si le champ est en train de faire tourner un v¨¦hicule avec une force du champ magn¨¦tique
	 */
	public Boolean getInRot() {
		return inRot;
	}
	
	/**
	 * cette m¨¦thode prend en param¨¨tre un boolean pour dire si le champ va faire tourner un v¨¦hicule avec une force du champ magn¨¦tique ou non
	 * @param entry, boolean pour dire si le champ va faire tourner un v¨¦hicule avec une force du champ magn¨¦tique ou non
	 */
	public void setInRot(boolean entry) {
		inRot = entry;
	}
	
	/**
	 * cette m¨¦thode permet ¨¤ calculer un vecteur de la force F = qVxB
	 * @param vecA, le vecteur vitesse d'un v¨¦hicule
	 * @param qEntry, la charge donn¨¦e au v¨¦hicule (C)
	 * @param vEntry, la vitesse du v¨¦hicule (m/s)
	 * @param BEntry, module du champ magn¨¦tique (T) 
	 * @return, retourne le vecteur de la  force F = qVxB
	 */
	private Vecteur calculateForcePerp(Vecteur vecA, double qEntry, double vEntry, double BEntry) {
		q = qEntry;
		v = vEntry;
		B = BEntry;
		Vecteur vecProd = new Vecteur(vecA.getY()*B-0*B,-vecA.getX()*B-0*B);
		
		vecProd = vecProd.multiplie(q);
		vecProd = vecProd.multiplie(v/1000);
		//vecProd = vecProd.multiplie(B);
		//vecProd = new Vecteur(coreX-vecProd.getX(), coreY-vecProd.getY());
		return vecProd;
	}
	
	/**
	 * cette m¨¦thide permet ¨¤ calculer le rayon du champ magn¨¦tique apr¨¨s activation
	 * @param vecV, le vecteur du v¨¦hicule oppos¨¦
	 * @param masse, la masse du v/hicule
	 * @return, retourne le rayon
	 */
	private double calRadius(Vehicule opp, double masse) {
		double rad = ((masse*(opp.getSpeed())/(q*B)))/1000;
		//System.out.println("radius" + rad+"km by: "+(masse*(opp.getSpeed()/1000))+"/"+(q*B) );
		return rad;
	}
	


}
