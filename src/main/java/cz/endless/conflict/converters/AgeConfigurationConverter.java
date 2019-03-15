package cz.endless.conflict.converters;

import cz.endless.conflict.entities.AgeConfiguration;
import cz.endless.conflict.services.AgeService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by dobeji1 on 15.03.2019.
 */

@ApplicationScoped
@Named
public class AgeConfigurationConverter implements Converter {

    @Inject
    private AgeService ageService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        AgeConfiguration foundedAgeConfiguration = null;
        if (s!=null && !s.isEmpty()) {
            Long id = Long.valueOf(s);
            foundedAgeConfiguration = ageService.getAgeConfigurationById(id);
        }
        return foundedAgeConfiguration;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof AgeConfiguration) {
            return ((AgeConfiguration) o ).getId().toString();
        }
        return "";
    }
}
