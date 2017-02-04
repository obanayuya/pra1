package test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.domain.model.entity.AreaEntity;

import java.awt.geom.Area;
import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {
    List<AreaEntity> findAll();
}
