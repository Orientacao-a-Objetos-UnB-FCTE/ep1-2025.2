package hospital.interfac;

import hospital.entidade.Consulta;
import hospital.entidade.Internacao;
import java.util.List;

public interface PacienteInterface {
    String getNome();
    int getIdade();
    String getId();
    String getCpf();
    List<Internacao> getHistoricoInternacoes();
    List<Consulta> getHistoricoConsultas();
}