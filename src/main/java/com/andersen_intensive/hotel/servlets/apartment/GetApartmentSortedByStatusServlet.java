package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class GetApartmentSortedByStatusServlet extends JsonServlet {

    private final ApartmentServiceImpl apartmentService;

    public GetApartmentSortedByStatusServlet(ApartmentServiceImpl apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        List<Apartment> apartments = apartmentService.getAll();
        apartments.sort(Comparator.comparing(Apartment::getApartmentStatus));
        return new Response(apartments);
    }
}
