package com.example.lr1_kg.models;

public class Matrix {


    public double [][] diagonalMatrix()
    {
        double[][] matrix = new double[4][4];
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix.length;j++)
            {
                if(i==j)
                    matrix[i][j]=1;
                else
                    matrix[i][j]=0;

            }
        }
        return  matrix;
    }


    public double [][] transform(double cofX, double cofY, double cofZ)
    {

        double [][] transf = diagonalMatrix();
        transf[3][0]=cofX;
        transf[3][1]=cofY;
        transf[3][2]=cofZ;

        return transf;

    }

    public double [][] dilation(double cofX, double cofY, double cofZ)
    {

        double [][] delate = diagonalMatrix();
        delate[0][0]=cofX;
        delate[1][1]=cofY;
        delate[2][2]=cofZ;

        return delate;

    }
    public double [][] rotationX(double corner)
    {

        double [][] rotate = diagonalMatrix();
        rotate[1][1]=Math.cos(corner);
        rotate[1][2]=Math.sin(corner);
        rotate[2][1]=-Math.sin(corner);
        rotate[2][2]=Math.cos(corner);

        return rotate;

    }
    public double [][] rotationY(double corner)
    {

        double [][] rotate =diagonalMatrix();
        rotate[0][0]=Math.cos(corner);
        rotate[0][2]=-Math.sin(corner);
        rotate[2][0]=Math.sin(corner);
        rotate[2][2]=Math.cos(corner);

        return rotate;

    }
    public double [][] rotationZ(double corner)
    {

        double [][] rotate = diagonalMatrix();

        rotate[0][0]=Math.cos(corner);
        rotate[0][1]=Math.sin(corner);
        rotate[1][0]=-Math.sin(corner);
        rotate[1][1]=Math.cos(corner);

        return rotate;

    }

}
