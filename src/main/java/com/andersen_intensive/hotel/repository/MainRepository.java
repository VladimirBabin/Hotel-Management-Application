package com.andersen_intensive.hotel.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public interface MainRepository<T, ID> {

    @PersistenceContext
    static EntityManager entityManager = EntityManagerUtil.getEntityManager();

    default void save(T t) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(t);
        entityTransaction.commit();
    }

    default Optional<T> findById(ID id) {
        Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        T entity = entityManager.find(type, id);
        return Optional.ofNullable(entity);
    }

    default List<T> findAll() {
        Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    default void update(T t) {
        entityManager.merge(t);
    }

    default void delete(T t) {
        entityManager.remove(t);
    }

    default boolean existById(ID id) {
        Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return entityManager.find(type, id) != null;
    }
}
