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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity(name = "ReservaMesa")
@Table(name = "reserva_mesas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaMesa.findAll", query = "SELECT r FROM ReservaMesa r"),
    @NamedQuery(name = "ReservaMesa.findByIdReserva", query = "SELECT r FROM ReservaMesa r WHERE r.idReserva = :idReserva"),
    @NamedQuery(name = "ReservaMesa.findByFecha", query = "SELECT r FROM ReservaMesa r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ReservaMesa.findByRangoHora", query = "SELECT r FROM ReservaMesa r WHERE r.rangoHora = :rangoHora"),
    @NamedQuery(name = "ReservaMesa.findByCantidadSolicitada", query = "SELECT r FROM ReservaMesa r WHERE r.cantidadSolicitada = :cantidadSolicitada")})
public class ReservaMesa implements Serializable {

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
    @Size(min = 1, max = 10)
    @Column(name = "rango_hora")    
    private String rangoHora;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente cliente;
    @JoinColumn(name = "id_mesa", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Mesa mesa;
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Restaurante restaurante;

    public ReservaMesa() {
    }

    public ReservaMesa(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public ReservaMesa(Integer idReserva, Date fecha, String rangoHora, int cantidadSolicitada) {
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

    public String getRangoHora() {
        return rangoHora;
    }

    public void setRangoHora(String rangoHora) {
        this.rangoHora = rangoHora;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
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
        if (!(object instanceof ReservaMesa)) {
            return false;
        }
        ReservaMesa other = (ReservaMesa) object;
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
