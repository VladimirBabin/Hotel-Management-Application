package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;
@RequiredArgsConstructor
public class GetApartmentsServlet extends JsonServlet {
    private final ApartmentService apartmentService;
    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(apartmentService.showAll());
    }
}