package com.hazy.bookstore.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(length = 10000)
  private String description;

  // Not Blank
  private String isbn;

  @Column(name = "publication_date") // make naming automatic
  @Past
  @NotNull
  private Date publicationDate;

  @Column(name = "nb_of_pages")
  @Min(1)
  private Integer nbOfPages;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Language language;

  public Book() {
  }

  public Book(String title, String isbn, Date publicationDate, Language language) {
    this.title = title;
    this.isbn = isbn;

    // Not Recommended
    if (publicationDate.after(new Date())) {
      throw new RuntimeException("Publication date should be at the past");
    }

    this.publicationDate = publicationDate;
    this.language = language;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) throws Exception {

    this.publicationDate = publicationDate;
  }

  public Integer getNbOfPages() {
    return nbOfPages;
  }

  public void setNbOfPages(Integer nbOfPages) {
    this.nbOfPages = nbOfPages;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }
}
