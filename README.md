# Best Fit Fulfillment
-------------------------

## Tech Stack Used
---------------- 
Java 8
REST
SpringBoot
Gradle

## How to run
-----------
- Extract the zip or clone/download from [github](https://github.com/asircar/BestFitFulfillment)

- Run the following commands in ```/BestFitFulfillment```  to build and start the service.

1.  [LINUX]/[macOS] ```./gradlew build```

    [WINDOWS] ```gradlew.bat build```

2.  [LINUX]/[macOS] ```./gradlew bootRun```

    [WINDOWS] ```gradlew.bat bootRun```

- Access the endpoint, default port of the service is ```8080```. You can change this port in ```resources/application.properties```.

## Supported Endpoints
-----------
[POST] Get generated consignments for a set of supplies orders

http://localhost:8080/fulfill

## Input/Output
------------
### Request

```json
{
	"orders":
	[
		{
			"destination":"Surat",
			"orderEntries": {
				"product-1":14.0,
				"product-2":10.0,
				"product-3":3.0
			}
		},
		{
			"destination":"Noida",
			"orderEntries": {
				"product-2":6.0,
				"product-3":1.0,
				"product-4":5.0
			}
		}
	],
	"warehouses":
	[
		{
			"location":"Goa",
			"inventory": {
				"product-1":10.0,
				"product-2":12.0,
				"product-3":5.0
			}
		},
		{
			"location":"Mumbai",
			"inventory": {
				"product-1":4.0,
				"product-2":4.0,
				"product-3":4.0
			}
		},
		{
			"location":"Moradabad",
			"inventory": {
				"product-1":4.0,
				"product-4":5.0
			}
		}
	]
}
```

### Response

```json
{
    "consignments": [
        {
            "location": "Goa",
            "destination": "Surat",
            "sku": "product-1",
            "amount": 10
        },
        {
            "location": "Goa",
            "destination": "Surat",
            "sku": "product-2",
            "amount": 10
        },
        {
            "location": "Goa",
            "destination": "Surat",
            "sku": "product-3",
            "amount": 3
        },
        {
            "location": "Mumbai",
            "destination": "Surat",
            "sku": "product-1",
            "amount": 4
        },
        {
            "location": "Mumbai",
            "destination": "Noida",
            "sku": "product-2",
            "amount": 4
        },
        {
            "location": "Mumbai",
            "destination": "Noida",
            "sku": "product-3",
            "amount": 1
        },
        {
            "location": "Moradabad",
            "destination": "Noida",
            "sku": "product-4",
            "amount": 5
        },
        {
            "location": "Goa",
            "destination": "Noida",
            "sku": "product-2",
            "amount": 2
        }
    ]
}
```
 
 ## Algorithm 
 ---------
 1. The problem statement was to achieve the minimum sets of location-destination combinations to fulfill the orders
 2. To achieve the desired output, the best way ahead was to fulfill an order to the max using the best fit warehouse
 3. If the order is not fulfilled completely then move to the next fit warehouse
 4. The best fit warehouse has to be chosen based on how good it is to fulfill the order based on order items and their quantities
 5. Warehouses are sorted in best fit order
 6. Order will be first fulfilled using warehouses sorted on their best fit as calculated above
 
 ## Code Explanation
 ----------------
 - Data structure chosen for order is Order.java
 - Data structure for Warehouse is Warehouse.java
 - Data structure for output is Consignment.java
 - The data structures are kept in the data package
 - The above data structures are in strict coherence to the given problem statement
 - The orders and warehouses data is expected as JSON input and the output is also a JSON
 - Input maps to the class FulfillmentRequest.java
 - Output maps to the class FulfillmentResponse.java
 - The endpoint invokes the fulfill method of FulfillmentService
 - Orders are iterated and each order is passed along with the list of warehouses to the calculateFitness method
 - calculateFitness method iterates over each warehouse and sends the order and warehouse to calculateFitnessOfWarehouseForOrder
 - calculateFitnessOfWarehouseForOrder calculates the percentage of the order fulfilled by the particular warehouse and updates the perecentFulfilled attribute in Warehouse class
 - The warehouses are then sorted based on descending order of percentFulfilled
 - The order along with the sorted warehouse is then passed to the fillOrder method
 - fillOrder method fulfills the order and updates the warehouse inventory along with population of the response object which is used to display the output
 
 ## Assumptions
 -----------
 1. The input/output has been prepared in strict accordance to the problem statement
 2. Factors such as distance between location and destination, priority of location etc. have been kept out as there was no mention of them in the problem statement
 3. The code assumes that the input orders are already collated for particular locations
 4. The code assumes that it will fulfill the orders regardless of whether they get fulfilled fully or partially
 5. The code does not use any database or data repository, it creates data on the fly and prepares the output for display
 
 ## How to do this in Hybris
 ------------------------
 Doing this in Hybris will not require any kind of customization. It will work out of the box with just configurations as Hybris provides the support of Fitness Evaluator in warehousing extension. To try this out perform the following steps
 
 1. Install the latest Hybris B2C accelerator along with the oms module
 2. Open backoffice and click on "Sourcing Configuration" under Order Management
 3. Double Click on the default "Hybris_OMS_Config", it will open up the respective SourcingConfig
 4. Hybris by default provides 4 weight factors - distance, allocation, priority and source
 5. Mark the "Allocation Weight Factor" as 100, rest all as 0.
 6. This will make sure that whenever an order is fulfilled the best suited warehouse to fulfill that order completely is picked, hence reducing the number of destination-location combinations over time.
