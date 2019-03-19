package cz.endless.conflict.services;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


/**
 * Created by dobeji1 on 18.03.2019.
 */
@Stateless
public class UtilsServiceImpl implements UtilsService {

    @Inject private FacesContext facesContext;
    @Inject private TranslationService translationService;

    public void addLocalizedMessage(String key, FacesMessage.Severity severity, String... params) {
        String message = translationService.getTranslationForKey(key,params);
        addMessage(message,severity);
    }

    private void addMessage(String message, FacesMessage.Severity severity) {
        if (message!=null && !message.isEmpty()) {
            if(severity==null) {
                severity = FacesMessage.SEVERITY_INFO;
            }
            FacesMessage facesMessage = new FacesMessage(message);
            facesMessage.setSeverity(severity);
            facesContext.addMessage(null, facesMessage);
        }
    }
}
