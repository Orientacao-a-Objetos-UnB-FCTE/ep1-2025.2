import java.util.List;

public interface PacienteInterface {
    String getNome();
    int getIdade();
    String getId();
    String getCpf();
    List<String> getHistoricoInternacoes();
    List<String> getHistoricoConsultas();
}