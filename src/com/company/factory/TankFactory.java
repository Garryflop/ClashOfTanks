package com.company.factory;

import com.company.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TankFactory {
    private static final Map<TankType, Function<FactoryParams, Tank>> registry = new HashMap<>();
    //Tank player1 = TankFactory.createTank(TankType.ASSAULT, "PlayerName", )
    static {
        registry.put(TankType.ASSAULT, params -> new AssaultTank(params.getName(), params.getX(), params.getY()));
        registry.put(TankType.DEFENDER, params -> new DefenderTank(params.getName(), params.getX(), params.getY()));
        registry.put(TankType.SCOUT, params -> new ScoutTank(params.getName(), params.getX(), params.getY()));
    }


    public static Tank createTank(TankType type, String name, int x, int y) {
        FactoryParams params = new FactoryParams(name, x, y);
        Function<FactoryParams, Tank> factory = registry.get(type);
        if (factory != null) {
            return factory.apply(params);
        } else {
            throw new IllegalArgumentException("Unknown tank type: " + type);
        }
    }

    public static void registerTankType(TankType type, Function<FactoryParams, Tank> factory) {
        registry.put(type, factory);
    }
}
