package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenFind() {
        Role role1 = new Role("role1");
        RoleStore RoleStore = new RoleStore();
        RoleStore.add(role1);
        assertThat(RoleStore.findById("role1"), is(role1));
    }

    @Test
    public void whenDelete() {
        Role role1 = new Role("role1");
        RoleStore RoleStore = new RoleStore();
        RoleStore.add(role1);
        RoleStore.delete("role1");
        assertNull(RoleStore.findById("role1"));
    }

    @Test
    public void whenReplace() {
        Role role1 = new Role("role1");
        Role Role2 = new Role("Role2");
        RoleStore RoleStore = new RoleStore();
        RoleStore.add(role1);
        RoleStore.replace("role1", Role2);
        assertNull(RoleStore.findById("role1"));
        assertThat(RoleStore.findById("Role2"), is(Role2));
    }

}