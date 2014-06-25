package controllers;

import model.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Created by home on 15.03.14.
 */
@Stateless(name = "BaseController")
public class BaseController {

    @PersistenceContext(name = "petybayunit")
    private EntityManager entityManager;

    public BaseController() {

    }


    public List<Location> getAll(){
        TypedQuery<Location> typedQuery = entityManager.createQuery("SELECT loc FROM Location loc", Location.class);

        return typedQuery.getResultList() ;
    }
}
