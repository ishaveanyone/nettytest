package com.dist.test6;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by Administrator on 2019/4/12.
 */
public class Client {
    public  static  void main(String [] args){
        TTransport tTransport=new TFramedTransport(new TSocket("localhost",8080),600);
        TProtocol protocol=new TCompactProtocol(tTransport);
        PersonService.Client client=new PersonService.Client(protocol);
        try{
            tTransport.open();
            Person person=client.getPersonByUsername("zhansan");
            System.out.println(person.getAge());
            System.out.println(person.getUsername());
            Person person1=new Person();
            person1.setMarried(false);
            person1.setUsername("lisi");
            person1.setAge(20);
            client.savePerson(person1);
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            tTransport.close();
        }
    }
}
