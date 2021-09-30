package com.library.app.repository.utils;

import com.library.app.repository.constants.DBOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBOperationExecutorService<T, ID>  {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
    private static final EntityManager em = emf.createEntityManager();
    private final Class<T> typeParameterClass;

    public DBOperationExecutorService(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T execute(DBOperations operation, T data, ID id) {
        switch (operation) {
            case PERSIST:
                data = performPersist(data);
                break;
            case MERGE:
                data = performMerge(data);
                break;
            case GET:
                data = performGet(id);
                break;
            case LIST:
                data = list();
                break;
            case DELETE:
                performDelete(id);
            default:
                break;
        }
        return data;
    }

    private void performDelete(ID id) {
        try {
            em.getTransaction().begin();
            final T type = (T) em.find(typeParameterClass, id);
            em.remove(type);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    private T performGet(ID id) {
        final T t = (T) em.find(typeParameterClass, id);
        return t;
    }

    private T performMerge(T data) {
        try {
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();
            return data;
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        return null;
    }

    private T performPersist(T data) {
        try {
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            return data;
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        return null;
    }

    private T list() {
        return (T) em.createQuery("from " + typeParameterClass.getTypeName()).getResultList();
    }

}
