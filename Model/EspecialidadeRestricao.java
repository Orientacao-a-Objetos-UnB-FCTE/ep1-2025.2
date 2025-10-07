// Interface para especialidades com restrições
public interface EspecialidadeRestricao {
    boolean isAdequadaPara(Integer idade, Genero genero);
}