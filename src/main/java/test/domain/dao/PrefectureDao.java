package test.domain.dao;


import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.PrefectureEntity;

import java.util.List;

public interface PrefectureDao {
    List<PrefectureEntity> getAll();

    PrefectureEntity getOne(int prefectureid);

    List<PrefectureDto> search(String ken);

    void update(String name, String capital, String flower, String bird, String tree, int num);

    void delete(int num);

    void insert(String name, String capital, String flower, String bird, String tree);

}
