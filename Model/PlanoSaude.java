public enum PlanoSaude {
    UNIMED("Unimed"),
    AMIL("Amil"),
    SULAMERICA("Sulamerica"),
    HAPVIDA("Hapvida"),
    PARTICULAR("Particular");

    private final String displayName;

    PlanoSaude(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}