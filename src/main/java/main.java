import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.Jsoup;

public class main {

        
private static String IMAGE_DESTINATION_FOLDER = "C:\\Users\\Vera\\Desktop\\pictures";
    
    public static void main(String[] args) throws IOException {
        
        String strURL = "https://astondevs.ru/";
        
        Document document = Jsoup
                .connect(strURL)
                .get();
        
        Elements imageElements = document.select("img");
        
        for(Element imageElement : imageElements){
            
            String strImageURL = imageElement.attr("abs:src");
            
            downloadImage(strImageURL);
            
        }
 
    }
    
    private static void downloadImage(String strImageURL){
        
        String strImageName = 
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );
        
        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
        
        try {
            
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();
            
            byte[] buffer = new byte[4096];
            int n = -1;
            
            OutputStream os = 
                new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );
            
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }
            
            os.close();
            
            System.out.println("Image saved");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}