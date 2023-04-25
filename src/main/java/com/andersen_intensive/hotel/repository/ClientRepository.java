package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientRepository extends MainRepository<Client, Long> {

    public Client findByPhoneNumber(String phoneNumber) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Client> query = cb.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);

        query.select(root).where(cb.equal(root.get("phone_number"), phoneNumber));

        TypedQuery<Client> typedQuery = getEntityManager().createQuery(query);

        List<Client> clients = typedQuery.getResultList();
        if (clients.isEmpty()) {
            return null;
        } else {
            return clients.get(0);
        }
    }
}