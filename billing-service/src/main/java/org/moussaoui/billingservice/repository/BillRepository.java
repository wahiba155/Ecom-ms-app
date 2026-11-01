package org.moussaoui.billingservice.repository;

import org.moussaoui.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository  extends JpaRepository<Bill, Long> {
}
