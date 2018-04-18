package com.lpoortal.game.Controller;

public class Matrix {
    private double matrix[][];

    public Matrix(double matrix[][]){
        this.matrix = matrix;
    }

    public void multiply(Matrix m){
        if(this.matrix.length == m.getMatrix().length){
            double[][] result = new double[this.matrix.length][m.getMatrix()[0].length];
            for(int i = 0; i < result.length; i++){
                for(int j = 0; j < result[0].length; j++){
                    for(int k = 0; k < this.matrix[0].length; k++) {
                        result[i][j] += this.matrix[i][k] * m.getMatrix()[k][j];
                    }
                }
            }
            this.matrix = result;
        }
    }

    public double[][] getMatrix(){
        return matrix;
    }

    public static Matrix getRotMatX(double angle){
        double[][] result = {
                {1, 0, 0},
                {0, Math.cos(angle), -Math.sin(angle)},
                {0, Math.sin(angle), Math.cos(angle)}
        };
        return new Matrix(result);
    }

    public static Matrix getRotMatY(double angle){
        double[][] result = {
                {Math.cos(angle), 0, Math.sin(angle)},
                {0, 1, 0},
                {-Math.sin(angle), 0, Math.cos(angle)}
        };
        return new Matrix(result);
    }

    public static Matrix getRotMatZ(double angle){
        double[][] result = {
                {Math.cos(angle), -Math.sin(angle), 0},
                {Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 1}
        };
        return new Matrix(result);
    }

}
