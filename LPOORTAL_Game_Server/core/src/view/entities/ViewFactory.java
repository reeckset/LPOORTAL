package view.entities;

import java.util.HashMap;
import java.util.Map;

import com.lpoortal.game.LpoortalGame;

import model.entities.EntityModel;
import model.entities.EntityModel.ModelType;

/**
 * A factory for EntityView objects with cache
 */

public class ViewFactory {
    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    /**
     * Creates or returns the view if already exists
     * @param game the main game object
     * @param model - the model of the view to create
     * @return the view required
     */
    public static EntityView makeView(LpoortalGame game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == ModelType.STICKMAN)
                cache.put(model.getType(), new StickmanView(game));
            if (model.getType() == ModelType.CURSOR)
                cache.put(model.getType(), new CursorView(game));
            if (model.getType() == ModelType.PORTAL)
                cache.put(model.getType(), new PortalView(game));
            if (model.getType() == ModelType.INKJAR)
                cache.put(model.getType(), new InkJarView(game));
        }
        return cache.get(model.getType());
    }
}