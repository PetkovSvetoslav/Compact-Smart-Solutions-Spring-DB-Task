# Compact-Smart-Solutions-Spring-DB-Task
There are models that the Car Service database application should contain in its functionality.
I design them, considering the following data constraints from the schema:
BRAND
•	id – accepts BIGINT values, a primary identification field, an auto incremented field.
•	name - VARCHAR – the enumeration, one of the following – AUDI, BMW, VW 
•	Constraint: The BRAND table has a relation with the MODEL table.  

MODEL
•	id – accepts BIGINT values, a primary identification field, an auto incremented field.
•	name - VARCHAR – the enumeration, one of the following – Q7, X4, Q5
•	 brand_id – accepts BIGINT values, FOREIGN KEY

TRANSMISSION 
•	id – accepts BIGINT values, a primary identification field, an auto incremented field.
•	name - VARCHAR – the enumeration, one of the following – AUTOMATIC, MANUAL, HYBRID

FUEL_TYPE 
•	id – accepts BIGINT values, a primary identification field, an auto incremented field.
•	name - VARCHAR – the enumeration, one of the following – GASOLINE, DIESEL, GAS, Electric

CAR	
•	id – accepts BIGINT values, a primary identification field, an auto incremented field.
•	vin_number – VARCHAR -  accepts char sequence .
•	model_id – accepts BIGINT values, FOREIGN KEY
•	price –  NUMBER - accepts a very big positive number.  
•	reg_date – a DATE . 
•	transmission_id – accepts BIGINT values, FOREIGN KEY
•	fuel_type_id – accepts BIGINT values, FOREIGN KEY
•	remarks – VARCHAR -  accepts char sequence .
•	Constraint: The CAR table has a relation with the MODEL table.  
•	Constraint: The CAR table has a relation with the TRANSMISSION table. 
•	Constraint: The CAR table has a relation with the FUEL_TYPE table.  

My Constraint:
•	Name the entities and their class members exactly in the format stated above. 
•	All fields are NOT NULL unless explicitly stated to be nullable.	

The Task Constraint:

For creation of the model you can use any type of relational database you know. To complete the task you must: 
1. Implement all the CRUD operation for create, update and delete. Be aware that you must add validation on creation for all mandatory fields because we can’t create any of the entities without them and throw exception if any of the mandatory fields are missing in the request. 
2. Implement search functionality on cars. For search criteria must use PRICE, MODEL, BRAND and FUEL_TYPE, REG_DATE, TRANSMISSION_TYPE and ORBER BY PRICE descending.

