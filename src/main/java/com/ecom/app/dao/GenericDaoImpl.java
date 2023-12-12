package com.ecom.app.dao;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

public class GenericDaoImpl<T> implements GenericDaoI<T> {

    private EntityManager em;

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> list(Class<?> entity) {
        String jpql = "FROM " + entity.getSimpleName() + " e";
    
        List<T> results = em.createQuery(jpql, (Class<T>) entity).getResultList();
    
        return results;
    }
    
 @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> listWithFilters(Class<?> entity, Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((Class<T>) entity);
        Root<T> root = criteriaQuery.from((Class<T>) entity);

        List<Predicate> predicates = filters.entrySet().stream()
                .map(entry -> criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
    @Override
    public void addOrUpdate(T entity) {
        em.merge(entity);

    }

    @Override
    public void delete(Class<?> entityClass, Long id) {

        T entity = em.find((Class<T>) entityClass, id);

        if (entity != null) {
            em.remove(entity);
        } else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
