import java.util.List;

public interface MedicosInterface {
    String getNome();
    String getCrm();
    String getEspecialidade();
    double getCustoConsulta();
    List<String> getHistoricoHorario();
    void adicionarConsulta(Consulta consulta);
}
