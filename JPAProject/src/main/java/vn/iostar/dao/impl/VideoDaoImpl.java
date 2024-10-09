package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.IVideoDao;
import vn.iostar.entity.Video;

public class VideoDaoImpl implements IVideoDao{

	@Override
	public void insert(Video video) {
		
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		try {
			trans.begin();
			
			//phuong thuc insert = persist
			ema.persist(video);
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			ema.close();
		}
		
	}

	@Override
	public void update(Video video) {
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		try {
			trans.begin();
			
			//phuong thuc update = merge
			ema.merge(video);
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			ema.close();
		}
		
	}

	@Override
	public void delete(String videoid) throws Exception {
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		try {
			trans.begin();
			Video vid = ema.find(Video.class, videoid);
			if(vid != null)
			{
				//phuong thuc delete = remove
				ema.remove(vid);				
			}
			else
				throw new Exception("Khong tim thay");
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			ema.close();
		}
		
	}

	@Override
	public Video findById(String videoid) {
		EntityManager ema = JPAConfig.getEntityManager();
		Video video = ema.find(Video.class, videoid);
		return video;
	}

	@Override
	public List<Video> findAll() {
		EntityManager ema = JPAConfig.getEntityManager();
		TypedQuery<Video> query = ema.createNamedQuery("Video.findAll",Video.class);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideotitle(String videotitle) {
		EntityManager ema = JPAConfig.getEntityManager();
		String jpql = "Select v FROM video v WHERE v.title like :videotitle";
		TypedQuery<Video> query = ema.createQuery(jpql,Video.class);
		query.setParameter("videotitle", "%" + videotitle + "%");
		
		return query.getResultList();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager ema = JPAConfig.getEntityManager();
		TypedQuery<Video> query = ema.createNamedQuery("Video.findAll",Video.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager ema = JPAConfig.getEntityManager();
		String jpql = "Select count(*) FROM video v ";
		Query query = ema.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
