package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemberController {


    public MemberController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createMember(Member member) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
        em.close();
    }

    public Member findMember(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Member.class, id);
    }

    public void updateMember(Member member) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(member);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteMember(Integer id) {
        EntityManager em = getEntityManager();
        Member member = em.find(Member.class, id);
        em.getTransaction().begin();
        em.remove(member);
        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }

    public List<Member> findMembersByNameAndSurname(String name, String surname) {
        EntityManager em = getEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.name = :name AND m.surname = :surname", Member.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    public void deleteMemberByNameAndSurname(String name, String surname) {
        List<Member> members = findMembersByNameAndSurname(name, surname);

        if (members.size() == 1) {
            deleteMember(members.get(0).getMember_id());
        } else if (members.size() > 1) {
            System.out.println("Se encontraron varios miembros con el mismo nombre y apellido. Por favor, proporciona un ID para eliminar el miembro correcto.");
        } else {
            System.out.println("No se encontró ningún miembro con el nombre y apellido proporcionados.");
        }
    }

}
