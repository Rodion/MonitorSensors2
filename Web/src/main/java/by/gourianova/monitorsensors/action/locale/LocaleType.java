package by.gourianova.monitorsensors.action.locale;

public enum LocaleType {
    DEFAULT("locale_en_US"),
    RU("locale_ru_RU");
    private String locale;

    LocaleType(String locale) {
        this.locale = locale;
    }

    public static String getLocale(String key) {
        LocaleType entry = LocaleType.valueOf(key.toUpperCase());
        return entry.locale;
    }
}
