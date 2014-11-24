package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.model.Comment;

public interface CommentDao {

	public Comment getCommentById(long id);
    public Comment save(Comment comment);
    public void delete(Comment comment);
    public List<Comment> allComment();
}
