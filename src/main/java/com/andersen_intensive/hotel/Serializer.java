package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;
import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.List;

public class Serializer {

    @SneakyThrows
    public void serialization (List<Client> clients, List<Apartment> apartments,
                               List<Reservation> reservations, List<Utility> utilities) throws RuntimeException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonClients = objectMapper.writeValueAsString(clients);
        String jsonApartments = objectMapper.writeValueAsString(apartments);
        String jsonReservations = objectMapper.writeValueAsString(reservations);
        String jsonUtilities = objectMapper.writeValueAsString(utilities);

        try (FileWriter fileWriter = new FileWriter("D:\\hotelAfterComments\\ser")) {
            fileWriter.write(jsonClients);
            fileWriter.write(jsonApartments);
            fileWriter.write(jsonReservations);
            fileWriter.write(jsonUtilities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}