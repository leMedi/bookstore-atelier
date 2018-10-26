package com.hazy.bookstore.repository;

import com.hazy.bookstore.model.Permission;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Stateless
public class PermissionRepository extends AbstractRepository<Permission> {

  @Inject
  private EntityManager em;

  public PermissionRepository() {
    super(Permission.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Optional<Permission> findByName(String name) {
    try {
      return Optional.of(
        this.em.createNamedQuery(Permission.FIND_BY_NAME, Permission.class)
          .setParameter("name", name).getSingleResult()
      );
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }
}
