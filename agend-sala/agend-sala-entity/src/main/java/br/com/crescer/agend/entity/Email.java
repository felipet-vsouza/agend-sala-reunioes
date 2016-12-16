/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author henrique.mentz
 */
@Entity
@Table( name = "EMAIL")
public class Email implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMAIL")
    @SequenceGenerator(name = "SEQ_EMAIL", sequenceName = "SEQ_EMAIL", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_EMAIL")
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Participante participante;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ENVIO", nullable = false)
    private Date dataEnvio;
    
    @Column(name = "HASH_EMAIL", nullable = false)
    private String hash;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
