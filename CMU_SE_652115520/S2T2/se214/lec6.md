# File Management (Chapter 7)

## Lecturers: 
- Dr. Phudinan Singkhamfu
- Dr. Parinya Suwansrikham

## Objectives of File Management:
1. **Meet User Data Requirements**
   - Ensure the system meets the data needs of the user.
2. **Guarantee Valid Data**
   - Ensure data integrity and correctness.
3. **Optimize Performance**
   - Improve throughput and reduce response time.
4. **Support a Wide Variety of Devices**
   - Ensure compatibility with multiple types of devices.
5. **Minimize Lost or Destroyed Data**
   - Protect data from loss or corruption.
6. **Provide Standard I/O Routines**
   - Offer a standard set of input/output routines for file management.
7. **Support Multiple Users**
   - Manage files for multiple users simultaneously.

## OS and File Operations:
Operating systems provide system calls for various file operations:
- **Create Files**
- **Delete Files**
- **Move Files**
- **Rename Files**
- **Copy Files**
- **Open Files**
- **Close Files**
- **Read Files**
- **Write Files**

### Open Files:
- The system searches the directory structure on the disk for the specified file and moves its content into memory.

### Close Files:
- The system moves the file content from memory back to the directory structure on the disk.

### File Management Tasks:
- **Control Access to Files**: Manage permissions and access control for files.
- **Move Data Between Files**: Transfer data from one file to another.
- **Backup and Recover Files**: Safeguard against data loss by backing up and recovering files.

# File Directory

## Definition
- **File Directory** is associated with any file management system and serves as a collection of files.

## Basic Information of a File Directory:
1. **File Name**:
   - Each file name must be unique within a specific directory.
2. **File Type**:
   - Examples include text, binary, and executable files.
   - File types can be identified by their extensions, such as `.jpg`, `.png`, `.docx`, etc.
3. **File Organization**:
   - Systems may support different methods of organizing files.

The file directory is a critical element in file management, ensuring the proper organization and accessibility of files within an operating system.

# File Management (Chapter 7)

### Lecturers:
- Dr. Phudinan Singkhamfu
- Dr. Parinya Suwansrikham

---

## File Management Overview
- **Definition**: File management is one of the critical services provided by the Operating System (OS).
- **Objectives**:
  1. Meet the data requirements of users.
  2. Guarantee valid data.
  3. Optimize performance:
     - Improve throughput.
     - Reduce response time.
  4. Support a wide variety of devices.
  5. Minimize lost or destroyed data.
  6. Provide a standard set of Input/Output routines.
  7. Support multiple users.

---

## OS and File Operations
- The OS provides **system calls** for:
  - Creating, deleting, moving, renaming, copying, opening, closing, reading, and writing files.
- **File Opening (Open Fi)**:
  - Searches the directory structure for the file and moves the entry into memory.
- **File Closing (Close Fi)**:
  - Moves the content of the entry in memory back to the directory on disk.
- Additional functions include:
  - Controlling access to files.
  - Moving data between files.
  - Backing up and recovering files.

---

## File Terms
- **Field**: The basic element of data, such as a last name, date, or sensor reading.
- **Record**: A collection of related fields, such as an employee's name, ID, and salary.
- **File**: A collection of similar records treated as a single entity by the system.
- **Database**: A collection of related data with explicit relationships between data elements.

---

## Access Methods
- **Definition**: Provides a standard interface between applications and the file system.
- **Types**:
  1. **Sequential Access**:
     - Operations: read next, write next, reset.
     - No reading after the last write.
  2. **Direct Access**:
     - Operations: read/write by relative block number (n), position to n, rewrite n.

---

## File Directory
- **Definition**: Associated with any file management system, a file directory is a collection of files.
- **Basic Information**:
  1. **File Name**: Must be unique within a specific directory.
  2. **File Type**: Examples include `.jpg`, `.png`, `.docx`.
  3. **File Organization**: Varies depending on the system.

---

## Directory Structure
- **Definition**: A collection of nodes containing information about all files.
- The directory and its files reside on the disk, with backups stored on tapes.

---

## Directory Implementation
1. **Linear List**:
   - Simple to program.
   - Time-consuming to search.
2. **Hash Table**:
   - Faster search times.
   - Fixed size with potential for collisions.

---

## File Sharing
- File sharing is **desirable** in multi-user and distributed systems.
- Methods:
  - Implementing protection schemes.
  - Using **Network File Systems (NFS)** for distributed file sharing.

---

## Protection
- The file owner/creator can control:
  - What actions (read, write, execute, etc.) can be performed.
  - Who has access to the file.
- **Types of Access**:
  1. Read.
  2. Write.
  3. Execute.
  4. Append.
  5. Delete.
  6. List.

---

## Allocation Methods
### 1. **Contiguous Allocation**
- Files occupy a contiguous set of blocks on disk.
- Pros:
  - Simple (requires starting location and length).
  - Allows random access.
- Cons:
  - Wasteful of space.
  - Fixed file size.

### 2. **Linked Allocation**
- Files are stored as linked lists of disk blocks.
- Pros:
  - No wasted space.
  - Allows dynamic growth.
- Cons:
  - No random access.
  - Slower sequential access due to pointer traversal.

### 3. **Indexed Allocation**
- All pointers are stored in an **index block**.
- Pros:
  - Supports random access.
  - Avoids external fragmentation.
- Cons:
  - Overhead due to the index block.

---

## Efficiency and Performance
- **Efficiency** depends on:
  - Disk allocation and directory algorithms.
  - Type of data stored in the directory entry.
- **Performance Optimization**:
  - **Disk Cache**: Frequently used blocks stored in memory.
  - **Free-behind** and **Read-ahead** techniques to optimize sequential access.

---

## Linux File Structure
- **Paths**:
  - Absolute Path: `/home/usr/CAMT/`
  - Relative Path: `../etc/host/`
- **Commands**:
  - `cd`: Change directory.
  - `ls`: List files.
  - `tree`: Displays the directory tree.
- **File Permissions**:
  - `chmod`: Modify file permissions.
  - Types:
    - `u`: User.
    - `g`: Group.
    - `o`: Others.
    - `d`: Directory.
    - `r`: Read.
    - `w`: Write.
    - `x`: Execute.

---

## Summary
- File management is a critical OS service.
- Disk blocks can be allocated using:
  1. Contiguous allocation.
  2. Linked allocation.
  3. Indexed allocation.
- Popular methods include:
  - Linked chain of blocks.
  - File-Allocation Table (FAT).
