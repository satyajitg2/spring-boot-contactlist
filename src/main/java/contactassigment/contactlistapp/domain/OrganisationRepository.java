package contactassigment.contactlistapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Integer>
{
	  List<Organisation> findByNameContainingIgnoreCase(String name);
}
