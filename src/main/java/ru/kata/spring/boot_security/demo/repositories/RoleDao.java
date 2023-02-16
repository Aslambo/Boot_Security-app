package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;


public interface RoleDao {

    boolean add(Role role);

    Role getRole(Long id);

    List<Role> getAll();

    void deleteRole(Long id);

    void editRole(Role role);

    List<Role> listByName(List<String> name);

    Role findByName(String name);

}
