# 3D Bin Packing

This algorithm try to solve the 3d Bin Packing problem. 

> "In the bin packing problem, objects of different volumes must be packed into a finite number of bins or containers each of volume V in a way that minimizes the number of bins used. In computational complexity theory, it is a combinatorial NP-hard problem." (WIKIPEDIA, https://en.wikipedia.org/wiki/Bin_packing_problem)

To understand their operation is necessary to understand the behavior of the main objects: 

* **Line:** Represents an axis on the graph x, y and z. A ```Line``` have a beginning value and a end value.
* **Point:** Represents a ```Point``` inside the Bin. It consists of three Lines representing the x, y, z.
* **Item:** An ```Item``` is made up of your measurements (width, length, height and weight) and the ```Point``` where it was inserted into the Bin. 
* **Bin:** Represents the location where the items are stored. In this software a bin is called by container and it contain the rules that are defined by the interface ```Restrictions```.

**The operation.**

The first thing to do is implement the rules to be use to packing. The ```Restrictions``` interface has the methods to be implemented.

* ```boolean isMinRestrictionsViolated(Measures measures)```: this method is used to inform if the restrictions of minimum values are violated.
* ```boolean fixMinRestrictionsViolated(Measures measures)```: this method is used to apply the fixes (if exists) when the minimum values are violated. 
* ```void isMaxRestrictionsViolated(Measures measures)```: this method is used to inform if the restrictions of maximum values are violated.
* ```void fixMaxRestrictionsViolated(Measures measures)```: this method is used to apply the fixes (if exists) when the maximum values are violated. 


After the restrictions being informed, the ```PackingController``` class uses this restrictions to try to packing the list of ```Item```s received. The controller try to pack each item at a time, if is not possible to pack a ```Item```, then, an other ```Container``` is created and this ```Item``` is placed on it.

When one ```Item``` is packed, it creates three ```Point```s into the ```Container```, this points are called Entry Points because are the entry points to next items. The image below shows the three entry points created when the first ```Item``` is placed into the ```Container```.

![alt tag](https://diegotonzi.github.io/img/BinPack/2.png) ![alt tag](https://diegotonzi.github.io/img/BinPack/1.png)

With the three entry points created, we can place the second ```Item``` into the ```Container```, for to do this the algorithm use the method ```getBestEntryPoint(Item item)``` where the best entry point is choose to place the new ```Item```. To choose the best entry point to place the new item the method simulates the placement of the item in all the entry points of the container, the entry point that results in a lower final volume for the container is chosen like the best entry point to place the new item

After to choosing the best entry point, the item is placed in it and the entry points of container are recalculated generating five entry points like is showed in the image below

![alt tag](https://diegotonzi.github.io/img/BinPack/3.png)

The algorithm repeat this steps for all items. In case of the restriction of the maximum size of container is reached, then, a new container is created. In case of a new container be created, in the next items, the algorithm try to place the item in the first container, if is not possible try to place in the second container.

The ```PackingController``` class is responsible to manage the packing of items. To create a PackingController is necessary to pass a list of items to be packed and the ```Restrictions``` implementation of rules to be used. 

For convenience, you can chose draw the result of packing, for to do this you need to call the ````drawContainers(PackingController controller)```` in the Main class when the controller finish the packing. Making it the Main class will use the class ```DrawContainer``` to draw the placement of items in the container. It facility to understand the placement of items.
