package fourthDim;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import vehicle.Vehicule;

/**
 * cette classe permet `a creer une forme et de la dessiner
 * @author XU WEI DUO
 *
 */
public class FormeCreator extends JPanel {
	public FormeCreator() {
	}
	private Forme4D forme4D = new Forme4D(0), forme3D,forme2D, formeOrigin;
	private boolean demoOn = true;
	private int demoChoice = 1;
	private int scale = 100;
	private int serial, serialP1, serialP2;
	private int yz, xz, xy, xw, yw, zw, x, y, z;
	private double valueW = 1 ;
	private double x1,y1,x2,y2;
	private double dp = -0.5;
	private boolean dimUped = false;
	private boolean pointSelected = false;
	private boolean created = false;
	private boolean drawPoint = false;
	private boolean linkSelected = false;
	private String loadPath = "";
	
	//game
	private int dx=0,dy=0;
	private boolean sciMode = false;
	/**
	 * initilialise le Jpanel et les ecouteurs pour le clavier et la souris
	 */
	
	/**
	 * cette methode permet `a faire une multiplication matricielle
	 */
	public void matrixMulti() {
		Matix_Multiplication multi = new Matix_Multiplication();
		double[][] mat1 = new double[][] {{1,3,4}, {7,6,0}};
		Forme4D formeMat = new Forme4D( new Point4D[] {new Point4D(0,0,0), new Point4D(2,4,1)});
		System.out.println(multi.multiplication4D(mat1,formeMat).toString());
		
		double[][] mat2 = new double[][] {{0,2}, {0,4},{0,1}};
		Forme4D formeMat2 = new Forme4D( new Point4D[] {new Point4D(1,7), new Point4D(3,6), new Point4D(4,0)});
		System.out.println(multi.multiplication4D(mat2,formeMat2).toString());
		
	}
	
	/**
	 * cette methode reinitialise ceratins des choix necessaire pour la creation d'une forme
	 */
	public void reset() {
		pointSelected = false;
		linkSelected = false;
		drawPoint = false;
		//created = false;
	}
	
	/**
	 * cette methode reinitialise tous les choix pour la creation d'une forme
	 */
	public void resetAll() {
		pointSelected = false;
		linkSelected = false;
		drawPoint = false;
		formeOrigin = null;
		created = false;
		repaint();
	}
	
	/**
	 * cette methode permet `a recevoir un nouveau point en R3
	 * @param x, le coordonne en x du point
	 * @param y, le coordonne en y du point
	 * @param z, le coordonne en z du point
	 */
	public void newPoint(double x, double y, double z) {
		if(formeOrigin == null) {
			formeOrigin = new Forme4D(x,y,z,0);
		}else {
			formeOrigin.setPoint(new Point4D(x,y,z,0));
		}
		this.created = true;
		System.out.println(formeOrigin.toString());
		repaint();
		//repaint();
	}
	
	/**
	 * cette methode permet `a enter tous informations de la matrix de la forme 4D dans une matrix d'information
	 * @return, la matrix d'information
	 */
	public String toString() {
		if(forme4D!=null)return forme4D.toString();	
		return formeOrigin.toString();
	}
	
	/**
	 * cette methode permet `a recevoir l'indice de rotaion pour la visualisation de la forme
	 * @param choice, le choix de plan de rotation
	 * @param angle, l'angle de rotation
	 */
	public void setRot(int choice, int angle) {
		switch(choice) {
			case 0 : yz = angle;
				break;
			case 1 : xz = angle;
				break;
			case 2 : xy = angle;
				break;
			case 3 : xw = angle;
				break;
			case 4 : yw = angle;
				break;
			case 5 : zw = angle;
				break;
			case 6 : x = angle;
				break;
			case 7 : y = angle;
				break;
			case 8 : z = angle;
				break;
		}
		
		repaint();
	}
	
	/**
	 * cette methode permet `a faire la multiplication matricielle pour realiser la rotation de la forme
	 * @param forme, la forme `a rotationner
	 * @param type, l'indice de la dimension pour faire laa rotation
	 * @return, retourne la forme apres ritation
	 */
	public Forme4D rotMulti(Forme4D forme, int type) {
		Matix_Multiplication multi = new Matix_Multiplication();
		if(type == 4) {
			Matrix_Rotation_4D rot = new Matrix_Rotation_4D();
			forme = multi.multiplication4D(rot.rotYZ(yz),forme);
			forme = multi.multiplication4D(rot.rotXZ(xz),forme);
			forme = multi.multiplication4D(rot.rotXY(xy),forme);
			forme = multi.multiplication4D(rot.rotXW(xw),forme);
			forme = multi.multiplication4D(rot.rotYW(yw),forme);
			forme = multi.multiplication4D(rot.rotZW(zw),forme);
			
		}else if (type == 3) {
			Matrix_Rotation_3D rot3D = new Matrix_Rotation_3D();
			forme = multi.multiplication4D(rot3D.rotAxeZ(z), forme);
			forme = multi.multiplication4D(rot3D.rotAxeY(y), forme);
			forme = multi.multiplication4D(rot3D.rotAxeX(x), forme);
		}
		return forme;
	}
	
	/**
	 * cette methode permet `a faire un deplacement
	 * @param dpEntry, l'indice de deplacement
	 */
	public void setDp(double dpEntry) {
		this.dp = dpEntry;
		repaint();
	}
	
	/**
	 * cette methode permet `a dessiner une forme avec un point choisi
	 * @param g2d, contexe graphique
	 * @param choice, le choix de la forme si le mode demo est ouvert
	 * @param search, quel sommet de la forme a ete selectionne
	 */
	public void dim4D(Graphics2D g2d, int choice, int search) {
		AffineTransform mat = g2d.getTransform();
		Color colorBack = g2d.getColor();
		if(demoOn) {
			forme4D = new Forme4D(choice);
		}else {
			forme4D = new Forme4D(formeOrigin.clone());
		}
		
		if(dimUped) {
			forme4D.dimentionUp(valueW);
			//System.out.println(forme4D.get4DMatrix().length);
		}
		
		int[][] links = forme4D.getAllLinks();
		
		forme4D.deplacement(dp);
		//System.out.println(dp);
		forme4D = this.rotMulti(forme4D, 4);
		
		
		
		DimentionalityReductionStrike dimDown = new DimentionalityReductionStrike();
		
		forme3D = dimDown.dim4Dto3D(forme4D,0,0,0,1.5);
		forme3D = this.rotMulti(forme3D, 3);

		forme2D = dimDown.dim3Dto2D(forme3D,0,0,400);
		
	
		forme2D.setAllLinks(links);
		
		DrawForme draw2D = new DrawForme(getWidth(),getHeight());
		//System.out.println(forme2D.get4DMatrix().length);
		//System.out.println(forme2D.toString());
		draw2D.drawComplex2DForme(g2d, forme2D, scale, dimUped,dx,dy,Color.GRAY);
		if(drawPoint) {
			drawPoint(g2d);
		}
		
		if(pointSelected) {
			drawPoint(g2d,search);
		}else if(linkSelected) {
			//System.out.println("linking...");
			drawPoint(g2d,serialP1);
			drawPoint(g2d,serialP2);
			//linkSelected = false;
		}
		
		g2d.setTransform(mat);
		g2d.setColor(colorBack);
	}
	
	
	/**
	 * cette methode permet `a dessiner une forme=
	 * @param g2d, contexe graphique
	 * @param choice, le choix de la forme si le mode demo est ouvert
	 * @param lineColor, le couleur des arretes
	 */
	public void dim4D(Graphics2D g2d, int choice,Color lineColor) {
		AffineTransform mat = g2d.getTransform();
		Color colorBack = g2d.getColor();
		if(demoOn || formeOrigin==null) {
			forme4D = new Forme4D(choice);
		}else {
			forme4D = new Forme4D(formeOrigin.clone());
		}
		
		if(dimUped) {
			forme4D.dimentionUp(valueW);
			//System.out.println(forme4D.get4DMatrix().length);
		}
		
		int[][] links = forme4D.getAllLinks();
		
		forme4D.deplacement(dp);
		//System.out.println(dp);
		forme4D = this.rotMulti(forme4D, 4);

		
		DimentionalityReductionStrike dimDown = new DimentionalityReductionStrike();
		
		forme3D = dimDown.dim4Dto3D(forme4D,0,0,0,1.5);
		forme3D = this.rotMulti(forme3D, 3);

		forme2D = dimDown.dim3Dto2D(forme3D,0,0,400);
		
	
		forme2D.setAllLinks(links);
		
		DrawForme draw2D = new DrawForme(getWidth(),getHeight());
		if(sciMode) {
			draw2D.drawComplex2DForme(g2d, forme2D, scale, dimUped,dx,dy,lineColor);
		}
		
		g2d.setTransform(mat);
		g2d.setColor(colorBack);
	}
	
	/**
	 * cette methode permet a deplacer la forme4D
	 * @param dxEntry, la deplacement en x
	 * @param dyEntry, la deplacement en y
	 */
	public void setDeplacement(int dxEntry, int dyEntry) {
		this.dx = dxEntry;
		this.dy = dyEntry;
	}
	
	/**
	 * cette methode permet a dessiner les sommets de la forme4D
	 * @param g2d, le contexte graphique
	 */
	public void drawPoint(Graphics2D g2d) {
		AffineTransform mat2 = g2d.getTransform();
		Color colorBack = g2d.getColor();
		Point4D[] forme = forme2D.get4DMatrix();
		int dim = forme.length;
		for(int count = 0; count<dim; count++) {
			double pX = forme2D.get4DMatrix()[count].getX()*scale;
			double pY = forme2D.get4DMatrix()[count].getY()*scale;
			Ellipse2D.Double pointCercle = new Ellipse2D.Double(pX-scale/20,pY-scale/20,scale/10,scale/10);
			g2d.setColor(Color.GRAY);
			//g2d.setStroke(new BasicStroke(3));
			g2d.fill(pointCercle);
			
		}
		
		g2d.setTransform(mat2);
		g2d.setColor(colorBack);
		//System.out.println("drawed");
	}
	
	/**
	 * cette methode retourne une forme en 2D apres une reduction dimensionnnelle
	 * @return, une forme en 2D apres une reduction dimensionnnelle
	 */
	public Forme4D getForme2D() {
		return forme2D;
	}
	
	/**
	public void drawPoint(Graphics2D g2d, Color color, Vehicule v1, Vehicule v2) {
		AffineTransform mat2 = g2d.getTransform();
		Color colorBack = g2d.getColor();
		Point4D[] forme = forme2D.get4DMatrix();
		int dim = forme.length;
		for(int count = 0; count<dim; count++) {
			double pX = forme2D.get4DMatrix()[count].getX()*scale+dx;
			double pY = forme2D.get4DMatrix()[count].getY()*scale+dy;
			//System.out.println(pX+" and "+pY);
			Ellipse2D.Double pointCercle = new Ellipse2D.Double(pX-scale/40,pY-scale/40,scale/20,scale/20);
			//Area collider = new Area(pointCercle);
			if(v1.getVecMov()!=null && v1.getCollider().contains(pX-scale/40,pY-scale/40)) {
				System.out.println("hit");
				v1.tailDestroy();
				g2d.setColor(Color.RED);
			}else if(v2.getVecMov()!=null && v2.getCollider().contains(pX-scale/40,pY-scale/40)) {
				System.out.println("hit");
				v2.tailDestroy();
				g2d.setColor(Color.RED);
			}else{
				g2d.setColor(color);
			}
			
			//g2d.setStroke(new BasicStroke(3));
			g2d.fill(pointCercle);
			
		}
		
		g2d.setTransform(mat2);
		g2d.setColor(colorBack);
		//System.out.println("drawed");
	}
	
	**/
	/**
	 * cette methode permet `a dessiner tous les sommets de la forme
	 * @param g2d, contexe graphique
	 * @param search, indice du point qui a ete choisi
	 */
	public void drawPoint(Graphics2D g2d,int search) {
		AffineTransform mat2 = g2d.getTransform();
		Color colorBack = g2d.getColor();
		double pX = forme2D.get4DMatrix()[search].getX()*scale;
		double pY = forme2D.get4DMatrix()[search].getY()*scale;
		Ellipse2D.Double pointCercle = new Ellipse2D.Double(pX-scale/20,pY-scale/20,scale/10,scale/10);
		g2d.setColor(Color.PINK);
		//g2d.setStroke(new BasicStroke(3));
		g2d.fill(pointCercle);
		g2d.setTransform(mat2);
		g2d.setColor(colorBack);
		//System.out.println("drawed");
	}
	
	/**
	 * cherche parmi la forme s'il y a un sommet qui satifait aux coordonnex entrees
	 * @param x, coordonne en x
	 * @param y, coordonne en y
	 */
	public void search(double x, double y) {
		//System.out.println(x+" and "+y);
		
		Point4D[] forme = forme2D.get4DMatrix();
		for(int count = 0; count<forme.length; count++) {
			//System.out.println(getHeight()/2);
			//System.out.println(forme[count].getX()*scale+getWidth()/2+" and "+(forme[count].getY()*scale+getHeight()/2));
			double newX = forme[count].getX()*scale+getWidth()/2;
			double newY = forme[count].getY()*scale+getHeight()/2;
			if((x+scale/15>newX && x-scale/15<newX)&&(y+scale/15>newY && y-scale/15<newY)) {
				//System.out.println("clicked on [" + x +","+ y +""+ "] | found [" + newX+","+newY+"]");
				serial = count;
				pointSelected = true;
				break;
			}
		}
		repaint();
	}
	
	/**
	 *  cherche parmi la forme s'il y a deux sommet qui satifait aux coordonnex entrees
	 * @param x1, coordonne en x du point 1
	 * @param y1, coordonne en y du point 1
	 * @param x2, coordonne en x du point 2
	 * @param y2, coordonne en y du point 2
	 */
	public void search(double x1, double y1, double x2, double y2) {
		//System.out.println(x+" and "+y);
		
		Point4D[] forme = forme2D.get4DMatrix();
		for(int count = 0; count<forme.length; count++) {
			//System.out.println(getHeight()/2);
			//System.out.println(forme[count].getX()*scale+getWidth()/2+" and "+(forme[count].getY()*scale+getHeight()/2));
			double newX = forme[count].getX()*scale+getWidth()/2;
			double newY = forme[count].getY()*scale+getHeight()/2;
			if((x1+scale/15>newX && x1-scale/15<newX)&&(y1+scale/15>newY && y1-scale/15<newY)) {
				//System.out.println("P1: clicked on [" + x1 +","+ y1 +""+ "] | found [" + newX+","+newY+"]");
				serialP1 = count;
				
				break;
			}
		}
		
		for(int count = 0; count<forme.length; count++) {
			//System.out.println(getHeight()/2);
			//System.out.println(forme[count].getX()*scale+getWidth()/2+" and "+(forme[count].getY()*scale+getHeight()/2));
			double newX = forme[count].getX()*scale+getWidth()/2;
			double newY = forme[count].getY()*scale+getHeight()/2;
			if((x2+scale/15>newX && x2-scale/15<newX)&&(y2+scale/15>newY && y2-scale/15<newY)) {
				//System.out.println("P2: clicked on [" + x2 +","+ y2 +""+ "] | found [" + newX+","+newY+"]");
				serialP2 = count;
				
				break;
			}
		}
		pointSelected = false;
		linkSelected = true;
		repaint();
	}
	
	/**
	 * cette methode permet `a construire une arrete antre deux sommets
	 */
	public void setLink() {
		//System.out.println("linking point " + serialP1 + " by " + serialP2);
		formeOrigin.setLink(serialP1, serialP2);
		//formeTemp = new Forme4D(forme4D.clone()); 
		repaint();
	}
	
	/**
	 * dessiner et initialiser tous les objets dans la scene
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
		if(created) {
			if(!demoOn) {
				//System.out.println("reset");
				//forme4D = new Forme4D(formeOrigin.clone());
				//System.out.println(formeTemp.toString());
			}
			if(!dimUped) {
				drawPoint = true;
			}
			
			dim4D(g2d,demoChoice, serial);
		}
		
		if(demoOn) {
			dim4D(g2d,demoChoice, serial);
		}
		
		
	}
	
	/**
	 * cette methode permet `a savoir si on augmente une dimension pour la forme ou non
	 * @param up,augmente une dimension pour la forme ou non
	 */
	public void setDimUp(boolean up) {
		this.dimUped = up;
		//if(!up) {
			reset();
		//}
		repaint();
	}
	
	/**
	 * cette methode permet `a creer un nouveau point en R3
	 * @param x, le coordonne en x du point
	 * @param y, le coordonne en y du point
	 * @param z, le coordonne en z du point
	 */
	public void creatPoint(double x, double y, double z) {
		this.formeOrigin.setPoint(new Point4D(x,y,z,0));
		
		repaint();
	}
	
	/**
	 * choisi la forme predefini dans le mode demo
	 * @param choice, l'indice de la forme predefini dans le mode demo
	 */
	public void setDemoChoice(int choice) {
		this.demoChoice = choice;
		reset();
		repaint();
	}
	
	/**
	 * cette methode permet `a savoir si on active le mode Demo ou non
	 * @param choice, activer le mode Demo ou non
	 */
	public void setDemoOn(boolean choice) {
		this.demoOn = choice;
		reset();
		if(!choice) {
			formeOrigin = null;
			created = false;
		}
		repaint();
	}
	
	/**
	public void creationFinished() {
		this.created = true;
		System.out.println("cloned");
		//formeOrigin = new Forme4D(forme4D.clone()); 
		repaint();
	}
	**/
	
	/**
	  * cette methode permet `a modifier l'echelle de la forme
	 * @param scaleEntry, le scale de la forme 4D
	 */
	public void setScale(int scaleEntry) {
		this.scale = scaleEntry;
		repaint();
	}
	
	/**
	 * cette methode permet `a initialiser une valeur de w en R4
	 * @param value, la valeur de w en R4
	 */
	public void setW(double value) {
		this.valueW = value;
		repaint();
	}
	
	/**
	 * cette methode permet `a faire un save pour la forme cree
	 */
	public void save() {
		 try {
				if(formeOrigin == null) {
					JOptionPane.showMessageDialog(null,"there's nothing to save");
				}else {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");  
					 FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("dat files (*.dat)", "dat");
					fileChooser.setAcceptAllFileFilterUsed(false);
					 fileChooser.addChoosableFileFilter(xmlFilter);
					File f = new File("saveForme4D/");
					fileChooser.setCurrentDirectory(f);
					
					int userSelection = fileChooser.showSaveDialog(null);
					 
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					    FileOutputStream save4D = new FileOutputStream(fileToSave.getAbsolutePath()+".dat");
						ObjectOutputStream fluxObj = new ObjectOutputStream(save4D);
						fluxObj.writeObject(formeOrigin);
						fluxObj.close();
						JOptionPane.showMessageDialog(null,"saved");
					   
					}
					
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not found");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		
	
		
	}
	
	/**
	 * cette methode permet `a faire un load pour une forme deja cree
	 */
	public void load() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to load");  
			 FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("dat files (*.dat)", "dat");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(xmlFilter);
			File f = new File("saveForme4D/");
			fileChooser.setCurrentDirectory(f);
			
			int userSelection = fileChooser.showOpenDialog(null);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    System.out.println("load file: " + fileToSave.getAbsolutePath());
			    loadPath=fileToSave.getAbsolutePath();
			    FileInputStream save4D = new FileInputStream(fileToSave.getAbsolutePath());
				ObjectInputStream fluxObj = new ObjectInputStream(save4D);
				this.formeOrigin = (Forme4D)fluxObj.readObject();
				fluxObj.close();
				created = true;
				repaint();
				JOptionPane.showMessageDialog(null,"load");
			}
			
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"File Not Found");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"IOException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"ClassNotFoundException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/**
	 * cette methode renvoie le load path pour une forme deja cree
	 * @return, le load path
	 */
	public String getLoadPath() {
			return loadPath;
	}
	
	/**
	 * cette methode permet a charger une forme4D a partir d'un load path
	 * @param path, le load path
	 */
	public void load(String path) {
		try {
			FileInputStream save4D = new FileInputStream(path);
			ObjectInputStream fluxObj = new ObjectInputStream(save4D);
			this.formeOrigin = (Forme4D)fluxObj.readObject();
			fluxObj.close();
			created = true;
			//demoOn = false;
			repaint();
			System.out.println("load");
			//JOptionPane.showMessageDialog(null,"load ");
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"File Not Found");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"IOException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"ClassNotFoundException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/**
	 * cette methode permet `a preciser si on active le mode scientifique ou non
	 * @param entry,active le mode scientifique ou non
	 */
	public void setSciMode(boolean entry) {
		this.sciMode = entry;
	}
	

}
