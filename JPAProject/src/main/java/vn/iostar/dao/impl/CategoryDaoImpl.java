package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.ICategoryDao;
import vn.iostar.entity.Category;

public class CategoryDaoImpl implements ICategoryDao {
	
	@Override
	public void insert(Category category)
	{
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		try {
			trans.begin();
			
			//phuong thuc insert = persist
			ema.persist(category);
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
	public void update(Category category)
	{
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		try {
			trans.begin();
			
			//phuong thuc update = merge
			ema.merge(category);
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
	public void delete(int cateid) throws Exception
	{
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		try {
			trans.begin();
			Category cate = ema.find(Category.class, cateid);
			if(cate != null)
			{
				//phuong thuc delete = remove
				ema.remove(cate);				
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
	public Category findById(int cateid) {
		EntityManager ema = JPAConfig.getEntityManager();
		Category category = ema.find(Category.class, cateid);
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager ema = JPAConfig.getEntityManager();
		TypedQuery<Category> query = ema.createNamedQuery("Category.findAll",Category.class);
		return query.getResultList();
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		EntityManager ema = JPAConfig.getEntityManager();
		String jpql = "Select c FROM Category c WHERE c.catename like :catname";
		TypedQuery<Category> query = ema.createQuery(jpql,Category.class);
		query.setParameter("catename", "%" + catname + "%");
		
		return query.getResultList();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager ema = JPAConfig.getEntityManager();
		TypedQuery<Category> query = ema.createNamedQuery("Category.findAll",Category.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
		
	}

	@Override
	public int count() {
		EntityManager ema = JPAConfig.getEntityManager();
		String jpql = "Select count(*) FROM Category c ";
		Query query = ema.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
		
	}
	
	
}
