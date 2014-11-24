package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.model.Post;

public class PostDaoImpl implements PostDao {

	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	
	@Override
	public Post getPostById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Post post = (Post) session.get(Post.class, id);
        session.close();
        System.out.println(("Post Returned..." + post.getId()));
		return post;
	}

	@Override
	public Post save(Post post) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(post);
        tx.commit();
        session.close();
        System.out.println(("Post Saved..." + post.getId()));
        return post;
	}

	@Override
	public void delete(Post post) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(post);
        tx.commit();
        session.close();
        System.out.println(("Post Deleted..." + post.getId()));
	}

	@Override
	public List<Post> allPost() {
		// TODO Auto-generated method stub
		
		List<Post> result = null;
		Session session = this.sessionFactory.openSession();

        result = (List<Post>)session.createQuery("from Post").list();

        session.close();
		return result;
	}

}
