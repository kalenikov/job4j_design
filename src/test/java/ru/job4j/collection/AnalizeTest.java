package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenAdded() {
        var before = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(1, "1"),
                        new Analize.User(2, "2")
                )
        );
        var after = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(1, "1"),
                        new Analize.User(2, "2"),
                        new Analize.User(3, "3")
                )
        );
        Analize.Info info = new Analize().diff(before, after);
        assertThat(info.added, is(1));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenDeleted() {
        var before = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(1, "1"),
                        new Analize.User(2, "2"),
                        new Analize.User(3, "3")
                )
        );
        var after = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(2, "2")
                )
        );
        Analize.Info info = new Analize().diff(before, after);
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(2));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenChanged() {
        var before = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(1, "1"),
                        new Analize.User(2, "2"),
                        new Analize.User(3, "3")
                )
        );
        var after = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(1, "100"),
                        new Analize.User(2, "200"),
                        new Analize.User(3, "3")
                )
        );
        Analize.Info info = new Analize().diff(before, after);
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(2));
    }

    @Test
    public void whenAdded1Deleted1Changed2() {
        var before = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(1, "1"),
                        new Analize.User(2, "2"),
                        new Analize.User(3, "3")
                )
        );
        var after = new ArrayList<Analize.User>(
                List.of(
                        new Analize.User(2, "200"),
                        new Analize.User(3, "300"),
                        new Analize.User(4, "3")
                )
        );
        Analize.Info info = new Analize().diff(before, after);
        assertThat(info.added, is(1));
        assertThat(info.deleted, is(1));
        assertThat(info.changed, is(2));
    }

}