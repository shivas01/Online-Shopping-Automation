package projectResource;

import java.io.IOException;

public class Validation extends DriverSetup{
	
	public void validation(String productPrice1,String productPrice2,String cartSubTotal) throws IOException {
		//converting productPrice1 to integer
	int price1=Integer.parseInt(productPrice1.replace(",",""));
	
	//converting productPrice2 to integer
	int price2=Integer.parseInt(productPrice2.replace(",",""));
	
	//converting cartSubTotal to integer
	int totalPrice=Integer.parseInt(cartSubTotal.replace(",",""));
	
	//validating cart price
	if(price1+price2==totalPrice) {
		System.out.println(productPrice1+" + "+productPrice2+" = "+cartSubTotal);
		System.out.println("Automation Successfull");
		
		//sending data to utility class to store in excel
		Utility.ExcelData(price1,price2,totalPrice);
	}
	else {
		System.out.println(productPrice1+" + "+productPrice2+" != "+cartSubTotal);
		

		System.out.println("Automation is not Successfull");
		
	}
	}
	
}



















