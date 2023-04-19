package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetApartmentsServlet extends JsonServlet {

    private final ApartmentServiceImpl apartmentService;

    public GetApartmentsServlet(ApartmentServiceImpl apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(apartmentService.getAll());
    }
}
