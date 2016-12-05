package com.ipt.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
//import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadView")
@SessionScoped
public class FileUploadManagedBean {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void copyFile(UploadedFile file) throws IOException {
        String urlToConnect = "http://192.168.8.1/media/data/";
       // String paramToSend = "fubar";
        UploadedFile fileToUpload = this.file;
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.

        URLConnection connection = new URL(urlToConnect).openConnection();
        connection.setDoOutput(true); // This sets request method to POST.
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));

            writer.println("--" + boundary);
        //    writer.println("Content-Disposition: form-data; name=\"paramToSend\"");
            writer.println("Content-Type: text/plain; charset=UTF-8");
            writer.println();
           // writer.println(paramToSend);

            writer.println("--" + boundary);
            writer.println("Content-Disposition: form-data; name=\"fileToUpload\"; filename=\"file.txt\"");
            writer.println("Content-Type: text/plain; charset=UTF-8");
            writer.println();
            BufferedReader reader = null;
            try {
                System.out.println("MEEEEEEE 1");
                reader = new BufferedReader(new InputStreamReader(new FileInputStream((File) fileToUpload), "UTF-8"));
                for (String line; (line = reader.readLine()) != null;) {
                    writer.println(line);
                }
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                        
                    } catch (IOException logOrIgnore) {
                    }
                }
            }

            writer.println("--" + boundary + "--");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

// Connection is lazily executed whenever you request any status.
        int responseCode = ((HttpURLConnection) connection).getResponseCode();
        System.out.println("MEEEEEEE 2");
        System.out.println(responseCode); // Should be 200

    }

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

}
