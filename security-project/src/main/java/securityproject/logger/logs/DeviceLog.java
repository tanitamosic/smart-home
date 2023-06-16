package securityproject.logger.logs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceLog {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private String deviceId;
    private String deviceType;
    private String ipAddress;
    private String message;
    private LogType logType;

    public DeviceLog(HttpServletRequest request, LogType type, String message, String timestamp, String deviceType) {
        String format = "yyyy-MM-dd'T'HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        this.setId(uuidString);
        this.setIpAddress(request.getRemoteAddr());
        this.setTimestamp(LocalDateTime.parse(timestamp, formatter));
        this.setLogType(type);
        this.setMessage(message);
        this.setDeviceType(deviceType);

    }
}