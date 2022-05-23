package com.root.affixes.service.service;

import com.root.affixes.service.entity.Developer;
import com.root.affixes.service.entity.Person;

/**
 * @author jc
 */
public interface GraphDataService {

    /**
     * 创建
     * @return Person
     */
    Person createData();

    /**
     * 创建开发者
     * @return Developer
     */
    Developer createDev();

}
