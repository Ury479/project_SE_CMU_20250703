<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "om";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Print the client version
echo "MySQLi client version: " . $conn->client_info . "<br>";
echo "MySQL server info: " . $conn->server_info . "<br>";
echo "MySQL server version: " . $conn->server_version;

// Close the connection
$conn->close();
?>

