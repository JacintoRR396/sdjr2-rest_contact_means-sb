package com.sdjr2.rest_contact_meanssb.models.mappers;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

/**
 * {@link AddressMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about Address DTO and Address Entity, implements to
 * {@link BaseMapper}.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link AddressDTO} <br> 02. Level Data -> the entity
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/07/15
 * @since 23/06/11
 */
@Mapper(componentModel = "spring")
public abstract class AddressMapper implements BaseMapper<AddressDTO, AddressEntity> {

	/**
	 * Map address entity to address request object, it does not apply {@link AuditableEntity}.
	 *
	 * @param entity address entity
	 * @return AddressDTO {@link AddressDTO}
	 */
	@Mapping(source = "entity.id", target = "id")
	@Mapping(source = "entity.street", target = "street")
	@Mapping(source = "entity.number", target = "number")
	@Mapping(source = "entity.letter", target = "letter")
	@Mapping(source = "entity.town", target = "town")
	@Mapping(source = "entity.city", target = "city")
	@Mapping(source = "entity.country", target = "country")
	@Mapping(source = "entity.postalCode", target = "postalCode")
	@Mapping(source = "entity.longitude", target = "longitude")
	@Mapping(source = "entity.latitude", target = "latitude")
	@Mapping(source = "entity.additionalInfo", target = "additionalInfo")
	@Override
	public abstract AddressDTO toDTO ( AddressEntity entity );

	/**
	 * Map address request object to address entity.
	 *
	 * @param dto      address request object
	 * @param entityDB address entity in db
	 * @param username username
	 * @return AddressEntity {@link AddressEntity}
	 */
	@Mapping(source = "dto.id", target = "id")
	@Mapping(source = "dto.street", target = "street")
	@Mapping(source = "dto.number", target = "number")
	@Mapping(source = "dto.letter", target = "letter")
	@Mapping(source = "dto.town", target = "town")
	@Mapping(source = "dto.city", target = "city")
	@Mapping(source = "dto.country", target = "country")
	@Mapping(source = "dto.postalCode", target = "postalCode")
	@Mapping(source = "dto.longitude", target = "longitude")
	@Mapping(source = "dto.latitude", target = "latitude")
	@Mapping(source = "dto.additionalInfo", target = "additionalInfo")
	@Mapping(target = "auditableEntity", ignore = true)
	public abstract AddressEntity toEntity ( AddressDTO dto, AddressEntity entityDB, String username );

	/**
	 * Map address request object to address entity with additional logic
	 *
	 * @param dto           address request object
	 * @param entityDB      address entity in db
	 * @param username      username
	 * @param addressEntity address entity about req
	 * @return AddressEntity {@link AddressEntity}
	 */
	@AfterMapping
	protected AddressEntity afterMappingToEntity ( AddressDTO dto, AddressEntity entityDB, String username,
																								 @MappingTarget AddressEntity addressEntity ) {
		AuditableEntity auditableEntity;

		if ( dto.getId().equals( 0L ) ) {
			auditableEntity = AuditableEntity.builder()
					.createdAt( LocalDateTime.now() )
					.createdBy( "admin" )
					.updatedAt( LocalDateTime.now() )
					.updatedBy( "admin" )
					.build();
		} else {
			auditableEntity = entityDB.getAuditableEntity();
			auditableEntity.setUpdatedAt( LocalDateTime.now() );
			auditableEntity.setUpdatedBy( username );
		}
		addressEntity.setAuditableEntity( auditableEntity );

		return addressEntity;
	}
}
