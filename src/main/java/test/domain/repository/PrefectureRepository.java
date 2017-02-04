package test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.domain.model.entity.PrefectureEntity;

import java.util.List;

@Repository
public interface PrefectureRepository extends JpaRepository<PrefectureEntity, Integer> {
    // List<PrefectureEntity> findByName(String ken);

    List<PrefectureEntity> findByNameLikeOrCapitalLikeOrFlowerLikeOrBirdLikeOrTreeLike(String ken, String capital, String flower, String bird, String tree);

    List<PrefectureEntity> findAll();
}