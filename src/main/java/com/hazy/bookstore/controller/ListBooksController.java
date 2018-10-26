package com.hazy.bookstore.controller;

import com.hazy.bookstore.model.Book;
import com.hazy.bookstore.model.User;
import com.hazy.bookstore.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import static org.omnifaces.util.Messages.addGlobalError;
import static org.omnifaces.util.Messages.addGlobalInfo;

@Named @ViewScoped
@Getter @Setter
public class ListBooksController implements Serializable{

    @Inject
    private BookRepository bookRepository;

    private List<Book> books;
    private List<Book> selectedBooks;

    private String username;

    @PostConstruct
    public void init() {
        books = bookRepository.findAll();
        if (SecurityUtils.getSubject().isAuthenticated()) {
            username = SecurityUtils.getSubject().getPrincipal().toString();
        }
    }

    // TODO : Security Method Level
    public void deleteBooks() {
      if (selectedBooks.size() <= 0) {
        addGlobalError("You should select at least one Book to perform this action.");
        return;
      }
      for (Book b : selectedBooks) {
        books.remove(b);
        bookRepository.remove(b);
      }
      addGlobalInfo("Book(s) deleted successfully!");
    }
}
