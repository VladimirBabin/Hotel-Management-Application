package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;
import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.io.*;
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
}
