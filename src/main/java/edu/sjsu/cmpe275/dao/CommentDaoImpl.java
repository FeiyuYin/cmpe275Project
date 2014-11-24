package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.model.Comment;

public class CommentDaoImpl implements CommentDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public Comment getCommentById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Comment comment = (Comment) session.get(Comment.class, id);
        session.close();
        System.out.println(("Comment Returned..." + comment.getId()));
		return comment;
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(comment);
        tx.commit();
        session.close();
        System.out.println(("Comment Saved..." + comment.getId()));
        return comment;
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(comment);
        tx.commit();
        session.close();
        System.out.println(("Comment Deleted..." + comment.getId()));
	}

	@Override
	public List<Comment> allComment() {
		// TODO Auto-generated method stub
		List<Comment> result = null;
		Session session = this.sessionFactory.openSession();

        result = (List<Comment>)session.createQuery("from Comment").list();

        session.close();
		return result;
	}

}
