/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cfg.services.bean;

import java.util.List;
import javax.ejb.LocalBean;
import org.cfg.model.Book;

/**
 *
 * @author Cesar Flores <Cesar.Flores@jalasoft.com>
 */
@LocalBean
public interface BookService {

    Book getBook(int id);
    
    boolean createBook(Book book);

    List<Book> getBooks();
}
