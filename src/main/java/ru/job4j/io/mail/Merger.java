package ru.job4j.io.mail;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Merger {
    public Map<String, Set<String>> merge(Map<String, Set<String>> input) {
        // плоская таблица "адрес - владелец"
        Map<String, String> mailOwners = new HashMap<>();
        for (var entry : input.entrySet()) {
            // владелец набора адресов
            String mailSetOwner = null;
            // набор адресов
            Set<String> mailSet = new TreeSet<>();
            // флаг того, что для набора адресов был найден новый владелец
            boolean isSetNewOwner = false;
            for (String mail : entry.getValue()) {
                if (!isSetNewOwner) {
                    // пробуем получить владельца почты из плоской таблицы
                    // если у почты уже есть владелец, значит он будет владельцем всего текущего набора
                    mailSetOwner = mailOwners.get(mail);
                    isSetNewOwner = mailSetOwner != null;
                    // если у почты еще нет владельца, то ей будет владеть текущий пользователь
                    if (mailSetOwner == null) {
                        mailSetOwner = entry.getKey();
                    }
                }
                mailSet.add(mail);
            }
            // привязываем все адреса к владельцу
            for (String mail : mailSet) {
                mailOwners.put(mail, mailSetOwner);
            }
        }
        // конвертируем плоскую таблицу "адрес - владелец" в таблицу "владелец - набор адресов";
        Map<String, Set<String>> rsl = mailOwners.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(
                                Map.Entry::getKey,
                                Collectors.toSet())));
        return rsl;
    }

}
