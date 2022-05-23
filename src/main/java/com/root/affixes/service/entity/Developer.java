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
@NodeEntity(label = "Developer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;
}
