//package com.andersen_intensive.hotel.stage_6_repository;
//
//import com.andersen_intensive.hotel.models.Utility;
//
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//public class UtilityRepository implements MainRepository<Utility, Long> {
//
//    public Utility findByName(String name) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Utility> query = cb.createQuery(Utility.class);
//        Root<Utility> root = query.from(Utility.class);
//
//        query.select(root).where(cb.equal(root.get("name"), name));
//
//        TypedQuery<Utility> typedQuery = entityManager.createQuery(query);
//
//        List<Utility> utilities = typedQuery.getResultList();
//        if (utilities.isEmpty()) {
//            return null;
//        } else {
//            return utilities.get(0);
//        }
//    }
//}
