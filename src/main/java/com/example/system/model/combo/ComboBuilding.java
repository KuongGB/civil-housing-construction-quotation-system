package com.example.system.model.combo;

import com.example.system.model.requestcontract.RequestContract;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "combo_building")
public class ComboBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comboBuildingId;
    @Column(nullable = false, columnDefinition = "varchar(255)", unique = true)
    private String comboBuildingName;
    //@Column(nullable = false)
    private Long unitPrice;
    @Column(nullable = false)
    private int type; // 0: xây nhà phần thô - 1: xây nhà hoàn thiện - 2: xây dựng trọn gói

    @OneToMany(mappedBy = "comboBuilding")
    @JsonIgnore
    Set<ComboDetail> comboDetails;
    @OneToMany(mappedBy = "comboBuilding")
    @JsonIgnore
    private Set<RequestContract> requestContracts;
    private boolean status;
}
