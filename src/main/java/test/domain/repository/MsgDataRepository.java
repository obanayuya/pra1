package test.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.domain.model.entity.MsgData;

import javax.tools.JavaCompiler;

public interface MsgDataRepository extends JpaRepository<MsgData, Long>{
}
