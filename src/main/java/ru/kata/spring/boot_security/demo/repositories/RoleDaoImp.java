package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @Autowired
    private EntityManager em;

    @Override
    public Role getRoleByName(String name) {
        Query query = em.createQuery("select role from Role role where role.name = :name", Role.class);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }

    @Override
    public List<Role> getListOfRoles() {
        return em.createQuery("select role from Role role", Role.class).getResultList();
    }
}
