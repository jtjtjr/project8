# Research Report

## Snake game & Oregon Trail

### Summary of Work

I researched different implementations of Oregon Trail and the Snake game using JAVA as the base which is what most of my group is comfortable with.

### Motivation

This gives us a base and we can draw ideas from this. However we also don't know if we want to use images or have it be purely CLI and thus I found versions with images that can easily be rewritten depending on what we choose.

### Time Spent

~60 minutes just looking around the internet for what we could use.

### Results

From the GeeksforGeeks Snake Game tutorial, I learned how to structure the game using separate classes for the snake, individual cells, and the board. The tutorial showed how to handle game mechanics such as collision detection, food generation, and snake growth when eating food.

In reviewing the Oregon Trail GitHub repositories, I noted how others approached the game recreation. One repository focuses on saving and loading game states, for the random events along a journey, and letting users name their party members. Another provides a straightforward Java-based implementation which highlights how to manage a text-based interface and track resources.

Overall, these examples gave me ideas on:
- Separation of concerns, such as a dedicated class for the board or game state.
- User input handling in a loop.
- Saving/loading game data when the game ends or starts.

### Sources

- GFG Snake Game[^1]
- Oregon Trail Repo 1[^2]
- Oregon Trail Repo 2[^3]

[^1]: https://www.geeksforgeeks.org/design-snake-game/
[^2]: https://github.com/LukeVance5/Oregon-Trail
[^3]: https://github.com/kurtisnelson/OregonTrail
