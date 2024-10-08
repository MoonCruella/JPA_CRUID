package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.ICategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{
	
	ICategoryDao catedao = new CategoryDaoImpl();
	@Override
	public void insert(Category category) {
		catedao.insert(category);
		
	}

	@Override
	public void update(Category category) {
		catedao.update(category);
		
	}

	@Override
	public void delete(int cateid) throws Exception {
		catedao.delete(cateid);
		
	}

	@Override
	public Category findById(int cateid) {
		return catedao.findById(cateid);
	}

	@Override
	public List<Category> findAll() {
		return catedao.findAll();
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		return catedao.findByCategoryname(catname);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return catedao.findAll();
	}

	@Override
	public int count() {
		return catedao.count();
	}

}
