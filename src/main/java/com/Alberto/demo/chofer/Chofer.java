package com.Alberto.demo.chofer;

import com.Alberto.demo.camion.Camion;
import com.Alberto.demo.manytomany.AsignarCaminionesAChofer;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "chofer")
public class Chofer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 45)
    private String nombre;
    @Column(nullable = false,length = 45)
    private String apellido;
    private int edad;
    private double salario;
    @Column(nullable = false,length = 45)
    private String sexo;

  //  @ManyToMany(mappedBy = "camion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  //  private List<Camion>camiones;
//-----------------------------------------WWW.BARLDUNG.COM---------------------------------------------
    @OneToMany(mappedBy = "chofer",cascade = {CascadeType.ALL})
    Set<AsignarCaminionesAChofer> asignar;

    public Set<AsignarCaminionesAChofer> getAsignar() {
        return asignar;
    }

    public void setAsignar(Set<AsignarCaminionesAChofer> asignar) {
        this.asignar = asignar;
    }

    //-----------------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Chofer(){}

    public Chofer(Integer id) {
        this.id = id;
    }

   /* public List<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }*/

    public Chofer(String nombre, String apellido, int edad, double salario, String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salario = salario;
        this.sexo = sexo;
       // this.camiones = camiones;
    }



    @Override
    public String toString() {
        return "Chofer{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                ", sexo='" + sexo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chofer chofer = (Chofer) o;
        return Objects.equals(id, chofer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
