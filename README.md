# Game-Engine-Improved
Game Engine with basic functionality using LWJGL 3.

TO USE THIS LIB FOR A PROJECT, DOWNLOAD IT AND ADD THE KATADAENGINE.JAR AS WELL AS ALL THE OTHER JARS 
THAT ARENT EXTENDED WITH "javadoc". THIS WILL ADD THE KATADA ENGINE LIBRARY AND THE LWJGL3 LIBRARY.
FEEL FREE TO LOOK AT THE SOURCE CODE, BUT PLEASE GIVE CREDIT BY PLACING A LINK TO THE ORIGINAL REPO IN 
YOUR PROJECT IF YOU ARE GOING TO USE THIS.

Setup:
Create new eclipse project.
Right click -> Build Path -> Configure Build Path
Add Libray (Classpath) -> User Library -> User Libraries
New -> Name It -> Add External Jars -> Navigate to /lib folder from extracted repo.
Add all jars except "-javadoc"
Create two new folders in your eclipse projects, res and shaders.
In res, create a folder models and a folder images.
Add all textures to images folder, all models (obj) to models folder.
Add all shaders in /shaders and make a subdirectory of each shader if you want.

Make a main class that implements GameEngine. Add unimplemented methods.

addStuff -> Add objects, lights, set skybox texture names (without extension - all png).
addShaders -> You can create your own shader class which extends Shader and then add that class. 
  Don't forget to place the actual shaders in /shaders and add the subdirectory names when initializing
   the class, if any.
   
transform -> Perform operations on gameobjecs during the game loop (e.g transformation, collision)



