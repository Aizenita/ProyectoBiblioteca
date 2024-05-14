package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
