package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoDao {
	
	void insert(Video video);
	
	void update(Video video);
	
	void delete(String videoid) throws Exception;
	
	Video findById(String videoid);
	
	List<Video> findAll();
	
	List<Video> findByVideotitle(String videotitle);
	
	List<Video> findAll(int page,int pagesize);
	
	int count();
}
