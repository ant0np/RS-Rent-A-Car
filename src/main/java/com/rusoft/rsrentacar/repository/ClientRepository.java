package com.rusoft.rsrentacar.repository;

import com.rusoft.rsrentacar.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.name=:name AND c.year=:year")
    Client findByNameAndYear(@Param("name") String name, @Param("year") String year);

}
