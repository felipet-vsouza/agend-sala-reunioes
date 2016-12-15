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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author henrique.ostermann
 */
@Entity
@Table(uniqueConstraints= {
    @UniqueConstraint(columnNames = {"usuario", "agendamento"})
}, name = "PARTICIPANTE")
public class Participante implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARTICIPANTE")
    @SequenceGenerator(name = "SEQ_PARTICIPANTE", sequenceName = "SEQ_PARTICIPANTE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_PARTICIPANTE")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "USUARIO_PARTICIPANTE")
    private Usuario usuario;
    
    @Basic(optional = false)
    @Column(name = "AGENDAMENTO_PARTICIPANTE")
    private Agendamento agendamento;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
  
}
