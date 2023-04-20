package com.andersen_intensive.hotel.serializer;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;
import lombok.SneakyThrows;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.io.File;
import java.io.FileWriter;
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

        try (FileWriter fileWriter = new FileWriter("hotel.json")) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, rootNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deserialization() throws RuntimeException {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.readTree(new File("hotel.json"));

            JsonNode clientsNode = rootNode.get("clients");
            List<Map<String, Object>> clients = mapper.convertValue(clientsNode, List.class);
            if (clients != null) {
                System.out.println("Clients:");
                for (Map<String, Object> client : clients) {
                    System.out.println(client);
                }
            }

            JsonNode apartmentsNode = rootNode.get("apartments");
            List<Map<String, Object>> apartments = mapper.convertValue(apartmentsNode, List.class);
            if (apartments != null) {
                System.out.println("\nApartments:");
                for (Map<String, Object> apartment : apartments) {
                    System.out.println(apartment);
                }
            }

            JsonNode utilitiesNode = rootNode.get("utilities");
            List<Map<String, Object>> utilities = mapper.convertValue(utilitiesNode, List.class);
            if (utilities != null) {
                System.out.println("\nUtilities:");
                for (Map<String, Object> utility : utilities) {
                    System.out.println(utility);
                }
            }

            JsonNode reservationsNode = rootNode.get("reservations");
            List<Map<String, Object>> reservations = mapper.convertValue(reservationsNode, List.class);
            if (reservations != null) {
                System.out.println("\nReservations:");
                for (Map<String, Object> reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
