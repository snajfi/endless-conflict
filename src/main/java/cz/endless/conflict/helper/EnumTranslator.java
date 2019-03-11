package cz.endless.conflict.helper;

import cz.endless.conflict.services.TranslationServiceImpl;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by dobeji1 on 09.08.2018.
 */

public class EnumTranslator {

    public static String getMessageKey(Enum<?> e) {
        return loadResourceBundle().getString(e.name());
    }


    private static ResourceBundle loadResourceBundle() {
        String bundleName = "cz.endless.conflict.i18n.messages";
        Locale locale;
        if (FacesContext.getCurrentInstance() == null) {
            locale = Locale.ENGLISH;
        } else {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        return ResourceBundle.getBundle(bundleName, locale, getClassLoader());

    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = TranslationServiceImpl.class.getClassLoader();
        }
        return classLoader;
    }
}
