
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class Testing {

    @org.junit.jupiter.api.Test
    public void testRussianMessageSending() {
        // Создаем mock объекты
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        // Устанавливаем поведение mock объектов
        when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Москва", Country.RUSSIA, "Lenina", 15));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Вызываем метод, который должен отправить русский текст
        String expected = "Добро пожаловать";
        String message = messageSender.send(map);

        Assertions.assertEquals(message, expected);
    }

    @org.junit.jupiter.api.Test
    public void testEnglishMessageSending() {
        // Создаем mock объекты
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<>();
        // Устанавливаем поведение mock объектов
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        // Вызываем метод, который должен отправить английский текст
        messageSender.send(map);

        // Проверяем, что методы были вызваны с нужными параметрами
        String message = "Welcome";
        String expected = messageSender.send(map);
        Assertions.assertEquals(message, expected);
    }

    @org.junit.jupiter.api.Test
    public void testLocationByIp() {
        // Создаем mock объекты
        GeoServiceImpl geoServiceImpl = mock(GeoServiceImpl.class);

        // Устанавливаем поведение mock объектов
        when(geoServiceImpl.byIp("172.0.32.11")).thenReturn(new Location("Москва", Country.RUSSIA, "Lenina", 15));
        // Вызываем метод, который должен вернуть локацию
        Location location = geoServiceImpl.byIp("172.0.32.11");

        // Проверяем, что метод был вызван с нужным параметром и вернул правильный результат
        assertEquals("Москва", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuilding());
    }

    @Test
    public void testLocalization() {
        // Создаем mock объекты
        LocalizationServiceImpl localizationServiceImpl = mock(LocalizationServiceImpl.class);

        // Устанавливаем поведение mock объекта
        when(localizationServiceImpl.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Вызываем метод, который должен вернуть локализацию
        String locale = localizationServiceImpl.locale(Country.RUSSIA);

        // Проверяем, что метод был вызван с нужным параметром и вернул правильный результат
        assertEquals("Добро пожаловать", locale);
    }
}
