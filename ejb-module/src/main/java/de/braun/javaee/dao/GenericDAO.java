package de.braun.javaee.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import de.braun.javaee.models.User;

public abstract class GenericDAO <T>{
	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO (Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void save (T entity) {
		em.persist(entity);
	}
	
	public void delete (T entity) {
		T entityToRemove = em.merge(entity);
		
		em.remove(entityToRemove);
	}
	
	public T update (T entity) {
		return em.merge(entity);
	}
	
	public T find (int entityID) {
		return em.find(entityClass, entityID);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll (){
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery(); 
		cq.select(cq.from(entityClass));
		
		return em.createQuery(cq).getResultList();
	}

	public List<T> findAll(final String... sortKeys){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(); 
		Root <T> entity = cq.from(entityClass);
		cq.select(entity);
		
		// generate orderlist
		List<Order> orderList = new ArrayList();
		for (String sortKey : sortKeys) {
			orderList.add (cb.asc(entity.get(sortKey)));
		}
		
		cq.orderBy(orderList);
		
		return em.createQuery(cq).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected T findOneResult (String namedQuery, Map<String, Object> parameters) {
		T result = null;
		
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			
			result = (T) query.getSingleResult();
		} 
		catch (Exception e) {
			System.out.println ("Error while running query" + e.getMessage());
		}
		
		return result;
	}
	
	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry <String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
