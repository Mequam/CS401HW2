# Homework 2 - CS 401 01 FA22

**Networking Cyphertext**

this is a simple program to query data from a server using udp
and a simple ceaser cypher

---

## Compilation

to compile the program simply run the command

```bash
make
```

on a unix system with gnu make tools and javac installed

the following commands will run the server and client respectivly

```bash
java DAK/HW2/Server
java DAK/HW2/Client
```

optionally when running the client you can specify the server address on the command line

for example:
```bash
java DAK/HW2/Client 127.0.0.1
```

note that the client defaults to localhost when no arguments are offered




> note that you are in the root folder of the project when you run these