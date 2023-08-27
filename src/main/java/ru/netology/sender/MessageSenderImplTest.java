package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;




public class MessageSenderImplTest {
    @Test
    public void testRussianMessageSending() {
        // Создаем mock объекты
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        // Устанавливаем поведение mock объектов
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Москва", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Вызываем метод, который должен отправить русский текст
        String expected = "Добро пожаловать";
        String message = messageSender.send(map);

        Assertions.assertEquals(message, expected);
    }

    @org.junit.jupiter.api.Test
    public void testEnglishMessageSending() {
        // Создаем mock объекты
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<>();
        // Устанавливаем поведение mock объектов
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        // Вызываем метод, который должен отправить английский текст
        messageSender.send(map);

        // Проверяем, что методы были вызваны с нужными параметрами
        String message = "Welcome";
        String expected = messageSender.send(map);
        Assertions.assertEquals(message, expected);
    }
}
