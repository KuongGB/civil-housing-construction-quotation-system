package com.example.system.model.combo;

import com.example.system.model.requestcontract.RequestContract;
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
@Table(name = "custom_detail")
public class CustomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customId;
    @Column(nullable = true)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "old_mate_id", unique = true)
    @JsonIgnore
    private Material oldMate;
    @ManyToOne
    @JoinColumn(name = "new_mate_id")
    @JsonIgnore
    private Material newMate;
    @ManyToOne
    @JoinColumn(name = "request_contract_id")
    @JsonIgnore
    private RequestContract requestContract;
}