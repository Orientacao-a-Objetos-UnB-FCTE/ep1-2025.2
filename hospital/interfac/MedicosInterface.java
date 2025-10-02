package hospital.interfac;
import hospital.entidade.Consulta;
import java.util.List;

public interface MedicosInterface {
    String getNome();
    String getCrm();
    String getEspecialidade();
    double getCustoConsulta();
    List<Consulta> getHistoricoHorario();

    List<Consulta> getHistoricoConsultas();

    void adicionarConsulta(Consulta consulta);
}
