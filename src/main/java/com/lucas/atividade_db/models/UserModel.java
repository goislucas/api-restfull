package com.lucas.atividade_db.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "usuarios")
public class UserModel implements Serializable {

    @Id
    private Long cpf;
    private String nome;
    private Date data_nascimento;

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

}
