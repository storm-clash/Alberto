package com.Alberto.demo.manytomany;

import com.Alberto.demo.camion.Camion;
import com.Alberto.demo.chofer.Chofer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "camion_has_chofer")
public class AsignarCaminionesAChofer {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
   //@EmbeddedId
    // Camion_has_chofer_key id;

    @ManyToOne
   // @MapsId("camionId")
    @JoinColumn(name = "camion_id")
    private Camion camion;

    @ManyToOne
   // @MapsId("choferId")
    @JoinColumn(name = "chofer_id")
    private Chofer chofer;

    private LocalDate fecha_inicio;
    private LocalDate fecha_termino;

  /*  public Camion_has_chofer_key getId() {
        return id;
    }

    public void setId(Camion_has_chofer_key id) {
        this.id = id;
    }*/

   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(LocalDate fecha_termino) {
        this.fecha_termino = fecha_termino;
    }


    public AsignarCaminionesAChofer(Camion camion, Chofer chofer, LocalDate fecha_inicio) {
        this.camion = camion;
        this.chofer = chofer;
        this.fecha_inicio = fecha_inicio;
    }

    public AsignarCaminionesAChofer(Camion camion, Chofer chofer, LocalDate fecha_inicio, LocalDate fecha_termino) {
        this.camion = camion;
        this.chofer = chofer;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
    }
    public AsignarCaminionesAChofer(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignarCaminionesAChofer that = (AsignarCaminionesAChofer) o;
        return Objects.equals(id, that.id) && Objects.equals(camion, that.camion) && Objects.equals(chofer, that.chofer) && Objects.equals(fecha_inicio, that.fecha_inicio) && Objects.equals(fecha_termino, that.fecha_termino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, camion, chofer, fecha_inicio, fecha_termino);
    }

    @Override
    public String toString() {
        return "AsignarCaminionesAChofer{" +
                "id=" + id +
                ", camion=" + camion +
                ", chofer=" + chofer +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_termino=" + fecha_termino +
                '}';
    }
}
