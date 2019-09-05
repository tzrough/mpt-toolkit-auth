package cn.com.pg.mpt.toolkit.auth.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class IOUtils {

    public static void downloadFile(HttpServletResponse response, String fileName, String content) {

        OutputStream out = null;

        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        try{
            out = response.getOutputStream();;
            out.write(content.getBytes());
            out.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
