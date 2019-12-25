
package demo;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;
class filter{
	node1 a[];
double distance;
int hops;
filter(node1[] a,double distance,int hops){
this.a=a;
this.distance=distance;
this.hops=hops;

}
}
class node1{
String name;
String type;
double xcod;
double ycod;
double dist;
int line;
node1(String name,String type,double xcod,double ycod,double dist,int line){
this.name=name;
this.type=type;
this.xcod=xcod;
this.ycod=ycod;
this.dist=dist;
this.line=line;
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
    ArrayList<filter> filtered;
    ArrayList<filter> w;
   graph(){

  adjvertices=new HashMap<String,List<node>>();
   stringtoint=new HashMap<String,Integer>();
   filtered=new ArrayList<filter>();
   }
  
   void addNode(String a) {
   adjvertices.putIfAbsent(a, new ArrayList<>());
}

double deg2rad( double deg) {
  return deg * (Math.PI/180);
}
double dist(node source,node destination) {
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
return d;
}
double distance(node1 source,node1 destination) {
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
return d;
}
   void addEdge(node source,node destination ) {
     
 destination.distance=dist(source,destination);
  adjvertices.get(source.name).add(destination);
   }
   void print() {
  /*for (String s : adjvertices.keySet()) {
  for (node n : adjvertices.get(s)) {
     System.out.println("key :" + s + " value: " + n.name+" "+n.distance );
  }
  if(adjvertices.get(s)==null) {
  System.out.println("Empty" + s);
  }
  }
   
  for(int i=0;i<adjvertices.get("Kankaria Telephone Exchange").size();i++) {
  System.out.println(adjvertices.get("Kankaria Telephone Exchange").get(i).name);
  }*/
  System.out.println(filtered.size());
   }
  //System.out.println(stringtoint.get("")+"  jnjcn");
  /*for(String s: stringtoint.keySet()) {
  if(stringtoint.get(s)==null) {
  System.out.println(s);
  }
 System.out.println(s+" "+stringtoint.get(s));
  }
   } */
   void printallpaths(String s,String d,HashSet<String> brtscontains) {
	 w=new ArrayList<filter>();
  boolean isVisited[]=new boolean[200];
 for(int j=0;j<200;j++) {
 isVisited[j]=false;
 }
 node1 n1;
 ArrayList<node1> pathList=new ArrayList<>();
  if(brtscontains.contains(s)) {
   n1=new node1(s,"BRTS",0,0,0,0);
  }
  else {
   n1=new node1(s,"AMTS",0,0,0,0);
  }
  pathList.add(n1);
  printAllPaths1(s, d, isVisited, pathList);
  /*for(int w=0;w<pathList.size();w++) {
  System.out.println(pathList.get(w).name +"aaaaa"+ pathList.get(w).dist);
  }*/
   }
   void p(String s) {
  for(int i=0;i<(adjvertices.get(s)).size();i++) {
      System.out.println(adjvertices.get(s).get(i).name);  
  }
   }
    void printAllPaths1(String s, String d, boolean[] isVisited, List<node1> localPath) {
      isVisited[stringtoint.get(s)]=true;
   
     
      int count=0;
      if(s.equals(d)) {
      node1 l[]=new node1[localPath.size()];
      for(int i=1;i<localPath.size();i++) {
      dist+=localPath.get(i).dist;
     
      }
      for(int i=0;i<localPath.size()-1;i++) {
     
    if(localPath.get(i).type.equals(localPath.get(i+1).type)) {
    //System.out.println(localPath.get(i).name+" "+localPath.get(i).type);
    }
    else {
    count++;
   // System.out.println("Walk  "+ localPath.get(i+1).dist + " "+localPath.get(i+1).name);
    }
    
   
      }
      for(int w=0;w<localPath.size();w++) {
    	l[w]=localPath.get(w); 
      }
 
      filter f=new filter(l,dist,count);
      filtered.add(f);
     
    
      isVisited[stringtoint.get(s)]=false;
     dist=0;
      return;
      }
      if(adjvertices.get(s)==null) {
      dist=0;      
      return;
       }
   
     
      for(int i=0;i<(adjvertices.get(s)).size();i++) {
     if(isVisited[stringtoint.get(adjvertices.get(s).get(i).name)]==false) {
     node1 n2=new node1(adjvertices.get(s).get(i).name,adjvertices.get(s).get(i).type,adjvertices.get(s).get(i).xcod,adjvertices.get(s).get(i).ycod,adjvertices.get(s).get(i).distance,adjvertices.get(s).get(i).line);
     localPath.add(n2);
   

     printAllPaths1(adjvertices.get(s).get(i).name,d,isVisited,localPath);
     
     localPath.remove(n2);
     
     
      }
      }
      isVisited[stringtoint.get(s)]=false;
     
    }
    void l() {
    	Collections.sort(filtered,new Sorts());
    	if(filtered.size()<5) {
    		
    		for(int i=0;i<filtered.size();i++) {
    			filtered.get(i).a[0].line=filtered.get(i).a[1].line;
    			for(int j=0;j<filtered.get(i).a.length-1;j++) {
    	    		  if(filtered.get(i).a[j].type.equals(filtered.get(i).a[j+1].type)) {
    	    			  System.out.print(filtered.get(i).a[j].name + " "+"("+filtered.get(i).a[j].type+" " +filtered.get(i).a[j].line+")" +" -->  ");
    	    		  }
    	    		  else {
    	    			  System.out.print("Walking to"+ " "+filtered.get(i).a[j+1].name + ", "+filtered.get(i).a[j+1].dist+"  away"+" --> ");
    	    		  }
    	    		}
    	    		 System.out.print(filtered.get(i).a[filtered.get(i).a.length-1].name + " "+"("+filtered.get(i).a[filtered.get(i).a.length-1].type+" "+filtered.get(i).a[filtered.get(i).a.length-1].line+" "+ ")");
    	    		 System.out.println();
    	    		 System.out.println("Total Distance travelled is"+"  "+ filtered.get(i).distance);
    	    		 System.out.println();
    	    		 System.out.println("Total hops made are"+" "+filtered.get(i).hops);
    	    		 System.out.println();
    	}
    	}
    	else {
    	for(int i=0;i<5;i++) {
    		filtered.get(i).a[0].line=filtered.get(i).a[1].line;
    		for(int j=0;j<filtered.get(i).a.length-1;j++) {
    		  if(filtered.get(i).a[j].type.equals(filtered.get(i).a[j+1].type)) {
    			  System.out.print(filtered.get(i).a[j].name + " "+"("+filtered.get(i).a[j].type+" " +filtered.get(i).a[j].line+")" +" -->  ");
    		  }
    		  else {
    			  System.out.print("Walking to"+ " "+filtered.get(i).a[j+1].name + ", "+filtered.get(i).a[j+1].dist+"  away"+" --> ");
    		  }
    		}
    		 System.out.print(filtered.get(i).a[filtered.get(i).a.length-1].name + " "+"("+filtered.get(i).a[filtered.get(i).a.length-1].type+" "+filtered.get(i).a[filtered.get(i).a.length-1].line+" "+ ")");
    		 System.out.println();
    		 System.out.println("Total Distance travelled is"+"  "+ filtered.get(i).distance);
    		 System.out.println();
    		 System.out.println("Total hops made are"+" "+filtered.get(i).hops);
    		 System.out.println();
    	  }
    	}
    }
    void f() {
    	Collections.sort(filtered,new Sortbyhops());
    	if(filtered.size()<5) {
    		for(int i=0;i<filtered.size();i++) {
    			filtered.get(i).a[0].line=filtered.get(i).a[1].line;
        		for(int j=0;j<filtered.get(i).a.length-1;j++) {
          		  if(filtered.get(i).a[j].type.equals(filtered.get(i).a[j+1].type)) {
          			  System.out.print(filtered.get(i).a[j].name + " "+"("+filtered.get(i).a[j].type+" " +filtered.get(i).a[j].line+")" +" -->  ");
          		  }
          		  else {
          			  System.out.print("Walking to"+ " "+filtered.get(i).a[j+1].name + ", "+filtered.get(i).a[j+1].dist+"  away"+" --> ");
          		  }
          		}
          		 System.out.print(filtered.get(i).a[filtered.get(i).a.length-1].name + " "+"("+filtered.get(i).a[filtered.get(i).a.length-1].type+" "+filtered.get(i).a[filtered.get(i).a.length-1].line+" "+ ")");
          		 System.out.println();
          		 System.out.println("Total Distance travelled is"+"  "+ filtered.get(i).distance);
          		 System.out.println();
        	  }
    	}
    	else {
    	for(int i=0;i<5;i++) {
    		filtered.get(i).a[0].line=filtered.get(i).a[1].line;
    		for(int j=0;j<filtered.get(i).a.length-1;j++) {
      		  if(filtered.get(i).a[j].type.equals(filtered.get(i).a[j+1].type)) {
      			  System.out.print(filtered.get(i).a[j].name + " "+"("+filtered.get(i).a[j].type+" " +filtered.get(i).a[j].line+")" +" -->  ");
      		  }
      		  else {
      			  System.out.print("Walking to"+ " "+filtered.get(i).a[j+1].name + ", "+filtered.get(i).a[j+1].dist+"  away"+" --> ");
      		  }
      		}
      		 System.out.print(filtered.get(i).a[filtered.get(i).a.length-1].name + " "+"("+filtered.get(i).a[filtered.get(i).a.length-1].type+" "+filtered.get(i).a[filtered.get(i).a.length-1].line+" "+ ")");
      		 System.out.println();
      		 System.out.println("Total Distance travelled is"+"  "+ filtered.get(i).distance);
      		 System.out.println();
      	
    	  }
    	}
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
class Sorts implements Comparator<filter>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(filter a, filter b)
    {
       return a.hops-b.hops;
    }
}
class Sortbyhops implements Comparator<filter>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(filter a, filter b)
    {
       if(a.distance<b.distance) {
      return -1;
       }
       else {
      return 1;
       }
    }
}
public class mysqlaccess {

public void abc(){
int counter=0,cnt=0,bb=0;
Scanner sc=new Scanner(System.in);
graph g=new graph();
String sql="SELECT miniproject.brtsmain.bname,miniproject.brtsmain.bxcod,miniproject.brtsmain.bycod,miniproject.brtsmainroutes.blid,miniproject.brtsmainroutes.type FROM miniproject.brtsmainroutes,miniproject.brtsmain where miniproject.brtsmainroutes.SBID=miniproject.brtsmain.bid or miniproject.brtsmainroutes.DBID=miniproject.brtsmain.bid LIMIT 0, 1000";
String sql1="SELECT miniproject.amts.aname,miniproject.amts.axcod,miniproject.amts.aycod,miniproject.amtsroutes.alid,miniproject.amtsroutes.type FROM miniproject.amtsroutes,miniproject.amts where miniproject.amtsroutes.said=miniproject.amts.aid or miniproject.amtsroutes.daid=miniproject.amts.aid LIMIT 0, 1000";
String sql2="SELECT * FROM miniproject.brtsmainroutes";
String url="jdbc:mysql://localhost:3306/miniproject";
String username="root";
String pwrd="Mehta1010";
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(url,username,pwrd);
PreparedStatement st = con.prepareStatement(sql);
PreparedStatement st1 = con.prepareStatement(sql1);
PreparedStatement st2 = con.prepareStatement(sql2);
   ResultSet rs=st.executeQuery();
   ResultSet rs1=st1.executeQuery();
   ResultSet rs2=st2.executeQuery();
   ArrayList<node> allamts=new ArrayList<node>();
   ArrayList<node> allbrts=new ArrayList<node>();
   HashSet<String> brtscontains=new HashSet<String>();
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

 allamts.add(c);
  }
 
 
   while(rs2.next()) {
  int flag=0;
  int flag1=0;
  int a=rs2.getInt(1);
  int b=rs2.getInt(2);
  int c=rs2.getInt(3);
  String d=rs2.getString(4);
String sql3=  "SELECT * FROM miniproject.brtsmain where bid=?";
PreparedStatement st3 = con.prepareStatement(sql3);
String s=Integer.toString(b);
st3.setString(1,s);
  ResultSet rs3=st3.executeQuery();
  String e="";
  double de=0,de1=0;
 
 
  while(rs3.next())
  {
  e=rs3.getString(2);
  de=rs3.getDouble(3);
  de1=rs3.getDouble(4);
  cnt=cnt+1;
  //System.out.println(cnt);
  //System.out.println(e+" "+g.stringtoint.get(e));
  if(g.stringtoint.get(e)==null){
 counter++;
 flag=1;
 g.stringtoint.put(e,counter);
//  System.out.println("Hh"+" "+counter);
 // System.out.println("rs3"+" "+e+" "+g.stringtoint.get(e));
 }
  /*else {
  System.out.println("repeat"+" "+e+" "+g.stringtoint.get(e));
  }*/
  rs3.next();
  }
  node q=new node(a,e,de,de1,d);
  if(brtscontains.contains(q.name)==false) {
    brtscontains.add(q.name);
  allbrts.add(q);
  }
  //cnt=0;
String sql4=  "SELECT * FROM miniproject.brtsmain where bid=?";
PreparedStatement st4 = con.prepareStatement(sql4);
s=Integer.toString(c);
st4.setString(1,s);
  ResultSet rs4=st4.executeQuery();
  String e1="";
  double dw1=0,de11=0;
  while(rs4.next()) {
  e1=rs4.getString(2);
  dw1=rs4.getDouble(3);
  de11=rs4.getDouble(4);
  cnt=cnt+1;
  //System.out.println(cnt);
  //System.out.println(e1+" "+g.stringtoint.get(e1));
  if(g.stringtoint.get(e1)==null){
 counter++;
 flag1=1;
 g.stringtoint.put(e1,counter);
 //System.out.println("dd"+" "+counter);
//   System.out.println("r4"+" "+e1+" "+g.stringtoint.get(e1));
 }
  rs4.next();
  }
  node w=new node(a,e1,dw1,de11,d);
  //System.out.println(q.name+" "+w.name);
  //System.out.println(w);
  if(flag==1 || flag1==1) {
  g.addNode(q.name);
  g.addEdge(q,w);
  for(int i=0;i<allamts.size();i++) {
  if(g.dist(q,allamts.get(i))<0.50) {
  g.addEdge(q, allamts.get(i));
  }
  }
  }
  //System.out.println(b+" "+c);
      //rs2.next();  
   }
   /*while(rs.next()) {
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
   }*/

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
   for(int i=0;i<allamts.size();i++) {
  for(int j=0;j<allbrts.size();j++) {
  if(g.dist(allamts.get(i),allbrts.get(j))<0.50) {
  g.addEdge(allamts.get(i), allbrts.get(j));
  }
  }
   }
  /* for(int i=0;i<allbrts.size();i++) {
  System.out.println(allbrts.get(i).name+"  "+i);
   }*/
   System.out.println("                            WELCOME!!                    ");
   System.out.println("We help you out in finding the most optimal route for your journey");
   System.out.println("Enter your source station");
   String e="";
    e+=sc.nextLine();
   System.out.println("Enter your destination station");
   String r="";
    r+=sc.nextLine();
   g.printallpaths(e,r,brtscontains);
   System.out.println("Preferences:");
   System.out.println("1)  Minimum change in hops ");
   System.out.println("2)  Minimum distance");
   int l=sc.nextInt();
if(l==1) {
	g.l();
}
if(l==2) {   
g.f();
}
   //System.out.println(dist);.name+" "+localPath.get(localPath.size()-1).type);
   //System.out.println(dist);
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
	
