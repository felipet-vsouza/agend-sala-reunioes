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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "AGENDAMENTO",
        indexes = {@Index(name = "index_por_data_inicial",  columnList="DT_INICIO_AGENDAMENTO", unique = false),
                   @Index(name = "index_por_data_final", columnList="DT_FINAL_AGENDAMENTO", unique = true)})
public class Agendamento implements Serializable {

    public Agendamento() {
    }

    public Agendamento(Date dataInicio, Date dataFinal, Usuario criador, Sala sala, String descricao) {
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.criador = criador;
        this.sala = sala;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENDAMENTO")
    @SequenceGenerator(name = "SEQ_AGENDAMENTO", sequenceName = "SEQ_AGENDAMENTO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_AGENDAMENTO")
    private Long id;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(optional = true)
    @Column(name = "DT_INICIO_AGENDAMENTO")
    private Date dataInicio;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(optional = true)
    @Column(name = "DT_FINAL_AGENDAMENTO")
    private Date dataFinal;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario criador;

    @OneToMany(mappedBy = "agendamento")
    private List<Participante> participantes;

    @ManyToOne
    @JoinColumn(name = "ID_SALA")
    private Sala sala;

    @Basic(optional = true)
    @Column(name = "DS_AGENDAMENTO")
    private String descricao;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

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

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

}
