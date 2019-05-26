package com.yado.service;

import java.util.List;

import javax.jws.WebService;

import com.yado.entity.Customer;
@WebService
public interface ICustomerService {
	public List<Customer> findAll();
	public List<Customer> findAllNotAssociation();
	public List<Customer> findAllHadAssociation(String decidedzoneId);
	public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds);
	public Customer findCustomerByTelephone(String teltphone);
	public String findDecidedzoneIdbyAddress(String address);
	

}
