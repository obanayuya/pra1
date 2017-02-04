package test.domain.dao;


import test.domain.model.entity.PrefectureAreaConectionEntity;

import java.util.List;

public interface IdtableDao {
    List<PrefectureAreaConectionEntity> getAll();

    List<PrefectureAreaConectionEntity> getOne(int tagid);

    void addTag(String addtagname, Integer num);

    void deleteTag(Integer num, Integer tagid);
}
