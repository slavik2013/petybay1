package controllers;

import client.PicasaClient;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by home on 26.04.14.
 */
public class PicasaController {

    /** Email of the Service Account */
    private static final String SERVICE_ACCOUNT_EMAIL = "824804454988-q5r0atmcco6efb46s80fr6euefaf9e3t@developer.gserviceaccount.com";

    /** Path to the Service Account's Private Key file */
    private static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "41f047ffb5b540e6d85ef026e1b509cb323ce10a-privatekey.p12";


    private static String CLIENT_ID = "824804454988-glq8c5rvf5tklaftsk8hg0pdo617iq1n.apps.googleusercontent.com";
    private static String CLIENT_SECRET = "1nqwZsBuBsfVAZ5wGFS-TybX";

    private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";


    public static void main(String[] args) throws Exception {

        PicasawebService myService = new PicasawebService("exampleCo-exampleApp-1");
        myService.setOAuth2Credentials(getCredentials());

        PicasaClient picasaClient = new PicasaClient(myService);


        PhotoEntry myPhoto = new PhotoEntry();
        MediaFileSource myMedia = new MediaFileSource(new File("test3.jpg"), "image/jpeg");

        myPhoto.setMediaSource(myMedia);

        List<AlbumEntry> list = null;
        list = picasaClient.getAlbums("petybay");



        AlbumEntry album = null;

        if(list != null){
            for (AlbumEntry albumEntry : list) {
                System.out.println("albumEntry.name = " + albumEntry.getName());
                if(albumEntry.getName().equals("Test")){
                    album = albumEntry;

                }
            }
        }else{
            System.out.println("NULL");
        }

//        if (album != null) {
//            System.out.println("link = " + album.getGphotoId());
//            URL albumPostUrl = new URL("https://picasaweb.google.com/data/feed/api/user/petybay/albumid/" + album.getGphotoId());
//            myService.insert(albumPostUrl, myPhoto);
//        }

        List<PhotoEntry> photos = null;


        photos = picasaClient.getPhotos(album);

        for (PhotoEntry photo : photos) {
            List<MediaThumbnail> mediaThumbnail = photo.getMediaThumbnails();
            for (MediaThumbnail thumbnail : mediaThumbnail) {
//                System.out.println("thumbnail.getUrl() = " + thumbnail.getUrl());
            }
            List<com.google.gdata.data.media.mediarss.MediaContent> mediaContent = photo.getMediaContents();
            for (com.google.gdata.data.media.mediarss.MediaContent content : mediaContent) {
//                System.out.println("content = " + content.getUrl());
            }
//            System.out.println("photo = " + mediaContent.get(0).getUrl());
        }


    }


    public static GoogleCredential getCredentials() throws Exception {

        GoogleCredential credential = null;
        Scanner scanner = new Scanner(new FileInputStream("credentials.txt"));

        String refreshToken = null;
        String accessToken = null;
        if(scanner.hasNext()){
            refreshToken = scanner.nextLine();
        }
        if(scanner.hasNext()){
            accessToken = scanner.nextLine();
        }

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        if(refreshToken != null && accessToken != null){

            credential = new GoogleCredential.Builder()
                    .setClientSecrets(CLIENT_ID, CLIENT_SECRET)
                    .setJsonFactory(jsonFactory).setTransport(httpTransport).build()
                    .setRefreshToken(refreshToken).setAccessToken(accessToken);
            credential.refreshToken();
            String newAccessToken =  credential.getAccessToken();
            System.out.println("newAccessToken = " + newAccessToken);
        }

        return credential;
    }
}
