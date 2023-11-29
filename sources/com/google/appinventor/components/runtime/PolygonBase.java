package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.runtime.util.MapFactory;

@SimpleObject
public abstract class PolygonBase extends MapFeatureBaseWithFill {
    public PolygonBase(MapFactory.MapFeatureContainer mapFeatureContainer, MapFactory.MapFeatureVisitor<Double> mapFeatureVisitor) {
        super(mapFeatureContainer, mapFeatureVisitor);
    }
}
