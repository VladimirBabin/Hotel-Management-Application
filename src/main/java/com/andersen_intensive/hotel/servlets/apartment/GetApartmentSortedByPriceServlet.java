package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GetApartmentSortedByPriceServlet extends JsonServlet {

    private final ApartmentService apartmentService;

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        List<Apartment> apartments = apartmentService.showAll();
        apartments.sort(Comparator.comparing(Apartment::getPrice));
        return new Response(apartments);
    }
}
