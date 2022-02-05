Basmallah Algahmi

Noe Sancen


Commands:
* Login
* Solve
* List
* ShutDown
* Logout

In order to run the program, the user is REQUIRED to start the server first BEFORE starting the client, otherwise the program will crash.
Once the server is started, run the client and insert the following commands. The first command, login, is required before any other command is called upon.
After user passes through the login requirement, then any of the other four commands can be called in any order.

There has been five commands implemented within the program. 
	The first command,  login, has the server take the username/password inputs from the user and checks the approved username/password list to check if it matches.
If approved, the server lets the username in before any other input is taken, otherwise the server denies the user from logging in and tells the user to re-enter their login information.
	The second command, Solve, takes the circle and retangle values. The radius of the circle using the multiplication by pi formula to find the area, then the circumference is found using "c = 2πr" formula.
The rectangle sides are inputted by the user and the sizes are used through formulas to find the perimeter and areas. 
	The third command, LIST, takes the user's inputs and displays them in the client while also creating a text file of past inputs from the user.
If the login is root, the user will be able to use the "-all" function and list all of the inputs every login user has made. 
Otherwise, the server will send an error with a message telling the user they are not the root user.
	The fourth command, Shutdown, closes both the server and client sockets.
	The fifth command, logout, terminates the login user. When the user logouts, the server requires the user to enter a new login or re-enter their information to log back in.   	






The following text below is a sample of the output of the client side:







You need to login in order to connect to the server by typing LOGIN follow by user name and password
Send command to server:    login root edfasfd
Server says: FAILURE: Please provide correct username and password. Try again.
Send command to server:    login root root22
Server says: SUCCESS
Send command to server:    solve -c 6
Server says: radius 6.0: Circle's circumference is 37.70 and area is 113.10

Send command to server:    solve -r 2 3
Server says: Side 2.0 3.0: Rectangle's perimeter is 10.00 and area is 6.00

Send command to server:    list
Server says: root
radius 4.0: Circleâ€™s circumference is 25.13 and area is 50.27
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
Side 2.0 2.0: Rectangleâ€™s perimeter is 8.00 and area is 4.00
Side 2.0 4.0: Rectangleâ€™s perimeter is 12.00 and area is 8.00
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
radius 4.0: Circleâ€™s circumference is 25.13 and area is 50.27
Side 4.0 4.0: Rectangleâ€™s perimeter is 16.00 and area is 16.00
Side 2.0 3.0: Rectangleâ€™s perimeter is 10.00 and area is 6.00
Error: No sides found/exceed accabtable number of sides
radius 6.0: Circleâ€™s circumference is 37.70 and area is 113.10
Side 3.0 4.0: Rectangleâ€™s perimeter is 14.00 and area is 12.00
radius 6.0: Circleâ€™s circumference is 37.70 and area is 113.10
Side 2.0 3.0: Rectangleâ€™s perimeter is 10.00 and area is 6.00
Side 3.0 4.0: Rectangle's perimeter is 14.00 and area is 12.00
radius 6.0: Circle's circumference is 37.70 and area is 113.10
Side 2.0 3.0: Rectangle's perimeter is 10.00 and area is 6.00

Send command to server:    list -all
Server says: root
radius 4.0: Circleâ€™s circumference is 25.13 and area is 50.27
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
Side 2.0 2.0: Rectangleâ€™s perimeter is 8.00 and area is 4.00
Side 2.0 4.0: Rectangleâ€™s perimeter is 12.00 and area is 8.00
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
Error: No sides found/exceed accabtable number of sides
radius 4.0: Circleâ€™s circumference is 25.13 and area is 50.27
Side 4.0 4.0: Rectangleâ€™s perimeter is 16.00 and area is 16.00
Side 2.0 3.0: Rectangleâ€™s perimeter is 10.00 and area is 6.00
Error: No sides found/exceed accabtable number of sides
radius 6.0: Circleâ€™s circumference is 37.70 and area is 113.10
Side 3.0 4.0: Rectangleâ€™s perimeter is 14.00 and area is 12.00
radius 6.0: Circleâ€™s circumference is 37.70 and area is 113.10
Side 2.0 3.0: Rectangleâ€™s perimeter is 10.00 and area is 6.00
Side 3.0 4.0: Rectangle's perimeter is 14.00 and area is 12.00
radius 6.0: Circle's circumference is 37.70 and area is 113.10
Side 2.0 3.0: Rectangle's perimeter is 10.00 and area is 6.00
sally
radius 4.0: Circleâ€™s circumference is 25.13 and area is 50.27
Error: No sides found/exceed accabtable number of sides
Side 2.0 6.0: Rectangleâ€™s perimeter is 16.00 and area is 12.00
john
radius 6.0: Circleâ€™s circumference is 37.70 and area is 113.10
qiang
No interactions yet

Send command to server:    logout
Server says: 200 OK
Send command to server:    login john john22
Server says: SUCCESS
Send command to server:    list -all
Server says: Error: you are not the root user

Send command to server:    shutdown
Server says: 
200 OK...
"








