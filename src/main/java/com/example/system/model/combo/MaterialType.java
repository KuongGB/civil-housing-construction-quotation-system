package com.example.system.model.combo;

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
@Table(name = "material_type")
public class MaterialType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialTypeId;
    @Column(nullable = false, columnDefinition = "varchar(50)", unique = true)
    private String typeName;
    private boolean status;

    @OneToMany(mappedBy = "materialType")
    Set<Material> materials;

}
