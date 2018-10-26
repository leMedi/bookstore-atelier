package com.hazy.bookstore.repository;

import com.hazy.bookstore.model.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Stateless
public class RoleRepository extends AbstractRepository<Role> {

  @Inject
  private EntityManager em;

  public RoleRepository() {
    super(Role.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Optional<Role> findByName(String name) {
    try {
      return Optional.of(
        this.em.createNamedQuery(Role.FIND_BY_NAME, Role.class)
          .setParameter("name", name).getSingleResult()
      );
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }
}
