package com.andersen_intensive.hotel.servlets;

import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ClientRepository;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.UtilityService;
import com.andersen_intensive.hotel.servlets.apartment.*;
import com.andersen_intensive.hotel.servlets.client.*;
import com.andersen_intensive.hotel.servlets.utility.*;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletsInteraction {

    private Server server;
    public final ClientService clientService = new ClientService(new ClientRepository());
    public final ApartmentService apartmentService = new ApartmentService(new ApartmentRepository());
    public final UtilityService utilityService = new UtilityService(new UtilityRepository());

    public void run() {
        configure();
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void configure() {
        server = new Server();

        try (ServerConnector connector = new ServerConnector(server)) {
            connector.setPort(8080);

            ServletHandler servletHandler = new ServletHandler();
            addServlets(servletHandler);

            server.setHandler(servletHandler);
            server.setConnectors(new Connector[]{connector});
        }
    }

    private void addServlets(ServletHandler servletHandler) {
        // apartment servlets
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentsServlet(apartmentService)),
                "/apartment/all"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new AddApartmentServlet(apartmentService)),
                "/apartment/create"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentByIdServlet(apartmentService)),
                "/apartment/*"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentSortedByTypeServlet(apartmentService)),
                "/apartment/sort/type"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentSortedByPriceServlet(apartmentService)),
                "/apartment/sort/price"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentSortedByStatusServlet(apartmentService)),
                "/apartment/sort/status"
        );

        // client servlets
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetClientsServlet(clientService)),
                "/client/all"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new AddClientServlet(clientService)),
                "/client/create"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new ChangePhoneNumberServlet(clientService)),
                "/client/update"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetClientsByIDServlet(clientService)),
                "/client/*"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetClientByPhoneNumber(clientService)),
                "/client/phone"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetClientsSortedByIDServlet(clientService)),
                "/client/sortById"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetClientsSortedByLastNameServlet(clientService)),
                "/client/sortByLastName"
        );

        // utility servlets
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetUtilitiesServlet(utilityService)),
                "/utility/all"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new AddUtilitiesServlet(utilityService)),
                "/utility/create"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetUtilityByIdServlet(utilityService)),
                "/utility/*"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetUtilitySortedByPriceServlet(utilityService)),
                "/utility/sort/price"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new GetUtilitySortedByNameServlet(utilityService)),
                "/utility/sort/name"
        );
    }
}