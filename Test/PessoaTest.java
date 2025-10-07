/*import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {
    
    @Test
    void testCriacaoPessoa() {
        // Arrange (Preparação)
        String nome = "João Silva";
        String cpf = "123.456.789-00";
        Integer idade = 30;
        Genero genero = Genero.MASCULINO;
        String telefone = "(11) 99999-9999";
        
        // Act (Ação)
        Pessoa pessoa = new Pessoa(nome, cpf, idade, genero, telefone);
        
        // Assert (Verificação)
        assertEquals(nome, pessoa.getNome());
        assertEquals(cpf, pessoa.getCpf());
        assertEquals(idade, pessoa.getIdade());
        assertEquals(genero, pessoa.getGenero());
        assertEquals(telefone, pessoa.getTelefone());
    }
    
    @Test
    void testSetters() {
        // Arrange
        Pessoa pessoa = new Pessoa("Nome", "111", 25, Genero.FEMININO, "(11) 11111-1111");
        
        // Act
        pessoa.setNome("Maria");
        pessoa.setIdade(26);
        
        // Assert
        assertEquals("Maria", pessoa.getNome());
        assertEquals(26, pessoa.getIdade());
    }
    
    @Test
    void testToString() {
        // Arrange
        Pessoa pessoa = new Pessoa("Teste", "999", 40, Genero.OUTRO, "(11) 88888-8888");
        
        // Act
        String resultado = pessoa.toString();
        
        // Assert
        assertTrue(resultado.contains("Teste"));
        assertTrue(resultado.contains("999"));
    }
}*/