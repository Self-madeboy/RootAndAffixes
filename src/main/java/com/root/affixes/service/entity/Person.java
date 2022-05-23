package com.root.affixes.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author jc
 */
@Data
@NodeEntity(label = "Person")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    /**
     * neo4j的id增长策略
     */
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;
}
