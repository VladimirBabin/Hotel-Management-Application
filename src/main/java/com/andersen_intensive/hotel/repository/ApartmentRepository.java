package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ApartmentRepository implements MainRepository<Apartment, int> {

   public Apartment findApartmentById(int apartmentId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apartment> query = cb.createQuery(Apartment.class);
        Root<Apartment> root = query.from(Apartment.class);

        query.select(root).where(cb.equal(root.get("apartmentId"), apartmentId));

        TypedQuery<Apartment> typedQuery = entityManager.createQuery(query);

        List<Apartment> apartments = typedQuery.getResultList();
        if (apartments.isEmpty()) {
            return null;
        } else {
            return apartments.get(0);
        }
    }
}
