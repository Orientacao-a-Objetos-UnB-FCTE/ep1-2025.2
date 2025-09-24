public interface usuariosmed {
    String getNome();
    String getCrm();
    String getEspecialidade();
    double getCustoDaConsulta();
    String[] getHistoricoDeHorario();
    void adicionarConsulta(Consulta consulta);
}
