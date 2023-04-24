package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class UpdateApartmentStatusServlet extends JsonServlet {
    private final ApartmentService apartmentService;
    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(apartmentService.updateStatus(
                Long.parseLong(body.get("id")),
                ApartmentStatus.valueOf(body.get("status"))
        ));
    }
}
