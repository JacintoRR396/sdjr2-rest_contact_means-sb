package com.sdjr2.rest_contact_meanssb.models.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * {@link UserAuthReqDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents a User about login in the Request / Response, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/08/04
 * @since 24/08/04
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthReqDTO implements BaseDTO {

	public static final String ATTR_USERNAME = "username";
	public static final String ATTR_PWD = "password";

	@NotNull
	@Size(min = 8, max = 40)
	private String username;

	@NotNull
	@Size(min = 10, max = 60)
	private String password;
}
