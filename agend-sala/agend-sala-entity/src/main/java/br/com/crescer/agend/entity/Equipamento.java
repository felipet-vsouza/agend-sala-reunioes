/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author henrique.ostermann
 */
@Entity
@Table(name = "EQUIPAMENTO")
public class Equipamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPAMENTO")
    @SequenceGenerator(name = "SEQ_EQUIPAMENTO", sequenceName = "SEQ_EQUIPAMENTO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_EQUIPAMENTO")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NOME_EQUIPAMENTO")
    private String nome;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE_EQUIPAMENTO")
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
