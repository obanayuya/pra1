package test.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import test.domain.model.entity.AreaEntity;
import test.domain.model.entity.PrefectureAreaConectionEntity;
import test.domain.model.dto.PrefectureDto;
import test.domain.model.entity.PrefectureEntity;
import test.domain.dao.IdtableDao;
import test.domain.dao.PrefectureDao;
import test.domain.dao.TagtableDao;
import test.domain.repository.PrefectureRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrefectureDtoServiceImpl implements PrefectureDtoService {
    @Autowired
    //   　フィールド変数　メソッドの中じゃなくてクラスの中に書く。
    private PrefectureDao prefectureDao;
    @Autowired
    private PrefectureRepository prefectureRepository;


    @Autowired
    private IdtableDao idtableDao;

    @Autowired
    private TagtableDao tagtableDao;

    @Override
    public List<PrefectureDto> getAll() {
//        List<PrefectureEntity> result = prefectureDao.getAll();
        List<PrefectureEntity> result = prefectureRepository.findAll();

        List<PrefectureAreaConectionEntity> conectionList = new ArrayList<PrefectureAreaConectionEntity>();

        List<PrefectureDto> prefectureDtoList = new ArrayList<PrefectureDto>();


        for (PrefectureEntity prefecture : result) {
            Integer id = prefecture.getId();
            PrefectureDto pref = new PrefectureDto();


            // id一つについているtagidをconectionListに入れる
            conectionList = (List<PrefectureAreaConectionEntity>) idtableDao.getOne(id);

            for (PrefectureAreaConectionEntity conection : conectionList) {

                AreaEntity areaname = tagtableDao.getOne(conection.getTagid());


                pref.getAreaList().add(areaname);

            }

            pref.setId(prefecture.getId());
            pref.setName(prefecture.getName());
            pref.setCapital(prefecture.getCapital());
            pref.setFlower(prefecture.getFlower());
            pref.setBird(prefecture.getBird());
            pref.setTree(prefecture.getTree());

            prefectureDtoList.add(pref);
        }

        return prefectureDtoList;
    }
}
