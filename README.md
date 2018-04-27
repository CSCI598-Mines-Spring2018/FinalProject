# Instructions

There are multiple projects contained in this repository. Three of these projects (centralized-buzz, decentralized-buzz, independent-buzz) are swarm control simulations which utilize an open-source robot simulation environment called ARGoS and a swarm control language called Buzz. The fourth project (MazeRobotics) runs in IntelliJ with Java. Instructions to install ARGoS and Buzz together are available [here](http://the.swarming.buzz/wiki/doku.php?id=buzz_argos).

After ARGoS and Buzz are installed, cd to the project root and compile the project using the Buzz toolchain.

```$ bzzc main.bzz```

Then, launch the simulation with one of the provided simulation files (*.argos). Click the play button on the ARGoS window to start the simulation. There is an issue with restarting the simulation after it has been stopped, so it is easiest to just restart ARGoS each time the simulation is paused, stopped, or reset.

```$ argos3 -c main.argos```