package br.edu.ifpr.foz.projeto_spring.models;

import java.time.LocalDate;

public class Task {
    private Long id;
    private String titulo;
    private String descricao;
    private TaskStatus status;
    private LocalDate date;

    public Task() {
    }

    public Task(Long id, String titulo, String descricao, LocalDate date) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.date = date;
        this.status = TaskStatus.EM_ANDAMENTO; 
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
