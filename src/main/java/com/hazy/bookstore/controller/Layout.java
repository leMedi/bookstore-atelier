package com.hazy.bookstore.controller;

import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named @ApplicationScoped
@Getter
public class Layout {

  private static final String APP_TEMPLATE_PATH = "WEB-INF/templates/template.xhtml";

  private String template;

  public String getTemplate() {
    return APP_TEMPLATE_PATH;
  }
}
