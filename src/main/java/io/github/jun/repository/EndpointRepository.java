package io.github.jun.repository;

import io.github.jun.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
}
