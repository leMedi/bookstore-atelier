package com.hazy.bookstore;


import com.hazy.bookstore.model.Book;
import com.hazy.bookstore.model.Language;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class BookTest {

  @Test(expected = RuntimeException.class)
  public void publicationDateTest() {
    // Publication date should be at the past, if not a RuntimeException will be thrown
    Date date = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime();
    Book b = new Book("JavaEE 7", "4596830145869",
      date, Language.ENGLISH);
  }

}
