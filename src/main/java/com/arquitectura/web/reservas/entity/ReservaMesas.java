/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "reserva_mesas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaMesas.findAll", query = "SELECT r FROM ReservaMesas r"),
    @NamedQuery(name = "ReservaMesas.findByIdReserva", query = "SELECT r FROM ReservaMesas r WHERE r.idReserva = :idReserva"),
    @NamedQuery(name = "ReservaMesas.findByFecha", query = "SELECT r FROM ReservaMesas r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReservaMesas.findByRangoHora", query = "SELECT r FROM ReservaMesas r WHERE r.rangoHora = :rangoHora"),
    @NamedQuery(name = "ReservaMesas.findByCantidadSolicitada", query = "SELECT r FROM ReservaMesas r WHERE r.cantidadSolicitada = :cantidadSolicitada")})
public class ReservaMesas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva")
    private Integer idReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rango_hora")
    @Temporal(TemporalType.DATE)
    private Date rangoHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente idCliente;
    @JoinColumn(name = "id_mesa", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Mesas idMesa;
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Restaurante idRestaurante;

    public ReservaMesas() {
    }

    public ReservaMesas(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public ReservaMesas(Integer idReserva, Date fecha, Date rangoHora, int cantidadSolicitada) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.rangoHora = rangoHora;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getRangoHora() {
        return rangoHora;
    }

    public void setRangoHora(Date rangoHora) {
        this.rangoHora = rangoHora;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Mesas getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Mesas idMesa) {
        this.idMesa = idMesa;
    }

    public Restaurante getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Restaurante idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaMesas)) {
            return false;
        }
        ReservaMesas other = (ReservaMesas) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arquitectura.web.reservas.entity.ReservaMesas[ idReserva=" + idReserva + " ]";
    }
    
}
