package com.hazy.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "permissions")
@NamedQueries({
  @NamedQuery(name = Permission.FIND_BY_NAME, query = "select p from Permission p where p.name = :name")
})
public class Permission implements Serializable {

  public static final String FIND_BY_NAME = "Permission.findByName";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "permissions")
  private Collection<Role> roles;

  public Permission() {
  }

  public Permission(final String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }
}
