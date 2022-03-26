# p2p-transactional-system
this is a peer-to-peer transactional system done as an assignment for my Distributed Systems module at university.

Daniel Youssef

206150

AI Specialization

Group 1

Peer-to-Peer System

Contents:

1.	Overview and System Design

2.	Summary of Tasks



3.	Design Decisions and Diagram


4.	GUI Screenshots



5.	Advantages and Disadvantages of Design Decisions












a. 	Overview and System Design
This is a peer-to-peer transactional system where there are three collaborators and an extra collaborator who will act as the initiator. Each collaborator will receive one task to execute and then send the revised payload to the next collaborator. The payload consists of three parts:


1.	Task ID
2.	Task list
3.	Task to execute for current collaborator
4.	“Finished” flag, set to 0 (false) by default.


The initiator starts by instantiating a task, thus beginning the task sequence, and delivers the payload to the first collaborator. Each collaborator will check the finished flag to confirm that the task sequence is still ongoing. The first collaborator will execute its assigned task and then deliver the revised payload to the second collaborator. Furthermore, the second collaborator will execute its assigned task and then deliver the revised payload to the third collaborator. Finally, the third collaborator will execute its assigned task then, the finished flag will be set to 1. Once the collaborator checks that the finished flag is set to 1, the collaborator will end the task sequence by delivering the payload back to the initiator. Each revised payload (equivalent to a header being added to the packet) is done by its assigned collaborator, although any collaborator can perform all tasks.


b.	Summary of Tasks


Each Collaborator is responsible for its assigned task.

Payload structure: Object {
Int task_id;
String text;
Boolean finished = false;
}

Collaborator one’s task: Set string to uppercase

Collaborator two’s task: append a special character to the end of the string.

Collaborator three’s task: append a number to the end of the string, set finished to true.



c.	Design Decisions and Diagram


The Initiator:

The Initiator begins the task sequence by serving the payload (Acts as a server) to the first collaborator. (Acts as a client). The initiator also requests the final revised payload (acts as a client) from the third collaborator (acts as a server).



The Collaborator: 

The first collaborator sends the revised payload (acts as a server) to the second collaborator (acts as a client). The second and third collaborators act as servers and clients simultaneously to allow the flow of data between the collaborators.

The method for allowing this sequence of data flow is a ring topology where each node on the network connects to two other nodes. The payload traverses the whole network, each collaborator modifying the payload according to this assigned task.

Both Initiator and Collaborators’ servers are Multithreaded.


d.	Advantages and Disadvantages of Design Decisions


Advantages:

•	Scalable solution: There is no limit on the number of nodes that can be connected together. Also, Collaborators can be arranged together without any overhead to assign them designated numbers.

•	Data Flows in one direction: Very small risk of payload collisions.

•	Isolation of devices: Adding or removing nodes is trivial as only two connections need to be configured.


Disadvantages:

•	Single point of failure: if the initiator or one collaborator fails, the whole network fails as the flow of data cannot continue.

•	Very difficult to troubleshoot: When the network fails, each node must be checked in order to find the error.

•	All nodes must be connected: data cannot flow unless all nodes are connected with each other. If a node attempts to receive or send data from a node that is offline or yet to connect, the whole network will fail.

•	Limited communication across nodes: There is no central node or controller that can manage the messaging between the nodes.





e. Ring Topology Diagram

![Ring Topology 3](https://user-images.githubusercontent.com/90353446/160250215-2a511f94-bf6c-4c06-bbe5-011aaacde00e.png)


f. GUI Screenshots

![GUI example](https://user-images.githubusercontent.com/90353446/160250213-9e11f7cc-7676-46c2-8c95-75d099305c51.png)


