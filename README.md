# rrs
Restaurant Reservation System

******************************
Problem Statement 1:
A Restaurant reservation system.
A restaurant has multiple tables with varying seating capacities. A table of a seating capacity of 'x' can be booked for 'n' number of guests for a specified date and time. Booking can be done for specific hours when the restaurant is open. Describe the classes, interfaces and data members and how they interact with each other.

Assumptions:
1.	Restaurant will contain set of tables of varying capacity. 
2.	It is usually operated in few hours of a week day and operates in the same way for future weeks
3.	Restaurant  can have holidays
4.	A table of capacity X can serve 2*X no of guests for bookings below 4PM (Lunch booking) and X no of guests for booking after 4PM (Dinner booking)
Input:
1.	Holidays of restaurant are feed to system in holidays.txt file in the format yyyy-mm-dd
2.	Working hours of a week day is feed to system in day_hour_availability.txt in the format day_no comma_seperated_hours. Day_no starts from zero for Sunday and increments 1 for next 6 days of week.
3.	Table capacity is feed to system in tables.txt file

How to Execute:
1. execute com.rrs.processor.RestaurantManagerDemo class and provide prompted input