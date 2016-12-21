/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author henrique.ostermann
 */
//uniqueConstraints= {
//    @UniqueConstraint(columnNames = {"USUARIO_PARTICIPANTE", "AGENDAMENTO_PARTICIPANTE"})
//},
@Entity
@Table( name = "PARTICIPANTE")
public class Participante implements Serializable{

    public Participante() {
    }

    public Participante(Usuario usuario, Agendamento agendamento, Status status) {
        this.usuario = usuario;
        this.agendamento = agendamento;
        this.status = status;
    }
       
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARTICIPANTE")
    @SequenceGenerator(name = "SEQ_PARTICIPANTE", sequenceName = "SEQ_PARTICIPANTE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_PARTICIPANTE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    
    @ManyToOne (targetEntity = Agendamento.class)
    @JoinColumn(name = "ID_AGENDAMENTO")
    private Agendamento agendamento;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "participante", cascade = CascadeType.ALL)
    private Email email;

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
    
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
  
}
