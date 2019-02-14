package fourthDim;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 * cette classe dessine la forme
 * @author XU WEI DUO
 *
 */
public class DrawForme {
	double width, height;
	/**
	 * constructeur qui prend en parametre l'hateur et la largeur
	 * @param x, largeur
	 * @param y, hauteur
	 */
	public DrawForme(double x, double y) {
		this.width = x;
		this.height = y;
	}
	
	/**
	 * cette methode permet `a dessiner une forme 2D de facon simple d'un seul coup
	 * @param g2d, contexe graphique
	 * @param forme2D, la forme en 2D
	 * @param scale, scale de la forme 
	 */
	public void drawSimple2DForme(Graphics2D g2d, Forme4D forme2D, int scale) {
		Path2D.Double line = new Path2D.Double();
		Point4D[] forme = forme2D.get4DMatrix();
		int dim = forme.length;
		line.moveTo(scale*forme[0].getX(),scale*forme[0].getY() );
		for(int a = 0; a<dim; a++) {
			line.lineTo(scale*forme[a].getX(),scale*forme[a].getY());
		}
		//line.closePath();
		
		g2d.translate(200, 120);
		
		//g2d.setColor(Color.PINK);
		//g2d.fill(line);
		//g2d.setColor(Color.BLACK);
		
		g2d.draw(line);
	}
	
	/**
	 * dessiner une forme 2D de facon complexe avec les arretes `a chaque sommet
	 * @param g2d, contexe graphique
	 * @param forme2D, la forme en 2D
	 * @param scale, scale de la forme
	 * @param dimUp, si on aumgmente une dimension de la dimension initiale d'une forme en R3
	 */
	public void drawComplex2DForme(Graphics2D g2d, Forme4D forme2D, int scale, boolean dimUp) {
		drawComplex2DForme(g2d,forme2D,scale,dimUp,0,0, Color.GRAY);
	}
	
	/**
	 ** dessiner une forme 2D de facon complexe avec les arretes `a chaque sommet
	 * @param g2d, contexe graphique
	 * @param forme2D, la forme en 2D
	 * @param scale, scale de la forme
	 * @param dimUp, si on aumgmente une dimension de la dimension initiale d'une forme en R3
	 * @param dx, la position centrale en x de la forme
	 * @param dy, la position centrale en y de la forme
	 * @param lineColor, la couleur des arrets
	 */
	public void drawComplex2DForme(Graphics2D g2d, Forme4D forme2D, int scale, boolean dimUp, int dx,int dy, Color lineColor) {
		Path2D.Double line = new Path2D.Double();
		Point4D[] forme = forme2D.get4DMatrix();
		int dim;
		if(dimUp) {
			dim = forme.length/2;
		}else {
			dim = forme.length;
		}
		
		for(int alpha = 0; alpha<dim; alpha++) {

			if(forme[alpha].getLink() != null) {
				//System.out.println("drawed line");
				int[] links = forme[alpha].getLink();
				for(int beta = 0; beta<links.length; beta++) {
					line.moveTo(scale*forme[alpha].getX()+dx, scale*forme[alpha].getY()+dy);
					line.lineTo(scale*forme[links[beta]].getX()+dx,scale*forme[links[beta]].getY() +dy);
					//System.out.print("["+alpha +" with "+ links[beta]+"] ");
				}
				
			}
		}
		//System.out.println("");
		
		if(dimUp) {
			for(int alpha = dim; alpha<forme.length; alpha++) {
				
				if(forme[alpha].getLink() != null) {
					//System.out.println("drawed line");
					int[] links = forme[alpha].getLink();
					for(int beta = 0; beta<links.length; beta++) {
						line.moveTo(scale*forme[alpha].getX()+dx, scale*forme[alpha].getY()+dy);
						line.lineTo(scale*forme[links[beta]+dim].getX()+dx,scale*forme[links[beta]+dim].getY() +dy);
					}
				}
			}
			
			for(int alpha = 0; alpha<dim; alpha++) {
				line.moveTo(scale*forme[alpha].getX()+dx, scale*forme[alpha].getY()+dy);
				line.lineTo(scale*forme[dim+alpha].getX()+dx, scale*forme[dim+alpha].getY()+dy);
			}
		}
		g2d.translate(width/2.0, height/2.0);
		//System.out.println("deplaced "+width/2.0+" and "+height/2.0);
		//g2d.setColor(Color.PINK);
		//g2d.fill(line);
		g2d.setColor(lineColor);
		g2d.draw(line);
	}
	
}
