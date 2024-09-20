# Modified Components
## Package: model
+ Add new class: SavingsGoal
+ Add new class: TransactionCategory
+ Modify class: TransactionRecord
    + Add @ManyToOne field: TransactionCategory
    + Edit constructor function TransactionRecord() to initialize the transaction category
## Package: dto
+ Add new class: SavingsGoalDTO
+ Add new class: TransactionCategoryDTO
+ Modify class: TransactionRecordDTO
    + Add field TransactionCategoryId to constructor TransactionRecordDTO()
## Package: repository
+ Add new Interface: SavingsGoalRepository
+ Add new Interface: TransactionCategoryRepository
## Package: service
+ Add new class: TransactionCategory, which contains:
    + createTransactionCategory()
        + For customer to create a new transaction category for his/her account
    + assignTransactionCategory()
        + Assign a transaction category to a transaction record
    + deleteTransactionCategory()
        + Delete a transaction category of an account, and set all assigned record's category to null
    + editTransactionCategory()
        + Edit a transaction category's category name
    + getAllTransactionCategories()
        + Customer checks all of the categories in one account
+ Add new class: SavingsGoalService, which contains:
    + getAllSavingsGoals()
        + Customer checks all of the savings goals in one account
    + createSavingsGoal()
        + Create a new savings goal for the account
    + deleteSavingsGoal()
        + Delete a savings goal for the account
    + updateSavingsGoal()
        + Update a savings goal's information, including: name, target amount, target date
    + updateSavingsGoalDistance()
        + Update the distance to target when customer get money to help finish the target
## Package: controller
+ Functions in this package are just calling functions from service package and turn them in http forms, so no detailed explaination
+ Add new class: SavingsGoalController, which contains:
    + @GetMapping("/all") getAllSavingsGoals()
    + @PostMapping("/create") createSavingsGoal()
    + @PostMapping("/delete") deleteSavingsGoal()
    + @PostMapping("/update") updateSavingsGoal()
    + @PostMapping("/update_distance") updateSavingsGoalDistance()
+ Add new class TransactionCategoryController, which contains:
    + @GetMapping("/all") getAllTransactionCategories()
    + @PostMapping("/create") createTransactionCategory()
    + @PostMapping("/edit") editTransactionCategory()
    + @PostMapping("/assign") assignTransactionCategory()
    + @PostMapping("/delete") deleteTransactionCategory()
# How to test the solution
Suppose there is an account with account id 1 and it has a transaction record with id 3. And the following steps has to be tested one by one in order.
The code for testing is usually begin with HTTP method (GET/POST/PUT/DELETE), and follows with the URL and request body.
## Transaction Category
Create a category:
```http
POST http://localhost:8080/api/customer/1/account/1/transaction_category/create  
Content-Type: application/json  
  
{  
    "categoryName": "BD"
}
```
Check all category:
```http
GET http://localhost:8080/api/customer/1/account/1/transaction_category/all
```
Edit a category:
```http
POST http://localhost:8080/api/customer/1/account/1/transaction_category/edit  
Content-Type: application/json  
  
{  
	"categoryId": 202,  
	"newCategoryName": "Ticket"  
}
```
Assign a category to a transaction record
```http
POST http://localhost:8080/api/customer/1/account/1/transaction_category/assign  
Content-Type: application/json  
  
{  
	"recordId": 3,  
	"categoryId": 202
}
```
Delete a category
```http
POST http://localhost:8080/api/customer/1/account/1/transaction_category/delete  
Content-Type: application/json  
  
{  
    "categoryId": 202
}
```
## Savings Goal
Create a savings goal
```http
POST http://localhost:8080/api/customer/1/account/1/savings_goal/create  
Content-Type: application/json  
  
{  
    "goalName": "Hatsune Miku: Rose Cage Ver. 1/7",    
    "goalAmount": 469.95,    
    "targetDate": "2024-12-20"
}
```
Check all savings goal:
```http
GET http://localhost:8080/api/customer/1/account/1/savings_goal/all
```
Edit a savings goal
```http
POST http://localhost:8080/api/customer/1/account/1/transaction_category/edit  
Content-Type: application/json  
  
{  
	"categoryId": 202,  
	"newCategoryName": "Ticket"  
}
```
Update the distance to target when customer get money to help finish the target (request and its response)
```http
POST http://localhost:8080/api/customer/1/account/1/savings_goal/update_distance  
Content-Type: application/json  
  
{  
	"goalId": 3,  
	"updateAmount": 100
}
```
Delete a savings goal
```http
POST http://localhost:8080/api/customer/1/account/1/savings_goal/delete  
Content-Type: application/json  
  
{  
	"goalId": 52
}
```
# Discussion of Changes
In order to realise the two proposed requirements, the model, repository, service, controller, and dto classes corresponding to the two entities were first created, and then business logic was added to implement basic CRUD operations, so that most of the requirements have been completed. 

It is worth mentioning that the distance to target field is added to the savings goal to help the user identify how much money is left to complete the savings goal, and the user can also update this field to allocate the use of the savings amount when saving.
# Screenshots
Here are the screenshots that showing the solution do provide the requested functionality.
## Transaction Category
Create a category (request and its response)
![[Pasted image 20240920144815.png]](./readmeAttachments/Pasted image 20240920144815.png)

Edit a category (request and its response)
![[Pasted image 20240920144855.png]](./readmeAttachments/Pasted image 20240920144855.png) 
![[Pasted image 20240920145651.png]](./readmeAttachments/Pasted image 20240920145651.png)

Delete a category (request and its response)
![[Pasted image 20240920145746.png]](./readmeAttachments/Pasted image 20240920145746.png) 
![[Pasted image 20240920145805.png]](./readmeAttachments/Pasted image 20240920145805.png)

Assign a category to a transaction record (request and its response)
![[Pasted image 20240920150329.png]](./readmeAttachments/Pasted image 20240920145805.png)
![[Pasted image 20240920150257.png]](./readmeAttachments/Pasted image 20240920150257.png)

Check all categories (request and its response)
![[Pasted image 20240920145828.png]](./readmeAttachments/Pasted image 20240920145828.png)
## Savings Goal
Create a savings goal (request and its response)
![[Pasted image 20240919194254.png]](./readmeAttachments/Pasted image 20240919194254.png)
![[Pasted image 20240919194323.png]](./readmeAttachments/Pasted image 20240919194323.png)

Edit a savings goal (request and its response)
![[Pasted image 20240919194618.png]](./readmeAttachments/Pasted image 20240919194618.png)
![[Pasted image 20240919194645.png]](./readmeAttachments/Pasted image 20240919194645.png)

Delete a savings goal (request and its response)
![[Pasted image 20240919194744.png]](./readmeAttachments/Pasted image 20240919194744.png)
![[Pasted image 20240919194832.png]](./readmeAttachments/Pasted image 20240919194832.png)

Update the distance to target when customer get money to help finish the target (request and its response)
![[Pasted image 20240919194942.png]](./readmeAttachments/Pasted image 20240919194942.png)
![[Pasted image 20240919194954.png]](./readmeAttachments/Pasted image 20240919194954.png)