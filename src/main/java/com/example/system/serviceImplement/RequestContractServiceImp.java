package com.example.system.serviceImplement;
import com.example.system.dto.buildingdto.pricedto.PriceDetailDto;
import com.example.system.dto.combodto.custom.CustomMateTypeDto;
import com.example.system.mail.EmailSender;
import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingDetail;
import com.example.system.model.combo.ComboBuilding;
import com.example.system.dto.requestcontractdto.CreateDto;
import com.example.system.dto.requestcontractdto.RequestDto;
import com.example.system.model.combo.Material;
import com.example.system.model.requestcontract.RequestContract;
import com.example.system.model.user.User;
import com.example.system.repository.building.BuildingDetailRepository;
import com.example.system.repository.combo.ComboBuildingRepository;
import com.example.system.repository.combo.CustomDetailRepository;
import com.example.system.repository.requestcontract.RequestContractRepository;
import com.example.system.repository.user.UserRepository;
import com.example.system.service.combobuilding.CustomDetailService;
import com.example.system.service.requestContract.RequestContractService;
import com.example.system.validator.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestContractServiceImp implements RequestContractService {
    @Autowired
    RequestContractRepository requestContractRepository;
    @Autowired
    BuildingDetailRepository buildingDetailRepository;
    @Autowired
    CustomDetailRepository customDetailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ComboBuildingRepository comboBuildingRepository;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    @Autowired
    CustomDetailService customDetailService;

    @Override
    public List<RequestContract> findAll() {
        return requestContractRepository.findAll();
    }

    @Override
    public List<RequestDto> findAllDto() {
        List<RequestContract> requestContractList = requestContractRepository.findAll();
        List<RequestDto> dtos = new ArrayList<>();
        for (RequestContract rc: requestContractList) {
            dtos.add(getRCDto(rc));
        }
        return dtos;
    }
    @Override
    public RequestDto getById(Long rcId) {
        RequestContract rc = requestContractRepository.findById(rcId).orElseThrow();
        return getRCDto(rc);
    }
    @Override
    public List<RequestDto> findDtosByEmail(String email) {
        try{
            User u = userRepository.findByEmail(email).orElseThrow();
            List<RequestContract> requestContractList = requestContractRepository.findByUser(u);
            List<RequestDto> dtos = new ArrayList<>();
            for (RequestContract rq: requestContractList){
                RequestDto dto = getRCDto(rq);
                dtos.add(dto);
            }
            return dtos;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public RequestDto createRequestContract(CreateDto dto) {
        try{
            RequestContract contract = requestContractRepository.findById(dto.getRequestContractId()).orElseThrow();
            contract.setUser(userRepository.findByEmail(dto.getEmail()).orElseThrow());
            contract.setRequestDate(new Date());
            contract.setComboBuilding(comboBuildingRepository.findById(dto.getComboId()).orElseThrow());
            contract.setBuildingDetail(buildingDetailRepository.findById(dto.getBuildingDetailId()).orElseThrow());
            contract.setCustomDetails(customDetailRepository.findAllByRequestContract(contract));
            contract.setPayStatus(false);
            contract.setStatus(false);

            //set timeout
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 7); // Thêm 7 ngày vào ngày hiện tại
            Date dateAfter7Days = calendar.getTime();
            contract.setTimeoutDate(dateAfter7Days);
            RequestContract rc = requestContractRepository.save(contract);
            //create custom combo
            customDetailService.makeCustomCombo(dto.getMateIds(), rc.getRequestContractId());

            //price
            Double total = getComboPrice(contract)*contract.getBuildingDetail().getArea();
            contract.setTotalPrice(total);
            return getRCDto(requestContractRepository.save(contract));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public RequestDto confirmRequestContract(Long rcId, Date dateMeet, String placeMeet) {
        RequestContract updaRequestContract = requestContractRepository.findById(rcId)
                .orElseThrow(
                        () -> new IllegalStateException("Request contract with id " + rcId + " does not exists"));
        updaRequestContract.setStatus(true);
        updaRequestContract.setDateMeet(dateMeet);
        updaRequestContract.setPlaceMeet(placeMeet);
        requestContractRepository.save(updaRequestContract);
        String subject = "Confirm your construction quotes";
        emailSender.send(
                updaRequestContract.getUser().getEmail(),
                buildEmail(updaRequestContract, dateMeet, placeMeet),
                subject
        );
        return getRCDto(updaRequestContract);
    }


    private RequestDto getRCDto(RequestContract contract){
        RequestDto dto = new RequestDto();
        dto.setRequestContractId(contract.getRequestContractId());
        dto.setRequestDate(contract.getRequestDate());
        dto.setTotalPrice(contract.getTotalPrice());
        dto.setDateMeet(contract.getDateMeet());
        dto.setPlaceMeet(contract.getPlaceMeet());
        dto.setStatus(contract.isStatus());

        dto.setComboId(contract.getComboBuilding().getComboBuildingId());
        dto.setComboName(contract.getComboBuilding().getComboBuildingName());

        dto.setUserId(contract.getUser().getUserId());
        dto.setUserName(contract.getUser().getName());
        dto.setEmail(contract.getUser().getEmail());
        dto.setPhone(contract.getUser().getPhone());

        dto.setBuildingDetail(contract.getBuildingDetail());
        return dto;
    }
    private Double getComboPrice(RequestContract contract){
        double comboPrice = 0.0;
        List<Material> materials = customDetailService.getMateByRequestContract(contract);
        for (Material m: materials) {
            comboPrice = comboPrice + (double)m.getUnitPrice();
        }
        comboPrice = comboPrice * 0.95;
        comboPrice = comboPrice * contract.getBuildingDetail().getBuilding().getPercentPrice();
        int count = getCount(contract);
        comboPrice = comboPrice*((((double)count*5)+100)/100);
        return comboPrice;
    }
    private int getCount(RequestContract contract) {
        int count = 0;
        if (contract.getBuildingDetail().isHasTunnel()) count++;
        if(contract.getBuildingDetail().getNumOBathroom() > 1) count += contract.getBuildingDetail().getNumOBathroom() - 1;
        if(contract.getBuildingDetail().getNumOBedroom() > 1) count += contract.getBuildingDetail().getNumOBedroom() - 1;
        if(contract.getBuildingDetail().getNumOKitchen() > 1) count += contract.getBuildingDetail().getNumOKitchen() - 1;
        if(contract.getBuildingDetail().getNumOFloor() > 1) count = count + count * contract.getBuildingDetail().getNumOFloor() - 1;
        if(contract.getBuildingDetail().isHasTunnel()) count++;
        return count;
    }
    private String buildEmail(RequestContract requestContract, Date dateMeet, String placeMeet) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(dateMeet);
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Chào bạn,</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Chúng tôi rất vui thông báo rằng yêu cầu của bạn về dự án thi công xây dựng đã được xác nhận.</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\"><strong>Thông tin chi tiết về cuộc họp như sau:</strong></p>" +
                "<ul style=\"Margin:0;padding:0 0 20px 20px;font-size:16px;line-height:22px;color:#0b0c0c\">" +
                "<li>Yêu cầu số <strong>" + requestContract.getRequestContractId() + "</strong> đã được xác nhận</li>" +
                "<li>Địa điểm gặp mặt: <strong>" + placeMeet + "</strong></li>" +
                "<li>Thời gian: <strong>" + formattedDate + "</strong></li>" +
                "</ul>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Rất mong bạn có mặt đúng hẹn. Xin cảm ơn bạn đã hợp tác.</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\"><strong>Lưu ý:</strong> Nếu bạn không đến hoặc trễ hơn 30 phút, yêu cầu hẹn sẽ bị hủy và bạn sẽ mất 200k tiền đã đặt cọc.</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Trân trọng,</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">[CILVIL HOUSING]</p>" +
                "</div>";
    }
    @Override
    public PriceDetailDto sendQuote(PriceDetailDto priceDetailDto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        String subject = "DETAILED EXAM QUOTE";
        emailSender.send(
                user.getEmail(),
                sendQuoteToMail(priceDetailDto),
                subject
        );
        return priceDetailDto;
    }
    private String sendQuoteToMail(PriceDetailDto priceDetailDto) {
        StringBuilder emailContent = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        emailContent.append("<h2>Báo giá chi tiết</h2>\n");
        emailContent.append("<p>Đây là chi tiết báo giá của công trình dựa theo thông số bạn đã lựa chọn:</p>\n");
        emailContent.append("<ul>\n");
        emailContent.append("  <li><strong>Loại nhà: </strong>").append(priceDetailDto.getBuildingName()).append("</li>\n");
        emailContent.append("  <li><strong>Diện tích: </strong>").append(priceDetailDto.getArea()).append("</li>\n");
        emailContent.append("  <li><strong>Số phòng ngủ: </strong>").append(priceDetailDto.getNumOBedroom()).append("</li>\n");
        emailContent.append("  <li><strong>Số phòng tắm: </strong>").append(priceDetailDto.getNumOBathroom()).append("</li>\n");
        emailContent.append("  <li><strong>Số phòng bếp: </strong>").append(priceDetailDto.getNumOKitchen()).append("</li>\n");
        emailContent.append("  <li><strong>Số tầng: </strong>").append(priceDetailDto.getNumOFloor()).append("</li>\n");
        emailContent.append("  <li><strong>Hầm: </strong>").append(priceDetailDto.isHasTunnel() ? "Có" : "Không").append("</li>\n");
        emailContent.append("</ul>\n");
        emailContent.append("<p><strong>Gói xây dựng có giá: </strong>").append(decimalFormat.format(priceDetailDto.getComboPrice())).append(" VND</p>\n");
        emailContent.append("<p><strong>Chi tiết nguyên liệu: </strong></p>\n");
        emailContent.append("<ul>\n");

        // Thêm chi tiết nguyên liệu từ danh sách nguyên liệu
        for (CustomMateTypeDto c : priceDetailDto.getMatesInCustom()) {
            emailContent.append("  <li>Loại nguyên liệu: ").append(c.getMateTypeName()).append(", Nguyên liệu: ").append(c.getMate().getMateName()).append(", Giá: ").append(c.getMate().getMatePrice()).append("</li>\n");
        }
        emailContent.append("</ul>\n");
        // Khởi tạo một định dạng cho số tiền
        // Giả sử priceDetailDto.getTotalPrice() trả về số tiền tổng
        double totalPrice = priceDetailDto.getTotalPrice();
        // Định dạng giá tiền thành chuỗi
        String formattedPrice = decimalFormat.format(totalPrice);
        emailContent.append("<p><strong>Tổng tiền là:</strong>").append(formattedPrice).append("VND</p>\n");
        emailContent.append("<p><strong>Lưu ý:</strong></p>\n");
        emailContent.append("<p>Mặc định: Một căn hộ ban đầu chỉ có 1 phòng ngủ, 1 phòng tắm, 1 phòng bếp, 1 lầu và không có hầm. Khi bạn tăng số lượng các thành phần trên thì giá sẽ được cộng thêm 3-5% tùy loại.<br>Giá của loại nhà sẽ được tăng theo kiểu và loại nhà bạn chọn thì sẽ tăng theo hệ số: ").append(priceDetailDto.getPercentPrice()).append("</p>\n");
        emailContent.append("<p>Cám ơn đã tin tưởng và sử dụng dịch vụ báo giá thi công nhà ở của hệ thống CHCQS!</p>");
        return emailContent.toString();
}
}
