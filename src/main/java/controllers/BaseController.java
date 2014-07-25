package controllers;

import model.Location;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.net.HttpURLConnection;
import java.net.URL;
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


    @Schedule(hour = "*")
    public void  makeRequest(){
        try{
            String url = "http://petybay.in.ua/api/pet";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
        }
        catch (Exception e){

        }
    }
}
