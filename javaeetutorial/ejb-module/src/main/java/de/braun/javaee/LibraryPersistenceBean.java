package de.braun.javaee;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class LibraryPersistenceBean
 */
@Stateless
@LocalBean
public class LibraryPersistenceBean implements LibraryPersistenceBeanRemote {

	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager entityManager;
	
	/**
     * @see LibraryPersistenceBeanRemote#getBookts()
     */
    public List<Book> getBooks() {
		return entityManager.createQuery("From Book").getResultList();
    }

	/**
     * @see LibraryPersistenceBeanRemote#addBook(Book)
     */
    public void addBook(Book book) {
    	entityManager.persist(book);
    }
}
