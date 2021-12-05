/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity(name = "Mesa")
@Table(name = "mesas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findById", query = "SELECT m FROM Mesa m WHERE m.id = :id"),
    @NamedQuery(name = "Mesa.findByNombreMesa", query = "SELECT m FROM Mesa m WHERE m.nombreMesa = :nombreMesa"),
    @NamedQuery(name = "Mesa.findByPosicionLat", query = "SELECT m FROM Mesa m WHERE m.posicionLat = :posicionLat"),
    @NamedQuery(name = "Mesa.findByPosicionLon", query = "SELECT m FROM Mesa m WHERE m.posicionLon = :posicionLon"),
    @NamedQuery(name = "Mesa.findByPlanta", query = "SELECT m FROM Mesa m WHERE m.planta = :planta"),
    @NamedQuery(name = "Mesa.findByCapacidad", query = "SELECT m FROM Mesa m WHERE m.capacidad = :capacidad")})
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_mesa")
    private String nombreMesa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "posicion_lat")
    private BigDecimal posicionLat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "posicion_lon")
    private BigDecimal posicionLon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "planta")
    private int planta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Restaurante restaurante;
    @OneToMany(mappedBy = "mesa", fetch = FetchType.EAGER)
    private List<ReservaMesa> reservaMesaList;

    public Mesa() {
    }

    public Mesa(Integer id) {
        this.id = id;
    }

    public Mesa(Integer id, String nombreMesa, BigDecimal posicionLat, BigDecimal posicionLon, int planta, int capacidad) {
        this.id = id;
        this.nombreMesa = nombreMesa;
        this.posicionLat = posicionLat;
        this.posicionLon = posicionLon;
        this.planta = planta;
        this.capacidad = capacidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public BigDecimal getPosicionLat() {
        return posicionLat;
    }

    public void setPosicionLat(BigDecimal posicionLat) {
        this.posicionLat = posicionLat;
    }

    public BigDecimal getPosicionLon() {
        return posicionLon;
    }

    public void setPosicionLon(BigDecimal posicionLon) {
        this.posicionLon = posicionLon;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @XmlTransient
    public List<ReservaMesa> getReservaMesaList() {
        return reservaMesaList;
    }

    public void setReservaMesasList(List<ReservaMesa> reservaMesaList) {
        this.reservaMesaList = reservaMesaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mesas{"+"id=" + id + ", nombreMesa=" + nombreMesa + ", posicionLat=" + posicionLat + ", posicionLon=" + posicionLon + ", planta=" + planta + ", capacidad=" + capacidad + ", restaurante=" + restaurante + '}';
    }

    
    
}
