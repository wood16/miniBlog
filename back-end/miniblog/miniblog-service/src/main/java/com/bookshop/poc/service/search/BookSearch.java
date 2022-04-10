package com.bookshop.poc.service.search;

import com.bookshop.poc.service.entity.BookEntity;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class BookSearch {
    private static final String BOOK_TITLE = "title";
    private static final String BOOK_DESCRIPTION = "description";
    private static final String BOOK_CATEGORY = "category";

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public BookSearch(EntityManager entityManager,
                      EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManager;
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<BookEntity> searchBook(String searchTerm) {

        entityManager = entityManagerFactory.createEntityManager();

        FullTextEntityManager fullTextEntityManager = initializedFullTextEntityManager();

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(BookEntity.class ).get();

//        create query
//        search by category
        BooleanJunction<BooleanJunction> booleanJunction =
                HibernateSearchUtil.buildBooleanJunctionSearch(
                        qb,
                        searchTerm,
                        BOOK_TITLE
//                        BOOK_DESCRIPTION,
//                        BOOK_CATEGORY
                        );

        FullTextQuery query =
                fullTextEntityManager.createFullTextQuery(booleanJunction.createQuery(), BookEntity.class);

        List<BookEntity> bookEntities = query.getResultList();

        commitTransaction();

        return bookEntities;
    }

    private FullTextEntityManager initializedFullTextEntityManager() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        entityManager.getTransaction().begin();

        return fullTextEntityManager;
    }

    private void commitTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
