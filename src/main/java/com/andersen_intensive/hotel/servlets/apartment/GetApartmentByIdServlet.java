package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetApartmentByIdServlet extends JsonServlet {

    private final ApartmentServiceImpl apartmentService;

    public GetApartmentByIdServlet(ApartmentServiceImpl apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        int id = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
        return new Response(apartmentService.getById(id));
    }
}
