package hibernate.hw_jdata_4.repository;

import hibernate.hw_jdata_4.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityCrudRepository extends JpaRepository<City, Integer> {
}
