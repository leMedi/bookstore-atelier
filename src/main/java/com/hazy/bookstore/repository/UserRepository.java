package com.hazy.bookstore.repository;

import com.hazy.bookstore.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Stateless
public class UserRepository extends AbstractRepository<User> {

  @Inject
  private EntityManager em;

  public UserRepository() {
    super(User.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Optional<User> getByUsername(final String username) {
    try {
      return Optional.of(
        this.em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
          .setParameter("username", username).getSingleResult()
      );
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }

  public Optional<User> getByEmail(final String email) {
    try {
      return Optional.of(
        this.em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
          .setParameter("email", email).getSingleResult()
      );
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }
}
