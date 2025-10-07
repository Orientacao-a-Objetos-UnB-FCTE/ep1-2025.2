public enum Genero {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private final String displayName;

    Genero(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}