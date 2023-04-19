package com.andersen_intensive.hotel.servlets;

import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.repository.ClientRepositoryImpl;
import com.andersen_intensive.hotel.repository.ReservationRepositoryImpl;
import com.andersen_intensive.hotel.repository.UtilityRepositoryImpl;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import com.andersen_intensive.hotel.service.ClientServiceImpl;
import com.andersen_intensive.hotel.service.ReservationServiceImpl;
import com.andersen_intensive.hotel.service.UtilityServiceImpl;
import com.andersen_intensive.hotel.servlets.apartment.GetApartmentsServlet;
import com.andersen_intensive.hotel.servlets.client.GetClientsServlet;
import com.andersen_intensive.hotel.servlets.reservation.CreateReservationServlet;
import com.andersen_intensive.hotel.servlets.reservation.GetReservationsServlet;
import com.andersen_intensive.hotel.servlets.utility.AddUtilitiesServlet;
import com.andersen_intensive.hotel.servlets.utility.GetUtilitiesServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletsInteraction {

    private Server server;

    public final ClientServiceImpl clientService = new ClientServiceImpl(new ClientRepositoryImpl());
    public final ReservationServiceImpl reservationService = new ReservationServiceImpl(new ReservationRepositoryImpl());
    public final ApartmentServiceImpl apartmentService = new ApartmentServiceImpl(new ApartmentRepositoryImpl());
    public final UtilityServiceImpl utilityService = new UtilityServiceImpl(new UtilityRepositoryImpl());

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

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8000);

        ServletHandler servletHandler = new ServletHandler();
        addServlets(servletHandler);

        server.setHandler(servletHandler);
        server.setConnectors(new Connector[]{connector});
    }

    private void addServlets(ServletHandler servletHandler) {
        // apartment servlets
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentsServlet(apartmentService)),
                "/apartment/all"
        );

        // client servlets
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetClientsServlet(clientService)),
                "/client/all"
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

        // reservation servlets
        servletHandler.addServletWithMapping(new ServletHolder
                (new GetReservationsServlet(reservationService)),
                "/reservation/all"
        );

        servletHandler.addServletWithMapping(
                new ServletHolder(new CreateReservationServlet(reservationService)),
                "/reservation/create"
        );
    }
}
