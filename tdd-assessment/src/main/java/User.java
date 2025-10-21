package src.main.java;

public class User {
    private String id;
    private String name;
    private boolean priority;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
