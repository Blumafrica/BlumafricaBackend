package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
