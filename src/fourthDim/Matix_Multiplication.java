package fourthDim;

import javax.swing.JOptionPane;

/**
 * cette classe permet `a faire la multiplication matricielle 
 * @author XU WEI DUO
 *
 */
public class Matix_Multiplication {

	/**
	 * cette methode permet `a faire une multiplication matricielle en R4
	 * @param mat, la premiere matrix de multiplication 
	 * @param forme4D, la forme en 4D
	 * @return, retourne la forme apres multiplication
	 */
	public Forme4D multiplication4D(double[][] mat, Forme4D forme4D){
		
		int matLine = mat.length;
		int matColonne = mat[0].length;
		Point4D[] matForme = forme4D.get4DMatrix();
		int forme4DColonne = matForme.length;
		int forme4DLine = matForme[0].getDim();
		
		
		//System.out.println(matColonne + " and " + forme4DLine);
		//if(matColonne == forme4DLine) {
			
			 Point4D[] matResult = new Point4D[forme4DColonne];
			 for(int count = 0; count<forme4DColonne; count++) {
				 matResult[count] = new Point4D();
			 }
			
			for(int alpha = 0; alpha < matLine; alpha++) {
				for(int beta = 0; beta < forme4DColonne; beta++) {
					double nb=0;
					for(int delta = 0; delta < matColonne; delta++) {
						nb += mat[alpha][delta] * matForme[beta].getByNb(delta);
						//System.out.println(nb +" ="+ mat[alpha][delta]+" * "+ matForme[beta].getByNb(delta));	
					}
					//System.out.println("setting... "+ nb +" to "+ beta + " with " + alpha);
					matResult[beta].setByNb(nb,alpha);
					//System.out.println("checking... "+ matResult[alpha].getX());
				}
			}
			
			return  new Forme4D(matResult);
		//}else {
			//JOptionPane.showMessageDialog(null,"multiplicaiton impossible"+"colonne nb: "+matColonne+" and line nb: "+ forme4DLine);
			//System.exit(0);
			//return null;
		//}
	}
	
	/**
	 * cette methode permet `a faire une multiplication matricielle entre deux matrix simplement
	 * @param mat1,la premiere matrix de multiplication 
	 * @param mat2,la deuxieme matrix de multiplication 
	 * @return, la matrice apres multiplication
	 */
	public static double[][] multiplication(double[][] mat1, double[][] mat2){
		
		int mat1Line = mat1.length;
		int mat1Colonne = mat1[0].length;
		int mat2Line = mat2.length;
		int mat2Colonne = mat2[0].length;
		
		if(mat1Colonne == mat2Line) {
			double[][] matMulti = new double[mat1Line][mat2Colonne];
			
			for(int alpha = 0; alpha < mat1Line; alpha++) {
				for(int beta = 0; beta < mat2Colonne; beta++) {
					for(int delta = 0; delta < mat1Colonne; delta++) {
						matMulti[alpha][beta] += mat1[alpha][delta] * mat2[delta][beta];
					}
				}
			}
			return matMulti;
		}else {
			System.out.println("multiplicaiton impossible");
			return null;
		}
	}
	
}
