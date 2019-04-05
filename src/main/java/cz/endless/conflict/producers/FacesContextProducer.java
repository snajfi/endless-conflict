package cz.endless.conflict.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Created by snajfi1 on 18.12.2017.
 */
public class FacesContextProducer {

    @Produces
    @RequestScoped
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }

}
