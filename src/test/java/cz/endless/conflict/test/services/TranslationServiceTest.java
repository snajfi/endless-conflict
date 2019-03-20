package cz.endless.conflict.test.services;

import cz.endless.conflict.services.TranslationServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TranslationServiceTest {


    private TranslationServiceImpl translationService = new TranslationServiceImpl();

    @Test
    public void testOfReadingFromBundle() {
        assertFalse(translationService.getTranslationForKey("app_version").isEmpty());
    }


}
