//package com.andersen_intensive.hotel.stage_6_repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class EntityManagerUtil {
//
//    private static EntityManagerFactory entityManagerFactory;
//
//    public static EntityManager getEntityManager() {
//        if (entityManagerFactory == null) {
//            entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
//        }
//        return entityManagerFactory.createEntityManager();
//    }
//}
