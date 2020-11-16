package com.github.T3Allam.warehouse;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import org.apache.log4j.BasicConfigurator;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/*
    I understand that the api key should be kept somewhere secure
 */

public class Coordinates {

    public double[] getCoordinates (String address) {
//        Logger logger = LoggerFactory.getLogger(Coordinates.class);
        BasicConfigurator.configure();
        String apiKey = "b463280fab29408ba2ed52518f46f5f5";
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(apiKey);
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        request.setLimit(1);
        request.setNoAnnotations(true);
        request.setNoRecord(true);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        if (response.equals(null)) {
            System.out.println("null");
        }
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition();
        double longitude = firstResultLatLng.getLat().doubleValue();
        double latitude = firstResultLatLng.getLng().doubleValue();
        double[] geolocation = {longitude, latitude};
        return geolocation;
    }
}
