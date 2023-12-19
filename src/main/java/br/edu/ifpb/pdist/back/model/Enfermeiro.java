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
public class Enfermeiro implements Serializable {

    // Para garantir que a assinatura de um número seja única , para o uso do @Id
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;

    private String coren;

    private String sexo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Past(message = "Data deve ser no passado")
    private Date dataNascimento;

    private String telefone;

    public void setName(String name) {
        if(name.isBlank())
            throw new IllegalArgumentException("Nome nao pode ser vazio!");
        this.nome = name;
    }

    public void setCoren(String coren) {
        if(coren.length() != 7)
            throw new IllegalArgumentException("COREN deve conter 7 digitos!");
        if(coren.isBlank())
            throw new IllegalArgumentException("COREN nao pode ser vazio!");    
        this.coren = coren;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Enfermeiro(String name, String coren, String sexo, String telefone) {
        this.nome = name;
        this.coren = coren;
        this.sexo = sexo;
    }
}
