package com.github.T3Allam.warehouse;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import org.apache.log4j.BasicConfigurator;

/*
    I understand that the api key should be kept somewhere secure
 */

public class Coordinates {
    private double latitude;
    private double longitude;

    public double[] getCoordinates (String address) {
//        Logger logger = LoggerFactory.getLogger(Coordinates.class);
        BasicConfigurator.configure();
        String apiKey = "e407501ceb0449908b12df2a1f554363";
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(apiKey);
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        request.setLimit(1);
        request.setNoAnnotations(true);
        request.setNoRecord(true);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition();
        this.longitude = firstResultLatLng.getLat().doubleValue();
        this.latitude = firstResultLatLng.getLng().doubleValue();
        double[] geolocation = {longitude, latitude};
        return geolocation;
    }
}
