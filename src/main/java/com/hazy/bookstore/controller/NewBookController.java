package com.hazy.bookstore.controller;

import com.hazy.bookstore.model.Book;
import com.hazy.bookstore.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.IOException;

import static org.omnifaces.util.Faces.redirect;
import static org.omnifaces.util.Messages.addFlashGlobalInfo;

@Model // Model : @RequestScoped and @Named
@Getter @Setter
public class NewBookController {

  @Inject
  private BookRepository bookRepository;

  private Book book;

  @PostConstruct
  public void init() {
    book = new Book();
  }

  public void save() throws IOException {
    bookRepository.create(book);

    addFlashGlobalInfo("Book added successfully!");
    redirect("index.xhtml");
  }
}
