package com.aldis.clientRest.repository;

import com.aldis.clientRest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByNameAndLastNameAndAge(final String name, final String lastName, final int age);

    @Query(value = """
        SELECT 
            AVG(age) AS averageAge,
            STDDEV(age) AS standardDeviation
                                             
        FROM client
        """, nativeQuery = true)
    Map<String, Object> getMetrics();

}