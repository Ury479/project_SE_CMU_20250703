# 20 Multiple Choice Questions at the University Level with Answers and Explanations

---

## Questions:

1. **A program in execution is best defined as:**a
   - a) An executable file.
   - b) A passive entity stored on disk.
   - c) A process.
   - d) A collection of data structures.
   - e) A sequence of instructions.
   - f) A set of system calls.

2. **Which of the following is NOT a typical part of a process's memory layout?** f
   - a) Text section (program code)
   - b) Stack (temporary data)
   - c) Heap (dynamically allocated memory)
   - d) Data section (global variables)
   - e) Interrupt Vector Table
   - f) Registers

3. **A process in the "Waiting" state is:**b
   - a) Currently executing instructions.
   - b) Ready to execute but waiting for a processor.
   - c) Being created.
   - d) Waiting for an event to occur (e.g., I/O completion).
   - e) Has finished execution.
   - f) In the process of being terminated.

4. **The Process Control Block (PCB) does NOT typically contain:**b
   - a) Process state (running, waiting, etc.).
   - b) Program counter.
   - c) CPU registers.
   - d) The current user's login information.
   - e) Memory management information.
   - f) I/O status information.

5. **The process scheduler's primary goal is to:**d
   - a) Minimize CPU usage.
   - b) Maximize CPU usage and quickly switch processes.
   - c) Prioritize processes based on user input.
   - d) Ensure all processes complete in a set order.
   - e) Only schedule processes of a specific type.
   - f) Prevent context switching.

6. **A context switch involves:**a
   - a) Running a single process to completion.
   - b) Saving the state of the old process and loading the state of the new process.
   - c) Allocating additional memory to a process.
   - d) Terminating a process without saving its state.
   - e) Only occurring when a process is terminated.
   - f) Ignoring the PCB of a process.

7. **Which system call creates a new process in UNIX?** c
   - a) exec()
   - b) wait()
   - c) exit()
   - d) fork()
   - e) abort()
   - f) create()

8. **A parent process can terminate a child process using:**c
   - a) The `fork()` system call.
   - b) The `exec()` system call.
   - c) The `exit()` system call.
   - d) The `abort()` system call.
   - e) By simply closing the child's file handles.
   - f) Only through explicit user intervention.

9. **A process that has terminated but its parent hasn't called `wait()` is a:**e
   - a) Orphan
   - b) Zombie
   - c) Ready process
   - d) Terminated process
   - e) Waiting process
   - f) Background process

10. **Interprocess communication (IPC) is primarily needed for:**a
    - a) Isolating processes completely from each other.
    - b) Preventing processes from sharing resources.
    - c) Information sharing and computation speedup.
    - d) Simplifying the OS kernel design.
    - e) Reducing the overall system performance.
    - f) Making processes completely independent.

11. **Shared memory as an IPC mechanism:**b
    - a) Requires the OS to manage all communication.
    - b) Is managed entirely by the operating system.
    - c) Is entirely user-controlled.
    - d) Does not require synchronization mechanisms.
    - e) Is only used in client-server systems.
    - f) Is typically faster than message passing.

12. **In message-passing IPC, the `send()` and `receive()` operations:**a
    - a) Always operate synchronously.
    - b) Always operate asynchronously.
    - c) Can be either blocking or non-blocking.
    - d) Are only used in shared memory systems.
    - e) Only work with fixed-size messages.
    - f) Do not require any form of buffering.

13. **Direct communication in message-passing:**a
    - a) Uses mailboxes.
    - b) Requires processes to name each other explicitly.
    - c) Allows links to be associated with multiple processes.
    - d) Is always unidirectional.
    - e) Is generally slower than indirect communication.
    - f) Does not require synchronization mechanisms.

14. **Indirect communication in message-passing uses:**d
    - a) Explicit process names.
    - b) Mailboxes or ports.
    - c) Shared memory segments.
    - d) Direct connections between processes.
    - e) Only works with synchronous communication.
    - f) Is always more efficient than direct communication.

15. **A rendezvous in message passing refers to:**a
    - a) A non-blocking send and receive.
    - b) A blocking send and a non-blocking receive.
    - c) A non-blocking send and a blocking receive.
    - d) A blocking send and a blocking receive.
    - e) A situation where processes ignore each other.
    - f) A situation where messages are lost.

16. **In bounded-buffer shared memory, the producer waits if:**a
    - a) The buffer is empty.
    - b) The buffer is full.
    - c) The consumer is waiting.
    - d) The system is overloaded.
    - e) The producer process is terminated.
    - f) A context switch has occurred.

17. **Windows uses which IPC mechanism?**a
    - a) Shared memory exclusively.
    - b) Message-passing centric via advanced local procedure call (LPC).
    - c) Sockets exclusively.
    - d) Remote Procedure Calls exclusively.
    - e) A combination of shared memory and sockets.
    - f) A purely direct communication model.

18. **A socket is defined as:**c
    - a) A type of shared memory.
    - b) An endpoint for communication.
    - c) A special type of process.
    - d) A system call.
    - e) A specific type of mailbox.
    - f) A mechanism for process termination.

19. **Remote Procedure Calls (RPCs) use:** b
    - a) Shared memory exclusively.
    - b) Mailboxes for communication.
    - c) Sockets for communication.
    - d) Stubs to handle client-server communication.
    - e) Only work within a single machine.
    - f) Always guarantee exactly-once message delivery.

20. **In RPC, stubs are:**a
    - a) Data structures stored in shared memory.
    - b) Client-side proxies for server procedures.
    - c) Special types of sockets.
    - d) Part of the operating system kernel.
    - e) Used to terminate processes gracefully.
    - f) Only used in connectionless communication.

---

## Answers and Explanations:

1. **c)** A process. A program in execution; it's the active instance of a program.
2. **e)** Interrupt Vector Table. A hardware feature, not part of a process's memory.
3. **d)** Waiting for an event to occur (e.g., I/O completion). Indicates a process is blocked.
4. **d)** The current user's login information. Handled at a higher level.
5. **b)** Maximize CPU usage and quickly switch processes. Ensures efficient scheduling.
6. **b)** Saving the state of the old process and loading the state of the new process.
7. **d)** `fork()`. Creates a copy of the current process.
8. **d)** The `abort()` system call. Terminates a child process forcefully.
9. **b)** Zombie. A process that is terminated but remains in the process table.
10. **c)** Information sharing and computation speedup. Facilitates process cooperation.
11. **c)** Is entirely user-controlled. Processes manage access to shared memory.
12. **c)** Can be either blocking or non-blocking. Depends on requirements.
13. **b)** Requires processes to name each other explicitly. Used in direct communication.
14. **b)** Mailboxes or ports. Used in indirect communication.
15. **d)** A blocking send and a blocking receive. Both wait for communication completion.
16. **b)** The buffer is full. Producer waits until space is available.
17. **b)** Message-passing centric via advanced local procedure call (LPC).
18. **b)** An endpoint for communication. Provides an address for communication.
19. **d)** Stubs to handle client-server communication. Simplifies RPCs.
20. **b)** Client-side proxies for server procedures. Simplifies interactions in RPC.

---

Please review and let me know if any further formatting or changes are needed!
