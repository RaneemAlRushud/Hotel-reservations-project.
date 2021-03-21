import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
public class hotel {

   public static Scanner sc=new Scanner(System.in);
   public static final int MAX_SIZE=100;
   public static reservation[] rList=new reservation[MAX_SIZE];

   public static void main(String[] args) {
   
      String option;
      do {
         
         System.out.println("Welcome to Hotel system, please select your option (guest or clerk) or END to end the program");
         option=sc.next();
         option =  option.toLowerCase() ;
         logIn(option); }
      
      while(!option.equals("end"));
      
   }// end main

   public static void logIn(String typeOfUser){
   
      switch (typeOfUser){
      
         case "guest" :
          
            System.out.println("Guest Menu\nYou have the following options:\nAdd Reservation ");
            if (AddReservation()) {
               int choice2 =0 ;
               System.out.println("Reservation added succefully.. you can preform the follwing opreations on your reservation");
               do{ 
                  System.out.println("1- Cancel reservation \n2-Display reservation \n3- Exit");
                  choice2=sc.nextInt();
                  //while(choice2)
                  switch (choice2){
                     case 1: 
                        if(CancelMyReservation())
                           System.out.println("your reservation was cancelled sucssefully");
                        else System.out.println("your reservation already cancel ");
                     
                        break;
                     case 2:DisplayMyReservation();
                        break;
                     case 3 : 
                        sc.nextLine();
                        System.out.println ("Exit");
                        break ;
                     
                     default : 
                        System.out.println("Invalid choice ! ");}
               }while(choice2!=3);
            }// end  if (addReservation())
            else 
            {System.out.println("Sorry all reservations are booked ") ;}/* reservation NOT added*/ 
         
            break;// end  case (guest)
          
         case "clerk" : 
            int choice=0;
            do{
            
               System.out.println("\n1-Find reservation \n2-Display all confirmed reservations \n3-Modify reservation \n4-Cancel reservation \n5-Count the number of reservations \n6-Display all due reservations \n7-Exit");
               choice=sc.nextInt();
               String resNumber="";
               switch(choice){
                  case 1: 
                     sc.nextLine();
                     System.out.println("Enter the reservation number: ");//1
                     resNumber = sc.nextLine();
                     int index = Find(resNumber);
                  
                     if(index==-1)
                        System.out.println("No reservations with this number of reservation");
                     else
                        System.out.println("Rservation number is 1"+index);
                     break ;
                  case 2 : Display();//2
                     break ;
                  case 3: //3
                     sc.nextLine();
                     System.out.println("Enter the reservation number: ");
                     resNumber = sc.nextLine();
                  
                     if(Modify(resNumber))
                        System.out.println("Room has been modified"); 
                     else
                        System.out.println("No reservation found with this number");
                  
                     break ; 
                  case 4: 
                     sc.nextLine();
                     System.out.println("Enter the reservation number: ");
                     resNumber = sc.nextLine();
                     if(Cancel(resNumber))
                        System.out.println("reservation cancelled");//4
                     else System.out.println(" No reservation found with this number");/***/
                     break ;
                  case 5:    //5******
                  
                     sc.nextLine();
                     System.out.println("Enter the status of reservation: ");
                     String status = sc.nextLine();
                     int count = CountReservations(status);
                     if (count == 0) 
                        System.out.println("No reservations found with this status.");
                     else 
                        System.out.println("Number of " + status + " reservations is: " + count);
                                   
                     break ;
                  case 6:
                     DisplayDueReservations();//6
                     break ;
                  case 7 : //7
                     sc.nextLine();
                     System.out.println ("Exit");
                     break ;
                     
                  default : 
                     System.out.println("Invalid choice ! "); }
               
            }while(choice!=7  ); 
            break ;  // end (clerk)
          
         case "end" :
            break ;
          
         default :
            System.out.println("Invalid user Type, enter clerk or guest or end to log out \n ") ;
      
      } // end outer switch
   }// end logIn method 


   public static boolean AddReservation(){
   
      if(reservation.resCount>=MAX_SIZE){
         System.out.println("you can not longer add a reservation, the reservation book is full");
         return false;}
   
      char roomType;
      long cardNumber;
      String   checkIn, checkOut;
      int  inDay, outDay,month;
   
      System.out.println("Enter reservation information");
      do{ 
         System.out.println("Enter Room type (S for single - T for twin - F for family)");
         roomType=sc.next().toUpperCase().charAt(0);
      
      }while(roomType!='S' && roomType != 'T' && roomType!='F'); 
      
      System.out.println("Enter Credit Card number");
      //sc.nextLine();
      cardNumber=sc.nextLong();
      System.out.println("Enter check in month");
      month=sc.nextInt();
      while (month > 12 || month < 1) {
         System.out.println("Invalid check in month  please try again");
         month = sc.nextInt();
      
      }
      System.out.println("Enter check in day");
      inDay=sc.nextInt();
      while (inDay > 31 || inDay < 1) {
         System.out.println("Invalid check in day  please try again");
         inDay = sc.nextInt();
      
      }
      System.out.println("Enter check out day");
      outDay=sc.nextInt();
      while (outDay > 31 || outDay < 1 || outDay < inDay) {
         System.out.println("Invalid check out date, please try again");
         outDay = sc.nextInt();
      
      }
   
   
   //validation of roomtype and card long
   
      checkIn = new SimpleDateFormat("dd-MM").format(new Date(2019,month-1,inDay));
      checkOut = new SimpleDateFormat("dd-MM").format(new Date(2019,month-1,outDay));
   
      rList[reservation.resCount]=new reservation(roomType, checkIn, checkOut, cardNumber);
      return true;
   }


   public static boolean CancelMyReservation(){
   
      if(rList[reservation.resCount-1].CancelReservation()) 
         return true;
   
      return false; }// end  cancelMyReservation method 

   public static void DisplayMyReservation(){
   
      rList[reservation.resCount-1].Print(); }// end displayMyReservation method 

   public static int Find(String resNumber){
      int index;
      for(index=0 ; index<reservation.resCount ; index++) {
         if(rList[index].getresNum().toLowerCase().equals(resNumber.toLowerCase()))
            return index;}
   
      return -1;}// end find method 

   public static void Display(){
      int index;
      if(reservation.resCount==0)
         System.out.println("The reservation book is empty");
      else
         for(index=0;index<reservation.resCount;index++){
            if(rList[index].getState().toLowerCase().equals("confirmed"))/****/
               rList[index].Print(); }
               
   }// end display method 

   public static boolean Modify(String resNumber) {
      double updatePrice;
      char roomType;
      int index=Find(resNumber);
   
   
      if(index>-1 && rList[index].getState().toLowerCase().equals("confirmed")) {/****/
         System.out.println("Enter Room type S for single - T for twin - F for family)");
         roomType=sc.next().toUpperCase().charAt(0);
      
         rList[index].SetRoom(roomType);
      //updatePrice=rList[index].CalculatePrice();
         rList[index].CalculatePrice();
      //Setprice(updatePrice);
         return true;
      }// end outer if 
   // end for 
      return false;
   }// end modify method 

   public static boolean Cancel(String resNumber){
      int index;
      for(index=0;index<reservation.resCount;index++){
         if(rList[index].getresNum().toLowerCase().equals(resNumber.toLowerCase()))
            return rList[index].CancelReservation(); }
      return false;
   }// end cancel method 

   public static int CountReservations(String state){
      int count=0,index;
      for(index=0;index<reservation.resCount;index++){
         if(rList[index].getState().toLowerCase().equals(state.toLowerCase())){
            count++;}}
      return count; }// end countReservations method 


   public static void DisplayDueReservations(){
      int count=0;
      String date = new SimpleDateFormat("dd-MM").format(new Date());
      int day=Integer.parseInt(date.substring(0,date.indexOf('-')));
      int month=Integer.parseInt(date.substring(date.indexOf('-')+1));
   
      int dayCheck,monthCheck,index; String checkIn;
      for(index=0;index<reservation.resCount;index++){
         checkIn=rList[index].getcheckIn();
         dayCheck=Integer.parseInt(checkIn.substring(0,checkIn.indexOf('-')));
         monthCheck=Integer.parseInt(checkIn.substring(checkIn.indexOf('-')+1));
         if(monthCheck==month)
         { 
            if(dayCheck==day+1){
               rList[index].Print();
               count++;
            //if (no)
            }
         }}
      if (count == 0) {
         System.out.println("No due reservations for tomorrow");// end displayDueReservations method  
      }}
}// end hotel class