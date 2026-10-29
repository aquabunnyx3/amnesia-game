# Amnesia

A dark, Death Note–inspired remake of my 2015 Java text adventure *Amnesia* —
rebuilt as a single-file browser game with hand-placed scene art, looping scene
animations, an original synthesized score, and timed reveals.

You wake up in front of an antique mirror, and the face in it isn't yours.
Chase the other you through a white room, a ruined station, a night train, a
tunnel and a boutique, collecting three notes that spell out the truth.

**Game over… or is it?**

## Play

Open `index.html` in any modern browser — no build step, no server needed.
Everything (art, animation, music) is embedded in the one file.

- Tap / click the text box (or press Space / Enter) to advance the story.
- Music starts on your first tap. The **Music** button by Restart mutes all
  audio, including the station's train horn.
- The game is a faithful remake: same scenes, same inventory gates, same
  blocked-move messages as the original.

## The original

`original/AmnesiaGame.java` is the 2015 original (Michelle Shannon, HW#3,
April 15, 2015) — a single-file Java text adventure with typed commands
(Look, Go, Get, Give, Drink) that this remake is based on.

## What's inside the remake

- 9 scenes with dark anime artwork in a desaturated blue/gray palette
- Looping scene animations (rain, lantern light, a breathing reflection,
  a shop owner who keeps sipping his coffee)
- Timed story reveals that change the artwork on the exact line they're
  mentioned — no spoilers in the buttons
- Two synthesized music loops: a sparse D-minor nightmare theme, and a warmer
  D-major theme for when you wake up
- A synthesized train horn that sounds when the story says it does
