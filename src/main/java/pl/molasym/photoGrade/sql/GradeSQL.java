package pl.molasym.photoGrade.sql;

/**
 * Created by moliq on 26.12.16.
 */
public class GradeSQL {
    public static final String removeGradeByPhotoAndUser = "Delete from Grade  where user= :userr and photo= :photoo";
    public static final String getGradesByPhotoId = "SELECT value, COUNT(value) FROM Grade where photo = :photoo group by value";
    public static final String getGradeByPhotoAndUser = "SELECT value from Grade where photo = :photoo and user = :userr";
}
