package com.hazy.bookstore.controller;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.enterprise.inject.Model;
import java.io.IOException;

import static org.omnifaces.util.Faces.getRequest;
import static org.omnifaces.util.Faces.redirect;
import static org.omnifaces.util.Messages.addGlobalError;

@Model
@Getter @Setter
public class LoginController {

  public static final String HOME_URL = "index.xhtml";

  private String username;
  private String password;
  private boolean remember;

  public void submit() throws IOException {
    try {
      SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
      SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(getRequest());
      redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
    } catch (AuthenticationException e) {
      addGlobalError("Unknown user, please try again!");
      e.printStackTrace();
    }
  }

}
