public enum StatusConsulta{
    AGENDADA("Agendada"),
    CONCLUIDA("Concluida"),
    CANCELADA("Cancelada");

    private final String displayName;

    StatusConsulta(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}