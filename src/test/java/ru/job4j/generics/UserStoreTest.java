package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenFind() {
        User user1 = new User("user1");
        UserStore userStore = new UserStore();
        userStore.add(user1);
        assertThat(userStore.findById("user1"), is(user1));
    }

    @Test
    public void whenDelete() {
        User user1 = new User("user1");
        UserStore userStore = new UserStore();
        userStore.add(user1);
        userStore.delete("user1");
        assertNull(userStore.findById("user1"));
    }

    @Test
    public void whenReplace() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        UserStore userStore = new UserStore();
        userStore.add(user1);
        userStore.replace("user1", user2);
        assertNull(userStore.findById("user1"));
        assertThat(userStore.findById("user2"), is(user2));
    }

}