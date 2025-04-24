package in.sagar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {

	private Integer totalEnquiry;
	private Integer lostEnquiry;
	private Integer openEnquiry;
	private Integer enrolledEnquiry;
}
