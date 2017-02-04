package test.domain.dao;


import test.domain.model.entity.AreaEntity;

import java.util.List;

public interface TagtableDao {
    List<AreaEntity> getAll();

    AreaEntity getOne(int tagid);


}
