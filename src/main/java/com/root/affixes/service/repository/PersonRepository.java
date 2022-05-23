package com.root.affixes.service.repository;

import com.root.affixes.service.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jc
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    /**
     * 根据name查询
     * @param name
     * @return
     */
    @Query("match (n:person{name:{name}}) return n LIMIT 1")
    Person queryPersonByName(@Param("name") String name);

}
