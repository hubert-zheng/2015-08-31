package com.hand.Handtest0831;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.Emptity.Address;
import com.hand.Emptity.Customer;
import com.hand.Emptity.Store;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = null;
        try{	
        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        }catch(Exception e){
        	System.out.println("找不到配置文件");
        	e.printStackTrace();
        }
        CusJDBCTemplate cusJDBCTemplate = 
        		      (CusJDBCTemplate)context.getBean("CusJDBCTemplate");     
    	AddressFactory addressFactory = (AddressFactory) context.getBean("AddressFactory");
    	Customer cus = new Customer();
    	Address address = new Address();
    	String cusAddress = null;
    	Short cusid = null;
//    	String firstName = null;
//    	String lastName = null;
//    	String email = null;
//    	Short address_id = null;
//    	Date create_date = null;
    	Scanner sc = new Scanner(System.in);
    	System.out.println("请输入FirstName(first_name):");
    	cus.setFirstName(sc.next());
    	System.out.println("请输入LastName(last_name):");
    	cus.setLastName(sc.next());
    	System.out.println("请输入Email(email):");
    	cus.setEmail(sc.next());
    	System.out.println("请输入AddressID:");
    	address.setAddressId(sc.nextShort());
    	if(!addressFactory.checkAddress(address.getAddressId())){
    		System.out.println("你输入的AddressID不存在，请重新输入：");
    	}
    	//cus.setAddress(addressFactory.getAddress(address.getAddressId()).getAddress());
    	cus.setAddress(addressFactory.getAddress(address.getAddressId()));
    	cus.setCreateDate(new Date());
    	cus.setLastUpdate(new Date());;
    	Store st = new Store();
    	st.setStoreId((byte) 1);
    	cus.setStore(st);
    	
    	if(cusJDBCTemplate.create(cus)!=0)System.out.println("插入完成");
    	else System.out.println("插入失败");
    	System.out.println("已经保存的数据如下：");
    	System.out.println("ID:" + address.getAddressId());
    	System.out.println("FirstName: "+cus.getFirstName());
    	System.out.println("LastName: " + cus.getLastName());
    	System.out.println("Email: "+cus.getEmail());
    	System.out.println("Address:" + cus.getAddress().getAddress());
    	
    	System.out.println("请输入要删除的Customer的ID：");
    	cusid = sc.nextShort();
    	cus.setCustomerId(cusid);
    	if(cusJDBCTemplate.checkcus(cus)){
    		Customer delcus = cusJDBCTemplate.getcus(cus);
        	cusJDBCTemplate.delete(delcus);
        	System.out.println("你输入的ID为"+cusid+"的Customer已经删除");
    	}
    	
    	else{
    	System.out.println("你输入的Customer_id不存在，请重新输入");
    	cus.setCustomerId(sc.nextShort());
    	Customer delcus = cusJDBCTemplate.getcus(cus);
    	cusJDBCTemplate.delete(delcus);
    	System.out.println("你输入的ID为"+cusid+"的Customer已经删除");
    	}
    }
}
