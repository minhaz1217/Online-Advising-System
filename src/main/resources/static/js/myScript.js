
/*
$(document).ready(function() {
    $('#sortTable').DataTable();
});
*/

$("th").click(
    function(){
        sortTable(this);
    }
);
function sortTable(a) {
    console.log(a.cellIndex);
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0, n = a.cellIndex;
    table = document.getElementById("sortTable");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount ++;
        } else {
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }

}