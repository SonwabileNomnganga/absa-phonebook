package za.co.absa.africanacity.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.absa.africanacity.phonebook.domain.Phonebook;

public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {
}
