package com.wl;

public class Main {
    public static void main(String[] args) {
        MenuRecommendation menuRecommendation = new MenuRecommendation();

        System.out.println(menuRecommendation.getRecommendedItem()); // nothing in stock

        menuRecommendation.addItem(1,"Item-1");
        menuRecommendation.rateItem(1,3);  // [item-1, 3.0 rating]

        menuRecommendation.addItem(2,"Item-2");
        menuRecommendation.rateItem(2,5);
        menuRecommendation.rateItem(2,4);  //[item-2, 4.5 rating]

        System.out.println(menuRecommendation.getRecommendedItem()); //[item-2, 4.5 rating]

        menuRecommendation.outOfStockItem(2); //only [item-1, 3.0 rating]
        System.out.println(menuRecommendation.getRecommendedItem()); // has to be [item-1, 3.0 rating]

        menuRecommendation.restockItem(2); // [item-1, 3.0 rating] and [item-2, 4.5 rating]
        System.out.println(menuRecommendation.getRecommendedItem()); // [item-2, 4.5 rating]

        menuRecommendation.makeDealOfTheDatItem(1); //[item-1, 3.0 rating] is the dealOfTheDay now
        System.out.println(menuRecommendation.getRecommendedItem()); //[item-1, 3.0 rating]

        menuRecommendation.outOfStockItem(1); //deal of the dat out of stock
        System.out.println(menuRecommendation.getRecommendedItem()); //[item-2, 4.5 rating]







    }
}