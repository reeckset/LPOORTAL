package com.lpoortal.game.controller;

public class GyroManager implements Runnable {

    private double x, y;
    private long lastRead;
    private boolean isActive = true;
    private final int GYRO_UPDATE_FREQUENCY = 1;

    public GyroManager(){
        x = 0;
        y = 0;
        lastRead = getCurMilis();
    }


    @Override
    public void run() {
        while(isActive) {
            if(getCurMilis() - lastRead >= GYRO_UPDATE_FREQUENCY) {
                lastRead = getCurMilis();
                double[] gyroResults = GyroCalculator.calc();
                x += gyroResults[0];
                y += gyroResults[1];
            }
        }
    }

    /**
     *
     * @return x cordinate
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Set Gyro state (activated/de-activated)
     * @param active true/false
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    private long getCurMilis(){
        return System.currentTimeMillis();
    }
}
