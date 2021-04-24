package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenConfWithComment() {
        String path = "src/test/resources/conf_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"), is("value1"));
        assertThat(config.value("key2"), is("value2"));
    }

    @Test
    public void whenConfWithoutComment() {
        String path = "src/test/resources/conf_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"), is("value1"));
        assertThat(config.value("key2"), is("value2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConfWithError() {
        String path = "src/test/resources/conf_with_error.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"), is("value1"));
    }
}