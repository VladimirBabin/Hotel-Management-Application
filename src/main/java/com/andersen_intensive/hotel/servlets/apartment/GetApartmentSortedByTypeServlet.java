package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.DEPRECATEDservice.ApartmentServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class GetApartmentSortedByTypeServlet extends JsonServlet {

    private final ApartmentServiceImpl apartmentService;

    public GetApartmentSortedByTypeServlet(ApartmentServiceImpl apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        List<Apartment> apartments = apartmentService.getAll();
        apartments.sort(Comparator.comparing(Apartment::getApartmentType));
        return new Response(apartments);
    }
}
