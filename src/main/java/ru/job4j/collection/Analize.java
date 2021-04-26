package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
    public static class Info {
        int added;
        int changed;
        int deleted;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

    }

    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        Map<Integer, User> prevUsersMap = new HashMap<>();
        for (User user : previous) {
            prevUsersMap.put(user.id, user);
        }
        User prevUser;
        for (User curUser : current) {
            if ((prevUser = prevUsersMap.get(curUser.id)) == null) {
                rsl.added++;
            } else {
                if (!prevUser.name.equals(curUser.name)) {
                    rsl.changed++;
                }
            }
        }
        rsl.deleted = previous.size() + rsl.added - current.size();
        return rsl;
    }

}
