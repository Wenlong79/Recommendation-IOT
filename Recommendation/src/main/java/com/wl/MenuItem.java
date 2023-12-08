package com.wl;

public class MenuItem {
    private int itemId;
    private String itemName;

    //{2,9} means 2 ratings, 9 points in total. Rating is 4.5
    private int[] ratings;

    private double rating;


    public MenuItem(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
        ratings = new int[]{0, 0};
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int[] getRatings() {
        return ratings;
    }

    public void setRatings(int[] ratings) {
        this.ratings = ratings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
