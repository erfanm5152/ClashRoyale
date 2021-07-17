package Model;

import View.MapView;

public class Bot extends User implements Runnable{
    private transient MapView mapView;
    public Bot() {
        super("bot", "1234");
    }

    @Override
    public void run() {

    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
}
