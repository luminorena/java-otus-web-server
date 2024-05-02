package ru.otus.luminorena.web.server.application;

import java.util.UUID;

public class Item {
    private UUID id;
    private String title;
    private int price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item() {
    }

    public Item(String title, int price) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (price != item.price) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        return title != null ? title.equals(item.title) : item.title == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
