package com.lpoortal.game.Controller;

import com.badlogic.gdx.Gdx;


public class HandleGyro {

    private static final double Y_SENSITIVITY = 18;
    private static final double X_SENSITIVITY = 18;

    private static double gyroX;
    private static double gyroZ;
    private static double accelX;
    private static double accelZ;

    static double[] result = new double[2];

    public static double[] calc() {

        updateGyroValues();

        result[0] = calcXTravel() * X_SENSITIVITY;
        result[1] = calcYTravel() * Y_SENSITIVITY;

        return result;
    }

    private static double calcXTravel(){
        return - (gyroX * (accelX / 10) + gyroZ * (accelZ / 10));
    }

    private static double calcYTravel(){
        return (gyroX * (accelZ / 10) + gyroZ * (-accelX / 10));
    }

    private static void updateGyroValues(){
        gyroX = Gdx.input.getGyroscopeX();
        gyroZ = Gdx.input.getGyroscopeZ();
        accelZ = Gdx.input.getAccelerometerZ();
        accelX = Gdx.input.getAccelerometerX();
    }
}
