/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Note;

/**
 *
 * @author 798419
 */
public class NoteDB {

    public int insert(Note note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        int number = 0;
        try {
            transaction.begin();
            em.persist(note);
            transaction.commit();
            number++;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            System.out.println("Fail to insert.");
        } finally {
            em.close();
        }
        return number;
    }

    public int update(Note note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        int number = 0;
        try {
            transaction.begin();
            em.merge(note);
            transaction.commit();
            number++;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            System.out.println("Fail to update.");
        } finally {
            em.close();
        }
        return number;
    }

    public List<Note> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Note> lists = null;
        try {
            lists = (List<Note>) em.createNamedQuery("Note.findAll", Note.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can not load the list");
        } finally {
            em.close();
        }
        return lists;
    }

    public Note get(int noteId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Note note = null;
        try {
            note = em.find(Note.class, noteId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can not find the note by noteId");
        } finally {
            em.close();
        }
        return note;
    }

    public int delete(Note note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        int number = 0;
        try {
            transaction.begin();
            em.remove(em.merge(note));
            transaction.commit();
            number++;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            System.out.println("Fail to delete.");
        } finally {
            em.close();
        }
        return number;
    }

}
