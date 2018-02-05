Problem #1:
This problem wanted to find when an object in free fall hit the ground wth no
drag. I accomplished ths with the use of the Runge-Kutta method, by refining
brackets of solutions in order to get to the desired accuracy and
extrapolating the bracket backward to get the solution. I got an answer of
1.427343 s. This was expected because the object was only falling from a height of
10 m, and g was 9.81 m/s/s.


Problem #2:
This problem wanted to find when an object in free fall hit the ground with
linear drag dependent on an object's velocity. I accomplished ths with the use of 
the Runge-Kutta method, by refining brackets of solutions in order to get to the 
desired accuracy and extrapolating the bracket backward to get the solution. I got an answer of 
1.853502 s. This was expected because the object was only falling from a height of
10 m, drag wasn't too much for that short timee,  and g was 9.81 m/s/s.


Problem #3:
This problem wanted to find when an object in free fall hit the ground with
quadratic drag dependent on an object's velocity. I accomplished ths with the use
of the Runge-Kutta method, by refining brackets of solutions in order to get
to the desired accuracy and extrapolating the bracket backward to get the
solution. I got an answer of 1.668196 s. This was expected because the object was 
only falling from a height of 10 m, drag wasn't too much for that short time, 
especially when looking at quadratic versus linear, since initially, quadratic 
curves grow more slowly that linear curves.


Problem #4:
This problem wanted to find when an object in free fall hit the ground with
no drag, linear drag, and quadratic drag dependent on an object's velocity.
Also, this object initally had a horizontal  velocity.I accomplished ths with the use
of the Runge-Kutta method, by refining brackets of solutions in order to get
to the desired accuracy and extrapolating the bracket backward to get the
solution. For no drag, I got an answer of 1.427343 s, for linear drag, I got an
answer of 1.853502, and for quadratic drag the answer was 1.461439 s. Note that
referencing the first 3 problems, no drag and linear drag answers don't change
even with a horizontal velocity. This is because nowhere in the equations for
acceleration in no drag and linear drag cases does horizontal velocity get mentioned.
However, in the quadratic case, total velocity appears in the equation, which
means that horizontal velocity/drag affects what time the ball hits the
ground. Therefore,with horizontal velocity in the quadratic drag, more total drag 
impacts the ball, making it fall faster.

Problem #5:
For this problem, we wanted to find out at what time a skydiver fell to 1000
meters from 40,000 meters up. N, in order to adapt this to my program, I made
the initial height 39000 m, and measured the amount of time it took the
skydiver to reach a height of 0. Because the numbers were so large in this
problem, this really didn't affect the results that much. I got an answer of 
240s when the skydiver hits h = 1000 m. The maximum velocity reached by the skydiver was 
around 363 m/s downward. This can also be expressed as about 812 miles per hour. 
Which is a lot so maybe I did this wrong, but I am cofident in my program. Anyways,
yes, the sound barrier was broken by about 45 mph.
