package cz.endless.conflict.test.services;

import cz.endless.conflict.services.TranslationServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TranslationServiceTest {

    private TranslationServiceImpl translationService = new TranslationServiceImpl();

    @Test
    public void testOfReadingFromBundle() {
        assertFalse(translationService.getTranslationForKey("app_version").isEmpty());

    }


}
