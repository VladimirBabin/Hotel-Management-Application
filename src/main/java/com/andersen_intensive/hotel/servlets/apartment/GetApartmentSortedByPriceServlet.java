package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class GetApartmentSortedByPriceServlet extends JsonServlet {

    private final ApartmentServiceImpl apartmentService;

    public GetApartmentSortedByPriceServlet(ApartmentServiceImpl apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        List<Apartment> apartments = apartmentService.getAll();
        apartments.sort(Comparator.comparing(Apartment::getApartmentPrice));
        return new Response(apartments);
    }
}
