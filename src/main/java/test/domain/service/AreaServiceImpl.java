package test.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.domain.dao.TagtableDao;
import test.domain.model.entity.AreaEntity;
import test.domain.repository.AreaRepository;

import java.util.List;


@Component
public class AreaServiceImpl implements AreaService{
    @Autowired
    private TagtableDao tagtableDao;


    @Autowired
    private AreaRepository areaRepository;

    @Override
    public AreaEntity getOne(int tagid) {

        AreaEntity areaname = tagtableDao.getOne(tagid);
        return areaname;
    }

    @Override
    public List<AreaEntity> getAllAreaEntity() {
        List<AreaEntity> areaEntityList = areaRepository.findAll();

        return areaEntityList;
    }
}
