package com.sysu.toolPlantform.database;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BaseDAO extends SqlMapClientDaoSupport {

    @Autowired
    @Qualifier("sqlMapClient")
    private SqlMapClient sqlMapClient;

    @PostConstruct
    private void init(){
        if (logger.isInfoEnabled()){
            logger.info("Initing BaseDAO");
        }

        setSqlMapClient(sqlMapClient);
    }
}