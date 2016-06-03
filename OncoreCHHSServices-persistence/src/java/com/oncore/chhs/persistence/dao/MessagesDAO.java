/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.persistence.dao;

import com.oncore.chhs.persistence.entity.Messages;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author oncore
 */
@Stateless
@LocalBean
public class MessagesDAO extends AbstractDAO<Messages> {

    @PersistenceContext(name = "OncoreCHHSServices-persistencePU")
    private EntityManager entityManager;

    /**
     * @{inherited}
     */
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
