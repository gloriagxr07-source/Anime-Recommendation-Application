# MyAnimeList

## What will the application do?

This application is designed to store a personal list of *Anime Series* for recommmendation. 
- Users can enter a name, a rating out of 5 and some comments for each anime series. 
- Users are allowed to delete the information they have written.
- Whenever users want to share their lists with others, they can print a list of highly recommended anime (e.g., rating above 4.7).


## Who will use it?

- anime content creators and anime influencers
- reviewers and anime critics
- educators or professionals in anime industry
- general anime fans


## Why is this project of interest to you?

I'm a person who like watching anime series very much. However, every time when I want to write some comments, it is a bit inconvenient. This application can solve that problem and make it easy for users to write and share recommendations.


## User Stories

- As a user, I want to be able to add a anime to my anime list and specify the name, a rating and an introduction
- As a user, I want to be able to view a list of anime series and each anime has a name, a rating and an introduction
- As a user, I want to be able to print a list of highly recommended anime
- As a user, I want to be able to delete a anime from my list
- As a user, I want to be able to save my animelist to file if I choose to save my list
- As a user, I want to be able to load my animelist from file if I choose to reload


## Instructions for End User

- You can view the panel that displays the Xs that have already been added to the Y one the right side panel and it is updated automatically
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by click button `Add An Anime` for several times
- You can generate the second required action related to the user story "adding multiple Xs to a Y" click button `Add A Comment` for several times
- You can locate my visual component by the anime images placed in center
- You can save the state of my application by click button `Save Data`
- You can reload the state of my application by click button `Load Data`


## Phase 4: Task 2
a representative sample of the events:

- Thu Mar 26 22:21:53 PDT 2026
- An anime added to anime list
- Thu Mar 26 22:22:04 PDT 2026
- A comment added to specific anime in the list


## Phase 4: Task 3
If I have more time to work on the project, I would refactor the design by representing comments as objects but not strings. This is more realistic though the three-level structure might require more efforts in implementing and testing.

Another improvement would be related to the GUI design. I once noticed that my frame size appeared too small on my TA's screen. This indicates that using borderLayout might let my GUI more adaptive and consistent.