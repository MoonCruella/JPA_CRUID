package vn.iostar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iostar.entity.Category;

public class Test {
	public static void main(String[] args) {
		EntityManager ema = JPAConfig.getEntityManager();
		EntityTransaction trans = ema.getTransaction();
		
		// Insert mau 
		Category cate = new Category();
		cate.setCategoryname("Iphone");
		cate.setImages("abc.jpg");
		cate.setStatus(1);
		try {
			trans.begin();
			ema.persist(cate);
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
}
