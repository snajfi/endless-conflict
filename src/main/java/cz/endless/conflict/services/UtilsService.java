package cz.endless.conflict.services;

import javax.faces.application.FacesMessage;

/**
 * Created by dobeji1 on 18.03.2019.
 */
public interface UtilsService {

    void addLocalizedMessage(String key, FacesMessage.Severity severity, String... params);

}
