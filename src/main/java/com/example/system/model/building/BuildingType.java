package com.example.system.model.building;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building_type")
public class BuildingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingTypeId;
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String buildingTypeName;
    private boolean status;

    @OneToMany(mappedBy = "buildingType")
    private List<Building> buildings;
}
