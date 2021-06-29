/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RiderProivder;

/**
 *
 * @author user
 */
import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
public class Car extends Vehicle{
    private String carName;
    private int totalCars;
    PreparedStatement pst = null;
    ResultSet rst = null;
    Car()
    {
        
    }
    Car(String Model,String Version,String Color,String plateNo,String registrationDate,String expirationDate, String carName)
    {
        super(Model,Version,Color,plateNo,registrationDate,expirationDate);
        this.carName=carName;
    }
    Car(int totalCars)
    {
        this.totalCars=totalCars;
    }
    public void addCar()
    {
        
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
 
        String sql="Insert into CarT (Model,Version,Color,PlateNo,RegistrationDate,ExpirationDate,CarName, LastRepair) values ' "
                    + super.getModel()+ "','"
                    + super.getVersion()+ "','"
                    + super.getColor()+ "','"
                    + super.getPlateNo()+ "','"
                    + super.getregistrationDate()+ "','"
                    + super.getExpirationDate()+ "','"
                    + getCarName()+ "','"
                    + super.getregistrationDate()+ "'";
        int flag=conn.InsertUpdateDelete(sql);
                 
           if(flag==1){
               JOptionPane.showMessageDialog(null, "You Have Successfully Added "+getCarName()+" "+super.getModel());
           }
           else{
               JOptionPane.showMessageDialog(null, "Insertion Failed");
           }
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);  
        }
    }
    public void editCar(String id, String model, String version, String color, String plate)
    {
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Update CarT SET Model='"+model+"', Version='"+version+"', Color='"+color+"', PlateNo='"+plate+"' WHERE CarId='"+id+"'";
                    
        int flag=conn.InsertUpdateDelete(sql);
                 
           if(flag==1){
               JOptionPane.showMessageDialog(null, "Car Details updated");
           }
           else{
               JOptionPane.showMessageDialog(null, "EditFailed");
           }
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);  
        }
    }
    public int assignCar()
    {
        
        
        int newAss=1;
        int ass=5;
        int i=1;
        int total=0;
               try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="SELECT TOP 1 CarId FROM CarT ORDER BY CarId DESC"; 
        rst= conn.GetData(sql);      
          
         while(rst.next()){
           total=rst.getInt("CarId");
           
                }
       
        
//           conn.CloseConnection();
        
        
            conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCouldn't Select Last CarId");  
        }
        total=total+1;
        while(i<total)
        { 
            
               try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select Assigned from CarT where CarId = '"+ i + "'"; 
        rst= conn.GetData(sql);      
          
         while(rst.next()){
           ass=rst.getInt("Assigned");
           
                }
       
        
//           conn.CloseConnection();
        
        
            conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
    
               
        
               
               
               if(ass==0)
               {
                   break;
               }
           
               i++;
        }
        DbConnection conn = new DbConnection();
        int flag;
        try{
        conn.OpenConnection();
        String sql = "UPDATE CarT SET Assigned = '"+ newAss +"' where CarId = '"+i+ "'";
       
        flag = conn.InsertUpdateDelete(sql);
           if(flag == 1){
               JOptionPane.showMessageDialog(null, "Car's Availablity Updated ");
           }
           else{
                JOptionPane.showMessageDialog(null, "Car's Availability COuldn't Be Updated " );
           }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "UpdateBill Query Failed");
        }
        return i;
               
    }
    
    public String getCarName()
    {
        return carName;
    }
     
    public String getRModel(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select Model from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("Model");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public String getRVersion(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select Version from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("Version");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public String getRColor(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select Color from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("Color");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public String getRPlateNo(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select PlateNo from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("PlateNo");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public String getRRDate(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select RegistrationDate from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("RegistrationDate");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public String getEDate(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select ExpirationDate from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("ExpirationDate");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public String getCarName(int id)
    {
        String ass=null;
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        String sql="Select CarName from CarT where CarId = '"+ id + "'";
        rst= conn.GetData(sql);      
          
         while(rst.next()){
            ass=rst.getString("CarName");
           
                }
       
        
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"\nCheck Car ID Error");  
        }
        return ass;
    }
    public void deleteCar(int id )
     {
     
    
     
     try
        {
        DbConnection con = new DbConnection();
        con.OpenConnection();
        String sql = "DELETE from CarT WHERE CarId = '"+id+"'";
        
         
        int flagg=con.InsertUpdateDelete(sql);
                 
           if(flagg==1){
               JOptionPane.showMessageDialog(null, "Car Deleted");
           }
           else{
               JOptionPane.showMessageDialog(null, "Please Enter A Valid CarId");
           }
           con.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);  
        }
     }
     public ResultSet getCarsData()
     {
         DbConnection conn = new DbConnection();
        try{
            conn.OpenConnection();
            String select_sql = "Select CarId,Model,PlateNo,CarName from CarT ";
            rst=conn.GetData(select_sql);
            do{
                return rst;
            } while(rst.next());
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "GetCarData Query Failed");
        }
        conn.CloseConnection();
        return null;
         
     }
     public ResultSet editCarsData()
     {
         DbConnection conn = new DbConnection();
        try{
            conn.OpenConnection();
            String select_sql = "Select CarId,Model,Version, Color, PlateNo,RegistrationDate,ExpirationDate, CarName from CarT ";
            rst=conn.GetData(select_sql);
            do{
                return rst;
            } while(rst.next());
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "GetCarData Query Failed");
        }
        conn.CloseConnection();
        return null;
         
     }
     public int FuelExpense(String plate)
     {
         
         int total=0;
         DbConnection conn = new DbConnection();
        try{
            conn.OpenConnection();
            String select_sql = "Select Bill from RideRealtime WHERE VehiclePlate='"+plate+"'";
            rst=conn.GetData(select_sql);
            while(rst.next())
            {
                total=rst.getInt("Bill");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "GetCarData Query Failed");
        }
        conn.CloseConnection();
        return total;
     }
     public ResultSet getRepairData()
     {
         DbConnection conn = new DbConnection();
        try{
            conn.OpenConnection();
            String select_sql = "Select CarId,Model,PlateNo,CarName, LastRepair from CarT ";
            rst=conn.GetData(select_sql);
            do{
                return rst;
            } while(rst.next());
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "GetCarData Query Failed");
        }
        conn.CloseConnection();
        return null;
         
     }
     public void editRepair(String plate)
    {
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        
        DateTime dt=new DateTime();
        String date1=dt.getDate();
        String sql="Update CarT SET LastRepair='"+date1+"' WHERE PlateNo='"+plate+"'";
                    
        int flag=conn.InsertUpdateDelete(sql);
                 
           if(flag==1){
               JOptionPane.showMessageDialog(null, "Repair date updated");
           }
           else{
               JOptionPane.showMessageDialog(null, "EditFailed");
           }
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);  
        }
    }
     public void addRepair(String plate, String Type)
    {
        
        try
        {
        DbConnection conn = new DbConnection();
        conn.OpenConnection();
        DateTime dt=new DateTime();
        String date1=dt.getDate();
        String sql="Insert into RepairHistory (Type, PlateNo, LastRepair) VALUES ('"+Type+"', '"+plate+"', '"+date1+"') ";
                    
        int flag=conn.InsertUpdateDelete(sql);
                 
           if(flag==1){
               JOptionPane.showMessageDialog(null, "You Have Successfully Added");}
           else{
               JOptionPane.showMessageDialog(null, "Insertion Failed");
           }
           conn.CloseConnection();
          }
        catch(Exception e){
          JOptionPane.showMessageDialog(null, e);  
        }
    }
     public ResultSet AllRepairData()
     {
         DbConnection conn = new DbConnection();
        try{
            conn.OpenConnection();
            String select_sql = "Select Type,PlateNo, LastRepair from RepairHistory";
            rst=conn.GetData(select_sql);
            do{
                return rst;
            } while(rst.next());
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "GetRepairHistory Query Failed");
        }
        conn.CloseConnection();
        return null;
         
     }
}
