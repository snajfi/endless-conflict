package cz.endless.conflict.services;

import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by snajfi1 on 03.03.2018.
 */
@ApplicationScoped
public class TranslationServiceImpl implements TranslationService {


    @Inject
    private Logger logger;

    private ResourceBundle bundle = null;

    public String getTranslationForKey(String resourceKey, String... params) {
        String translation = "";

        if (bundle==null) {
            loadResourceBundle();
        }

        if (!isBlank(resourceKey)) {

            if (bundle != null && bundle.containsKey(resourceKey)) {
                translation = bundle.getString(resourceKey);

                if(params!=null) {
                    MessageFormat mf = new MessageFormat(translation);
                    translation = mf.format(params);
                }
            } else {
                logger.error("Translation not found for key - " + resourceKey);
            }
        } else {
            logger.error("Attempt to search by null or empty key!");
        }
        return translation;
    }

    private void loadResourceBundle() {
        String bundleName = "cz.endless.conflict.i18n.messages";
        try {
            bundle = ResourceBundle.getBundle(bundleName, Locale.ENGLISH, getClassLoader());
        } catch (MissingResourceException mre) {
            logger.error("Can't find bundle!", mre);
        }
    }

    private ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = TranslationServiceImpl.class.getClassLoader();
        }
        return classLoader;
    }

    private boolean isBlank(String string) {
        return string==null || string.trim().isEmpty();
    }

}
