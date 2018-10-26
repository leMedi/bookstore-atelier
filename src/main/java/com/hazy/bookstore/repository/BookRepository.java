package com.hazy.bookstore.repository;

import com.hazy.bookstore.model.Book;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class BookRepository extends AbstractRepository<Book> {

  @Inject
  private EntityManager em;

  public BookRepository() {
    super(Book.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }
}
