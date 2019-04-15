package com.dist.test6;

import org.apache.thrift.TException;

/**
 * Created by Administrator on 2019/4/12.
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("get client pram: "+username);
        Person person =new Person();
        person.setAge(30);
        person.setUsername("zhansan");
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("get client pram:");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
