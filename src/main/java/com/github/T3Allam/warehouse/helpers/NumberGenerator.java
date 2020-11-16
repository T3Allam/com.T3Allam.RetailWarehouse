package com.github.T3Allam.warehouse.helpers;

/*
    In real world application the database will take care of generating an ID
 */

public final class NumberGenerator {
    private static int itemId;

    //generate unqiue item id
    public static int generateUniqueItemId() {
        return ++itemId;
    }

}
