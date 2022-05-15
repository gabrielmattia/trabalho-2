package com.example.trabalho2;

public class Jogador {
    private int id;
    private int idTime;
    private String nome;
    private String cpf;
    private int anoNascimento;

    public Jogador(int id, int idTime, String nome, String cpf, int anoNascimento) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.cpf = cpf;
        this.anoNascimento = anoNascimento;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", idTime=" + idTime +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", anoNascimento=" + anoNascimento +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
}
