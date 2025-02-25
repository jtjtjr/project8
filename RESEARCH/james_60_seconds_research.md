# Research Report

## 60 Seconds Game Design

### Summary of Work

I watched 60 Seconds gameplay and read its wiki to get a good grasp of its game mechanics and how it plays. I took notes on what did and didn't work (in my opinion, although I tried to be as objective as possible). With these, I then thought of how these game elements could be worked into our own game's design.

### Motivation

60 Seconds is a game with a very similar premise to what we're doing, and as it's a popular and modern game, there's a plethora of videos and webpages on the game. Understanding its good (and poor) decisions regarding how they implement their mechanics will greatly benefit the planning of our project.

### Time Spent

70 minutes - Watching 60 Seconds gameplay videos
30 minutes - Reading the 60 Seconds wiki

### Results

<!--Explain what you learned/produced/etc. This section should explain the important things you learned so that it can serve as an easy reference for yourself and others who could benefit from reviewing this topic. Include your sources as footnotes. Make sure you include the footnotes where appropriate e.g [^1]-->

This game starts with a countdown to grab as many items as you can in 60 seconds before a nuke destroys everything outside your bunker. Applying this to our game, a system where we have to choose a limited number of items to take with us before launch would make a lot of sense and incentivize strategic planning.[^1]

Each "day" has a description of a few pre-generated sentences based on the state of the characters, along with the daily "event". We're not doing individual characters yet but we'll need a system like that if/when we implement it.[^1]

4 crew members seems to be the max that we could deal with before it becomes too much micromanagement.[^1]

The key strategy in this game seems to be to wait till the last minute to use your items. This doesn't seem like a good game design choice. Adding negative effects that only partially go away after using an item if you let it get bad enough would fix this.[^1]

Using emotional language during events requiring urgent action would add to the immersion.[^1]

A mechanic that allows our ship to travel further, at the cost of higher risk, would be a good trade-off to implement. Like the foraging mechanic in this game, where you have a high chance of harm but also can get supplies to last much longer (and therefore get closer to the end).[^2]

We need some kind of menu where we display the crew status, items, current event, etc.[^2]

The statuses are vague (eg. "on death's door"). This led me to be confused about how much "trouble" they actually are in. For our game, an integer value or some other more definitive metric would be best practice.[^2]

Some event types we could have:
Remove an item
Add an item
Force you to use an item or receive a debuff/die
Event chains, where selecting one decision for an event leads to a related event the following day
Randomly offer to use an item you have to start an event chain
RNG events, where you gamble for a reward
Easter egg/joke events, very rare
Endgame events, where you can choose to end the game or play for a bit longer hoping for another ending to come up. Might include RNG.[^3]

There's a very large amount of event ideas inspired by this game; many events are listed on the wiki. Will make specific suggestions to the group once our events get more fleshed out.[^1][^2][^3]

There appears to be a debuff to your characters doing nothing. This would be a nice feature to add to our game. Doing nothing would decrease the crew's morale.[^3]

Achievements, given at the end of a game run, for completing certain tasks would be a good feature to (eventually) add.[^4]

### Sources

<!--list your sources and link them to a footnote with the source url-->

- 60 Seconds! - Full Game Walkthrough[^1]
- 60 Seconds Reatomized | Longest Survival - Zero Items - Hard - One Member - 120 days[^2]
- Events (60 Seconds!)[^3]
- Achievements (60 Seconds!)[^4]

[^1]: https://www.youtube.com/watch?v=LGOmJmFaejA
[^2]: https://www.youtube.com/watch?v=GYLrJdMuHFQ
[^3]: https://60seconds.fandom.com/wiki/Events_(60_Seconds!)
[^4]: https://60seconds.fandom.com/wiki/Achievements_(60_Seconds!)

