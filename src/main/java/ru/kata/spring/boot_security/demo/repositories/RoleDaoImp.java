package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @Autowired
    private EntityManager em;

    @Override
    public boolean add(Role role) {
        em.persist(role);
        return true;
    }

    @Override
    public Role getRole(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> getAll() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public void editRole(Role role) {
        em.merge(role);
    }

    @Override
    public void deleteRole(Long id) {
        em.remove(getRole(id));
    }

    @Override
    public List<Role> listByName(List<String> name) {
        return em.createQuery("select u from Role  u where u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
    }

    @Override
    public Role findByName(String name) {
        return em.createQuery("select u from Role  u where  u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }
}
