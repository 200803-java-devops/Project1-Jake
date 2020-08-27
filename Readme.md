# Project1 - Jake

Load Balancing App Jake Hernandez 08/27/2020
Will handle requests by getting load data from
various servers and process requests to the 
server with current least number of requests
on it. Uses Least connections algorithm to 
determine which server to place sequent requests
into. Assumes all servers can handle same
number of requests.

## Features
- [] Can fetch load data from servers
    - [] Asks each server how many requests they currently have
    - [] Finds the server with least number of requests
    - [] Process is done before each request that comes in
    - [] Can handle variable number of servers with minor adjustments
- [] Redirects requests to server of choice
    - [] Agnostic of type of application on each server
    - [] concurrent