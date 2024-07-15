package com.sdjr2.rest_contact_meanssb.controllers;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link AddressController} class.
 * <p>
 * <strong>Controller</strong> - Represents a handler of request about Addresses that implements to
 * {@link BaseController}.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link AddressDTO} <br> 02. Level Logic -> the service
 * {@link AddressService} <br> 03. Level Data -> the entity {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/07/15
 * @see Validator Validator validates all constraints.
 * @see HttpServletRequest HttpServletRequest provides request information for HTTP servlets.
 * @since 23/06/10
 */
@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController implements BaseController<AddressDTO> {

	/**
	 * Address service object
	 */
	private final AddressService addressService;

	/*********** GET ALL ***********/
	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAll () {
		return new ResponseEntity<>( this.addressService.getAddresses(), HttpStatus.OK );
	}

	@GetMapping(value = "/pagination")
	public ResponseEntity<Page<AddressDTO>> getAllWithPagination (
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value = "size", defaultValue = "15", required = false) Integer pageSize ) {
		return null;
		//return new ResponseEntity<>( this.addressService.getAddressesWithPagination( pageNum, pageSize ), HttpStatus.OK );
	}

	@GetMapping(value = "/order")
	public ResponseEntity<List<AddressEntity>> getAddressesWithOrder (
			@RequestParam(value = "attribute", defaultValue = "postalCode", required = false) String attribute,
			@RequestParam(value = "asc", defaultValue = "true", required = false) boolean isAsc ) {
		return new ResponseEntity<>( this.addressService.getAddressesWithOrder( attribute, isAsc ), HttpStatus.OK );
	}

	@GetMapping(value = "/paginationAndOrder")
	public ResponseEntity<Page<AddressEntity>> getAddressesWithPaginationAndOrder (
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value = "size", defaultValue = "15", required = false) Integer pageSize,
			@RequestParam(value = "attribute", defaultValue = "postalCode", required = false) String attribute,
			@RequestParam(value = "asc", defaultValue = "true", required = false) boolean isAsc ) {
		return new ResponseEntity<>(
				this.addressService.getAddressesWithPaginationAndOrder( pageNum, pageSize, attribute, isAsc ), HttpStatus.OK );
	}

	/*********** GET ***********/
	@GetMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> getOneById ( @PathVariable("addressId") Integer id ) {
		return new ResponseEntity<>( this.addressService.getAddressById( id ), HttpStatus.OK );
	}

	/*********** POST ***********/
	@PostMapping
	public ResponseEntity<AddressDTO> create ( @Valid @RequestBody AddressDTO addressDTO, BindingResult resValidation ) {
		this.checkValidation( resValidation );

		return new ResponseEntity<>( this.addressService.createAddress( addressDTO ), HttpStatus.CREATED );
	}

	/*********** PUT ***********/
	@PutMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> update ( @Valid @RequestBody AddressDTO addressDTO,
																						 BindingResult resValidation,
																						 @PathVariable("addressId") Integer id ) {
		return null;
		//return new ResponseEntity<>( this.addressService.updateAddress( addressDTO, id ), HttpStatus.OK );
	}

	/*********** DELETE ***********/
	@DeleteMapping(value = "/{addressId}")
	public ResponseEntity<Void> delete ( @PathVariable("addressId") Integer id ) {
		this.addressService.deleteAddress( id );
		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}
