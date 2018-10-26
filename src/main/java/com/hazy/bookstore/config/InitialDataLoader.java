package com.hazy.bookstore.config;

import com.hazy.bookstore.model.Permission;
import com.hazy.bookstore.model.Role;
import com.hazy.bookstore.model.User;
import com.hazy.bookstore.repository.UserRepository;
import org.apache.shiro.authc.credential.DefaultPasswordService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitialDataLoader {

  @Inject
  private UserRepository userRepository;

  // TODO: Inject DefaultPasswordService
  private DefaultPasswordService getPasswordService() {
    return new DefaultPasswordService();
  }

  @PostConstruct
  public void init() {
    Permission BOOKS_CREATE = new Permission("BOOKS:CREATE");
    Permission BOOKS_UPDATE = new Permission("BOOKS:UPDATE");
    Permission BOOKS_DELETE = new Permission("BOOKS:DELETE");

    Permission ALL = new Permission("*");

    Role userRole = new Role("ROLE_USER");
    userRole.addPremission(BOOKS_CREATE);

    Role adminRole = new Role("ROLE_ADMIN");
    adminRole.addPremission(ALL);

    User hazy = new User("hazy", getPasswordService().encryptPassword("123"), "hamz.youssef@gmail.com");
    hazy.addRole(adminRole);
    hazy.setActive(true);
    userRepository.create(hazy);

    User jiher = new User("lorem", getPasswordService().encryptPassword("123"), "lorem.ipsum@gmail.com");
    jiher.addRole(userRole);
    jiher.setActive(true);
    userRepository.create(jiher);
  }

}
