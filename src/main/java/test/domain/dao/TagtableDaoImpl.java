package test.domain.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import test.domain.model.entity.AreaEntity;

import java.util.List;

@Component
public class TagtableDaoImpl implements TagtableDao{
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<AreaEntity> getAll() {
        return null;
    }

    @Override
    public AreaEntity getOne(int tagid) {

        RowMapper<AreaEntity> mapper3 = new BeanPropertyRowMapper<AreaEntity>(AreaEntity.class);

        AreaEntity areaname = jdbc.queryForObject("select * from tagtable where tagid = ?", mapper3, tagid);


        return areaname;
    }
}
