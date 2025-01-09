package com.example.system.model.combo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "combo_detail")
public class ComboDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comboDetailId;
    @Column(nullable = true)
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "material_id")
    @JsonIgnore
    private Material material;
    @ManyToOne()
    @JoinColumn(name = "combo_building_id")
    @JsonIgnore
    private ComboBuilding comboBuilding;
}
