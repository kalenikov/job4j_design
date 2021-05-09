package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.mail.Merger;
import ru.job4j.io.mail.Parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MergerTest {

    @Test
    public void whenMerge2() {
        Map<String, Set<String>> input = new HashMap<>();
        input.put("user1", new HashSet<>(Set.of("aa", "bb", "cc")));
        input.put("user3", new HashSet<>(Set.of("zz", "aa")));

        Map<String, Set<String>> rsl = new HashMap<>();
        rsl.put("user1", Set.of("aa", "bb", "cc", "zz"));
        assertThat(new Merger().merge(input), is(rsl));
    }

    @Test
    public void whenMerge5FromFile() {
        Map<String, Set<String>> input = new Parser("src/test/resources/usermails.csv").parse();
        Map<String, Set<String>> rsl = new HashMap<>();
        rsl.put("user1", Set.of("aaa@bbb.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "xxx@ya.ru"));
        rsl.put("user5", Set.of("vasya@pupkin.com", "xyz@pisem.net"));
        assertThat(new Merger().merge(input), is(rsl));
    }

}