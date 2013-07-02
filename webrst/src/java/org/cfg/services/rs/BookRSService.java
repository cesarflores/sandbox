/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cfg.services.rs;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.cfg.model.Book;
import org.cfg.services.bean.BookService;

/**
 *
 * @author Cesar Flores <Cesar.Flores@jalasoft.com>
 */
@Path("/books")
@RequestScoped
public class BookRSService {

    private static final Logger logger = Logger.getLogger(BookRSService.class.getName());
    
    @EJB
    private BookService bookServiceBean;
    
    @Context
    private UriInfo info;
    
    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id){
        logger.log(Level.INFO, "context info - requestUri:{0}", info.getRequestUri().toString());
        Book book = bookServiceBean.getBook(Integer.parseInt(id));
        return Response.ok(book).build();
    }
    
//    @POST
//    public Response post(Book book){
//        boolean state = bookServiceBean.createBook(book);
//        if (state)
//            return Response.ok("The book has been created.").build();
//        
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }
    
    @POST
    public Response post(List<Book> books){
        boolean state = true;
        for (Book book : books) {
            state = state && bookServiceBean.createBook(book);
            if (!state)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
        return Response.ok("The book has been created.").build();
    }
}
