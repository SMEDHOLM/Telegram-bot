Plan: Shopping list

Description: Bot creates a shopping list. Products can be added to list, or removed by index. List can be cleared. You can make alarm that will send you list when you are in shop

Auxiliary classes and variables:
- AddingOn, if on listens to messages and adds new products.
- RemovingOn, if on listens to messages and removes items from list if its' index is sended.

Methods:
- Method, that reads meassage and adds it to list and sorts it
- Method, that reads numbers from messages and removes items from list by it
- Method, that shows list
- Method, that clears list
- Method that send list in given tim
- Method, that shows all commands

Listeners:
-onUserCommand, /adding that activates possability to add items to list without command
-onUserCommand, /removing that activates possability to remove items from list without command
-onUserCommand, /clear that clears the list
-onUserCommand, /help that show all commands
-onUserCommand, /show that shows the list
-onUserCommand, /alarmIn that will send reminder in give time.