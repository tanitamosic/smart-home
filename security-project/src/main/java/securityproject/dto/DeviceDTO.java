package securityproject.dto;

import securityproject.model.enums.DeviceType;

import javax.persistence.Column;

public class DeviceDTO {

    public Long houseId;
    public DeviceType type;
    public Integer readTime;
    public String filterRegex;
    public String name;
}
