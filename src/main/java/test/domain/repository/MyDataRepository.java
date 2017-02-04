package test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.domain.model.entity.MyData;

/**
 * Created by yuya on 西暦16/12/07.
 */
public interface MyDataRepository extends JpaRepository<MyData, Long>{

    public MyData findById(Long id);
}
