package br.edu.ifpb.pdist.back.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recepcionista implements Serializable{

    // Para garantir que a assinatura de um número seja única , para o uso do @Id
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;

    private String matricula;

    private String sexo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Past(message = "Data deve ser no passado")
    private Date dataNascimento;

    private String telefone;

    public void setName(String name) {
        this.nome = name;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setData_de_nascimento(Date data_de_nascimento) {
        this.dataNascimento = data_de_nascimento;
    }

    public Recepcionista(String name, String telefone, String sexo, String matricula) {
        this.nome = name;
        this.telefone = telefone;
        this.sexo = sexo;
        this.matricula = matricula;
    }
}
