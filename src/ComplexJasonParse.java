import org.testng.Assert;

import files.JasonValuefetch;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJasonParse {
	
	public static void main(String[] args) {
		
		JsonPath js = JasonValuefetch.jasonMethod(Payload.coursePrice());
		
		
		System.out.println("Print No of courses returned by API "+js.getInt("courses.size()"));
		System.out.println("--------------------------------------------------");
		System.out.println("Print Purchase Amount "+js.getInt("dashboard.purchaseAmount"));
		System.out.println("--------------------------------------------------");
		System.out.println("Print Title of the first course "+js.get("courses[0].title"));
		
		System.out.println("--------------------------------------------------");
		System.out.println("Print All course titles and their respective Prices");
		for(int i=0;i<js.getInt("courses.size()");i++)
		{
			
			System.out.println("course Title is "+js.get("courses["+i+"].title")+" and its price is "+js.get("courses["+i+"].price"));
		}
		System.out.println("--------------------------------------------------");
		
		System.out.println("Print no of copies sold by RPA Course");
		
		for(int i=0;i<js.getInt("courses.size()");i++)
		{
				
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
					{
						
							System.out.println("no of copies sold by RPA Course "+js.get("courses["+i+"].copies"));
							break;
					}
		
		}
		
		System.out.println("--------------------------------------------------");
		
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		
		int sum=0;
		
		for(int i=0;i<js.getInt("courses.size()");i++)
		{
		
			sum=sum+js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
			
		}
		System.out.println(sum);
		
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
		
		System.out.println("--------------------------------------------------");
		
		
	}

}
