package utils;

public enum MonthEnum {
    JANEIRO(1),
    FEVEREIRO(2),
    MARCO(3),
    ABRIL(4),
    MAIO(5),
    JUNHO(6),
    JULHO(7),
    AGOSTO(8),
    SETEMBRO(9),
    OUTUBRO(10),
    NOVEMRBO(11),
    DEZEMBRO(12);

    private int numero;

    private MonthEnum(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static String getDescription(int incident) {
        for(MonthEnum incidentType : MonthEnum.values()) {
            if(incidentType.getNumero() == incident)
                return incidentType.name().replace("_", " ");
        }
        return "";
    }

    public static int getEnum(String incident) {
        for(MonthEnum incidentType : MonthEnum.values()) {
            if(incidentType.name().equals(incident.replace(" ", "_").toUpperCase()))
                return incidentType.getNumero();
        }
        return -1;
    }
}
