package client;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.Link;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 26.04.14.
 */
public class PicasaClient {

    private static final String API_PREFIX = "https://picasaweb.google.com/data/feed/api/user/";

    private final PicasawebService service;

    public PicasaClient(PicasawebService service) {
        this.service = service;
    }

    public List<AlbumEntry> getAlbums(String username) throws IOException,
            ServiceException {

        String albumUrl = API_PREFIX + username;
        UserFeed userFeed = getFeed(albumUrl, UserFeed.class);

        List<GphotoEntry> entries = userFeed.getEntries();
        List<AlbumEntry> albums = new ArrayList<AlbumEntry>();
        for (GphotoEntry entry : entries) {
            GphotoEntry adapted = entry.getAdaptedEntry();
            if (adapted instanceof AlbumEntry) {
                albums.add((AlbumEntry) adapted);
            }
        }
        return albums;
    }

    public List<PhotoEntry> getPhotos(AlbumEntry album) throws IOException,
            ServiceException {

        String feedHref = getLinkByRel(album.getLinks(), Link.Rel.FEED);
        AlbumFeed albumFeed = getFeed(feedHref, AlbumFeed.class);

        List<GphotoEntry> entries = albumFeed.getEntries();
        List<PhotoEntry> photos = new ArrayList<PhotoEntry>();
        for (GphotoEntry entry : entries) {
            GphotoEntry adapted = entry.getAdaptedEntry();
            if (adapted instanceof PhotoEntry) {
                photos.add((PhotoEntry) adapted);
            }
        }
        return photos;
    }

    public <T extends GphotoFeed> T getFeed(String feedHref, Class<T> feedClass) throws IOException, ServiceException {
        System.out.println("Get Feed URL: " + feedHref);
        return service.getFeed(new URL(feedHref), feedClass);
    }

    public String getLinkByRel(List<Link> links, String relValue) {
        for (Link link : links) {
            if (relValue.equals(link.getRel())) {
                return link.getHref();
            }
        }
        throw new IllegalArgumentException("Missing " + relValue + " link.");
    }
}
