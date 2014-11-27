import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import edu.sjsu.cmpe275.dao.OrgDaoImpl;
import edu.sjsu.cmpe275.dao.PersonDaoImpl;
import edu.sjsu.cmpe275.model.Address;
import edu.sjsu.cmpe275.model.Organization;
import edu.sjsu.cmpe275.model.Person;
import edu.sjsu.cmpe275.service.AllServices;


public class Test {
	
//	public static void main(String argu []){
//		
//		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
//		
////        PersonDaoImpl personDao = ctx.getBean("personDao", PersonDaoImpl.class);
//        AllServices as = ctx.getBean("allServices", AllServices.class);
////        OrgDaoImpl orgDao = ctx.getBean("orgDao", OrgDaoImpl.class);
//        
//        Address add = new Address("1417 Main Entrance Dr",  "San Jose",  "USA",  "95131");
//        
//        Organization org = new Organization("SJSU", "a University", null);
//        
//        as.createOrg(org);
////
////        
////        as.createPerson(person1);
////        
////        Person person2 = new Person("Xiaotong", "Yin", "123@abc", "NB", null, null);
////        person2.setId(1);
//        
////        as.createOrg(org);
////        Person person1 = new Person("Feiyu", "Yin", "123@abc", "NB", null, null);
////        as.createPerson(person1);
//        
//        Person person2 = new Person("Xiaotong", "Yin", "123@abc", null, "NB", add, org);
//        as.createPerson(person2);
//        
////        as.addFriend(person1.getId(), person2.getId());
//  
// //       as.deletePersonById(2);
////        Person tmp = as.getPersonById(1);
////        System.out.println(tmp.getFriends());
////        System.out.println(as.rmFriend(person1.getId(), person2.getId()));
//        
////        Person tmp = as.getPersonById(1);
////        System.out.println(tmp.getPersons().get(0).getId());
//        
////        Organization org2 = new Organization("SJUS", "a University", null);
////        org2.setId(1);
////        as.updateOrg(org2);
//        
//        
////        Person person2 = new Person("Xiaotong", "Wang", "123@abc", "NB", add, org);
////        
////        personDao.save(person1);
////        personDao.save(person2);
//        
//        //allService.addFriend(person1.getId(), person2.getId());
//
//	}

}
