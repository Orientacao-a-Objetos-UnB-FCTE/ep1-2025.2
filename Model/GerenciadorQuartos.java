import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GerenciadorQuartos {
    private Map<Integer, Quarto> todosOsQuartos = new HashMap<>();

    public void adicionarQuarto(Quarto quarto) {
        todosOsQuartos.put(quarto.getNumero(), quarto);
    }

    public Quarto buscarQuartoDisponivel(int numeroQuartoDesejado) {
        Quarto quarto = todosOsQuartos.get(numeroQuartoDesejado);
        if (quarto != null && !quarto.isOcupado()) {
            return quarto;
        }
        return null;
    }

    // Método para listar todos os quartos disponíveis
    public List<Quarto> listarQuartosDisponiveis() {
        return todosOsQuartos.values().stream()
                .filter(quarto -> !quarto.isOcupado())
                .collect(Collectors.toList());
    }
}