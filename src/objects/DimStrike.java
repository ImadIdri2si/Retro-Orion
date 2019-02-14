package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import fourthDim.Forme4D;
import fourthDim.FormeCreator;
import fourthDim.Point4D;
import vehicle.Vehicule;
/**
 * cette classe s'occupe d`une forme de 4eme dimention
 * @author XU WEI DUO
 *
 */
public class DimStrike {
	//4dim
	private FormeCreator forme4D;
	private int rotCounter = 0;
	private int scale = 0, minScale,maxScale;
	private int centerX, centerY;
	private int rotX = 0, rotY = 0,rotZ = 0;
	private int rotXZ = 0, rotYZ = 0,rotXY = 0;
	private int rotXW = 0, rotYW = 0,rotZW = 0;
	private int[] dummy, counter;
	private boolean drawCore = false;
	private boolean first =true;
	private Ellipse2D.Double core;
	private int radius = 10;
	private boolean load = false;
	private int choice;
	private int exchange = 20;
	private boolean sciMode =  false;
	//private int consumer = 20;
	//private int rotXZ = (int)(Math.random()*10), rotYZ = (int)(Math.random()*10),rotXY = (int)(Math.random()*10);
	//private int rotXW = (int)(Math.random()*10), rotYW = (int)(Math.random()*10),rotZW = (int)(Math.random()*10);
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour creer une forme en 4D
	 * @param entryX, la position centrale en x
	 * @param entryY, la position centrale en y
	 * @param minEntry, scale minimale de l`objet
	 * @param maxEntry, scale macimale de l`objet
	 * @param consumer, le nombre de WDwarf que chaque sommet va consommer
	 */
	public DimStrike(int entryX, int entryY,int minEntry, int maxEntry, int consumer) {
		forme4D = new FormeCreator();
		forme4D.setDimUp(true);
		centerX = entryX;
		centerY = entryY;
		forme4D.setDeplacement(centerX,centerY);
		minScale = minEntry;
		scale = minScale;
		maxScale = maxEntry;
		this.exchange = consumer;
		System.out.println("consumer: "+consumer);
		core = new Ellipse2D.Double(centerX-radius/2,centerY-radius/2,radius,radius);
		//forme4D.setScale(400);
		//if(load) {
			//forme4D.setDemoOn(false);
			//forme4D.load();
		//}
		
	}
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour creer une forme en 4D
	 * @param entryX, la position centrale en x
	 * @param entryY, la position centrale en y
	 * @param minEntry, scale minimale de l`objet
	 * @param maxEntry, scale macimale de l`objet
	 */
	public DimStrike(int entryX, int entryY,int minEntry, int maxEntry) {
		this(entryX,entryY,minEntry,maxEntry,20);
	}
	
	/**
	 * cette methode permet `a deplacer cet objet
	 * @param dx, le deplacement en x
	 * @param dy, le deplacement en y
	 */
	public void deplace(int dx, int dy) {
		centerX = dx;
		centerY = dy;
		forme4D.setDeplacement(centerX,centerY);
		core = new Ellipse2D.Double(centerX-radius/2,centerY-radius/2,radius,radius);
	}
	
	/**
	 * cette methode permet `a dessiner l'objet en entier de facon simple
	 * @param g2d, contexe graphique 
	 * @param pointColor, la couleur des sommets
	 * @param up, le scale de l`objet
	 * @param choice, l`indice du choix de l`objet
	 * @param rotation, si la rotation de l`objet est activer ou pas
	 */
	public void simpleDraw(Graphics2D g2d, Color pointColor,boolean up,int choice, boolean rotation) {
		strike(g2d,null,null,rotation,pointColor,pointColor,up,choice);
	}
	
	/**
	 * cette methode permet `a dessiner l'objet en entier
	 * @param g2d, contexe graphique 
	 * @param v1, le vehicule beta
	 * @param v2, le vehicule zeta
	 * @param entry, l`indice du choix de l`objet
	 */
	public void strike(Graphics2D g2d, Vehicule v1, Vehicule v2,int entry) {
		strike(g2d,v1,v2,true,Color.WHITE,Color.DARK_GRAY,true,entry);
	}
	
	/**
	 * cette methode permet `a dessiner l'objet en entier
	 * @param g2d, contexe graphique 
	 * @param v1, le vehicule beta
	 * @param v2, le vehicule zeta
	 */
	public void strike(Graphics2D g2d, Vehicule v1, Vehicule v2) {
		strike(g2d,v1,v2,true,Color.WHITE,Color.DARK_GRAY,true,choice);
	}
	
	/**
	 * redefinir l`indice du choix de l`objet
	 * @param entry,l`indice du choix de l`objet
	 */
	public void setChoice(int entry) {
		choice = entry;
	}
	
	/**
	 * cette methode renvoit le scale de l`objet
	 * @return, le scale de l`objet
	 */
	public int getScale() {
		return scale;
	}
	
	/**
	 * cette methode retourne les coordonnes centrale de l`objet
	 * @return, les coordonnes centrale de l`objet
	 */
	public Point getCenter() {
		return new Point(centerX,centerY);
	}
	
	/**
	 * cette methode permet `a dessiner l'objet en entier
	 * @param g2d, contexe graphique 
	 * @param v1, le vehicule beta
	 * @param v2, le vehicule zeta
	 * @param rotation, si la rotation de l`objet est activer ou pas
	 * @param baseColor, la couleur des arretes
	 * @param PointColor, la couleur des sommets
	 * @param scaleUp, le scale de l`objet
	 * @param choice, l`indice du choix de l`objet
	 */
	public void strike(Graphics2D g2d, Vehicule v1, Vehicule v2,boolean rotation,Color baseColor,Color PointColor,boolean scaleUp, int choice) {
			
			AffineTransform back = g2d.getTransform();
			rotCounter = (rotCounter+1)%4;
			if(scaleUp&&scale<maxScale) {
				scale++;
				forme4D.setScale(scale);
			}else if(!scaleUp&&scale>minScale) {
				scale--;
				forme4D.setScale(scale);
			}else if(scale!=maxScale){
				forme4D.setScale(minScale);
			}
			//g2d.translate(zeta.getTailHeadCoordinate().getX(),zeta.getTailHeadCoordinate().getY());
			//g2d.translate(width*WORLD_SIZE/2,height*WORLD_SIZE/2);
			//forme4D.setDp(10);
			
			
			if(rotation && rotCounter == 0) {
				
				rotYZ =(rotYZ+1)%360;
				rotXZ =(rotXZ+1)%360;
				rotXY =(rotXY+1)%360;
				rotZW =(rotZW+1)%360;
				rotXW =(rotXW+1)%360;
				rotYW =(rotYW+1)%360;
				//rotX =(rotX+1)%360;
				//rotY =(rotY+1)%360;
				//rotZ =(rotZ+1)%360;
				forme4D.setRot(0, rotYZ);
				forme4D.setRot(1, rotXZ);
				forme4D.setRot(2, rotXY);
				forme4D.setRot(3, rotXW);
				forme4D.setRot(4, rotYW);
				forme4D.setRot(5, rotZW);
				//forme4D.setRot(6, rotX);
				//forme4D.setRot(7, rotY);
				//forme4D.setRot(8, rotZ);
			}
			forme4D.dim4D(g2d,choice,baseColor);
			drawStrikers(g2d, PointColor,v1, v2);
			g2d.setTransform(back);
		
	}
	/**
	 * cette methode dessine un cercle au centre de cet objet
	 * @param entry, dessine oui on non le cercle 
	 */
	public void drawCore(boolean entry) {
		drawCore = entry;
	}
	
	
	/**
	 * cette methode permet `a dessiner les sommets  et detecte les collisions avec les vehicules
	 * @param g2d, contexe graphique
	 * @param color, la couleur des sommets
	 * @param v1, le vehicule beta
	 * @param v2, le vehicule zeta
	 */
	private void drawStrikers(Graphics2D g2d, Color color, Vehicule v1, Vehicule v2) {
			
			AffineTransform mat2 = g2d.getTransform();
			Color colorBack = g2d.getColor();
			Color oriColor = color;
			Color centerColor = Color.WHITE;
			Forme4D forme2D = forme4D.getForme2D();
			Point4D[] forme = forme2D.get4DMatrix();
			int dim = forme.length;
			
			if(first) {
				dummy = new int[dim];
				counter = new int[dim];
				first = false;
			}
			
			if(drawCore) {
				g2d.setColor(Color.PINK);
				g2d.draw(core);
			}
			
			for(int count = 0; count<dim; count++) {
				centerColor = Color.WHITE;
				double pX = forme2D.get4DMatrix()[count].getX()*scale+centerX;
				double pY = forme2D.get4DMatrix()[count].getY()*scale+centerY;
				//System.out.println(pX+" and "+pY);
				Ellipse2D.Double pointCercle = new Ellipse2D.Double(pX-scale/40,pY-scale/40,scale/20,scale/20);
				//Area collider = new Area(pointCercle);
				if(count<dim && v1!=null && v2!=null) {
					if(v1.getVecMov()!=null && v1.getCollider().contains(pX-scale/40,pY-scale/40)) {
						if(dummy[count]==2) {
							if(v2.getTailDim()<v2.getTailLimit()) {
								if(counter[count] > 1) {
									if(v1.getTailDim()>2) {
										v1.tailDestroy();
										v2.tailExtend(2);
										color=Color.WHITE;
									}else {
										v2.tailExtend();
										color=v2.getHeadColor();
									}
									
									counter[count]--;
								}else {
									dummy[count]=0;
									color=Color.WHITE;
								}
								//color=Color.WHITE;
								centerColor = Color.BLACK;
							}else {
								color=v2.getHeadColor();
							}
							
						}else if(dummy[count] == 1){
							color=v1.getHeadColor();
						}else if(v1.getTailDim()>exchange){
							dummy[count] = 1;
							counter[count] = exchange;
							v1.tailDestroyNb(exchange);
							color=v1.getHeadColor();
						}else {
							color=oriColor;
						}
						
					}else if(v2.getVecMov()!=null && v2.getCollider().contains(pX-scale/40,pY-scale/40)) {
						if(dummy[count]==1) {
							if(v1.getTailDim()<v1.getTailLimit()) {
								if(counter[count] > 1) {
									if(v2.getTailDim()>2){
										v2.tailDestroy();
										v1.tailExtend(2);
										color=Color.WHITE;
									}else {
										v1.tailExtend();
										color=v1.getHeadColor();
									}
									counter[count]--;
								}else {
									dummy[count]=0;
									color=Color.WHITE;
								}
								//color=Color.WHITE;
								centerColor = Color.BLACK;
							}else {
								color=v1.getHeadColor();
							}
							
						}else if(dummy[count] == 2){
							color=v2.getHeadColor();
						}else if(v2.getTailDim()>exchange){
							dummy[count] = 2;
							counter[count] = exchange;
							v2.tailDestroyNb(exchange);
							color=v2.getHeadColor();
						}else {
							color=oriColor;
						}
					}else {
						//if(count<dummy.length && dummy[count]==1) {
						if(dummy[count]==1) {
							color=v1.getHeadColor();
						//}else if(count<dummy.length && dummy[count]==2){
						}else if(dummy[count]==2){
							color=v2.getHeadColor();
						}else {
							color=oriColor;
						}
					}
				}else {
					color=oriColor;
				}
				
				if(pointCercle.getWidth()>0) {
					RadialGradientPaint gp = new RadialGradientPaint((int)pointCercle.getX()+(int)pointCercle.getWidth()/2, (int)pointCercle.getY()+(int)pointCercle.getWidth()/2,(int)pointCercle.getWidth(),new float[] {0.0f,0.3f},new Color[] {centerColor,color});
					g2d.setPaint(gp);
				}
				
				//g2d.setStroke(new BasicStroke(3));
				g2d.fill(pointCercle);
				if(v1!=null && v2!=null && sciMode) {
					g2d.setColor(Color.WHITE);
					g2d.drawString("P"+dummy[count]+":"+counter[count], (int)pX, (int)pY);
				}
				
				
			}
			
			g2d.setTransform(mat2);
			g2d.setColor(colorBack);
			
	
			//System.out.println("drawed");
	}

	/**
	 * cette methode permet `a activer le mode scientifique
	 * @param entry, si oui alors active le mode scientifique
	 */
	public void setSciMode(boolean entry) {
		sciMode = entry;
		forme4D.setSciMode(entry);
	}
	
	/**
	 * cette methode permet a envoyer un load path pour une forme4D
	 * @return, retorune le load path
	 */
	public String load() {
		forme4D.setDemoOn(false);
		forme4D.load();
		return forme4D.getLoadPath();
	}
	
	/**
	 * cette methode permet a charger une forme4D avec un load path
	 * @param path, le load path
	 */
	public void load(String path) {
		if(path!="") {
			forme4D.setDemoOn(false);
			forme4D.load(path);
		}else {
			forme4D.setDemoOn(true);
		}
		
	}

}
