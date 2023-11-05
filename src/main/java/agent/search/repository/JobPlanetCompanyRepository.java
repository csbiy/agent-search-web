package agent.search.repository;

import agent.search.entity.JobPlanetCompany;
import agent.search.entity.MilitaryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPlanetCompanyRepository extends JpaRepository<JobPlanetCompany,Long> {

    JobPlanetCompany findByCompany(MilitaryCompany company);
}
