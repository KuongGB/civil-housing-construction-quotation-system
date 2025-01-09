package com.example.system.model.requestcontract;

import com.example.system.model.building.BuildingDetail;
import com.example.system.model.combo.ComboBuilding;
import com.example.system.model.combo.CustomDetail;
import com.example.system.model.payment.Invoice;
import com.example.system.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request_contract")
public class RequestContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestContractId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date timeoutDate; //qua time này sẽ xóa request
    private Double totalPrice;
    private boolean status; //false: đang xử lý // true: đã xử lý
    private boolean payStatus;

    private String placeMeet;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateMeet; //qua time này sẽ xóa request


    @ManyToOne
    @JoinColumn(name = "combo_building_id")
    @JsonIgnore
    private ComboBuilding comboBuilding;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "building_detail_id", referencedColumnName = "buildingDetailId")
    @JsonIgnore
    private BuildingDetail buildingDetail;
    @OneToMany(mappedBy = "requestContract")
    @JsonIgnore
    List<CustomDetail> customDetails;
    @OneToOne(mappedBy = "requestContract")
    private Invoice invoice;
}
