package com.emazon.msvc.stock.msvcstock.domain.enums;

public enum SortDirection {
    ASC,
    DESC;
    public static boolean contains(String direction){
        for(SortDirection sortDirection : SortDirection.values()){
            if(sortDirection.name().equals(direction)){
                return true;
            }
        }
        return false;
    }
}
