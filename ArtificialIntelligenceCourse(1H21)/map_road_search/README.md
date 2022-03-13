## MAP SEARCH

---
### BFS
It searches for all 4 neighbours (find_neighbors function) of the point where the code is at the moment and adds them 
to the Queue. As well, it checks, if the point is visited. If not, then move there. Searches all the area till finds  
the needed point. This search has no idea where the point is located. At the end goes to add_road function, where it 
uses came_from to make a road from end to the start.

##### Map 300X300:

- Iterations: 61438
- Road length: 554

###### Time 1 try: 0.617855s
###### Time 2 try: 0.465103s
###### Time 3 try: 0.611219s

___

### Greedy
The same as BFS, but gives a weight to the next point. As a weight is a distance from the end point. This algorithm 
knows where is an end pont.

##### Map 300X300:

- Iterations: 3402
- Road length: 918

###### Time 1 try: 0.0435s
###### Time 2 try: 0.027293s
###### Time 3 try: 0.050397s

___

### A*
The same as greedy, but gives to the weight of the previous made road, so if the algorithm goes far, the new road 
starts quick, no need to go back. This algorithm knows where is an end pont.

##### Map 300X300:

- Iterations: 10079
- Road length: 554

###### Time 1 try: 0.126287s
###### Time 2 try: 0.099224s
###### Time 3 try: 0.138275s

___