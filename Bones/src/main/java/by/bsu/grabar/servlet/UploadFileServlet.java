package by.bsu.grabar.servlet;


import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.entity.UserEnum;
import by.bsu.grabar.manager.MessageManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * The type Upload file servlet.
 */
@WebServlet("/upload")
@MultipartConfig()
public class UploadFileServlet extends HttpServlet {

    private static String fileName;
    private final static Logger LOG = Logger.getLogger(UploadFileServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "";
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(MessageManager.PARAM_NAME_USER);
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        path="images" +File.separator+
                                "photos"+File.separator+name;
                        fileName = getServletContext().getRealPath("/")+ path;
                        item.write(new File(fileName));
                    }
                }

                user.setImgPath(path);
                session.setAttribute(MessageManager.PARAM_NAME_USER, user);
                UserDao.getInstance().changeUserPhoto(user.getEmail(), path);
                LOG.info(path);
            } catch (Exception ex) {
                request.setAttribute(MessageManager.PARAM_NAME_MESSAGE, "File Upload Failed due to " + ex);
                LOG.info(ex.getMessage());
            }
        }else{
            request.setAttribute(MessageManager.PARAM_NAME_MESSAGE,
                    "Sorry this Servlet only handles file upload request");
        }
        if (user != null && user.getRole().equals(UserEnum.ADMIN)){
            request.getRequestDispatcher("jsp/admin.jsp").forward(request, response);
        }else if(user != null && user.getRole().equals(UserEnum.USER)){
            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}