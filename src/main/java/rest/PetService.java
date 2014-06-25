package rest;

import model.Pet;

import javax.ws.rs.Path;

/**
 * Created by home on 04.04.14.
 */

@Path("/pet")
public class PetService extends BaseEntityService<Pet> {

    public PetService(){
        super(Pet.class);
    }
}
