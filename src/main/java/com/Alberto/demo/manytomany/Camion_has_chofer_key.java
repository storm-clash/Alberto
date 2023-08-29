package com.Alberto.demo.manytomany;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Camion_has_chofer_key implements Serializable {

    @Column(name = "camion_id")
    private Integer camionId;

    @Column(name = "chofer_id")
    private  Integer choferId;

    public Integer getCamionId() {
        return camionId;
    }

    public void setCamionId(Integer camionId) {
        this.camionId = camionId;
    }

    public Integer getChoferId() {
        return choferId;
    }

    public void setChoferId(Integer choferId) {
        this.choferId = choferId;
    }

    public Camion_has_chofer_key(Integer camionId, Integer choferId) {
        this.camionId = camionId;
        this.choferId = choferId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camion_has_chofer_key that = (Camion_has_chofer_key) o;
        return Objects.equals(camionId, that.camionId) && Objects.equals(choferId, that.choferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camionId, choferId);
    }

    @Override
    public String toString() {
        return "Camion_has_chofer_key{" +
                "camionId=" + camionId +
                ", choferId=" + choferId +
                '}';
    }
}
