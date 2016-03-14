
# Car Park

A small domain modeling / coding exercise.

## Goals

- Come up with a domain model as straight forward/simple as possible.
- Implement incrementally.
- Code should *tell the story*. 
  Use layers of abstraction (*what* it does vs. *how* it's done).

## Requirements

- Before entering the car park, motorists must take a ticket.
- Entry shows red signal, has closed barrier.
- Entry opens barrier, shows green signal after a ticket was requested.
- Entry shows red signal, closes barrier after vehicle entered the car park.
- Entry shows whether space is available or not.
- Entry only returns ticket if space is available.
- Entry only returns ticket if vehicle is standing in front of it.
- Parking fee has to be payed at the pay station.
- Parking is free for the first 30 minutes.
- After the first 30 minutes, the parking fee is 1 euro per hour.
- Exit shows red signal, has closed barrier.
- Exit accepts ticket and shows green signal / opens barrier, 
  if parking fee was payed.
- Exit shows red signal, returns ticket is parking fee was not payed.
- Exit shows red signal, closes barrier after car left the car park.

