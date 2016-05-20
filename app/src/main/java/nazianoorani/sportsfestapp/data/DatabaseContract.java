package nazianoorani.sportsfestapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by nazianoorani on 20/05/16.
 */
public class DatabaseContract {
    public static final String MATCH_TABLE = "match_table";
    public static final class match_table implements BaseColumns
    {
        //Table data
        public static final String DATE_COL = "date";
        public static final String TIME_COL = "time";
        public static final String TEAM_A_COL = "team_a_name";
        public static final String TEAM_B_COL = "team_b_name";
        public static final String EVENT = "event";
        public static final String MATCH_DATE = "match_date";

        public static Uri MATCHES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH)
        .build();

        //Types
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        public static Uri buildMatchWithMatchDate()
        {
            return BASE_CONTENT_URI.buildUpon().appendPath("match_date").build();
        }

//        public static Uri buildScoreWithDate()
//        {
//            return BASE_CONTENT_URI.buildUpon().appendPath("date").build();
//        }



    }
    //URI data
    public static final String CONTENT_AUTHORITY = "nazianoorani.sportsfestapp";
    public static final String PATH = "matches";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);



}
