package com.root.affixes.service.repository;

import com.root.affixes.service.entity.Developer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jc
 */
@Repository
public interface DeveloperRepository extends Neo4jRepository<Developer, Long>{

    /**
     * 自定义创建developer与createRelationPersionAndDeveloper间关系的方法
     *
     * @param person
     * @param department
     */
    @Query(value = "MATCH (p:Person{name:$person}),(d:Developer{name:$department}) with p,d"
            + " CREATE (p)-[r:RELATION{name:'BelongTo'}]->(d)")
    void create(@Param("person") String person, @Param("department") String department);

}
