package demo;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;
class node1{
	String name;
	String type;
	node1(String name,String type){
		this.name=name;
		this.type=type;
	}
}
class node{
	
	String name;
	double distance;	
	double xcod;
	double ycod;
	int line;
	String type;
	node(int line,String name,double xcod,double ycod,String type){
		this.line=line;
		this.name=name;
		this.xcod=xcod;
		this.ycod=ycod;
		this.type=type;
		distance=0;
		
	}
}
class graph{
	Map<String,List<node>> adjvertices;
	Map<String,Integer> stringtoint;
    double dist=0;
	
   graph(){
	   
	   adjvertices=new HashMap<String,List<node>>();
	    stringtoint=new HashMap<String,Integer>();
   }
   void addNode(String a) {
	    adjvertices.putIfAbsent(a, new ArrayList<>());
	}

	 double deg2rad( double deg) {
	   return deg * (Math.PI/180);
	 }
   void addEdge(node source,node destination ) {
           double lat1=source.xcod;
           double lon1=source.ycod;
           double lat2=destination.xcod;
           double lon2=destination.ycod;
		   int R = 6371; // Radius of the earth in km
		   double dLat = deg2rad(lat2-lat1);  // deg2rad below
		   double dLon = deg2rad(lon2-lon1); 
		   double a = 
		     Math.sin(dLat/2) * Math.sin(dLat/2) +
		     Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
		     Math.sin(dLon/2) * Math.sin(dLon/2)
		     ; 
		   double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		   double d = R * c; // Distance in km
		  destination.distance=d;
	   adjvertices.get(source.name).add(destination);
   }
   void print() {
	   for (String s : adjvertices.keySet()) {
		   for (node n : adjvertices.get(s)) {
		      System.out.println("key :" + s + " value: " + n.name+" "+n.distance );
		   }
		   if(adjvertices.get(s)==null) {
			   System.out.println("Empty" + s);
		   }
	   }
	   for(int i=0;i<adjvertices.get("ASTODIA CHAKLA").size();i++) {
		   System.out.println(adjvertices.get("ASTODIA CHAKLA").get(i).name);
	   }
	   System.out.println(stringtoint.get("")+"  jnjcn");
	   
	   for(String s: stringtoint.keySet()) {
		   if(stringtoint.get(s)==null) {
			   System.out.println(s);
		   }
		  System.out.println(s+" "+stringtoint.get(s));
	   }
   }
   void printallpaths(String s,String d) {
	   boolean isVisited[]=new boolean[200];
	  for(int j=0;j<200;j++) {
		  isVisited[j]=false;
	  }
	   ArrayList<node1> pathList=new ArrayList<>();
	   node1 n1=new node1(s,"BRTS");
	   pathList.add(n1);
	    printAllPaths1(s, d, isVisited, pathList); 
   }
   void p(String s) {
	   for(int i=0;i<(adjvertices.get(s)).size();i++) {
	       System.out.println(adjvertices.get(s).get(i).name);   
	   }
   }
    void printAllPaths1(String s, String d, boolean[] isVisited, List<node1> localPath) {
    	   isVisited[stringtoint.get(s)]=true;
    	  if(adjvertices.get(s)==null) {
    		  return;
    	  }
    	   if(s.equals(d)) {
    		   for(int i=0;i<localPath.size();i++) {
    			   System.out.println(localPath.get(i).name+" "+localPath.get(i).type);
    		   }
    		   System.out.println(dist);
    		   isVisited[stringtoint.get(s)]=false;
    		   dist=0;
    		   return;
    	   }
    	 
    	   
    	   for(int i=0;i<(adjvertices.get(s)).size();i++) {
    		  if(isVisited[stringtoint.get(adjvertices.get(s).get(i).name)]==false) {
    			  node1 n2=new node1(adjvertices.get(s).get(i).name,adjvertices.get(s).get(i).type);
    			  localPath.add(n2);
    			 dist+=adjvertices.get(s).get(i).distance;

    			  printAllPaths1(adjvertices.get(s).get(i).name,d,isVisited,localPath);
    			  dist-=adjvertices.get(s).get(i).distance;
    			  localPath.remove(n2);
    			  
    			  
    	   }
    	   }
    	   isVisited[stringtoint.get(s)]=false;
    	   
    }
    public static boolean isConnected(graph g, String src, String dest,
			boolean[] discovered, Queue<String> path)
{
// mark current node as discovered
discovered[g.stringtoint.get(src)] = true;

// include current node in the path
path.add(src);

// if destination vertex is found
if (src == dest) {
return true;
}

// do for every edge (src -> i)
for (int i=0;i<g.adjvertices.get(src).size();i++)
{
// u is not discovered
if (!discovered[g.stringtoint.get(g.adjvertices.get(src).get(i).name)])
{
	// return true if destination is found
	if (isConnected(g, g.adjvertices.get(src).get(i).name, dest, discovered, path)) {
		return true;
	}
}
}

// backtrack: remove current node from the path
path.poll();

// return false if destination vertex is not reachable from src
return false;
}


}
public class mysqlaccess {
	
	public void abc(){
		int counter=0;
		graph g=new graph();
		String sql="SELECT miniproject.brts.bname,miniproject.brts.bxcod,miniproject.brts.bycod,miniproject.brtsroutes.blid,miniproject.brtsroutes.type FROM miniproject.brtsroutes,miniproject.brts where miniproject.brtsroutes.SBID=miniproject.brts.bid or miniproject.brtsroutes.DBID=miniproject.brts.bid LIMIT 0, 1000";
		String sql1="SELECT miniproject.amts.aname,miniproject.amts.axcod,miniproject.amts.aycod,miniproject.amtsroutes.alid,miniproject.amtsroutes.type FROM miniproject.amtsroutes,miniproject.amts where miniproject.amtsroutes.said=miniproject.amts.aid or miniproject.amtsroutes.daid=miniproject.amts.aid LIMIT 0, 1000";
		
		String url="jdbc:mysql://localhost:3306/miniproject";
		String username="root";
		String pwrd="Mehta1010";
	try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,username,pwrd);
	PreparedStatement st = con.prepareStatement(sql);
	PreparedStatement st1 = con.prepareStatement(sql1);
   ResultSet rs=st.executeQuery();
   ResultSet rs1=st1.executeQuery();
   while(rs.next()) {
	 node a=new node(rs.getInt(4),rs.getString(1),rs.getDouble(2),rs.getDouble(3),rs.getString(5));
	  if(g.stringtoint.get(rs.getString(1))==null){
		  counter++;
		  g.stringtoint.put(rs.getString(1),counter);
	  }
	  
	  rs.next();
	  node b=new node(rs.getInt(4),rs.getString(1),rs.getDouble(2),rs.getDouble(3),rs.getString(5));
	  if(g.stringtoint.get(rs.getString(1))==null){
		  counter++;
		  g.stringtoint.put(rs.getString(1),counter);
	  }
	  
	  
	  g.addNode(a.name);
	  g.addEdge(a, b);
   }
   while(rs1.next()) {
		 node c=new node(rs1.getInt(4),rs1.getString(1),rs1.getDouble(2),rs1.getDouble(3),rs1.getString(5));
		  if(g.stringtoint.get(rs1.getString(1))==null){
			  counter++;
			  g.stringtoint.put(rs1.getString(1),counter);
		  }
		   
		 
		 rs1.next();
		  node d=new node(rs1.getInt(4),rs1.getString(1),rs1.getDouble(2),rs1.getDouble(3),rs1.getString(5));
		  if(g.stringtoint.get(rs1.getString(1))==null){
			  counter++;
			  g.stringtoint.put(rs1.getString(1),counter);
		  }
		  
		  
		  g.addNode(c.name);
		  g.addEdge(c, d);
	   }
   boolean discovered[]=new boolean[counter+10];
   Queue<String>path=new ArrayDeque();
   String src="RTO CIRCLE";
   String dest="RANIP";
   /*if (g.isConnected(g, src, dest, discovered, path))
	{
		System.out.println("Path exists from vertex " + src + " to vertex " + dest);
		System.out.println("The complete path is: " + path);
	}
	else {
		System.out.println("No path exists between vertices " + src + " and " + dest);
	}*/
   
   g.printallpaths("IIM","NEHRU BRIDGE");
   //g.p("ASTODIA CHAKLA");
   //g.print();
  rs.close();
  st.close();
  con.close();
	

	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}
	//jdbc:mysql://127.0.0.1:3306/?user=root
}
