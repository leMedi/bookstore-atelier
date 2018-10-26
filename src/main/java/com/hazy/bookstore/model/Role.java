package com.hazy.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@NamedQueries({
  @NamedQuery(name = Role.FIND_BY_NAME, query = "select r from Role r where r.name = :name")
})
public class Role implements Serializable {

  public static final String FIND_BY_NAME = "Role.findByName";

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "roles")
  private Collection<User> users;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "roles_permissions",
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
  private Set<Permission> permissions = new HashSet<>();

  public Role() {
  }

  public Role(String name) {
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

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public Collection<User> getUsers() {
    return users;
  }

  public void setUsers(Collection<User> users) {
    this.users = users;
  }

  public void addPremission(Permission p) {
    permissions.add(p);
  }
}
