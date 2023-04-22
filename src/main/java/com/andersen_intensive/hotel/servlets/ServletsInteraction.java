package com.andersen_intensive.hotel.servlets;

import com.andersen_intensive.hotel.service.UtilityService;
import com.andersen_intensive.hotel.DEPRECATEDrepository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.DEPRECATEDrepository.ClientRepositoryImpl;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import com.andersen_intensive.hotel.DEPRECATEDservice.ApartmentServiceImpl;
import com.andersen_intensive.hotel.DEPRECATEDservice.ClientServiceImpl;
import com.andersen_intensive.hotel.servlets.apartment.*;
import com.andersen_intensive.hotel.servlets.client.*;
import com.andersen_intensive.hotel.servlets.utility.AddUtilitiesServlet;
import com.andersen_intensive.hotel.servlets.utility.GetUtilitiesServlet;
import com.andersen_intensive.hotel.servlets.utility.GetUtilityByIdServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletsInteraction {

    private Server server;

//    public final UtilityRepositoryImpl utilityRepository = new UtilityRepositoryImpl();
    public final ClientServiceImpl clientService = new ClientServiceImpl(new ClientRepositoryImpl());
//    public final ReservationServiceImpl reservationService = new ReservationServiceImpl(new ReservationRepositoryImpl
//            (utilityRepository));
    public final ApartmentServiceImpl apartmentService = new ApartmentServiceImpl(new ApartmentRepositoryImpl());
//    public final UtilityServiceImpl utilityService = new UtilityServiceImpl(utilityRepository);
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
                "/apartment/sortByType"
        );
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentSortedByPriceServlet(apartmentService)),
                "/apartment/sortByPrice"
        );
        servletHandler.addServletWithMapping(
                new ServletHolder(new GetApartmentSortedByStatusServlet(apartmentService)),
                "/apartment/sortByStatus"
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
                new ServletHolder(new RemoveClientServlet(clientService)),
                "/client/delete"
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

//        // reservation servlets
//        servletHandler.addServletWithMapping(new ServletHolder
//                (new GetReservationsServlet(reservationService)),
//                "/reservation/all"
//        );
//
//        servletHandler.addServletWithMapping(
//                new ServletHolder(new CreateReservationServlet(reservationService)),
//                "/reservation/create"
//        );
//
//        servletHandler.addServletWithMapping(new ServletHolder
//                (new GetReservationByIdServlet(reservationService)),
//                "/reservation/*"
//        );
//
//        servletHandler.addServletWithMapping(new ServletHolder
//                (new UpdateReservationServlet(reservationService)),
//                "/reservation/update"
//        );
    }
}
