package controller;

public enum Category {
    MARE, METEO, MEDICO, SALVATAGGIO,
    LEGGE, PISCINE, BLS;

    public static Category categoryMapper(String toMap) {
        Category category = null;

        if(toMap.equalsIgnoreCase("mare")) { category = MARE; }
        else if(toMap.equalsIgnoreCase("meteorologia")) { category = METEO; }
        else if(toMap.equalsIgnoreCase("medico")) {category = MEDICO; }
        else if(toMap.equalsIgnoreCase("salvataggio")) { category = SALVATAGGIO; }
        else if(toMap.equalsIgnoreCase("legge")) { category = LEGGE; }
        else if(toMap.equalsIgnoreCase("piscine")) { category = PISCINE; }
        else if(toMap.equalsIgnoreCase("bls")) { category = BLS; }

        return category;
    }
}
