package test.domain.service;


import test.domain.model.entity.AreaEntity;

import java.awt.geom.Area;
import java.util.List;

public interface AreaService {
    AreaEntity getOne(int tagid);

    List<AreaEntity> getAllAreaEntity();


}
