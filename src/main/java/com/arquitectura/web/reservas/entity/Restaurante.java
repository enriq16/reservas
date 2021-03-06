/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Enrique
 */
@Entity
@Table(name = "restaurante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurante.findAll", query = "SELECT r FROM Restaurante r"),
    @NamedQuery(name = "Restaurante.findByIdRestaurante", query = "SELECT r FROM Restaurante r WHERE r.idRestaurante = :idRestaurante"),
    @NamedQuery(name = "Restaurante.findByNombre", query = "SELECT r FROM Restaurante r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Restaurante.findByDireccion", query = "SELECT r FROM Restaurante r WHERE r.direccion = :direccion")})
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_restaurante")
    private Integer idRestaurante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER)
    private List<Mesa> mesaList;
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER)
    private List<ReservaMesa> reservaMesaList;

    public Restaurante() {
    }

    public Restaurante(Integer idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Restaurante(Integer idRestaurante, String nombre, String direccion) {
        this.idRestaurante = idRestaurante;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Integer idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Mesa> getMesaList() {
        return mesaList;
    }

    public void setMesaList(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }

    @XmlTransient
    public List<ReservaMesa> getReservaMesaList() {
        return reservaMesaList;
    }

    public void setReservaMesasList(List<ReservaMesa> reservaMesasList) {
        this.reservaMesaList = reservaMesasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRestaurante != null ? idRestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurante)) {
            return false;
        }
        Restaurante other = (Restaurante) object;
        if ((this.idRestaurante == null && other.idRestaurante != null) || (this.idRestaurante != null && !this.idRestaurante.equals(other.idRestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "idRestaurante=" + idRestaurante + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
    
}
