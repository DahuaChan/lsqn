package cn.cdahua.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cdahua.model.Pager;
import cn.cdahua.model.SystemContext;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private Class<T> clz;

	@SuppressWarnings("unchecked")
	public Class<T> getClz() {
		if (clz == null) {
			// 获取泛型的Class对象
			clz = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments()[0]));
		}
		return clz;
	}

	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(this.load(id));
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T load(int id) {
		return this.getHibernateTemplate().load(getClz(), id);
	}

	@Override
	public List<T> loadAll() {
		return this.getHibernateTemplate().loadAll(getClz());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(String hql, Object[] arg) {
		Query q = this.getSession().createQuery(hql);

		for (int i = 0; i < arg.length; i++) {
			q.setParameter(i, arg[i]);
		}

		return q.list();
	}

	@Override
	public List<T> list(String hql) {
		return list(hql, null);
	}

	@Override
	public List<T> list(String hql, Object arg) {
		return list(hql, new Object[] { arg });
	}

	private String getCountHql(String hql) {
		// 1、获取from前面的字符串
		String f = hql.substring(0, hql.indexOf("from"));
		// 2、将from前面的字符串替换为select count(*)
		if (f.equals("")) {
			hql = "select count(*) " + hql;
		} else {
			hql = hql.replace(f, "select count(*) ");
		}
		// 3、将fetch替换为""，因为抓取查询不能使用count(*)
		hql = hql.replace("fetch", " ");
		return hql;
	}
	
	private Query setParamterQuery(String hql,Object[] args) {
		Query q = this.getSession().createQuery(hql);
		if(args!=null) {
			for(int i=0;i<args.length;i++) {
				q.setParameter(i, args[i]);
			}
		}
		return q;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<T> find(String hql, Object[] args) {
		int pageSize = SystemContext.getPageSize();
		int pageOffSet = SystemContext.getPageOffSet();
		if (pageSize <= 0) {
			pageSize = 15;
		}
		if (pageOffSet < 0) {
			pageOffSet = 1;
		}
		Pager<T> pages = new Pager<T>();
		Query q = setParamterQuery(hql, args);
		q.setFirstResult(pageOffSet).setMaxResults(pageSize);

		Query cq = setParamterQuery(getCountHql(hql), args);
		long totalRecord = (long)cq.uniqueResult();
		pages.setPageOffSet(pageOffSet);
		pages.setPageSize(pageSize);
		pages.setDatas(q.list());
		pages.setTotalRecord(totalRecord);
		return pages;
	}

	@Override
	public Pager<T> find(String hql) {
		return find(hql, null);
	}

	@Override
	public Pager<T> find(String hql, Object arg) {
		return find(hql, new Object[] { arg });
	}

	@Override
	public Object queryByHql(String hql, Object[] args) {
		Query q = setParamterQuery(hql, args);
		return q.uniqueResult();
	}

	@Override
	public Object queryByHql(String hql, Object arg) {
		return this.queryByHql(hql, new Object[] {arg});
	}

	@Override
	public Object queryByHql(String hql) {
		return this.queryByHql(hql, null);
	}

}
