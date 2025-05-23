package in.sagar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

	private Integer counsellorId;
	private String name;
	private String email;
	private String password;
	private Long phno;
}
