package br.edu.ifpb.pdist.back;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.pdist.back.model.Recepcionista;

@SpringBootTest
public class RecepcionistaTest {
    @Test
    public void NaoDeveAlterarMatriculaSemDigitos(){
        Recepcionista recepcionista = new Recepcionista("Maria", "123-ABC", "Feminino", "123456789");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            recepcionista.setMatricula("");
        });
    }

    @Test
    public void NaoDeveAlterarMatriculaSeNaoHouver7Digitos(){
        Recepcionista recepcionista = new Recepcionista("Maria", "123-ABC", "Feminino", "123456789");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            recepcionista.setMatricula("123456");
        });
    }

    @Test
    public void DeveAlterarMatricula(){
        Recepcionista recepcionista = new Recepcionista("Maria", "123-ABC", "Feminino", "123456789");
        recepcionista.setMatricula("12345");
        Assertions.assertEquals("12345", recepcionista.getMatricula());
    }

    @Test
    public void NaoDeveAlterarNomeSeVazio(){
        Recepcionista recepcionista = new Recepcionista("Maria", "123-ABC", "Feminino", "123456789");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            recepcionista.setName("");
        });
    }

    @Test
    public void DeveAlterarNome(){
        Recepcionista recepcionista = new Recepcionista("Maria", "123-ABC", "Feminino", "123456789");
        recepcionista.setName("Maria");
        Assertions.assertEquals("Maria", recepcionista.getNome());
    }
}
