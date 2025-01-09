package com.example.system.model.building;

import com.example.system.model.requestcontract.RequestContract;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String buildingName;
    @Column(nullable = false)
    private Double percentPrice;
    private boolean status;

    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<BuildingDetail> buildingDetails;
    @ManyToOne
    @JoinColumn(name = "building_type_id")
    @JsonIgnore
    private BuildingType buildingType;
}
