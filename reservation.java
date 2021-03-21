public class reservation {
 
// Attributes

   private char RoomType ;
   private String inDate ;
   private String outDate ;
   private double price ;
   private String resNum;
   private String resState ;
   public static int resCount=0 ;

// Methods

   public reservation (char rType, String indate, String outdate , long card ){
   
      RoomType = rType;
      inDate=indate;
      outDate=outdate;
      resCount++;
      boolean isValidCard=verfyCard(card);
   
      if (isValidCard){
      
         price=CalculatePrice();
         resNum= GenerateReservationNumber();
      
      }
      else{/*{{{*/
         price=0;
         resNum= "#####";}
   }// end constructor

   public void SetRoom(char roomType){
      RoomType =roomType  ; }

   public void  Setprice(double newPrice ){
      price= newPrice;}

   public char getRoomType(){
      return RoomType ;}

   public String  getcheckIn(){
      return inDate ;}

   public double getprice(){
      return price ;}

   public String getState(){
      return resState; }

   public String getresNum(){
      return resNum ;}

   public boolean verfyCard (long Card){
   
   //end of declaration 
      String newCard=""+Card;
   // ^ Convert Card to String 
      int sum=0,doubleOfOdd,secondDigit;
      for(int count=0;count<newCard.length();count+=2)
         sum+=(Integer.parseInt(newCard.charAt(count)+""));
      for(int count=1;count<newCard.length();count+=2){
         doubleOfOdd=Integer.parseInt(newCard.charAt(count)+"");
         doubleOfOdd*=2;
         if(doubleOfOdd<10)
            sum+=doubleOfOdd;
         else{
            secondDigit=doubleOfOdd%10;
            doubleOfOdd/=10;
            sum+=(doubleOfOdd+secondDigit);
         }
      }
     
     //? //System.out.print(sum);
      if(sum%10==0 &&newCard.length()==10){
         resState="confirmed";
         return true; }
      
      
      else {
         resState="unconfirmed";
      
         return false ; }
   
   }

   public String GenerateReservationNumber (){
      int num=0 , num2 = 0 ,twoDigitnum = 0;
      boolean isTwoDigit=false;
      do{
         num = (int)(Math.random()*100);
      }
      while (num <65 || num >90);
   
      char letter = (char)num ;
      char lower=Character.toLowerCase(letter);
   
      do{
         num2 = (int)(Math.random()*100);
      }
      while (num2 <65 || num2 >90);
      char upper = (char)num2 ;
   
      while (!isTwoDigit){
         twoDigitnum=(int)(Math.random()*100);
         if (twoDigitnum>=10 && twoDigitnum<=99)
            isTwoDigit=true;
      }
      resNum="" + resCount+lower + twoDigitnum + upper;
   
      return resNum; }// end GenerateReservationNumber

   public double CalculatePrice(){
      int nights,inDay,outDay;
   //String inDayStr, outDayStr ;
   
      inDay=Integer.parseInt(inDate.substring(0,inDate.indexOf("-")));
      outDay=Integer.parseInt(outDate.substring(0,outDate.indexOf("-")));
   
      if(outDay==inDay)
         nights=1;
      else
         nights = (outDay-inDay);
      double cost =0;
      switch(RoomType){
         case 'S': cost= 150;
            break;
         case 'T':cost= 220; 
            break;
         case 'F': cost=400;
            break;
         default :cost =0;
      }
      price=nights*cost;
      return price;
   }//end CalculatePrice

   public boolean CancelReservation(){
      String state = resState.toLowerCase();
      if (resState.equals("confirmed") || resState.equals("unconfirmed") ){
         resState = "canceled";
         return true ;}
      else 
      { System.out.println("The room is already canceled"); 
         return false;} 
   } // end CancelReservation 


   public void Print(){
      System.out.printf("Reservation number : %-10s\t",resNum);
      System.out.printf("Reservation state :%-10s%n",resState);
      System.out.printf("Room type : %-18C\t",RoomType);
      System.out.printf("Check in Date : %-10s%n",inDate);
      System.out.printf("Price: %-10.2f%n%n",price );} // end Print

} // end reservation