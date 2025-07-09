# 25 Multiple Choice Questions at the University Level

### 1. Which of the following is NOT a typical operating system service provided to users? e
- a) Program execution  
- b) I/O operations  
- c) Resource allocation (for the system itself)  
- d) File system manipulation  
- e) Direct hardware manipulation  

---

### 2. Which user interface type is characterized by commands entered via text? c
- a) Graphical User Interface (GUI)  
- b) Touchscreen Interface  
- c) Command-Line Interface (CLI)  
- d) Batch Interface  
- e) Hybrid Interface  

---

### 3. What is the primary function of a system call? d
- a) To directly access hardware resources.  
- b) To provide a programming interface to OS services.  
- c) To manage the user interface.  
- d) To handle background processes.  
- e) To control the boot process.  

---

### 4. Which of the following is NOT a common API mentioned in the text? c
- a) Win32 API  
- b) POSIX API  
- c) Java API  
- d) Linux API  
- e) Apple API  

---

### 5. What is a common method for passing parameters to the OS during a system call? a
- a) Exclusively through system registers.  
- b) Using only a memory block.  
- c) Always pushing parameters onto the stack.  
- d) A combination of registers, memory blocks, or the stack.  
- e) Through a dedicated communication channel.  

---

### 6. Which of these is NOT a type of system call related to process control? e
- a) Create process  
- b) Read file  
- c) Terminate process  
- d) Wait for time  
- e) Allocate and free memory  

---

### 7. What system call is used in FreeBSD to create a new process? e
- a) exec()  
- b) load()  
- c) fork()  
- d) run()  
- e) start()  

---

### 8. Which type of system call manages the creation and deletion of communication connections? b
- a) File management  
- b) Process control  
- c) Device management  
- d) Information maintenance  
- e) Communications  

---

### 9. What is the primary role of a linker in software development? a
- a) To load executable files into memory.  
- b) To compile source code into object files.  
- c) To combine object files and libraries into a single executable.  
- d) To manage system calls.  
- e) To interpret bytecode.  

---

### 10. Why are applications often operating system-specific? d
- a) All applications are written in a single language.  
- b) They are designed for a specific hardware architecture.  
- c) Each OS has its own system calls, file formats, and APIs.  
- d) Operating systems all use the same architecture.  
- e) Programmers always write applications for a single OS.  

---

### 11. What is the Application Binary Interface (ABI)?
- a) A high-level programming interface.  
- b) A method for passing parameters to the OS.  
- c) An architecture-equivalent of the API, defining how binary code components interface.  
- d) A type of system call.  
- e) A user interface design.  

---

### 12. Which OS structure is described as having a limited structure and is exemplified by MS-DOS?
- a) Layered  
- b) Microkernel  
- c) Monolithic  
- d) Hybrid  
- e) Modular  

---

### 13. Which operating system structure is characterized by moving as much functionality as possible from the kernel into user space?
- a) Monolithic  
- b) Layered  
- c) Hybrid  
- d) Microkernel  
- e) Modular  

---

### 14. What is a benefit of using a microkernel architecture?
- a) Increased complexity.  
- b) Reduced security.  
- c) Improved portability.  
- d) Higher performance (always).  
- e) Increased code size in the kernel.  

---

### 15. What is a potential drawback of a microkernel architecture?
- a) Increased modularity  
- b) Improved security  
- c) Performance overhead from inter-module communication  
- d) Easier system extension  
- e) Reduced code size  

---

### 16. Which OS uses a hybrid approach combining multiple architectural models?
- a) MS-DOS  
- b) Original UNIX  
- c) Apple macOS  
- d) Arduino  
- e) FreeBSD  

---

### 17. What is the role of a bootloader in a system like Arduino?
- a) To manage multitasking.  
- b) To load programs from flash memory into memory.  
- c) To handle system calls.  
- d) To manage the GUI.  
- e) To provide networking capabilities.  

---

### 18. What is a common feature of background services?
- a) They are always part of the GUI.  
- b) They run in kernel context only.  
- c) They are launched only by the user.  
- d) They are launched at boot time and may run until shutdown.  
- e) They cannot be controlled by the OS.  

---

### 19. What is a system program that combines object files into a single executable?
- a) Loader  
- b) Compiler  
- c) Interpreter  
- d) Linker  
- e) Debugger  

---

### 20. What is a dynamically linked library in Windows?
- a) A statically linked library.  
- b) A file that is compiled at runtime.  
- c) A DLL (Dynamic Link Library) that is loaded as needed.  
- d) A type of system call.  
- e) A program that compiles source code.  

---

### 21. What does a return code of 0 typically indicate in FreeBSD after a process exits?
- a) A critical system error.  
- b) No error.  
- c) A minor error.  
- d) The process was terminated by the user.  
- e) The process was interrupted.  

---

### 22. Which OS is based on a modified Linux kernel and includes a Dalvik virtual machine?
- a) iOS  
- b) macOS  
- c) Windows  
- d) Android  
- e) FreeBSD  

---

### 23. What is the primary role of the Android runtime environment?
- a) To manage the user interface  
- b) To provide access to the Linux kernel directly.  
- c) To provide core libraries and a virtual machine.  
- d) To handle system calls.  
- e) To manage file systems  

---

### 24. What language are many Android apps developed in?
- a) C++  
- b) Python  
- c) Java  
- d) Swift  
- e) Objective-C  

---

### 25. Which of the following is NOT a typical component of the Android runtime environment?
- a) Core libraries  
- b) Dalvik virtual machine  
- c) A full-featured C library (libc)  
- d) Webkit (web browser framework)  
- e) SQLite (database framework)  

---

## Answer Key
1. c) Resource allocation (for the system itself)  
2. c) Command-Line Interface (CLI)  
3. b) To provide a programming interface to OS services.  
4. d) Linux API  
5. d) A combination of registers, memory blocks, or the stack.  
6. b) Read file  
7. c) fork()  
8. e) Communications  
9. c) To combine object files and libraries into a single executable.  
10. c) Each OS has its own system calls, file formats, and APIs.  
11. c) An architecture-equivalent of the API, defining how binary code components interface.  
12. c) Monolithic  
13. d) Microkernel  
14. c) Improved portability.  
15. c) Performance overhead from inter-module communication  
16. c) Apple macOS  
17. b) To load programs from flash memory into memory.  
18. d) They are launched at boot time and may run until shutdown.  
19. d) Linker  
20. c) A DLL (Dynamic Link Library) that is loaded as needed.  
21. b) No error.  
22. d) Android  
23. c) To provide core libraries and a virtual machine.  
24. c) Java  
25. c) A full-featured C library (libc)  
