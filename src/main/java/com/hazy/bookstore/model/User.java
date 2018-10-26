package com.hazy.bookstore.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"username", "email"})
})
@NamedQueries({
  @NamedQuery(name = User.FIND_BY_USERNAME, query = "select a from User a where a.username = :username")
  ,
  @NamedQuery(name = User.FIND_BY_EMAIL, query = "select a from User a where a.email = :email")
})
public class User implements Serializable {

  public static final String FIND_BY_USERNAME = "User.findByUsername";
  public static final String FIND_BY_EMAIL = "User.findByEmail";

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 1, max = 100)
  private String username;

  @NotNull
  @Column(length = 128)
  private String password;

  @NotNull
  @Size(min = 1, max = 100)
  private String email;

  @Column(name = "active")
  @NotNull
  private boolean active;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", email='" + email + '\'' +
      ", active=" + active +
      '}';
  }
}
