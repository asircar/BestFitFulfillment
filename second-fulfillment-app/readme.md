Fulfillment Rest Web Service
A web service used to fulfill orders and return the generated consignments.

Setup
Start web service
Run the following commands in BestFitFulfillment/second-fulfillment-app to start the service.

1.  [LINUX] ./gradlew build
    [WINDOWS] gradlew.bat build

2.  [LINUX] ./gradlew bootRun
    [WINDOWS] gradlew.bat bootRun

Default port of the service is 8080. You can change this port in resources/application.properties.

Supported Endpoints

[POST] Get generated consignments for a set of supplies orders
http://localhost:8080/fulfill

Request
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

Response
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


Developer Setup
Requirements
Java 8
Optional
Gradle - If you have gradle installed you can use the gradle command to build. If you do not have it installed you can use the distributed gradlew wrapper.
Settings
Application configuration is in resources/application.properties file.

Build
[LINUX] ./gradlew build [WINDOWS] gradlew.bat build

Run App on embedded server
[LINUX] ./gradlew bootRun [WINDOWS] gradlew.bat bootRun
