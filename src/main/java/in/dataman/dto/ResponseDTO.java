package in.dataman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String name;
    private Integer age;
    private String gender;
    private Double price;
    private byte[] data;


}
