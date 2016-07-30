package cn.cdahua.dao;

import org.springframework.stereotype.Repository;

import cn.cdahua.model.Author;

@Repository("authorDao")
public class AuthorDao extends BaseDao<Author> implements IAuthorDao {

}
