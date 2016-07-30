package cn.cdahua.dao;


import org.springframework.stereotype.Repository;

import cn.cdahua.model.Admin;

@Repository("adminDao")
public class AdminDao extends BaseDao<Admin> implements IAdminDao{

	

}
