package ru.netology;

import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GeoServiceImplTest {
    @org.junit.jupiter.api.Test
    public void testLocationByIp() {
        // Создаем mock объекты
        GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
        // Вызываем метод, который должен вернуть локацию
        Location location = geoServiceImpl.byIp("172.0.32.11");
        // Проверяем, что метод был вызван с нужным параметром и вернул правильный результат
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuilding());
    }
}
