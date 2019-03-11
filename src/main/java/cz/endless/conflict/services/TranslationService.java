package cz.endless.conflict.services;

/**
 * Created by snajfi1 on 03.03.2018.
 */
public interface TranslationService {

    String getTranslationForKey(String resourceKey, String... params);

}
