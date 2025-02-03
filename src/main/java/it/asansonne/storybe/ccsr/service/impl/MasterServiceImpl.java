package it.asansonne.storybe.ccsr.service.impl;

import static it.asansonne.storybe.constant.MessageConstant.PERSON_ACTIVE_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_INACTIVE_EMPTY;

import it.asansonne.storybe.ccsr.repository.jpa.MasterRepository;
import it.asansonne.storybe.ccsr.service.MasterService;
import it.asansonne.storybe.model.jpa.Master;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type Master service.
 */
@Service
@AllArgsConstructor
public final class MasterServiceImpl implements MasterService {
  private final MasterRepository masterRepository;

  @Override
  public Optional<Master> findMasterByEmail(String email) {
    return masterRepository.findByEmail(email);
  }

  @Override
  public Optional<Master> findMasterByUuid(UUID masterUuid) {
    return masterRepository.findMasterByUuid(masterUuid);
  }

  @Override
  public Page<Master> findAllMasters(Pageable pageable) {
    Page<Master> masters = masterRepository.findAll(pageable);
    if (masters.isEmpty()) {
      throw new EntityNotFoundException(PERSON_EMPTY);
    }
    return masters;
  }

  @Override
  public Page<Master> findActiveMasters(Pageable pageable) {
    Page<Master> masters = masterRepository.findAllByIsActiveTrue(pageable);
    if (masters.isEmpty()) {
      throw new EntityNotFoundException(PERSON_ACTIVE_EMPTY);
    }
    return masters;
  }

  @Override
  public Page<Master> findInactiveMasters(Pageable pageable) {
    Page<Master> masters = masterRepository.findAllByIsActiveFalse(pageable);
    if (masters.isEmpty()) {
      throw new EntityNotFoundException(PERSON_INACTIVE_EMPTY);
    }
    return masters;
  }

  @Override
  public Master updateMaster(Master master) {
    return masterRepository.save(master);
  }
}
