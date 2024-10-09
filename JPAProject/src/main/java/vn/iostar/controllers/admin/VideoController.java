package vn.iostar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.entity.Video;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.IVideoService;
import vn.iostar.services.impl.CategoryServiceImpl;
import vn.iostar.services.impl.VideoServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(urlPatterns = {"/admin/category/videos","/admin/category/video/add","/admin/category/video/insert","/admin/category/video/edit",
		                   "/admin/category/video/update","/admin/category/video/delete","/admin/category/video/search"})
public class VideoController extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public IVideoService vidService = new VideoServiceImpl();
    public ICategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if(url.contains("videos"))
		{
			int cateid = Integer.parseInt(req.getParameter("id")); 
			Category cate = cateService.findById(cateid);
			List<Video> list = cate.getVideos();
			req.setAttribute("cateid", cateid);
			req.setAttribute("listvid", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);			
		}
		else if(url.contains("/admin/category/video/add"))
		{
			int cateid = Integer.parseInt(req.getParameter("id")); 
			Category cate = cateService.findById(cateid);
			req.setAttribute("category", cate);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);		
		}
		else if(url.contains("/admin/category/video/edit")) {
			
			String id = req.getParameter("id");
			Video video = vidService.findById(id);
			req.setAttribute("vid",video);
			
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);	
			
		}
		else if (url.contains("/admin/category/video/delete"))
		{
			String id = req.getParameter("id");
			Video video = vidService.findById(id);
			int cateid = video.getCategory().getCategoryid();
			try {
				vidService.delete(id);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/category/videos?id=" + cateid);
			//resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if(url.contains("/admin/category/video/insert"))
		{
			String videoid = req.getParameter("videoid");
			System.out.println(videoid);
			String title = req.getParameter("title");
			System.out.println(title);
			String description = req.getParameter("description");
			System.out.println(description);
			String viewss = req.getParameter("view");
			int views = Integer.parseInt(viewss);
			System.out.println(views);
			String activess = req.getParameter("active");
			int actives = Integer.parseInt(activess);
			System.out.println(actives);
			String category = req.getParameter("category");
			int categoryid = Integer.parseInt(category);
			
			Video video = new Video();
			video.setVideoId(videoid);
			video.setActive(actives);
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(views);
			Category cate = new Category();
			cate = cateService.findById(categoryid);
			video.setCategory(cate);

			String fname = "";
			String uploadPath = "D:\\upload";
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try
			{
				Part part = req.getPart("poster");
				if(part.getSize() > 0)
				{
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					
					//doi ten file 
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					
					//upload file
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				}
				else
				{
					video.setPoster("avata.png");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			vidService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/category/videos?id="+categoryid);
		
		
		}
		else if(url.contains("/admin/category/video/update")) {
			
			String videoid = req.getParameter("videoid");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			String viewss = req.getParameter("view");
			int views = Integer.parseInt(viewss);
			String activess = req.getParameter("active");
			int actives = Integer.parseInt(activess);
			String category = req.getParameter("category");
			int categoryid = Integer.parseInt(category);
			
			Video video = new Video();
			video.setVideoId(videoid);
			video.setActive(actives);
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(views);
			Category cate = new Category();
			cate = cateService.findById(categoryid);
			video.setCategory(cate);

			String fname = "";
			String uploadPath = "D:\\upload";
			
			// luu hinh cu 
			Video vidold = vidService.findById(videoid);
			String fileold = vidold.getPoster();
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try
			{
				Part part = req.getPart("poster");
				if(part.getSize() > 0)
				{
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					
					//doi ten file 
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					
					//upload file
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				}
				else
				{
					video.setPoster(fileold);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			vidService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/category/videos?id="+categoryid);

			
			
		}
	}
}
