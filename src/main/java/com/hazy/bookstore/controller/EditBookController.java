package com.hazy.bookstore.controller;

import com.hazy.bookstore.model.Book;
import com.hazy.bookstore.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import static org.omnifaces.util.Faces.redirect;
import static org.omnifaces.util.Messages.addFlashGlobalInfo;

@Named @ViewScoped
@Getter @Setter
public class EditBookController implements Serializable {

  public static final String HOME_URL = "index.xhtml";

  @Inject
  private BookRepository bookRepository;

  private Book book;
  private Long bookId;

  public void load() {
    book = bookRepository.findById(bookId);
  }

  public void save() throws IOException {
    bookRepository.edit(book);

    addFlashGlobalInfo("Book updated successfully!");
    redirect(HOME_URL);
  }
}
