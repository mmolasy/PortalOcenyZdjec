package pl.molasym.photoGrade.sql;

/**
 * Created by moliq on 26.12.16.
 */
public class GradeSQL {
    public static final String REMOVE_GRADE_BY_PHOTO_AND_USER = "Delete from Grade  where user= :userr and photo= :photoo";
    public static final String GET_GRADES_BY_PHOTO_ID = "SELECT value, COUNT(value) FROM Grade where photo = :photoo group by value";
    public static final String GET_GRADE_BY_PHOTO_AND_USER = "SELECT value from Grade where photo = :photoo and user = :userr";
}
