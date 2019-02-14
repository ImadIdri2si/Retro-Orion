package fourthDim;

/**
 * cette classe fait les calcules pour la projection de reduction de la dimension 
 * @author XU WEI DUO
 *
 */
public class DimentionalityReductionStrike {
	/**
	 * reduction dimensionnelle de R4 `a R3 `a l'aide de la projecion
	 * @param forme4D,  la forme en R4
	 * @param dx, point de la visualisation de la projection en x
	 * @param dy, point de la visualisation de la projection en y
	 * @param dz, point de la visualisation de la projection en z
	 * @param dw, point de la visualisation de la projection en w
	 * @return, la forme en R3
	 */
	public Forme4D dim4Dto3D (Forme4D forme4D, double dx, double dy, double dz, double dw) {
		Point4D[] entry4D = forme4D.get4DMatrix();
		int dim = entry4D.length;
		double x,y,z;
		Point4D[] exit3D = new Point4D[dim];
		for(int count = 0; count<dim; count++) {
			//entry4D[count] = new Point4D();
			x = dx - dw*(entry4D[count].getX() - dx) / (entry4D[count].getW() - dw);
			y = dy - dw*(entry4D[count].getY() - dy) / (entry4D[count].getW() - dw);
			z = dz - dw*(entry4D[count].getZ() - dz) / (entry4D[count].getW() - dw);
			exit3D[count] = new Point4D(x,y,z);
			//System.out.println("col "+ count + "with [" + x +"|" + y +"|" + z +"]");
		}
		Forme4D forme3D = new Forme4D(exit3D);
		return forme3D;
	}
	
	/**
	 * reduction dimensionnelle de R3 `a R2 `a l'aide de la projecion
	 * @param forme3D, la forme en R3
	 * @param dx, point de la visualisation de la projection en x
	 * @param dy, point de la visualisation de la projection en y
	 * @param dz, point de la visualisation de la projection en z
	 * @return, la forme en R2
	 */
	public Forme4D dim3Dto2D (Forme4D forme3D, double dx, double dy, double dz) {
		Point4D[] entry3D = forme3D.get4DMatrix();
		int dim = entry3D.length;
		double x,y;
		Point4D[] exit3D = new Point4D[dim];
		for(int count = 0; count<dim; count++) {
			//entry4D[count] = new Point4D();
			x = dx - dz*(entry3D[count].getX() - dx) / (entry3D[count].getZ() - dz);
			y = dy - dz*(entry3D[count].getY() - dy) / (entry3D[count].getZ() - dz);
			
			exit3D[count] = new Point4D(x,y);
			//System.out.println("col "+ count + "with [" + x +"|" + y +"|" + z +"]");
		}
		Forme4D forme2D = new Forme4D(exit3D);
		return forme2D;
	}
}
