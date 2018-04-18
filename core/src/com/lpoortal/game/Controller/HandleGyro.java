package com.lpoortal.game.Controller;

import com.badlogic.gdx.Gdx;


public class HandleGyro {

    static double accumulatedX = 0;
    static double accumulatedY = 0;
    static double lastX = 0;
    static double lastY = 0;

    private static final double DEADZONE_AMOUNT_X = 0.2;
    private static final double DEADZONE_AMOUNT_Y = 0.2;
    private static final double Y_SENSITIVITY = 1;
    private static final double X_SENSITIVITY = 1;


    public static double[] calc() {

        double[] result = new double[]{lastX * X_SENSITIVITY, lastY * Y_SENSITIVITY};

        accumulatedX += calcXTravel();
        accumulatedY += calcYTravel();

        if(Math.abs(accumulatedX) > DEADZONE_AMOUNT_X){
            lastX += accumulatedX;
            accumulatedX = 0;
            result[0] = lastX * X_SENSITIVITY;
        }

        if(Math.abs(accumulatedY) > DEADZONE_AMOUNT_Y){
            lastY += accumulatedY;
            accumulatedY = 0;
            result[1] = lastY * Y_SENSITIVITY;
        }

        return result;
    }

    private static double calcXTravel(){
        float gyroX = Gdx.input.getGyroscopeX();
        float gyroZ = Gdx.input.getGyroscopeZ();
        float accelZ = Gdx.input.getAccelerometerZ();
        float accelX = Gdx.input.getAccelerometerX();
        return - (gyroX * (accelX / 10) + gyroZ * (accelZ / 10));
    }

    private static double calcYTravel(){
        float gyroX = Gdx.input.getGyroscopeX();
        float gyroZ = Gdx.input.getGyroscopeZ();
        float accelZ = Gdx.input.getAccelerometerZ();
        float accelX = Gdx.input.getAccelerometerX();
        return (gyroX * (accelZ / 10) + gyroZ * (-accelX / 10));
    }

}
