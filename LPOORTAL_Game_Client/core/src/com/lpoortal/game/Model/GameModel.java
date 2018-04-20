package com.lpoortal.game.Model;

public class GameModel {
    private static GameModel instance;

    /**
     * Make GameModel a singleton
     * @return singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }
}
