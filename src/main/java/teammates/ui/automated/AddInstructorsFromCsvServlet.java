package teammates.ui.automated;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import teammates.common.util.Const;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddInstructorsFromCsvServlet extends HttpServlet {

    @Override
    public final void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.doPost(req, resp);
    }


    @Override
    public final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    //in case it's not a file
                } else {
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(fileContent, writer);
                    String csvInString = writer.toString();
                    csvInString.replaceAll(";", "|");
                    req.setAttribute("CsvToString", csvInString);
                    req.getRequestDispatcher(Const.ViewURIs.ADMIN_HOME).forward(req, resp);
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot Parse request", e);
        }


    }
}
