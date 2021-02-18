package com.eh.grocery.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionUtil {

    public SessionFactory getSessionFactory(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
          throw new NullPointerException("factory is not a hibernate factory");
        }
        return factory.unwrap(SessionFactory.class);
      }
}