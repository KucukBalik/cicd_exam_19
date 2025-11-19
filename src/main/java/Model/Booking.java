package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Booking {


    private String driverName;

    private String  RegNumber;

    private String email;

    private int passengers;








}
