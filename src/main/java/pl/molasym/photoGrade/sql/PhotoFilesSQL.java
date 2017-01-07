package pl.molasym.photoGrade.sql;

/**
 * Created by moliq on 19.11.16.
 */
public class PhotoFilesSQL {
    public static final String PhotoFromUser = "Select p from Photo p where p.user = :user and visibility = :visibility";
    public static final String PhotoById = "Select p from Photo p where p.photoId = :photoId";
    public static final String AllPhotoFromUser = "Select p from Photo p where p.user = :user";
    public static final String GET_PHOTO_OWNER = "Select p.user from Photo p where p = :photo";
}
