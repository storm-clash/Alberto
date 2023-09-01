package com.Alberto.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Truck_Driver_Key implements Serializable {

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "truck_id")
    private Long truckId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck_Driver_Key that = (Truck_Driver_Key) o;
        return Objects.equals(driverId, that.driverId) && Objects.equals(truckId, that.truckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, truckId);
    }
}
