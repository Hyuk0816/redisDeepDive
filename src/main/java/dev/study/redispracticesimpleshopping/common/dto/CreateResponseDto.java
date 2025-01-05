package dev.study.redispracticesimpleshopping.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Schema(
	name = "Response",
	description = "Schema to hold successful response information"
)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateResponseDto {

	@Schema(
		description = "Status code in the response"
	)
	private String statusCode;

	@Schema(
		description = "Status message in the response"
	)
	private String statusMsg;

}
