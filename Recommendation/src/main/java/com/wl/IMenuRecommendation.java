package com.wl;

public interface IMenuRecommendation {
    void addItem(int itemId, String displayName);
    MenuItem getRecommendedItem();
    void outOfStockItem(int itemId);
    void restockItem(int itemId);
    void makeDealOfTheDatItem(int itemId);
    void rateItem(int itemId, int rating);
}
