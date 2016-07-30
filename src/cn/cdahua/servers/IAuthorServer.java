package cn.cdahua.servers;

import java.util.List;

import cn.cdahua.model.Author;
import cn.cdahua.model.Pager;

public interface IAuthorServer {

	public void add(Author author);

	public void delete(int id);

	public void update(Author author);

	public Author load(int id);
	
	public Author loadByEmail(String name);

	public List<Author> listAllAuthor();

	public Pager<Author> findAuthor();

	public Pager<Author> authorFindAuthor(int a_id);
	
	public Author login(Author author);
}
