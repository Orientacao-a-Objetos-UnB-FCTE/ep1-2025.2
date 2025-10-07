import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {
    private Especialidade especialidade;
    private String CRM;
    private Integer numeroConsultas;
    private double custoConsulta;
    private List<LocalDateTime> AgendaHorarios; // Lista de horários agendados

    public Medico(String nome, String cpf, Integer idade, Genero genero, String telefone, Especialidade especialidade, String CRM, double custoConsulta) {
        super(nome, cpf, idade, genero, telefone);
        this.especialidade = especialidade;
        this.CRM = CRM;
        this.numeroConsultas = 0;
        this.custoConsulta = custoConsulta;
        this.AgendaHorarios = new ArrayList<>();
    }

    // Getters e Setters
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public Integer getNumeroConsultas() {
        return numeroConsultas;
    }

    public void setNumeroConsultas(Integer numeroConsultas) {
        this.numeroConsultas = numeroConsultas;
    }

    public double getCustoConsulta() {
        return custoConsulta;
    }

    public void setCustoConsulta(double custoConsulta) {
        this.custoConsulta = custoConsulta;
    }

    public List<LocalDateTime> getAgenda() {
        return AgendaHorarios;
    }

    public void setAgenda(List<LocalDateTime> agenda) {
        this.AgendaHorarios = agenda;
    }

    // Método para adicionar um horário na agenda (quando agendar uma consulta)
    public void adicionarHorarioAgenda(LocalDateTime horario) {
        this.AgendaHorarios.add(horario);
    }

    // Método para remover um horário da agenda (quando cancelar uma consulta)
    public void removerHorarioAgenda(LocalDateTime horario) {
        this.AgendaHorarios.remove(horario);
    }
}