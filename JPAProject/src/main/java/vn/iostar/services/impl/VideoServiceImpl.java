package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.IVideoDao;
import vn.iostar.dao.impl.VideoDaoImpl;
import vn.iostar.entity.Video;
import vn.iostar.services.IVideoService;

public class VideoServiceImpl implements IVideoService {
	IVideoDao viddao = new VideoDaoImpl();
	@Override
	public void insert(Video video) {
		
		viddao.insert(video);
	}

	@Override
	public void update(Video video) {
		viddao.update(video);
		
	}

	@Override
	public void delete(String videoid) throws Exception {
		viddao.delete(videoid);
	}

	@Override
	public Video findById(String videoid) {
		return viddao.findById(videoid);
	}

	@Override
	public List<Video> findAll() {
		return viddao.findAll();
	}

	@Override
	public List<Video> findByVideotitle(String videotitle) {
		return viddao.findByVideotitle(videotitle);
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return viddao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return viddao.count();
	}

}
