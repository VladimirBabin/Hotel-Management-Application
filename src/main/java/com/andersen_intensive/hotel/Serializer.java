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
import java.util.List;

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
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("D:\\hotelAfterComments\\ser");
            JsonNode rootNode = objectMapper.readTree(file);

            List<Client> clients = new ArrayList<>();
            if (rootNode.has("clients")) {
                JsonNode clientsNode = rootNode.get("clients");
                clients = objectMapper.readValue(clientsNode.traverse(), new TypeReference<List<Client>>() {});
            }

            List<Apartment> apartments = new ArrayList<>();
            if (rootNode.has("apartments")) {
                JsonNode apartmentsNode = rootNode.get("apartments");
                apartments = objectMapper.readValue(apartmentsNode.traverse(), new TypeReference<List<Apartment>>() {});
            }

            List<Reservation> reservations = new ArrayList<>();
            if (rootNode.has("reservations")) {
                JsonNode reservationsNode = rootNode.get("reservations");
                reservations = objectMapper.readValue(reservationsNode.traverse(), new TypeReference<List<Reservation>>() {});
            }

            List<Utility> utilities = new ArrayList<>();
            if (rootNode.has("utilities")) {
                JsonNode utilitiesNode = rootNode.get("utilities");
                utilities = objectMapper.readValue(utilitiesNode.traverse(), new TypeReference<List<Utility>>() {});
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
