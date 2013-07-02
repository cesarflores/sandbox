/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cfg.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cesar Flores <Cesar.Flores@jalasoft.com>
 */
@XmlRootElement
public class BookStore {

    private List<Book> books;

    public BookStore() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
}
