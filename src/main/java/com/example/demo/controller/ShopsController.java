/**
 * 
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Delegates.CustomerServiceDelegate;

/**
 * @author hari.om.misra
 *
 */

@RestController
public class ShopsController {

	
	@Autowired
    CustomerServiceDelegate customerServiceDelegate;
 
    @RequestMapping(value = "/fetchAllCustomers", method = RequestMethod.GET)
    public String getAllCustomers() {
        System.out.println("Going to call student service to get data!");
        return customerServiceDelegate.callCustomerServiceAndGetData();
    }
    
    @RequestMapping(value = "/fetchCustomerByName", method = RequestMethod.GET)
    public String getCustomersByName(@RequestParam("firstName") String name) {
        System.out.println("Going to call student service to get data!");
        return customerServiceDelegate.callCustomerServiceAndGetCustomerByName(name);
    }
}
