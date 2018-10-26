package com.hazy.bookstore.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractRepository<T> {

  private final Class<T> entityClass;

  public AbstractRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  protected abstract EntityManager getEntityManager();

  public void create(T entity) {
    getEntityManager().persist(entity);
  }

  public T edit(T entity) {
    return getEntityManager().merge(entity);
  }

  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
  }

  public T findById(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  public List<T> findAll() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    Root<T> root = cq.from(entityClass);
    cq.select(root);
    return getEntityManager().createQuery(cq).getResultList();
  }

  public int count() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    Root<T> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    javax.persistence.Query q = getEntityManager().createQuery(cq);
    return ((Long) q.getSingleResult()).intValue();
  }

}
