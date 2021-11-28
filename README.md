# The Cheapest Flight Tickets Searching Application with API
## Project introduction 
This project loads Flight Data from **Travelpayouts Data API**.  
It allows users to search the cheapest tickets and get the details of a selected flight.  
There is a non-stop option for users to choose.

### Note:
The application searches the cheapest flights based on the month of the date picked by users. 

## API
Flight Data [Travelpayouts Data API](https://travelpayouts.github.io/slate/)

### Cheapest tickets
Returns the cheapest non-stop tickets, as well as tickets with 1 or 2 stops, for the selected route with departure/return date filters.   
[Travelpayouts Cheapest tickets](https://support.travelpayouts.com/hc/en-us/articles/203956163-Travel-insights-with-Travelpayouts-Data-API#cheapest_tickets)
 
### Non-stop tickets
Returns the cheapest non-stop ticket for the selected route with departure/return date filters.   
[Travelpayouts Non-stop tickets](https://support.travelpayouts.com/hc/en-us/articles/203956163-Travel-insights-with-Travelpayouts-Data-API#non_stop_tickets)

## Project structure
It has the first view with searching function, and the second view with flight details.

### Search Flight
Users can fill all the fields to get the flights searching result.   
Non-stop is optional.   
<img width="500" alt="Screen Shot 2021-11-27 at 2 53 17 PM" src="https://user-images.githubusercontent.com/78240130/143731372-e960eac2-b4de-46d9-a3e6-c7f942746126.png">

Users can access a flight details after select one from the list.   
<img width="500" alt="Screen Shot 2021-11-27 at 2 53 32 PM" src="https://user-images.githubusercontent.com/78240130/143731471-1ea256bd-aeec-4d42-ad42-0fd84256594b.png">

### Flight Details
Users can check the details of selected flight. The airline logo will be shown accordingly.   
<img width="500" alt="Screen Shot 2021-11-27 at 2 53 48 PM" src="https://user-images.githubusercontent.com/78240130/143731511-55a675b4-d8bc-44e3-afa5-358f4835dc23.png">

