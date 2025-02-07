# File_Deduplication_System

## A Java-based Solution to detect,delete or backup duplicate files in a direcory

This File Deduplication System Scans a given folder and detects duplicates efficiently based on their content(not just filenames).It allows the user to delete duplicates or move them to a backup folder with features include scanning a given folder and sub folder to find duplicate files which uses SHA-256 hashing to detect duplicates based on content.Ensure safe deletion or backup.

### Implementation 
The program starts by taking the directory path as input from the user.The **scanFolder() function** is used to scan through all files in the directory and its subdirectories.

Each file is hashed using **SHA-256** to ensure duplicate detection based on content using fileHashing() Method.The function reads the file in chunks to optimize memory usage and avoid performance issues with large files.The hash value is converted into a hexadecimal string for easy comparison and stored in a HashMap.

A HashMap Data structure is implemented to track the files by storing the hash values of file as keys and the value is a list of files sharing the same hash.

**Handle Duplicate method** is used to delete or backup the files depending on the user choice.
Deletion process safely deletes the file by ensuring at least one copy of file remain.Backup process ensures to safely move the duplicate files to the directory where the user is asked to provide the path.

This system **automates duplicate file detection and management**, helping users free up disk space while ensuring important files are safely backed up.

### How to Run?
1.Clone/download this repo.
2.Use IntelliJ IDEA, Eclipse, or VS Code.
3.Open File_Deduplication_system project into the IDE(Ensure java is installed).
4.Run  [Main.java]() and Provide the absolute path of the folder to scan.
5.Try deleting or backup the files.

