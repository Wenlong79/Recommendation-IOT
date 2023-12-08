package com.wl;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuRecommendation implements IMenuRecommendation {

    ArrayList<MenuItem> inStockItems = new ArrayList<>();
    ArrayList<MenuItem> outOfStockItems = new ArrayList<>();

    static boolean DealOfTheDayStockStatus = false;

    static MenuItem dealOfTheDay = null;

    @Override
    public void addItem(int itemId, String displayName) {
        MenuItem item = new MenuItem(itemId, displayName);
        inStockItems.add(item);
    }

    @Override
    public MenuItem getRecommendedItem() {
        if (inStockItems.size() == 0){
            return null;
        }

        if (dealOfTheDay != null && DealOfTheDayStockStatus == true){
            return dealOfTheDay;
        } else {
            //if no rating at all, then recommend the first one. Otherwise, return the one with the highest rating
            MenuItem recommendedItem = inStockItems.get(0);
            for (int i = 1; i < inStockItems.size(); i++){
                if (inStockItems.get(i).getRating() > recommendedItem.getRating()){
                    recommendedItem = inStockItems.get(i);
                }
            }
            return recommendedItem;
        }
    }

    @Override
    public void outOfStockItem(int itemId) {
        MenuItem outOfStockItem = null;
        int index = -1;
        for (int i = 0; i < inStockItems.size(); i++) {
            if (inStockItems.get(i).getItemId() == itemId) {
                index = i;
                outOfStockItem = inStockItems.get(i);
                break;
            }
        }
        if (index >= 0) {
            outOfStockItems.add(outOfStockItem);
            inStockItems.remove(index);
            if (dealOfTheDay != null && outOfStockItem.getItemId() == dealOfTheDay.getItemId()) {
                DealOfTheDayStockStatus = false;
            }
        }
    }

    @Override
    public void restockItem(int itemId) {
        MenuItem restockItem = null;
        int index = -1;
        for (int i = 0; i < outOfStockItems.size(); i++) {
            if ((outOfStockItems.get(i).getItemId() == itemId)) {
                index = i;
                restockItem = outOfStockItems.get(i);
                break;
            }
        }
        if (index >= 0) {
            inStockItems.add(restockItem);
            outOfStockItems.remove(index);
            if (dealOfTheDay != null && restockItem.getItemId() == dealOfTheDay.getItemId()) {
                DealOfTheDayStockStatus = true;
            }
        }
    }

    @Override
    public void makeDealOfTheDatItem(int itemId) {
        for (MenuItem menuItem : inStockItems) {
            if (menuItem.getItemId() == itemId) {
                dealOfTheDay = menuItem;
                DealOfTheDayStockStatus = true;
            }
        }
    }

    @Override
    public void rateItem(int itemId, int rating) {
        MenuItem targetItem = null;
        for (MenuItem menuItem : inStockItems) {
            if (menuItem.getItemId() == itemId) {
                targetItem = menuItem;
            }
        }
        if (targetItem != null) {
            int[] prevRatings = targetItem.getRatings();
            int[] updatedRatings = new int[2];
            updatedRatings[0] = prevRatings[0] + 1;
            updatedRatings[1] = prevRatings[1] + rating;
            targetItem.setRatings(updatedRatings);
            double newRating = (double) updatedRatings[1] / (double) updatedRatings[0];
            targetItem.setRating(newRating);
        }
    }

}
