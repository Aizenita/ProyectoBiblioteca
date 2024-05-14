package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.Notification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NotificationController {


    public NotificationController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createNotification(Notification notification) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(notification);
        em.getTransaction().commit();
        em.close();
    }

    public Notification findNotification(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Notification.class, id);
    }

    public void updateNotification(Notification notification) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(notification);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteNotification(Integer id) {
        EntityManager em = getEntityManager();
        Notification notification = em.find(Notification.class, id);
        em.getTransaction().begin();
        em.remove(notification);
        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }



}
