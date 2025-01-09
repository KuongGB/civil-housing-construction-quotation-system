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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building_detail")
public class BuildingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingDetailId;
    @Column(nullable = false)
    private Double area;
    @Column(nullable = false)
    private Integer numOKitchen;
    @Column(nullable = false)
    private Integer numOBathroom;
    @Column(nullable = false)
    private Integer numOBedroom;
    @Column(nullable = false)
    private Integer numOFloor;
    @Column(nullable = false)
    private boolean hasTunnel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate; // thêm status sang 1
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date checkDate; // khi status == 1
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date finishDate; // thêm thì status sang 2
    private int status; //khi request contract được tạo -1: mẫu
                        //khi request contract qua timeout  0: hủy
                        // 1: đang thi công /  2 : đã xong

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @OneToOne(mappedBy = "buildingDetail")
    @JsonIgnore
    private RequestContract requestContract;
}
