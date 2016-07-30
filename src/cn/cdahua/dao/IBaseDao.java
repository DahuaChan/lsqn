package cn.cdahua.dao;

import java.util.List;

import cn.cdahua.model.Pager;


public interface IBaseDao<T> {
	
	public void add(T t);
	public void delete(int id);
	public void update(T t);
	public T load(int id);
	public List<T> loadAll();
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql);
	public List<T> list(String hql,Object arg);
	
	public Pager<T> find(String hql,Object[] args);
	public Pager<T> find(String hql);
	public Pager<T> find(String hql,Object arg);
	
	public Object queryByHql(String hql,Object[] args);
	public Object queryByHql(String hql,Object arg);
	public Object queryByHql(String hql);
	
}
