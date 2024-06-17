package com.sdjr2.rest_contact_meanssb.services.mock;

import com.sdjr2.rest_contact_meanssb.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.repositories.AddressRepository;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link AddressServiceMockImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service mock that implements to {@link AddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/06/17
 * @since 23/06/10
 */
@Service
@RequiredArgsConstructor
public class AddressServiceMockImpl implements AddressService {

  /**
   * Address dto request mapper object
   */
  private final AddressMapper addressMapper;

  /**
   * Address repository object
   */
  private final AddressRepository<AddressEntity, Integer> addressRepo;

  @Override
  public List<AddressEntity> getAddresses() {
    return this.addressRepo.findAll();
  }

  @Override
  public Page<AddressEntity> getAddressesWithPagination(Integer pageNum, Integer pageSize) {
    return Page.empty();
  }

  @Override
  public List<AddressEntity> getAddressesWithOrder(String attribute, boolean isAsc) {
    return new ArrayList<>();
  }

  @Override
  public Page<AddressEntity> getAddressesWithPaginationAndOrder(Integer pageNum, Integer pageSize,
                                                                String attribute, boolean isAsc) {
    return Page.empty();
  }

  /**
   * Gets a list of towns about addresses
   *
   * @return a list of {@link String} object.
   */
  public List<String> getTowns() {
    return new ArrayList<>();
  }

  @Override
  public AddressEntity getAddressById(Integer id) {
    return this.checkExistsAddress(id);
  }

  @Override
  public AddressEntity createAddress(AddressDTO addressDTO) {
    return null;
  }

  @Override
  public AddressEntity updateAddress(Integer id, AddressDTO addressDTO) {
    return null;
  }

  @Override
  public void deleteAddress(Integer id) {

  }

  private AddressEntity checkExistsAddress(final Integer id) {
    return this.addressRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
      String.format("Address with ID '%d' not found", id)));
  }
}