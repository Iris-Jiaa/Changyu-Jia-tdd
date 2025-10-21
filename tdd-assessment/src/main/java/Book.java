package src.main.java;

public class Book {
    private String id;
    private String title;
    private int copiesAvailable;

    public Book(String id, String title, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.copiesAvailable = copiesAvailable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copies) {
        this.copiesAvailable = copies;
    }
}