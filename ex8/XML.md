<h1 align="center">Reading XML Data using Servlets / PHP</h1>

- Ex No. : 8
- Date : 

# Aim
The aim of this experiment is to read XML data using Servlets / PHP. The XML data is stored in a file and the data is read using Servlets / PHP.

# Procedure
1. Create an PHP File: Create a PHP file with the .php extension and include the XML file to be read.
2. Create an XML FIle: Create an XML file with the .xml extension and add the data to be read.
3. Read XML Data: Use the simplexml_load_file() function to read the XML data.
4. Display XML Data: Use the echo statement to display the XML data.
5. Run the PHP File: Run the PHP file using the localhost server.
6. Use Table Styles: Use the table styles to display the XML data in a tabular format.

# Program
* index.php
```php
<!DOCTYPE html>
<html>
<head>
    <title>Space Objects</title>
    <link rel="stylesheet" href="style.css">
</head>
    <body>
        <h1>Space Objects</h1>
        <table>
            <tr>
                <th>Name</th>
                <th>Distance</th>
                <th>Radius</th>
                <th>Gravity</th>
                <th>Temperature</th>
            </tr>
            <?php
            $xml = simplexml_load_file('space.xml');
            if ($xml) {
                foreach ($xml->space as $space) {
                    echo "<tr>";
                    echo "<td>" . $space->name . "</td>";
                    echo "<td>" . $space->distance . "</td>";
                    echo "<td>" . $space->radius . "</td>";
                    echo "<td>" . $space->gravity . "</td>";
                    echo "<td>" . $space->temperature . "</td>";

                    echo "</tr>";
                }
            } else {
                echo "<tr><td colspan='5'>Failed to load the XML file.</td></tr>";
            }
            ?>
        </table>
    </body>
</html>
```

* space.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<space-items>
    <space>
        <name> Neptune </name> 
        <distance> 4.5 billion km </distance>
        <radius> 24,764 km </radius>
        <gravity> 11.15 m/s² </gravity>
        <temperature> -201 °C </temperature>
    </space>
    <space>
        <name> Uranus </name> 
        <distance> 2.9 billion km </distance>
        <radius> 25,362 km </radius>
        <gravity> 8.87 m/s² </gravity>
        <temperature> -197 °C </temperature>
    </space>
    <space>
        <name> Saturn </name> 
        <distance> 1.4 billion km </distance>
        <radius> 58,232 km </radius>
        <gravity> 10.44 m/s² </gravity>
        <temperature> -139 °C </temperature>
    </space>
    <space>
        <name> Jupiter </name> 
        <distance> 778 million km </distance>
        <radius> 69,911 km </radius>
        <gravity> 24.79 m/s² </gravity>
        <temperature> -145 °C </temperature>
    </space>
    <space>
        <name> Mars </name> 
        <distance> 228 million km </distance>
        <radius> 3,390 km </radius>
        <gravity> 3.711 m/s² </gravity>
        <temperature> -63 °C </temperature>
    </space>    
</space-items>
```

* style.css
```css
html {
    background-image: url("./Starry_Background.jpg");
    color:antiquewhite;
}

table {
    width: 100%;
    border-collapse: collapse;
}

h1 {
    text-align: center;
}


th,
td {
    padding: 8px;
    text-align: center;
    font-weight: bold;
    font-size: 20px;
}
```

# Output
![Solar Objects](XML/Output/Snap-1.png)

# Result
Thus the XML data is read using Servlets / PHP and the output is displayed.

<div style="page-break-after: always"></div>