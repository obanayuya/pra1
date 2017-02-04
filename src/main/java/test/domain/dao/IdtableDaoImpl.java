package test.domain.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import test.domain.model.entity.PrefectureAreaConectionEntity;

import java.util.ArrayList;
import java.util.List;
@Component
public class IdtableDaoImpl implements IdtableDao{
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<PrefectureAreaConectionEntity> getAll() {
        RowMapper<PrefectureAreaConectionEntity> mapper2 = new BeanPropertyRowMapper<PrefectureAreaConectionEntity>(PrefectureAreaConectionEntity.class);
        List<PrefectureAreaConectionEntity> conections = jdbc.query("SELECT * FROM idtable", mapper2);

    return conections;

    }

    @Override
    public List<PrefectureAreaConectionEntity> getOne(int tagid) {
        RowMapper<PrefectureAreaConectionEntity> mapper2 = new BeanPropertyRowMapper<PrefectureAreaConectionEntity>(PrefectureAreaConectionEntity.class);
        List<PrefectureAreaConectionEntity> conectionList = new ArrayList<PrefectureAreaConectionEntity>();
        conectionList = jdbc.query("select tagid from idtable where id = ?", mapper2, tagid);

        return conectionList;
    }

    @Override
    public void addTag(String addtagname, Integer num){
        Integer addtagid = 0;
        switch (addtagname){
            case "北海道":
                addtagid = 1;
                break;
            case "東北":
                addtagid = 2;
                break;
            case "関東":
                addtagid = 3;
                break;
            case "中部":
                addtagid = 4;
                break;
            case "近畿":
                addtagid = 5;
                break;
            case "中国":
                addtagid = 6;
                break;
            case "四国":
                addtagid = 7;
                break;
            case "九州":
                addtagid = 8;
                break;
        }

        jdbc.update("insert into idtable(id, tagid) values(?, ?)", num , addtagid);

        return;
    }


    @Override
    public void deleteTag(Integer num, Integer tagid){
        jdbc.update("delete from idtable where id = ? and tagid  = ?", num, tagid);
    }


}
