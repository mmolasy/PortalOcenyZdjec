package pl.molasym.photoGrade.sql;

/**
 * Created by moliq on 19.11.16.
 */
public class PhotoFilesSQL {
    public static final String PhotoFromUser = "Select p from Photo p where p.user = :user and visibility = :visibility";
    public static final String PhotoById = "Select p from Photo p where p.photoId = :photoId";

}
