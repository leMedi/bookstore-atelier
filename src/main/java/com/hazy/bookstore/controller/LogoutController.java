package com.hazy.bookstore.controller;

import org.apache.shiro.SecurityUtils;

import javax.enterprise.inject.Model;
import java.io.IOException;

import static org.omnifaces.util.Faces.invalidateSession;
import static org.omnifaces.util.Faces.redirect;

@Model
public class LogoutController {

  public static final String HOME_URL = "login.xhtml";

  public void submit() throws IOException {
    SecurityUtils.getSubject().logout();
    invalidateSession();
    redirect(HOME_URL);
  }
}
