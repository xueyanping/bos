package com.yado.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.yado.entity.Customer;
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	private JdbcTemplate JdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		JdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		List<Customer> list = JdbcTemplate.query(sql, new RowMapper<Customer>(){

			@Override
			public Customer mapRow(ResultSet arg0, int arg1) throws SQLException {
				
				int id = arg0.getInt("id");
				String name = arg0.getString("name");
				String station = arg0.getString("station");
				String telephone = arg0.getString("telephone");
				String address = arg0.getString("address");
				String decidedzone_id = arg0.getString("decidedzone_id");				
				
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
			
		});
		return list;
	}

	//查询未关联到定区的客户
	@Override
	public List<Customer> findAllNotAssociation() {
		String sql = "select * from t_customer where decidedzone_id is null ";
		List<Customer> list = JdbcTemplate.query(sql, new RowMapper<Customer>(){

			@Override
			public Customer mapRow(ResultSet arg0, int arg1) throws SQLException {
				
				int id = arg0.getInt("id");
				String name = arg0.getString("name");
				String station = arg0.getString("station");
				String telephone = arg0.getString("telephone");
				String address = arg0.getString("address");
				String decidedzone_id = arg0.getString("decidedzone_id");				
				
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
			
		});
		return list;
	}


	@Override
	public List<Customer> findAllHadAssociation(String decidedzoneId) {
		String sql = "select * from t_customer where decidedzone_id = ?";
		List<Customer> list = JdbcTemplate.query(sql, new RowMapper<Customer>(){

			@Override
			public Customer mapRow(ResultSet arg0, int arg1) throws SQLException {
				
				int id = arg0.getInt("id");
				String name = arg0.getString("name");
				String station = arg0.getString("station");
				String telephone = arg0.getString("telephone");
				String address = arg0.getString("address");
				String decidedzone_id = arg0.getString("decidedzone_id");	
				
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
			
		},decidedzoneId);
		return list;
	}


	@Override
	public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds) {
		String sql = "update t_customer set decidedzone_id = null where decidedzone_id = ?";
		JdbcTemplate.update(sql, decidedzoneId);		
		for (Integer cut_id : customerIds) {
			sql = "update t_customer set decidedzone_id = ? where id = ?";
			JdbcTemplate.update(sql,decidedzoneId,cut_id);
		}
		
	}


	@Override
	public Customer findCustomerByTelephone(String telephone) {
		
		String sql = "select * from t_customer where telephone = ?";
		List<Customer> list = JdbcTemplate.query(sql, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet arg0, int arg1) throws SQLException {
				
				int id = arg0.getInt("id");
				String name = arg0.getString("name");
				String station = arg0.getString("station");
				String telephone = arg0.getString("telephone");
				String address = arg0.getString("address");
				String decidedzone_id = arg0.getString("decidedzone_id");	
				
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
			
		},telephone);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
		
	}


	@Override
	public String findDecidedzoneIdbyAddress(String address) {
		String sql = "select decidedzone_id from t_customer where address = ?";
		String decidedzoneId = JdbcTemplate.queryForObject(sql, String.class,address);
		return decidedzoneId;
	}

}
