package com.example.trabalho2;

public class Time {
    private int idTime;
    private String descricao;

    public Time(int idTime, String descricao) {
        this.idTime = idTime;
        this.descricao = descricao;
    }

    public int getIdTime() {
        return idTime;
    }

    @Override
    public String toString() {
        return "Time{" +
                "idTime=" + idTime +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
