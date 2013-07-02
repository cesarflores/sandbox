/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cfg.services.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cfg.model.Book;

/**
 *
 * @author Cesar Flores <Cesar.Flores@jalasoft.com>
 */
@Stateless
public class BookServiceBean implements BookService{

    @PersistenceContext(unitName="webrstPU")
    private EntityManager entityManager;
    
    public Book getBook(int id) {
        return entityManager.find(Book.class, id);
    }

    public boolean createBook(Book book) {
        entityManager.persist(book);
        return true;
    }

}
