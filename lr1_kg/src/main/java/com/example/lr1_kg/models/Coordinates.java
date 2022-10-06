package com.example.lr1_kg.models;

public class Coordinates {
    private double x;
    private double y;
    private double z;

    private Matrix matrix;

    public  Coordinates(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
        matrix = new Matrix();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double[] vectorAffinTransform()
    {
        double[] vector = new double[4];
        vector[0]=x;
        vector[1]=y;
        vector[2]=z;
        vector[3]=1;
        return  vector;
    }
    public void multiply( double [][]matrix)
    {
        double[] vector = vectorAffinTransform();
        double [] res = new double[vector.length];

        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix.length; j++)
            {
                res[i]+=vector[j]*matrix[j][i];
            }
        }
        x=res[0];
        y=res[1];
        z=res[2];
    }


}
