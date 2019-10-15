package mini;
import java.sql.*;
public class miniproject {
	
	public void abc(){
	
		String sql="SELECT * FROM miniproject.brts";
		String url="jdbc:mysql://localhost:3306";
		String username="root";
		String pwrd="Mehta1010";
	try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,username,pwrd);
	PreparedStatement st = con.prepareStatement(sql);
   ResultSet rs=st.executeQuery();
   while(rs.next()) {
	   System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getDouble(3)+""+rs.getDouble(4));
   }
  rs.close();
  st.close();
  con.close();
	

	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	

}
}