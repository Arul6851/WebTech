<!DOCTYPE html>
<html>

<head>
    <title>Event Management</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <h1>Event Management</h1>
    <table>
        <tr>
            <th>Event Name</th>
            <th>Date</th>
            <th>Location</th>
            <th>Organizer</th>
            <th>Attendees</th>
        </tr>
        <?php
        $xml = simplexml_load_file('events.xml');

        if ($xml) {
            foreach ($xml->event as $event) {
                echo "<tr>";
                echo "<td>" . $event->name . "</td>";
                echo "<td>" . $event->date . "</td>";
                echo "<td>" . $event->location . "</td>";
                echo "<td>" . $event->organizer . "</td>";
                echo "<td>" . $event->attendees . "</td>";
                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='5'>Failed to load the XML file.</td></tr>";
        }
        ?>
    </table>
</body>

</html>
