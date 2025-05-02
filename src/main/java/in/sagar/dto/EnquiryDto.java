package in.sagar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryDto {

	private Integer enquiryId;
	private String name;
	private Long phno;
	private String classMode;
	private String course;
	private String status;
}
