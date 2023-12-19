package br.edu.ifpb.pdist.back;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.pdist.back.model.Enfermeiro;

@SpringBootTest
public class EnfermeiroTest {
    @Test
    public void NaoDeveAlterarCORENSemDigitos(){
        Enfermeiro enfermeiro = new Enfermeiro("Maria", "123-ABC", "Feminino", "123456789");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            enfermeiro.setCoren("");
        });
    }

    @Test
    public void NaoDeveAlterarCORENSeNaoHouver7Digitos(){
        Enfermeiro enfermeiro = new Enfermeiro("Maria", "123-ABC", "Feminino", "123456789");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            enfermeiro.setCoren("123456");
        });
    }

    @Test
    public void DeveAlterarCOREN(){
        Enfermeiro enfermeiro = new Enfermeiro("Maria", "123-ABC", "Feminino", "123456789");
        enfermeiro.setCoren("1234567");
        Assertions.assertEquals("1234567", enfermeiro.getCoren());
    }

    @Test
    public void NaoDeveAlterarNomeSeVazio(){
        Enfermeiro enfermeiro = new Enfermeiro("Maria", "123-ABC", "Feminino", "123456789");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            enfermeiro.setName("");
        });
    }

    @Test
    public void DeveAlterarNome(){
        Enfermeiro enfermeiro = new Enfermeiro("Maria", "123-ABC", "Feminino", "123456789");
        enfermeiro.setName("Maria");
        Assertions.assertEquals("Maria", enfermeiro.getNome());
    }
}
