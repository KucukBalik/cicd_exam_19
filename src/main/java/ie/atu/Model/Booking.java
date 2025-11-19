package ie.atu.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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


    @NotBlank
    private String driverName;

    @Pattern(regexp = "^[0-9]{2,3}-[A-Z]{1,2}-[0-9]{1,6}$")
    private String  RegNumber;

    @Email
    private String email;

    @Min(1)
    @Max(8)
    private int passengers;








}
