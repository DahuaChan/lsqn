package cn.cdahua.servers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cdahua.dao.IAuthorDao;
import cn.cdahua.dao.IMessageDao;
import cn.cdahua.exception.MyException;
import cn.cdahua.model.Author;
import cn.cdahua.model.Pager;

@Service("authorServer")
public class AuthorServer implements IAuthorServer {

	private IAuthorDao authorDao;
	private IMessageDao messageDao;

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	@Resource
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public IAuthorDao getAuthorDao() {
		return authorDao;
	}

	@Resource
	public void setAuthorDao(IAuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public void add(Author author) {
		if(loadByEmail(author.getA_email())!=null){
			
			throw new MyException("账号已被注册，若忘记密码可联系管理员");
		}
		authorDao.add(author);
	}

	@Override
	public void delete(int id) {
		long count = messageDao.countByAuthor(id);
		if (count > 0)
			throw new MyException("该投稿人员存在文章");
		authorDao.delete(id);
	}

	@Override
	public void update(Author author) {
		authorDao.update(author);

	}

	@Override
	public Author load(int id) {
		return authorDao.load(id);
	}

	@Override
	public List<Author> listAllAuthor() {
		return authorDao.loadAll();
	}

	@Override
	public Pager<Author> findAuthor() {
		String hql = "from Author";
		return authorDao.find(hql);
	}

	@Override
	public Pager<Author> authorFindAuthor(int a_id) {
		String hql = "from Author a where a.a_id = "+a_id;
		return authorDao.find(hql);
	}

	@Override
	public Author login(Author author) {
		Author a = this.loadByEmail(author.getA_email());
		if(a==null) throw new MyException("用户名或密码不存在");
		if(!a.getA_password().equals(author.getA_password())) throw new MyException("用户名或密码不正确");
		return a;
	}

	@Override
	public Author loadByEmail(String name) {
		String hql = "select author from Author author where author.a_email= ? ";
		return (Author)authorDao.queryByHql(hql, name);
	}
}
