package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalizationServiceImplTest {
    @Test
    public void testLocalization() {
        LocalizationServiceImpl localizationServiceImpl = new LocalizationServiceImpl();
        // Вызываем метод, который должен вернуть локализацию
        String locale = localizationServiceImpl.locale(Country.RUSSIA);

        // Проверяем, что метод был вызван с нужным параметром и вернул правильный результат
        assertEquals("Добро пожаловать", locale);
    }
}
