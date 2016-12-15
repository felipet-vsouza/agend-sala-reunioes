/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author henrique.ostermann
 */
@Entity
@Table (name = "SALA")
public class Sala implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALA")
    @SequenceGenerator(name = "SEQ_SALA", sequenceName = "SEQ_SALA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_SALA")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "NOME_SALA")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "CAPACIDADE_SALA")
    private Integer capacidade;
   
    @ManyToMany(targetEntity = Equipamento.class, cascade = CascadeType.PERSIST)
    private List<Equipamento> equipamento;

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

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public List<Equipamento> getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(List<Equipamento> equipamento) {
        this.equipamento = equipamento;
    }
    
}
