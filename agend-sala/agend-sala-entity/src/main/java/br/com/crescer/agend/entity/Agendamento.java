/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author henrique.ostermann
 */
@Entity
@Table(name = "RESERVA")
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESERVA")
    @SequenceGenerator(name = "SEQ_RESERVA", sequenceName = "SEQ_RESERVA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_RESERVA")
    private Long id;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(optional = true)
    @Column(name = "DT_INICIO_RESERVA")
    private Date dataInicio;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(optional = true)
    @Column(name = "DT_FINAL_RESERVA")
    private Date dataFinal;

    @ManyToMany(targetEntity = Participante.class, cascade = CascadeType.PERSIST)
    private List<Participante> caomigos;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Participante> getCaomigos() {
        return caomigos;
    }

    public void setCaomigos(List<Participante> caomigos) {
        this.caomigos = caomigos;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

}
