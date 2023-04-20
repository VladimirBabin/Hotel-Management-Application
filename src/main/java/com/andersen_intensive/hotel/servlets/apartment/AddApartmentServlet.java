package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.math.BigDecimal;
import java.util.Map;

public class AddApartmentServlet extends JsonServlet {
    private final ApartmentServiceImpl apartmentService;

    public AddApartmentServlet(ApartmentServiceImpl apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(apartmentService.create(Integer.parseInt(body.get("apartmentId")),
                new BigDecimal(body.get("price")), ApartmentType.valueOf(body.get("apartmentType"))));
    }
}