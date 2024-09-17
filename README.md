# A Platform for Coffee Tasting

## Introduction

Coffee is becoming more popular nowadays, and we all spend a significant amount of money on coffee drinks. For coffee lovers, we want to share the joy of brewing coffee so that everyone knows what constitutes an excellent cup of coffee. We aim to create a platform for coffee lovers to share the coffee beans they have purchased and the quality of the coffee they brewed. Users can easily **create, read, update, and delete** information about the coffee beans they purchased and the brewing notes for those coffee beans. To visualize a brewing tasting note, a *graphical user interface* (**GUI**) will be developed to allow users to interact with the platform and read others' brewing reviews.

## What Will the Application Do?

- Users can record details about their coffee bean purchases
- Users can record brewing notes
- Users can view purchase and brewing notes by searching
- A visualized diagram for brewing notes will be available

## User Stories
- As a user, I want to add coffee beans purchase to my list of beans
- As a user, I want to add brewing notes to my tasting list
- As a user, I want to compute the average of the overall score of bean
- As a user, I want to view a list of coffee bean purchase records
- As a user, I want to view a list of brewing notes
- As a user, I want to have an option to save the purchase list and bean review list
- As a user, I want to have an option to reload the lists that previously saved and continue to work on the lists
- As a user, I want to select a brewing note and rate the coffee
- As a user, I want to select a coffee bean purchase and edit the details


## Sample functionalities of the program

- You can add coffee beans purchase to bean list by filling fields in tab of "Purchase Log" and press save.
- You can add brewing notes to tasting list by filling fields in tab of "Bean Review Log" and press save.
- You can remove last brew note list added (Bean ID specific) by pressing "Remove Last Element of Bean Review List" in "View Bean Review".
- You can calculate average score of brew note rating (Bean ID specific) by pressing "View Bean Review (Average)" in "View Bean Review".
- You can locate my visual component by pressing the "Show Image" in "HomeTab".
- You can save the state of my application by pressing the "save purchase log" or "save review log" in "HomeTab".
- You can reload the state of my application by pressing the "load purchase log" or "load review log" in "HomeTab".

## Sample output upon exiting program
- Below are the sample log event message printed on Console when closing GUI 
  -     Print log before exit 
        Sun Mar 31 19:17:43 PDT 2024
        BrewNote of BeanID 1 added to BrewNote List
        Sun Mar 31 19:17:43 PDT 2024
        BrewNote of BeanID 1 added to BrewNote List
        Sun Mar 31 19:17:43 PDT 2024
        BrewNote of BeanID 2 added to BrewNote List
        Sun Mar 31 19:17:43 PDT 2024
        BrewNote of BeanID 3 added to BrewNote List
        Sun Mar 31 19:17:44 PDT 2024
        Bean ID 2 added to Bean List
        Sun Mar 31 19:17:44 PDT 2024
        Bean ID 3 added to Bean List
        Sun Mar 31 19:17:48 PDT 2024
        Print all purchase
        Sun Mar 31 19:17:51 PDT 2024
        Print all bean review
        Sun Mar 31 19:17:52 PDT 2024
        Calculated average rating of BeanID 1
        Sun Mar 31 19:17:52 PDT 2024
        Calculated average rating of BeanID 2
        Sun Mar 31 19:17:52 PDT 2024
        Calculated average rating of BeanID 3
        Sun Mar 31 19:17:52 PDT 2024
        Print average rating of bean review
        Sun Mar 31 19:17:53 PDT 2024
        Remove the last added BeanID 3
        Sun Mar 31 19:17:54 PDT 2024
        Print all bean review
        Sun Mar 31 19:17:55 PDT 2024
        Calculated average rating of BeanID 1
        Sun Mar 31 19:17:55 PDT 2024
        Calculated average rating of BeanID 2
        Sun Mar 31 19:17:55 PDT 2024
        Print average rating of bean review
        Sun Mar 31 19:17:56 PDT 2024
        Remove the last added BeanID 2
        Sun Mar 31 19:17:57 PDT 2024
        Print all bean review
        Sun Mar 31 19:17:58 PDT 2024
        Calculated average rating of BeanID 1
        Sun Mar 31 19:17:58 PDT 2024
        Print average rating of bean review
        Sun Mar 31 19:17:58 PDT 2024
        Remove the last added BeanID 1
        Sun Mar 31 19:17:59 PDT 2024
        Print all bean review
        Sun Mar 31 19:18:00 PDT 2024
        Print average rating of bean review 

## Appendix and Improvement remarks for future development
![UML_Design_Diagram.jpg](src%2FUML_Design_Diagram.jpg)
Download as pdf: [UML_Design_Diagram.pdf](src%2FUML_Design_Diagram.pdf)
- Create Interface for HomeTab and CoffeePlatform as they have similar save/load methods
- Create abstract class for ViewBeanReivewTab and ViewPurchaseTab to handle print method. Currently the GUI print methods are located in MultiBeanReviews and MultiPurchases which exhibit unnecessarily coupling
- CoffeePlatform shall contain instance of MultiPurchases and MultiBeanReviews which automatically construct purchase and beanreview arraylists
- UI classes, JsonWriter and JsonReader shall be refactored to write and save on a single json file instead of two
- In ReviewLogTab, doBeanReview method checked duplicate bean ID of BeanReview Instance before instantiating which can be implement equals and hashcode contract in BeanReview class# CoffeeTastingPlatform
