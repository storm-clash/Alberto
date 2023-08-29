package com.Alberto.demo.camion;


import com.Alberto.demo.chofer.Chofer;
import com.Alberto.demo.manytomany.AsignarCaminionesAChofer;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "camion")
public class Camion {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true,length = 15)
    private String matricula;
    @Column(nullable = false)
    private double capacidad;
    private int ruedas;
    private double kilometraje;
    private String combustible;
    @Value("0")
    private boolean utilizado;
//----------------Crear Relacion ManyToMany en Spring----------------------
   /* @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "camion_has_chofer", joinColumns = @JoinColumn(name = "camion_id"),
    inverseJoinColumns = @JoinColumn(name = "chofer_id"))
     private Set<Chofer> chofers = new HashSet<>();
//---------------------------------------------------------------------------
    public void anadirChofer(Chofer chofer){
        this.chofers.add(chofer);
    }
    public void eliminarChofer(Chofer chofer){
        this.chofers.remove(chofer);
    }*/

//------------------------------------WWW.BAELDUNG.COM---------------------------------
     @OneToMany(mappedBy = "camion",cascade = CascadeType.ALL)
     Set<AsignarCaminionesAChofer> asignar;



    public Set<AsignarCaminionesAChofer> getAsignar() {
        return asignar;
    }

    public void setAsignar(Set<AsignarCaminionesAChofer> asignar) {
        this.asignar = asignar;
    }
//-------------------------------------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public int getRuedas() {
        return ruedas;
    }

    public void setRuedas(int ruedas) {
        this.ruedas = ruedas;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

   /* public Set<Chofer> getChofers() {
        return chofers;
    }

    public void setChofers(Set<Chofer> chofers) {
        this.chofers = chofers;
    }*/

    public Camion(){}

    public Camion(String matricula, double capacidad, int ruedas, double kilometraje, String combustible, boolean utilizado) {
        this.matricula = matricula;
        this.capacidad = capacidad;
        this.ruedas = ruedas;
        this.kilometraje = kilometraje;
        this.combustible = combustible;
        this.utilizado = utilizado;
    }



    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", capacidad=" + capacidad +
                ", ruedas=" + ruedas +
                ", kilometraje=" + kilometraje +
                ", combustible='" + combustible + '\'' +
                ", utilizado=" + utilizado +

                '}';
    }
}
