package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.model.Post;

public interface PostDao {

	public Post getPostById(long id);
    public Post save(Post post);
    public void delete(Post post);
    public List<Post> allPost();

}
