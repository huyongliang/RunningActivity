package firework.hyl.running.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imgPath = request.getParameter("imgPath");
		response.setContentType("image/*"); 
		ServletOutputStream outputStream = null;
		outputStream = response.getOutputStream();
		FileInputStream fileInputStream = new FileInputStream(new File(imgPath));
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fileInputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}

		fileInputStream.close();
		outputStream.flush();
		outputStream.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
