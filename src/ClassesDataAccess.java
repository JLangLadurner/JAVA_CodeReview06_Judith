import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDataAccess {

    private Connection conn;
    private static final String classTable = "classRooms";

    public ClassesDataAccess()
            throws SQLException, ClassNotFoundException {

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

        //STEP 2: Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/CR6_Judith" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // we will use this connection to write to a file
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }
    public void closeDb() throws SQLException {
        conn.close();
    }

    /**
     * Get all db records
     * @return
     * @throws SQLException
     */

    public List<ClassRooms> getAllClass()  throws SQLException {

        String sql2 = "SELECT classRoomId, className FROM " + classTable + " INNER JOIN teacher ON fk_teacherId = teacherId";
        PreparedStatement pstmnt = conn.prepareStatement(sql2);
        ResultSet rs2 = pstmnt.executeQuery();
        List<ClassRooms> listClass = new ArrayList<>();

        while (rs2.next()) {
            int i = rs2.getInt("classRoomId");
            String className = rs2.getString("className");

            listClass.add(new ClassRooms(i,className));
        }

        pstmnt.close(); // also closes related result set
        return listClass;
    }


}
