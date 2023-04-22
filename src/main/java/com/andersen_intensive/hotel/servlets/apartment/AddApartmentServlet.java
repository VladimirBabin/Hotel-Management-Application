package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
@RequiredArgsConstructor
public class AddApartmentServlet extends JsonServlet {
    private final ApartmentService apartmentService;

    @Override
    public Response post(String uri, Map<String, String> body) {
        Apartment apartment = new Apartment(new BigDecimal(body.get("price")),
                ApartmentType.valueOf(body.get("apartmentType")),
                ApartmentStatus.valueOf(body.get("apartmentStatus")));
        return new Response(apartmentService.saveApartment(apartment));
    }
}