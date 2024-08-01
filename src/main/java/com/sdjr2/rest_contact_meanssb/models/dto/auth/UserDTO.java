package com.sdjr2.rest_contact_meanssb.models.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.RoleTypeEnum;
import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * {@link UserDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents a User in the Request / Response, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements BaseDTO, Comparable<UserDTO> {

	public static final String ATTR_ID = "id";
	public static final String ATTR_USERNAME = "username";
	public static final String ATTR_PWD = "pwd";
	public static final String ATTR_NICKNAME = "nickname";
	public static final String ATTR_EMAIL = "email";
	public static final String ATTR_IS_ACTIVE = "is_active";
	public static final String ATTR_LAST_ACCESS = "last_access";
	public static final String ATTR_ROLES = "roles";

	/* VARIABLES */
	@PositiveOrZero
	@Digits(integer = 8, fraction = 0)
	private Long id;

	@NotNull
	@Size(min=8,max=40)
	private String username;

	@NotNull
	@Size(min=10,max=60)
	private String pwd;

	@Size(min=6,max=30)
	private String nickname;

	@NotNull
	@Pattern(regexp = UConstants.REGEX_EMAIL)
	private String email;

	@JsonProperty(ATTR_IS_ACTIVE)
	private boolean isActive = true;

	@JsonProperty(ATTR_LAST_ACCESS)
	private LocalDateTime lastAccess;

	@Valid
	private List<RoleTypeEnum> roles;

	/* METHODS OF INSTANCE */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id.hashCode();
		result = prime * result + this.username.hashCode();
		return result;
	}

	@Override
	public boolean equals ( final Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( !( obj instanceof UserDTO other ) ) {
			return false;
		}
		return this.id.equals( other.getId() )
				&& this.username.equals( other.getUsername() );
	}

	@Override
	public String toString () {
		final StringBuilder res = new StringBuilder(
				"The DTO '" + this.getClass().getSimpleName() + "' contains the attributes: \n" );
		res.append( " - Id » " ).append( this.id ).append( ".\n" );
		res.append( " - Username » " ).append( this.username ).append( ".\n" );
		res.append( " - Pwd » " ).append( this.pwd ).append( ".\n" );
		res.append( " - Nickname » " ).append( this.nickname ).append( ".\n" );
		res.append( " - Email » " ).append( this.email ).append( ".\n" );
		res.append( " - IsActive » " ).append( this.isActive ).append( ".\n" );
		res.append( " - LastAccess » " ).append( this.lastAccess ).append( ".\n" );
		res.append( " - Roles » " ).append( this.roles ).append( ".\n" );

		return res.toString();
	}

	@Override
	public int compareTo( final UserDTO obj) {
		// Username
		return this.getUsername().compareTo( obj.getUsername() );
	}

	/* METHODS OF CLASSES */
	public static UserDTO valueOf(final UserDTO obj) {
		return new UserDTO( obj.getId(), obj.getUsername(), obj.getPwd(), obj.getNickname(), obj.getEmail(), obj.isActive(),
				obj.getLastAccess(), obj.getRoles() );
	}

	public static final Comparator<UserDTO> comparatorNickname =
			Comparator.comparing( ( UserDTO obj ) -> obj.getNickname().toUpperCase());
	public static final Comparator<UserDTO> comparatorIsActive = Comparator.comparing(UserDTO::isActive);
}