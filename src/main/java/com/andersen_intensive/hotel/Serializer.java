package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;
import lombok.SneakyThrows;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Serializer {

    @SneakyThrows
    public void serialization(List<Client> clients, List<Apartment> apartments,
                              List<Reservation> reservations, List<Utility> utilities) throws RuntimeException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();

        if (!clients.isEmpty()) {
            ArrayNode clientsNode = objectMapper.valueToTree(clients);
            rootNode.put("clients", clientsNode);
        }

        if (!apartments.isEmpty()) {
            ArrayNode apartmentsNode = objectMapper.valueToTree(apartments);
            rootNode.put("apartments", apartmentsNode);
        }

        if (!reservations.isEmpty()) {
            ArrayNode reservationsNode = objectMapper.valueToTree(reservations);
            rootNode.put("reservations", reservationsNode);
        }

        if (!utilities.isEmpty()) {
            ArrayNode utilitiesNode = objectMapper.valueToTree(utilities);
            rootNode.put("utilities", utilitiesNode);
        }

        try (FileWriter fileWriter = new FileWriter("D:\\hotelAfterComments\\ser")) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, rootNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deserialization() throws RuntimeException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            File file = new File("ser.json");

            Map<String, ArrayList<Client>> clientDataMap = objectMapper.readValue(file,
                    new TypeReference<Map<String, ArrayList<Client>>>() {
                    });
            ArrayList<Client> clients = clientDataMap.get("clients");
            System.out.println("Clients:");
            for (Client client: clients) {
                System.out.println(client);
            }

            Map<String, ArrayList<Apartment>> apartmentDataMap = objectMapper.readValue(file,
                    new TypeReference<Map<String, ArrayList<Apartment>>>() {
                    });
            ArrayList<Apartment> apartments = apartmentDataMap.get("apartments");
            System.out.println("\nApartments:");
            for (Apartment apartment: apartments) {
                System.out.println(apartment);
            }

            Map<String, ArrayList<Reservation>> reservationDataMap = objectMapper.readValue(file,
                    new TypeReference<Map<String, ArrayList<Reservation>>>() {
                    });
            ArrayList<Reservation> reservations = reservationDataMap.get("reservations");
            System.out.println("\nReservations:");
            for (Reservation reservation: reservations) {
                System.out.println(reservation);
            }

            Map<String, ArrayList<Utility>> utilityDataMap = objectMapper.readValue(file,
                    new TypeReference<Map<String, ArrayList<Utility>>>() {
                    });
            ArrayList<Utility> utilities = utilityDataMap.get("utilities");
            System.out.println("\nReservations:");
            for (Utility utility: utilities) {
                System.out.println(utility);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
